import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FindEmergingPatterns {
	
	static void computeSupRatio(String inputFPfile, int freqPatternsCount, 
							double minRatio, String outputEPfile) {
		
		int EPcount;
		
		// writing frequent patterns with support counts from both classes 
				File File = new File(outputEPfile);
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

		
		
		 
		String cwd = new File(inputFPfile).getAbsolutePath();
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
				for(int i = 0; i < freqPatternsCount;) {
					try {
						while ((sCurrentLine = br.readLine()) != null) {
							itemsInCurrentLine = sCurrentLine.split("[,:]");
							int patSup = 0;
							int patSupOtherClass = 0;
							//int supRatio = 0;
							
							patSup = Integer.parseInt(itemsInCurrentLine[itemsInCurrentLine.length-2]);
							patSupOtherClass = Integer.parseInt(itemsInCurrentLine[itemsInCurrentLine.length-1]);
							
							if(patSupOtherClass == 0 || (patSup/patSupOtherClass) >= minRatio) {
								for(int k = 0; k < itemsInCurrentLine.length-2; k++) {
									if(k == itemsInCurrentLine.length-3) {
										BufferWriter.write(String.valueOf(itemsInCurrentLine[k]) + ":");
									}
									else {
										BufferWriter.write(String.valueOf(itemsInCurrentLine[k]) + ",");	
									}																	
								}	
								BufferWriter.write(String.valueOf(itemsInCurrentLine[itemsInCurrentLine.length-2]) + 
													":"+ (itemsInCurrentLine[itemsInCurrentLine.length-1]));
								BufferWriter.newLine();
							}
							i++;													
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			finally {
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
	}
	
}
