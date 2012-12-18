import java.io.File;


public class DelFilesatEnd {

static void clear() {
		
	File file = new File("transpose.data");
	if(file.exists()){
		file.delete();
	}
	
	File file2 = new File("entropytransposed.data");
	if(file2.exists()){
		file2.delete();
	}
	
	File file3 = new File("numberedtransposed.data");
	if(file3.exists()){
		file3.delete();
	}
	
	File file11 = new File("frequent_patterns_pos_neg.data");
	if(file11.exists()){
		file11.delete();
	}
	File file12 = new File("frequent_patterns_neg_pos.data");
	if(file12.exists()){
		file12.delete();
	}
	
	File file16 = new File("ep_distance_neg.data");
	if(file16.exists()){
		file16.delete();
	}
	File file17 = new File("ep_distance_pos.data");
	if(file17.exists()){
		file17.delete();
	}
	File file26 = new File("top-genedata-transposed.data");
	if(file26.exists()){
		file26.delete();
	}
	/*File file28 = new File("genesets_pos.data");
	if(file28.exists()){
		file28.delete();
	}
	File file29 = new File("genesets_neg.data");
	if(file29.exists()){
		file29.delete();
	}*/
	}
}