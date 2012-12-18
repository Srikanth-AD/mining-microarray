import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class TopGenesNumberedData {
 static void topgenes(String inputTopGenesFile, String opTopGeneDataFile, int topgenes) {
	 
	 // write data from the top 50 genes - based on info gain to file
	// writing frequent patterns with support counts from both classes 
		File File = new File(opTopGeneDataFile);
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
	 
	 
	 
	 
	 
	 
	 
	
	 // read first 100 lines from top-genes-infogain.data file
		BufferedReader br = null;
		String inputFileName = inputTopGenesFile;
		String cwd = new File(inputFileName).getAbsolutePath();

		try { 
			String sCurrentLine;
			String[] itemsInCurrentLine = null;
			br = new BufferedReader(new FileReader(cwd));
			
			  ArrayList<Integer> topGenes = new ArrayList<Integer>();;
			  int k = 0;  // top 50 genes limit
			  while ((sCurrentLine = br.readLine()) != null) {					
				  	itemsInCurrentLine = sCurrentLine.split("=");
					String temp = itemsInCurrentLine[0].substring(1);
					topGenes.add(Integer.parseInt(temp));
					k++;
					if(k == topgenes) break;
				}
			Collections.sort(topGenes);
			
			// read from numberedtransposed.data file and write the top 100 genes - line data to file
			
			BufferedReader bfr = null;
			
			String ipFileName = "numberedtransposed.data";
			String pwd = new File(ipFileName).getAbsolutePath();	
			String kwd = pwd;
			try { 
				String pCurrentLine;
				String[] itemsinLine = null;
				bfr = new BufferedReader(new FileReader(pwd));
				int h = 1;
				for(int i = 0; i < topGenes.size();) {
				while ((pCurrentLine = bfr.readLine()) != null) {
					
					if(h == topGenes.get(i)) {
						itemsinLine = pCurrentLine.split(",");
						for(int j = 0; j < itemsinLine.length; j++) {
							if(j == itemsinLine.length-1) {
								BufferWriter.write(itemsinLine[j] + "");	
							}
							else {
								BufferWriter.write(itemsinLine[j] + ",");
							}
						}
						//System.out.println("h: " + h + " i: " + topGenes.get(i));
						BufferWriter.newLine();
						
						i++;
					}
					h++;
					if(i == topGenes.size()) { break;}
				}
				}
				
				
				// write the class labels to the last line of top gene data file
				BufferedReader bffr = null;
				try { 
					String kCurrentLine;
					String[] kitemsinLine = null;
					bffr = new BufferedReader(new FileReader(kwd));
					while ((kCurrentLine = bffr.readLine()) != null) {
						kitemsinLine = kCurrentLine.split(",");
					}
					for(int y = 0; y < kitemsinLine.length; y++) {
						if(y == kitemsinLine.length-1) {
							BufferWriter.write(kitemsinLine[y] + "");	
						}
						else {
							BufferWriter.write(kitemsinLine[y] + ",");
						}
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (bffr != null)bffr.close();
						
					} catch (IOException ex) {
						ex.printStackTrace();
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
			
			
			
			
 }catch (IOException e) {
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
}
}
