import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;


public class SortedEPAvgDistance {
	static void sort(String inputAvgDistanceEPfile, String outputSortedAvgDistanceEPfile) {
		
	// Write the average sorted distance for emerging patterns to file
	File opSortedAvgDistanceEP = new File(outputSortedAvgDistanceEPfile);
	if(!opSortedAvgDistanceEP.exists()){
		try {
			opSortedAvgDistanceEP.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	FileWriter opSortedAvgDistanceEPfileWriter = null;
	try {
		opSortedAvgDistanceEPfileWriter = new FileWriter(opSortedAvgDistanceEP.getName(),true);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	BufferedWriter opSortedAvgDistanceEPBufferWriter = new BufferedWriter(opSortedAvgDistanceEPfileWriter);	

				
	// Read from emerging patterns average distance file
	String cwd = new File(inputAvgDistanceEPfile).getAbsolutePath();
	String sCurrentLine;
	BufferedReader br = null;	
	try {
		br = new BufferedReader(new FileReader(cwd));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		String[] itemsInCurrentLine = null;
		Hashtable<String, Double> ht = new Hashtable<String, Double>();
		try {
			while ((sCurrentLine = br.readLine()) != null) {				
				itemsInCurrentLine = sCurrentLine.split(":");
				ht.put(itemsInCurrentLine[0],Double.valueOf(itemsInCurrentLine[1]));
			}
			ArrayList<Entry<String, Double>> l = sortEPAvgDistanceFunction.sort(ht);
			Iterator<Entry<String, Double>> i = l.iterator();
		    while (i.hasNext()) {
		    	opSortedAvgDistanceEPBufferWriter.write("" + i.next());
		    	opSortedAvgDistanceEPBufferWriter.newLine();
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
		opSortedAvgDistanceEPBufferWriter.close();
	} catch (IOException e3) {
		// TODO Auto-generated catch block
		e3.printStackTrace();
	}	
	} // method
} // class
