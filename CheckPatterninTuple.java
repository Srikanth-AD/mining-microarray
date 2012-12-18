import java.util.Arrays;


public class CheckPatterninTuple {
	static boolean CheckPattern(int[] pattern, int[] tuple) {

	Arrays.sort(tuple); 
	Arrays.sort(pattern); 
	int tupleIndex = 0;
	int PatternIndex = 0;
	int counter = 0;
	Boolean state = null;
	while (tupleIndex < tuple.length && PatternIndex < pattern.length) {
		
	  if (tuple[tupleIndex] == pattern[PatternIndex]) {
		  //System.out.print(tuple[tupleIndex] + " is in both tuple and pattern.");
		  counter++;
		  tupleIndex++;
		  PatternIndex++;  
	  }
	  else if (tuple[tupleIndex] < pattern[PatternIndex]) {
		  tupleIndex++;
	  }
	  else {
		  PatternIndex++;
	  }	  
	}
	if(counter == (pattern.length)-1) {
		state = true;
	}
	else {
		state = false;	
	}
	return state;
} // CheckPattern method
} // class
