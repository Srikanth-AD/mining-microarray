import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ComputeDistance {
	static void EmergingPatterns(String inputEPfile, String outputEPdistanceFile, 
			String outputInterestingPatterns) {
		
		// create a new file to write the distance between each pair of emerging pattern
		File EPdistanceFile = new File(outputEPdistanceFile);
		if(!EPdistanceFile.exists()){
			try {
				EPdistanceFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileWriter EPdistancefileWriter = null;
		try {
			EPdistancefileWriter = new FileWriter(EPdistanceFile.getName(),true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedWriter EPdistanceBufferWriter = new BufferedWriter(EPdistancefileWriter);

		
		
		// create a new file to write the average distance for each emerging pattern
		File EPavgdistanceFile = new File(outputInterestingPatterns);
		if(!EPavgdistanceFile.exists()){
			try {
				EPavgdistanceFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileWriter EPavgdistancefileWriter = null;
		try {
			EPavgdistancefileWriter = new FileWriter(EPavgdistanceFile.getName(),true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedWriter EPavgdistanceBufferWriter = new BufferedWriter(EPavgdistancefileWriter);
		
		
		
		
		// Read from emerging patterns file - first reader
		String cwd = new File(inputEPfile).getAbsolutePath();
	 	BufferedReader br = null;
	 										
		try { 
			String sCurrentLine;
			String[] itemsInCurrentLine = null;
			
			try {
				br = new BufferedReader(new FileReader(cwd));
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while ((sCurrentLine = br.readLine()) != null) {
					
					itemsInCurrentLine = sCurrentLine.split("[,:]");
					int[] ep1 = new int[itemsInCurrentLine.length-2];
					
					for(int i = 0; i < itemsInCurrentLine.length-2; i++) {
						ep1[i] = Integer.parseInt(itemsInCurrentLine[i]);						
					}
					
					
					
					// print ep1 - for the average distance file
					for(int i = 0; i < itemsInCurrentLine.length-2; i++) {
						if(i == 0) {
							//System.out.print("{" + ep1[i] + ",");
							EPavgdistanceBufferWriter.write(""+ ep1[i] + ",");								
						}
						else if(i == itemsInCurrentLine.length-3) {
							//System.out.print(ep1[i] + "}");
							EPavgdistanceBufferWriter.write(ep1[i] + "");							
						}
						else {
							//System.out.print(ep1[i] + ",");
							EPavgdistanceBufferWriter.write(ep1[i] + ",");														
						}
					}

					
					
					
					// second reader
				 	String cwd2 = new File(inputEPfile).getAbsolutePath();
				 	BufferedReader br2 = null;
				 	String sCurrentLine2;
					try {
						br2 = new BufferedReader(new FileReader(cwd2));
						
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					double averageDistanceofPattern = 0.0;
					
					while ((sCurrentLine2 = br2.readLine()) != null) {
						String[] itemsInCurrentLine2 = null;
						itemsInCurrentLine2 = sCurrentLine2.split("[,:]");
						int[] ep2 = new int[itemsInCurrentLine2.length-2];
						
						
						// print ep1
						for(int i = 0; i < itemsInCurrentLine.length-2; i++) {
							if(i == 0) {
								//System.out.print("{" + ep1[i] + ",");
								//EPdistanceBufferWriter.write("{" + ep1[i] + ",");
								
								
							}
							else if(i == itemsInCurrentLine.length-3) {
								//System.out.print(ep1[i] + "}");
								//EPdistanceBufferWriter.write(ep1[i] + "}");
								
							}
							else {
								//System.out.print(ep1[i] + ",");
								//EPdistanceBufferWriter.write(ep1[i] + ",");
								
								
							}
						}
						
						for(int i = 0; i < itemsInCurrentLine2.length-2; i++) {
							ep2[i] = Integer.parseInt(itemsInCurrentLine2[i]);
							if(i == 0) {
								//System.out.print("{" + ep2[i] + ",");
								//EPdistanceBufferWriter.write("{" + ep2[i] + ",");
							}
							else if(i == itemsInCurrentLine2.length-3) {
								//System.out.print(ep2[i] + "}");
								//EPdistanceBufferWriter.write(ep2[i] + "}");
							}
							else {
								//System.out.print(ep2[i] + ",");
								//EPdistanceBufferWriter.write(ep2[i] + ",");
							}													
						}
						//System.out.print(":" + EPdistanceFunction.distance(ep1, ep2));
						//EPdistanceBufferWriter.write(":" + Double.toString(EPdistanceFunction.distance(ep1, ep2)));
						//System.out.println();
						//EPdistanceBufferWriter.newLine();
						
						//if (br2 != null)br2.close();
						// System.out.print(EPdistanceFunction.distance(ep1, ep2) + ",");
						
						averageDistanceofPattern += EPdistanceFunction.distance(ep1, ep2);
						
					}
					
					
					EPavgdistanceBufferWriter.write(":" + Double.toString(averageDistanceofPattern));
					EPavgdistanceBufferWriter.newLine();
					//EPdistanceBufferWriter.newLine();
					//System.out.println();
					
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally {
			try {
				if (br != null)br.close();
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		try {
			EPdistanceBufferWriter.close();
			EPavgdistanceBufferWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
