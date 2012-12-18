//package ca.pfv.spmf.frequentpatterns.fpgrowth_saveToFile;

import java.util.ArrayList;
import java.util.List;

public class FPNode {
	int itemID = -1;  // item id
	int counter = 1;  // frequency counter
	
	FPNode parent = null; 
	List<FPNode> childs = new ArrayList<FPNode>();
	
	FPNode nodeLink = null; // link to next node with the same item id (for the header table).
	
	/**
	 * constructor
	 */
	FPNode(){
		
	}

	/**
	 * Return the immmediate child of this node having a given ID.
	 * If there is no such child, return null;
	 */
	public FPNode getChildWithID(int id) {
		for(FPNode child : childs){
			if(child.itemID == id){
				return child;
				
			}
		}
		return null;
	}

}
