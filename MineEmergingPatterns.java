import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MineEmergingPatterns {
	static void getEmergingPatterns(String inputNumberedFileName, 
					int freqPatternsCount, String inputFreqPatternFileName, 
						String outputFreqPatternswithSupports) {	
		
		
	// Write frequent patterns with support counts from both classes 
		File File = new File(outputFreqPatternswithSupports);
		if(!File.exists()){
			try {
				File.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(File.getName(),true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedWriter BufferWriter = new BufferedWriter(fileWriter);
		
		
		
		
		
		// Scan the numbered data file once to get the number of rows and columns
		String ccwd = new File(inputNumberedFileName).getAbsolutePath();
		int numRows = 0;
		int numColumns = 0;
		BufferedReader bbr = null;

		try { 
			String sCurrentLine;
			String[] itemsInCurrentLine = null;
			bbr  = new BufferedReader(new FileReader(ccwd));
			
			// Print number of rows and columns in the input file	
			while ((sCurrentLine = bbr.readLine()) != null) {
				numRows++;
				itemsInCurrentLine = sCurrentLine.split(","); //  delimiter
				numColumns = itemsInCurrentLine.length - 1;
			}			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bbr != null)bbr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		

		
			// Store the negative numbered data into a 2d array
			int numberedDataCols = numColumns+1;  // 50 and 40 - testing for negative data
			int numberedDataRows = numRows;
			//System.out.println(numberedDataRows + " " + numberedDataCols);
			String[][] numberedDataMatrix = new String[numberedDataRows][numberedDataCols];
			String numberedDataFilename = inputNumberedFileName; 
			String pwd = new File(numberedDataFilename).getAbsolutePath();
		 	BufferedReader bfr = null;
				try { 
					String sCurrentLine;
					String[] itemsInCurrentLine = null;
					bfr = new BufferedReader(new FileReader(pwd));

					for(int i = 0; i < numberedDataRows;) {
						while ((sCurrentLine = bfr.readLine()) != null) {
							itemsInCurrentLine = sCurrentLine.split(",");
								for(int j = 0; j < numberedDataCols; j++) {									
									numberedDataMatrix[i][j] = itemsInCurrentLine[j];
									//System.out.print(numberedDataMatrix[i][j] + " ");
								}
								i++;
								//System.out.println();
							}
						}
						
				} catch (IOException e) {						
						e.printStackTrace();
					} finally {
						try {
							if (bfr != null)bfr.close();
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}	
				
				
				
	
				// Read the frequent patterns file
				//String freqFilename = "frequent_patterns_pos.data"; 
				String cwd = new File(inputFreqPatternFileName).getAbsolutePath();
			 	BufferedReader br = null;
			 	int[] pattern = null;
			 	
					try { 
						String sCurrentLine;
						String[] itemsInCurrentLine = null;
						br = new BufferedReader(new FileReader(cwd));
						for(int i = 0; i < freqPatternsCount;) {
							while ((sCurrentLine = br.readLine()) != null) {
								itemsInCurrentLine = sCurrentLine.split("[,:]");
								String patrnSup = itemsInCurrentLine[itemsInCurrentLine.length-1];
								pattern = new int[itemsInCurrentLine.length]; 
									// pattern
									//System.out.println("pat # : " + i);									
									for(int j = 0; j < itemsInCurrentLine.length-1; j++) {																
										pattern[j] = Integer.parseInt(itemsInCurrentLine[j]);
										if(j == itemsInCurrentLine.length-2) {
											BufferWriter.write(String.valueOf(pattern[j]) + "");
											//System.out.print(pattern[j] + "");
										} 
										else {
											BufferWriter.write(String.valueOf(pattern[j]) + ",");
											//System.out.print(pattern[j] + ",");	
										}										
									}
									
									BufferWriter.write(":" + String.valueOf(patrnSup));
									//System.out.print(  ":" + patrnSup);
									
									int PatternsupportInOtherClass = 0;
									for(int l = 0; l < numberedDataMatrix.length; l++) {
										// tuple
										int[] tuple = new int[numberedDataMatrix[l].length];
										for(int k = 0; k < numberedDataMatrix[l].length; k++) {
										//	System.out.print(numberedDataMatrix[0][k] + ",");
											tuple[k] = Integer.parseInt(numberedDataMatrix[l][k]);
										}
										//System.out.println("tuple: " + l);
										if(CheckPatterninTuple.CheckPattern(pattern, tuple) == true) {
											PatternsupportInOtherClass++;
											//System.out.print("t");
										}
									}		
									BufferWriter.write(":" + String.valueOf(PatternsupportInOtherClass));
									//System.out.print(":" + PatternsupportInOtherClass);
									BufferWriter.newLine();
									//System.out.println();
									i++;
								}
							}
							
					} catch (IOException e) {						
							e.printStackTrace();
						} finally {
							try {
								if (br != null)br.close();
							} catch (IOException ex) {
								ex.printStackTrace();
							}
						}		
								
					try {
						BufferWriter.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					
		
	} // getEmergingPatterns method
	} //class
	
