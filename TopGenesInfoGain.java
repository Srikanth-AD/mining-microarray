import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class TopGenesInfoGain {
	static void topgenes(String inputGeneMaxGainFileName, String outputTopGenesFile) {
		
		// Write the average sorted distance for emerging patterns to file
		File opTopGenesFile = new File(outputTopGenesFile);
		if(!opTopGenesFile.exists()){
			try {
				opTopGenesFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileWriter opTopGenesfileWriter = null;
		try {
			opTopGenesfileWriter = new FileWriter(opTopGenesFile.getName(),true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedWriter opTopGenesBufferWriter = new BufferedWriter(opTopGenesfileWriter);	

		
		
		
		
	// read from the gene-maxgain-splitvalue file
	
	String cwd = new File(inputGeneMaxGainFileName).getAbsolutePath();
	BufferedReader br = null;
	
	try { 
		String sCurrentLine;
		String[] itemsInCurrentLine = null;
		br = new BufferedReader(new FileReader(cwd));
		
		Hashtable<String, Double> t = new Hashtable<String,Double>();

			
		while ((sCurrentLine = br.readLine()) != null) {
			itemsInCurrentLine = sCurrentLine.split(",");
			for(int i = 0; i < itemsInCurrentLine.length; i++) {
				t.put(itemsInCurrentLine[0], Double.valueOf(itemsInCurrentLine[1]));
			}
		}
		
		ArrayList<Entry<String, Double>> l = sortEPAvgDistanceFunction.sort(t);
		Iterator<Entry<String, Double>> i = l.iterator();
	    while (i.hasNext()) {
	    	opTopGenesBufferWriter.write("" + i.next());
	    	opTopGenesBufferWriter.newLine();
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
		opTopGenesBufferWriter.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
