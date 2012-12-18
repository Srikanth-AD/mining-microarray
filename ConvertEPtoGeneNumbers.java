import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class ConvertEPtoGeneNumbers {
	static void convert(String inputSortedEPDistanceFileName, String outputEPDistanceGenNumsFilename) {
				
		File File = new File(outputEPDistanceGenNumsFilename);
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
		
		
		String cwd = new File(inputSortedEPDistanceFileName).getAbsolutePath();		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(cwd));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String currentLine;
			String itemsinline[] = null;
			
			try {
				while ((currentLine = br.readLine()) != null) {
					itemsinline = currentLine.split("[,=]");
					if(itemsinline[1].equals("")) {
						if(Integer.parseInt(itemsinline[0]) % 2 == 0) {
							//System.out.print(((Integer.parseInt(itemsinline[i])) / 2) + "+,");
							BufferWriter.write(((Integer.parseInt(itemsinline[0])) / 2) + "+");
						}
						else if(Integer.parseInt(itemsinline[0]) % 2 == 1) {
							//System.out.print(((Integer.parseInt(itemsinline[i]) + 1) / 2) + "-," );
							BufferWriter.write(((Integer.parseInt(itemsinline[0]) + 1) / 2) + "-" );
						}						
					}
					else {
						for(int i = 0; i < itemsinline.length-1; i++) {
							if(i == itemsinline.length-2) {
								if(Integer.parseInt(itemsinline[i]) % 2 == 0) {
									//System.out.print(((Integer.parseInt(itemsinline[i])) / 2) + "+,");
									BufferWriter.write(((Integer.parseInt(itemsinline[i])) / 2) + "+");
								}
								else if(Integer.parseInt(itemsinline[i]) % 2 == 1) {
									//System.out.print(((Integer.parseInt(itemsinline[i]) + 1) / 2) + "-," );
									BufferWriter.write(((Integer.parseInt(itemsinline[i]) + 1) / 2) + "-" );
								}
							}
							else {
							if(Integer.parseInt(itemsinline[i]) % 2 == 0) {
								//System.out.print(((Integer.parseInt(itemsinline[i])) / 2) + "+,");
								BufferWriter.write(((Integer.parseInt(itemsinline[i])) / 2) + "+,");
							}
							else if(Integer.parseInt(itemsinline[i]) % 2 == 1) {
								//System.out.print(((Integer.parseInt(itemsinline[i]) + 1) / 2) + "-," );
								BufferWriter.write(((Integer.parseInt(itemsinline[i]) + 1) / 2) + "-," );
							}
						}	
						}
					}
					
					//System.out.print("=" + itemsinline[itemsinline.length-1]);
					//System.out.println();
					BufferWriter.write("=" + itemsinline[itemsinline.length-1]);
					BufferWriter.newLine();
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
			BufferWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
