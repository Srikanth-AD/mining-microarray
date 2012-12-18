import java.util.Arrays;


public class EPdistanceFunction {
	static double distance(int[] ep1, int[] ep2) {
		Arrays.sort(ep1); 
		Arrays.sort(ep2); 
		int ep1Index = 0;
		int ep2Index = 0;
		double counter = 0;
		while (ep1Index < ep1.length && ep2Index < ep2.length) {
			
		  if (ep1[ep1Index] == ep2[ep2Index]) {
			  //System.out.print(tuple[tupleIndex] + " is in both tuple and pattern.");
			  counter++;
			  ep1Index++;
			  ep2Index++;  
		  }
		  else if (ep1[ep1Index] < ep2[ep2Index]) {
			  ep1Index++;
		  }
		  else {
			  ep2Index++;
		  }	  
		}
		double ep1Len = ep1.length;
		double ep2Len = ep2.length;
		double distance = (1 - (counter)/(ep1Len + ep2Len - counter));
		
		return distance;
	} //  method
}
