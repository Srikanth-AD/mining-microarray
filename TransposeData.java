import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class TransposeData {
 static String[][] transpose(String filename, int rows, int columns, String transposeOutputFilename) {
	 /* SECOND FILE SCAN - to save the data in file to a 2d-array
		 * */
		String cwd2 = new File(filename).getAbsolutePath();
		String[][] dataMatrix = new String[rows][columns];
		String[][] transpose2darray = new String[columns][rows];
		BufferedReader br = null;
		try { 
			String sCurrentLine;
			String[] itemsInCurrentLine2 = null;
			br = new BufferedReader(new FileReader(cwd2));
			
			for(int i = 0; i < rows;) {
			while ((sCurrentLine = br.readLine()) != null) {
				
					for(int j = 0; j < columns; j++) {									
						itemsInCurrentLine2 = sCurrentLine.split(",");
						dataMatrix[i][j] = itemsInCurrentLine2[j];
						//System.out.print("\t" + my2darray[i][j] + " [" + i + ";" + j + "] ");
					}
					i++;
					//System.out.println();
				}
			}
			
			/* Transpose the contents of the 2d array and write it to a file 
			 * */ 
			
			String data;
			File transposeFile = new File(transposeOutputFilename);
			if(!transposeFile.exists()){
				transposeFile.createNewFile();
			}
 		FileWriter transposefileWriter = new FileWriter(transposeFile.getName(),true);
	    BufferedWriter transposebufferWriter = new BufferedWriter(transposefileWriter);
			
	    	for (int i = 0; i < dataMatrix[0].length; i++) {
             for (int j = 0; j < dataMatrix.length; j++) {
             	transpose2darray[i][j] = dataMatrix[j][i];
             	if(j== dataMatrix.length-1) {
             		data = transpose2darray[i][j];
             		transposebufferWriter.write(data);
             	}
             	else {
                 	data = transpose2darray[i][j] + ",";
                     //System.out.print(transpose2darray[i][j] + ",");
                 	transposebufferWriter.write(data);
             	}
             }
             //System.out.println();
             transposebufferWriter.newLine();
			}
			transposebufferWriter.close();
	    	
		} catch (IOException e) {						
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return transpose2darray;
 }
}
