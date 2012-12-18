import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class EmergingToGeneSet {
	 static void conversion(String inputEmergingFilename, String outputgenesetFilename) {

		 // create a new file to write the frequent gene sets correlated to class label
		File GeneSetFile = new File(outputgenesetFilename);
		if(!GeneSetFile.exists()){
			try {
				GeneSetFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileWriter GeneSetfileWriter = null;
		try {
			GeneSetfileWriter = new FileWriter(GeneSetFile.getName(),true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedWriter GeneSetBufferWriter = new BufferedWriter(GeneSetfileWriter);

		
	 
	 
	 // read numbered data from frequent patterns file and convert it to gene set data
	String cwd = new File(inputEmergingFilename).getAbsolutePath();
 	 BufferedReader br = null;
		try { 
			String sCurrentLine;
			String[] itemsInCurrentLine = null;
			br = new BufferedReader(new FileReader(cwd));
			while ((sCurrentLine = br.readLine()) != null) { 
				itemsInCurrentLine = sCurrentLine.split("[,:]");
				for(int i = 0; i < itemsInCurrentLine.length-2; i++) {
					if(i == itemsInCurrentLine.length-3) {
						int currentItem = Integer.parseInt(itemsInCurrentLine[i]);
						if (currentItem % 2 == 0)
						  {
							GeneSetBufferWriter.write((currentItem/2) + "");
						  }
						  else 
						  {
							  GeneSetBufferWriter.write((currentItem+1)/2 + "");
						  }	
					}
					else {
						int currentItem = Integer.parseInt(itemsInCurrentLine[i]);
						if (currentItem % 2 == 0)
						  {
							GeneSetBufferWriter.write((currentItem/2) + ",");
						  }
						  else 
						  {
							  GeneSetBufferWriter.write((currentItem+1)/2 + ",");
						  }	
					}
										
					}				
					//GeneSetBufferWriter.write(":" + itemsInCurrentLine[itemsInCurrentLine.length-1]);
					GeneSetBufferWriter.newLine();
				}
			
			
			try {
				GeneSetBufferWriter.close();
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
 } // conversion
} // class
