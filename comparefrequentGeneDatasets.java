import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class comparefrequentGeneDatasets {
	
	
	static void getSingleItemsets(String inputFreqGenesetFile) {
		System.out.println("printing single itemsets");
		// for now - print the single itemsets to screen
		
		String cwd = new File(inputFreqGenesetFile).getAbsolutePath();
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
						if(itemsInCurrentLine.length == 2) {
							System.out.println(itemsInCurrentLine[0]);
						}
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
	} //getSingleItemsets method
} // class
			
