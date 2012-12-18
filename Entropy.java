import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


public class Entropy {
	static void columnEntropy(String transpose2darray[][], int numRows, int numColumns) {
		System.out.println("Performing entropy discretization ... "); // visual clue for end user
		
		// create a new file to write the max gain and split values
		String data;
		File splitValsFile = new File("gene-maxgain-splitvalue.data");
		if(!splitValsFile.exists()){
			try {
				splitValsFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileWriter splitValsfileWriter = null;
		try {
			splitValsfileWriter = new FileWriter(splitValsFile.getName(),true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedWriter splitValsbufferWriter = new BufferedWriter(splitValsfileWriter);
		
		
		
		// create a new file to write the discretized data based on entropy 
				String discrData = null;
				File discrFile = new File("entropytransposed.data");
				if(!discrFile.exists()){
					try {
						discrFile.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				FileWriter discrFileWriter = null;
				try {
					discrFileWriter = new FileWriter(discrFile.getName(),true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				BufferedWriter discrBufferWriter = new BufferedWriter(discrFileWriter);
		
		
				
				// create a new file to write the numbered data 
				String numberedData = null;
				File numberedFile = new File("numberedtransposed.data");
				if(!numberedFile.exists()){
					try {
						numberedFile.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				FileWriter numberedFileWriter = null;
				try {
					numberedFileWriter = new FileWriter(numberedFile.getName(),true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				BufferedWriter numberedBufferWriter = new BufferedWriter(numberedFileWriter);
				
		
		// ENTROPY of data set
		int i = transpose2darray.length-1;		
		double posCount = 0;
		double negCount = 0;	
		for(int j = 0; j < numRows; j++) {
			if(transpose2darray[i][j].equals("positive")) {
				posCount++;
			}
			else if(transpose2darray[i][j].equals("negative")) {
				negCount++;
			}
		}
		
		// computing entropy of the data set
		double entropy_set = (-(posCount/numRows) *
				(Math.log10(posCount/numRows)/Math.log10(2)))
				- ((negCount/numRows) *
					(Math.log10(negCount/numRows)/Math.log10(2))); 
		//System.out.println("Entropy of set: " + entropy_set); 
		
		
		//========== ENTROPY COMPUTATION FOR EACH COLUMN
		
		for(int k = 0; k < numColumns-1; k++) {
			Map<Double, String> attrClassPairs = new HashMap<Double, String>();
			for(int l = 0; l < transpose2darray[k].length; l++) {		
				attrClassPairs.put(Double.parseDouble(transpose2darray[k][l]), transpose2darray[numColumns-1][l]);			
			}
			Map<Double, String> sortedAttrClassPairsMap = new TreeMap<Double, String>(attrClassPairs);
			
			// print the sorted treemap
			//	for (Map.Entry<Double, String> entry : sortedAttrClassPairsMap.entrySet()) {
				//System.out.println("Key: " + entry.getKey() + ". Value: " + entry.getValue());
			//}
			
			Iterator<Map.Entry<Double, String>> entries = sortedAttrClassPairsMap.entrySet().iterator();
			Map.Entry<Double, String> entry1 = entries.next();
			
			Map<Double, Double> gainVals = new HashMap<Double, Double>();
			
			
			for(int n = 0; n < sortedAttrClassPairsMap.size()-1;n++) {
				
				
				Double key1 = entry1.getKey();
				entry1 = entries.next();
				for(int m = 0; m < 1;m++) {
					Double key2 = entry1.getKey();
					Double splitVal = (key1+key2)/2;
				//	System.out.println("split value for " + key1 + ", " + key2 + " : " + splitVal);
					
					/* get entries from the unsorted map and if entry key is less than split value, put it in treemapbin1 else
					*	put it in treemapbin2
					*/
					Map<Double, String> bin1 = new HashMap<Double, String>();
					Map<Double, String> bin2 = new HashMap<Double, String>();
					
					for (Map.Entry<Double, String> entry : attrClassPairs.entrySet()) {
						if( entry.getKey() <= splitVal) {
							bin1.put(entry.getKey(), entry.getValue());
						}
						else if( entry.getKey() > splitVal) {
							bin2.put(entry.getKey(), entry.getValue());
						}
					}
					
					// ========= BIN 1
					double bin1PosCount = 0;
					double bin1NegCount = 0;
					for (Map.Entry<Double, String> bin1entry : bin1.entrySet()) {
						if( bin1entry.getValue().equals("positive")) {
							bin1PosCount++;
						}
						else if(bin1entry.getValue().equals("negative")) {
							bin1NegCount++;
						}
					}	
					
					// entropy of bin1
					
					double entropy_bin1_chunk1 = (-(bin1PosCount/(double)bin1.size()) *
							(Math.log10(bin1PosCount/(double)bin1.size())/Math.log10(2)));
					double entropy_bin1_chunk2 =  ((bin1NegCount/(double)bin1.size()) *
								(Math.log10(bin1NegCount/(double)bin1.size())/Math.log10(2))); 

					boolean bin1_1 = Double.isInfinite((Math.log10(bin1PosCount/(double)bin1.size())/Math.log10(2)));
					boolean bin1_2 = Double.isInfinite((Math.log10(bin1NegCount/(double)bin1.size())/Math.log10(2)));
					if(bin1_1) {
						entropy_bin1_chunk1 = 0;
					}
					if(bin1_2) {
						entropy_bin1_chunk2 = 0;
					}
					double entropy_bin1 = entropy_bin1_chunk1 - entropy_bin1_chunk2;							
					//System.out.println("entropy of bin1:" + entropy_bin1); 
					
					// ====== BIN 2
					double bin2PosCount = 0;
					double bin2NegCount = 0;
					for (Map.Entry<Double, String> bin2entry : bin2.entrySet()) {
						if( bin2entry.getValue().equals("positive")) {
							bin2PosCount++;
						}
						else if(bin2entry.getValue().equals("negative")) {
							bin2NegCount++;
						}
					}
								
					
					// entropy of bin2
					
					double entropy_bin2_chunk1 = (-(bin2PosCount/(double)bin2.size()) *
							(Math.log10(bin2PosCount/(double)bin2.size())/Math.log10(2)));
					double entropy_bin2_chunk2 =  ((bin2NegCount/(double)bin2.size()) *
								(Math.log10(bin2NegCount/(double)bin2.size())/Math.log10(2))); 

					boolean bin2_1 = Double.isInfinite((Math.log10(bin2PosCount/(double)bin2.size())/Math.log10(2)));
					boolean bin2_2 = Double.isInfinite((Math.log10(bin2NegCount/(double)bin2.size())/Math.log10(2)));
					if(bin2_1) {
						entropy_bin2_chunk1 = 0;
					}
					if(bin2_2) {
						entropy_bin2_chunk2 = 0;
					}
					double entropy_bin2 = entropy_bin2_chunk1 - entropy_bin2_chunk2;		
					//System.out.println("entropy of bin2:" + entropy_bin2); 
						
					double IS = (((double)bin1.size()/(double)attrClassPairs.size()) * (entropy_bin1))
								+ (((double)bin2.size()/(double)attrClassPairs.size()) * (entropy_bin2)); 
										
					double gainofSplit = entropy_set - IS;
					//System.out.println("gain for split value: " + splitVal + " is " + gainofSplit);
					gainVals.put(gainofSplit, splitVal);
				
				}
	
			}
			// sort the gain values
			Map<Double, Double> sortedGainSplitVals = new TreeMap<Double, Double>(gainVals);

			// print the sorted gain values - treemap
						int h = 0;
						for (Map.Entry<Double, Double> sortedGainEntry : sortedGainSplitVals.entrySet()) {
							h++;
							if(h == sortedGainSplitVals.size()) {
								// geneNum is the gene number - for writing to split values,maxgain file
								int geneNum = k+1;
								
								data = "g" + geneNum + "," + sortedGainEntry.getKey() + "," + sortedGainEntry.getValue();
			             		try {
									splitValsbufferWriter.write(data);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			             		try {
									splitValsbufferWriter.newLine();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			             		
							//	System.out.println("gene:" + geneNum + " Max Gain: " + sortedGainEntry.getKey() + " Split Value: " + sortedGainEntry.getValue());
			             		
			             		
			             		
			             		
			             		int tempCounter = 0;
			             		// Go through values in each column  of transposed data and write discretized data to file
			             		for(int g = 0; g < numRows; g++) {
			             			double transposeAttrVal = Double.parseDouble(transpose2darray[k][g]);
			             			tempCounter++;
									if( transposeAttrVal <= sortedGainEntry.getValue() && tempCounter < numRows) {
										//System.out.println(transposeAttrVal + " is less than " + sortedGainEntry.getValue() );
										discrData = "a,";
										numberedData = Integer.toString((2*(k+1))-1)+",";
									}
									else if( transposeAttrVal <= sortedGainEntry.getValue() && tempCounter == numRows) {
										discrData = "a";
										numberedData = Integer.toString((2*(k+1))-1);
									}
									else if( transposeAttrVal > sortedGainEntry.getValue() && tempCounter < numRows) {
										//System.out.println(transposeAttrVal + " is greater than " + sortedGainEntry.getValue() );
										discrData = "b,";
										numberedData = Integer.toString(2*(k+1)) + ",";
									}
									else if( transposeAttrVal > sortedGainEntry.getValue() && tempCounter == numRows) {
										discrData = "b";
										numberedData = Integer.toString(2*(k+1));
									}
									try {
										discrBufferWriter.write(discrData);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									try {
										numberedBufferWriter.write(numberedData);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
				             		
								}
			             		
							}
						}
						try {
									
							discrBufferWriter.newLine();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							numberedBufferWriter.newLine();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	             		
	
			
		} // end of for loop
 
		try {
			splitValsbufferWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		try {
			// write the class labels in the last line of the file
			for(int x = 0; x < numRows; x++) {
				if(x == (numRows - 1)) {
					discrData = transpose2darray[numColumns-1][x];
				}
				else {
					discrData = transpose2darray[numColumns-1][x] + ",";
				}
				discrBufferWriter.write(discrData);
			}
			// close the file once all the data has been written to file
			discrBufferWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		try {
			// write the class labels in the last line of the file
						for(int x = 0; x < numRows; x++) {
							if(x == (numRows - 1)) {
								numberedData = transpose2darray[numColumns-1][x];
							}
							else {
								numberedData = transpose2darray[numColumns-1][x] + ",";
							}
							numberedBufferWriter.write(numberedData);
						}
			numberedBufferWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			
	} // columnEntropy Method

}// class
