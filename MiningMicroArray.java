

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MiningMicroArray {
	public static void main(String[] args) {
		
		// Program Execution - start time
		long startTime = System.nanoTime();
				
		// Clear contents of existing files
		ClearFiles.clear();
		
		//System.out.println("Enter a file name and task number - 1 or 3: ");
		//System.out.println("Ex: java MiningMicroArray cc.data 1");

		String inputFileName = null;
		
		// Accept command line arguments for tasks
		if(args.length != 2)
	    {
	        System.out.println("Proper Usage is: java program filename tasknumber");
	        System.exit(0);
	    }
		else if(args.length == 2) {
			inputFileName = args[0];
			if(Integer.parseInt(args[1]) == 1) {
				System.out.println("Task 1: Find minimal genesets correlated with the class");				
				// methods for task 1 go here
			}
			else if(Integer.parseInt(args[1]) == 3) {
				System.out.println("Task 3: Neighbourhood based interestingness analysis of emerging patterns");				
				// methods for task 3 go here
			}	
		}

		/* FIRST FILE SCAN - 
		 * 	to get the number of rows and columns in given data set
		 */
				
		// Accept a file name as input from command line arguments
		BufferedReader br = null;
		//Scanner scanFileName = new Scanner(System.in);
		//System.out.print("Enter a file name: ");
		
		String cwd = new File(inputFileName).getAbsolutePath();
		
		int numRows = 0;
		int numAttributes = 0;
		int numColumns = 0;
		
		try { 
			String sCurrentLine;
			String[] itemsInCurrentLine = null;
			br = new BufferedReader(new FileReader(cwd));
			
			// Print number of rows and columns in the input file	
			while ((sCurrentLine = br.readLine()) != null) {
				numRows++;
				itemsInCurrentLine = sCurrentLine.split(",");
				numAttributes = itemsInCurrentLine.length - 1;
			}
			numColumns = numAttributes + 1;
			System.out.println("# Rows: " + numRows);
			//System.out.println("# Attributes: " + numAttributes); // doesn't include class
			System.out.println("# Columns: " + numColumns); // includes class column			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		// END OF - FIRST FILE SCAN	
		
		// Transpose the given data, save it to a file and run the entropy method
		Entropy.columnEntropy(TransposeData.transpose(inputFileName, numRows, numColumns, "transpose.data"), numRows, numColumns);

		// Transpose the contents of entropy.data file to get back to original data's form
		TransposeData.transpose("entropytransposed.data", numColumns, numRows, "entropy.data");
		
		// Write the sorted gene numbers based on info gain  - to file
		TopGenesInfoGain.topgenes("gene-maxgain-splitvalue.data", "top-genes-infogain.data");
		
		int topgenes = 50;
		// Print the sorted - top 50 genes
		TopGenesNumberedData.topgenes("top-genes-infogain.data", "top-genedata-transposed.data", topgenes);
		
		// Transpose the contents of numbered.data file to get back to original data's form
		TransposeData.transpose("numberedtransposed.data", numColumns, numRows, "numbered.data");
		
		
		// top genes - based on info gain
				
		
		// Transpose the contents of top 50 genes numbered data back to original data's form - 
		// to be fed to splitNumeredData program and subsequently to FP growth
		TransposeData.transpose("top-genedata-transposed.data", topgenes+1, numRows, "top-gene-numbered.data");
		
		/*  === For the first 50 genes ====
		* Split the numbered data file into two files - 
		* one with positive tuples and the other with negative tuples
		
		int geneLimit = 50;
		splitNumeredData.splitNumberedDataFile("numbered.data", numRows, numColumns,geneLimit);
		*/
		
		/* ========= for the top 50 genes based on info gain =====
		*/
		System.out.println("=> Enter the number of top genes to used for find frequent patterns");
		System.out.println("=> Example: 35");
		Scanner scanGeneLimit = new Scanner(System.in);
		int geneLimit = scanGeneLimit.nextInt();
		splitNumeredData.splitNumberedDataFile("top-gene-numbered.data", topgenes+1, numRows,geneLimit);
		
		
		System.out.println("=> Enter minimum support to find frequent patterns");
		System.out.println("=> Example: 0.6");
		Scanner scanMinsup = new Scanner(System.in);
		double minsup = scanMinsup.nextDouble();
		
		
		String inputNumberedFileNeg = "negativetuplesnumbered.data";
		String inputNeg = new File(inputNumberedFileNeg).getAbsolutePath();
		String outputFPNeg = "frequent_patterns_neg.data";
		String inputNumberedFilePos = "positivetuplesnumbered.data";
		String inputPos = new File(inputNumberedFilePos).getAbsolutePath();
		String outputFPPos = "frequent_patterns_pos.data";  // the path for saving the frequent itemsets found		
		String inputFPfile_neg_pos = "frequent_patterns_neg_pos.data";
		String outputEPfile_neg_pos = "emerging_patterns_neg_pos.data";
		String inputFPfile_pos_neg = "frequent_patterns_pos_neg.data";
		String outputEPfile_pos_neg = "emerging_patterns_pos_neg.data";
			
			
		    
		// Run the FP Growth algorithm - for tuples with negative class label
		System.out.println("Executing FP Growth for tuples with negative class label ..."); // visual 
		//System.out.println("Note: only the first 50 genes are being considered for FP-Growth");
		// Applying the FP GROWTH algorithm - for tuples with negative class label
		AlgoFPGrowth algoNeg = new AlgoFPGrowth();
		try {
			algoNeg.runAlgorithm(inputNeg, outputFPNeg, minsup);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		algoNeg.printStats();	
		int freqPatternCountNeg = algoNeg.frequentPatternsCount();

		// Get Support from both the classes for each Frequent pattern
		 MineEmergingPatterns.getEmergingPatterns
				(inputPos, freqPatternCountNeg, 
						outputFPNeg, inputFPfile_neg_pos);		
			
			
			System.out.println("=> Enter minimum ratio to find emerging patterns");
			System.out.println("=> Example: 7");
			Scanner scanMinRatio = new Scanner(System.in);
			int minRatio = scanMinRatio.nextInt();
			
			FindEmergingPatterns.computeSupRatio(inputFPfile_neg_pos, 
						freqPatternCountNeg, minRatio, outputEPfile_neg_pos);			
			
			// If task 1 is chosen by the user, execute the following methods
			if(Integer.parseInt(args[1]) == 1) {
			    // Convert emerging patterns to gene sets - negative class
				EmergingToGeneSet.conversion("emerging_patterns_neg_pos.data", "genesets_neg.data");
			} // end of - If task 1
			
			// Execute the FP Growth algorithm - for tuples with positive class label
			System.out.println("Executing FP Growth for tuples with positive class label ..."); // visual clue for end user
	 
			// Applying the FP GROWTH algorithm
			AlgoFPGrowth algoPos = new AlgoFPGrowth();
			try {
				algoPos.runAlgorithm(inputPos, outputFPPos, minsup);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			algoPos.printStats();		
			int freqPatternCountPos = algoPos.frequentPatternsCount();	
			
			
			
			// Get Support from both the classes for each Frequent Pattern
			MineEmergingPatterns.getEmergingPatterns
					(inputNeg, freqPatternCountPos, outputFPPos, inputFPfile_pos_neg);

			FindEmergingPatterns.computeSupRatio(inputFPfile_pos_neg, 
					freqPatternCountPos, minRatio, outputEPfile_pos_neg);
			
			// If task 1 is chosen by the user, execute the following methods
			if(Integer.parseInt(args[1]) == 1) {
				// Convert emerging patterns to gene sets - positive class
				EmergingToGeneSet.conversion("emerging_patterns_pos_neg.data", "genesets_pos.data");
				
				// Generate gene sets which have many emerging patterns using FP growth - negative
				System.out.println("Generating gene sets which have many emerging patterns - negative");
				
				System.out.println("Enter the minimum threshold for generating genesets from emerging patterns");
				System.out.println("Example: 0.005");
				Scanner scangenesetMinSup = new Scanner(System.in);
				double genesetMinSup = scangenesetMinSup.nextDouble();
				String inputEP_Neg = "genesets_neg.data";
				String outputGS_Neg = "corr_genesets_neg.data";
				// Applying the FP GROWTH algorithm
				AlgoFPGrowth algoGenesetsNeg = new AlgoFPGrowth();
				try {
					algoGenesetsNeg.runAlgorithm(inputEP_Neg, outputGS_Neg, genesetMinSup);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				algoGenesetsNeg.printStats();	
				
				
				// Generate gene sets which have many emerging patterns using FP growth - positive
				System.out.println("Generating gene sets which have many emerging patterns - positive");
				String inputEP_Pos = "genesets_pos.data";
				String outputGS_Pos = "corr_genesets_pos.data";
				// Apply the FP GROWTH algorithm
				AlgoFPGrowth algoGenesetsPos = new AlgoFPGrowth();
				try {
					algoGenesetsPos.runAlgorithm(inputEP_Pos, outputGS_Pos, genesetMinSup);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				algoGenesetsPos.printStats();	
				
				SortedEPAvgDistance.sort("corr_genesets_pos.data", "sorted_corr_genesets_pos.data");
				SortedEPAvgDistance.sort("corr_genesets_neg.data", "sorted_corr_genesets_neg.data");
				
				
				System.out.println("Task 1 results have been written to the directory");
				System.out.println("=> Refer to: sorted_corr_genesets_pos.data and sorted_corr_genesets_neg.data");
				scangenesetMinSup.close();
			} // end of If  - task 1
			
			// If task 3 is chosen by the user, execute the following methods
			if(Integer.parseInt(args[1]) == 3) {
				// Distance between Emerging Patterns - negative
				System.out.println("Computing distance between emerging pattern pairs - negative ...");
				ComputeDistance.EmergingPatterns("emerging_patterns_neg_pos.data", 
						"ep_distance_neg.data", "ep_avg_distance_neg.data");
				
				// Distance between Emerging Patterns - positive
				System.out.println("Computing distance between emerging pattern pairs - positive ...");
				ComputeDistance.EmergingPatterns("emerging_patterns_pos_neg.data", 
									"ep_distance_pos.data", "ep_avg_distance_pos.data");		
		
				// Sort emerging patterns with average distances - negative
				System.out.println("Sorting emerging patterns with average distances - negative");
				SortedEPAvgDistance.sort("ep_avg_distance_neg.data", "sorted_ep_avg_distance_neg.data");
				
				// Sort emerging patterns with average distances - positive
				System.out.println("Sorting emerging patterns with average distances - positive");
				SortedEPAvgDistance.sort("ep_avg_distance_pos.data", "sorted_ep_avg_distance_pos.data");	
				
				System.out.println("Task 3 results have been written to the directory");
				System.out.println("=> Refer to: sorted_ep_avg_distance_pos.data " +
						"and sorted_ep_avg_distance_neg.data");
				
				// Convert the sorted EP_avg_distance files to gene numbers 
				//	with +,- signs for easier readability
				ConvertEPtoGeneNumbers.convert("sorted_ep_avg_distance_neg.data", 
						"sorted_ep_distance gene_nums_neg.data");
				ConvertEPtoGeneNumbers.convert("sorted_ep_avg_distance_pos.data", 
						"sorted_ep_distance gene_nums_pos.data");
				
			} // end of - IF - task 3
			
			
			scanMinRatio.close(); 
			scanMinsup.close();
			scanGeneLimit.close();
			
			
			
			
			// Clear temporary files
			DelFilesatEnd.clear();
			
			// Total program execution time
			System.out.println(ExecutionTime.executionTime(startTime));
			
	} //main

} //class
