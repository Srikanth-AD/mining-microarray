import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;


public class sortEPAvgDistanceFunction {
	static ArrayList<Map.Entry<String, Double>> sort(Hashtable<String, Double> t){
	
   //Transfer as List and sort it
   ArrayList<Map.Entry<String, Double>> l = new ArrayList<Entry<String, Double>>(t.entrySet());
   Collections.sort(l, new Comparator<Map.Entry<String, Double>>(){
     public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {	
        return o2.getValue().compareTo(o1.getValue());	            
    }});   
   return l;
	}
}
