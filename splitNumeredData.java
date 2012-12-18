import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class splitNumeredData {
	static void splitNumberedDataFile(String inputfilename, int numRows, int numColumns, int geneLimit) {

		
		// create a new file to write the numbered data tuples with positive class
				String positiveData = null;
				File positiveTuplesFile = new File("positivetuplesnumbered.data");
				if(!positiveTuplesFile.exists()){
					try {
						positiveTuplesFile.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				FileWriter positiveTuplesfileWriter = null;
				try {
					positiveTuplesfileWriter = new FileWriter(positiveTuplesFile.getName(),true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				BufferedWriter positiveTuplesBufferWriter = new BufferedWriter(positiveTuplesfileWriter);
		
		
				
				// create a new file to write the numbered data tuples with negative class
				String negativeData = null;
				File negativeTuplesFile = new File("negativetuplesnumbered.data");
				if(!negativeTuplesFile.exists()){
					try {
						negativeTuplesFile.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				FileWriter negativeTuplesfileWriter = null;
				try {
					negativeTuplesfileWriter = new FileWriter(negativeTuplesFile.getName(),true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				BufferedWriter negativeTuplesBufferWriter = new BufferedWriter(negativeTuplesfileWriter);			
				
				
				
		// take the input filename argument and write data to the appropriate file
		String cwd = new File(inputfilename).getAbsolutePath();
		BufferedReader br = null;
		
		try { 
			String sCurrentLine;
			String[] itemsInCurrentLine = null;
			br = new BufferedReader(new FileReader(cwd));
			
			while ((sCurrentLine = br.readLine()) != null) {
				itemsInCurrentLine = sCurrentLine.split(",");
				if (itemsInCurrentLine[itemsInCurrentLine.length-1].equals("positive")) {
					// gene limit is imposed as FP growth algorithm 
					// takes relatively long time when executed for all the 2000 attributes
					for(int i = 0; i < geneLimit; i++) {
						if(i == geneLimit-1) {
							positiveData = itemsInCurrentLine[i] + "";
						}
						else {
							positiveData = itemsInCurrentLine[i] + ",";
						}
						try {
							positiveTuplesBufferWriter.write(positiveData);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
						try {
							positiveTuplesBufferWriter.newLine();
						} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if (itemsInCurrentLine[itemsInCurrentLine.length-1].equals("negative")) {
					for(int i = 0; i < geneLimit; i++) {
						if(i == geneLimit-1) {
							negativeData = itemsInCurrentLine[i] + "";
						}
						else {
							negativeData = itemsInCurrentLine[i] + ",";
						}
						try {
							negativeTuplesBufferWriter.write(negativeData);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
						try {
							negativeTuplesBufferWriter.newLine();
						} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}
			
			try {
				positiveTuplesBufferWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			try {
				negativeTuplesBufferWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	}
}
