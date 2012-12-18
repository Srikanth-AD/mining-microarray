import java.io.File;

// clear contents of existing files
public class ClearFiles {
	static void clear() {
		
		File file = new File("transpose.data");
		if(file.exists()){
			file.delete();
		}
		
		File file1 = new File("gene-maxgain-splitvalue.data");
		if(file1.exists()){
			file1.delete();
		}
		
		File file2 = new File("entropytransposed.data");
		if(file2.exists()){
			file2.delete();
		}
		
		File file3 = new File("numberedtransposed.data");
		if(file3.exists()){
			file3.delete();
		}
		
		File file4 = new File("numbered.data");
		if(file4.exists()){
			file4.delete();
		}
		
		File file5 = new File("positivetuplesnumbered.data");
		if(file5.exists()){
			file5.delete();
		}
		
		File file6 = new File("negativetuplesnumbered.data");
		if(file6.exists()){
			file6.delete();
		}
		
		File file7 = new File("frequent_patterns_pos.data");
		if(file7.exists()){
			file7.delete();
		}
		
		File file8 = new File("frequent_patterns_neg.data");
		if(file8.exists()){
			file8.delete();
		}
		
		File file9 = new File("frequent_genesets_neg.data");
		if(file9.exists()){
			file9.delete();
		}
		
		File file10 = new File("frequent_genesets_pos.data");
		if(file10.exists()){
			file10.delete();
		}
		
		File file11 = new File("frequent_patterns_pos_neg.data");
		if(file11.exists()){
			file11.delete();
		}
		File file12 = new File("frequent_patterns_neg_pos.data");
		if(file12.exists()){
			file12.delete();
		}
		File file13 = new File("emerging_patterns_pos_neg.data");
		if(file13.exists()){
			file13.delete();
		}
		File file14 = new File("corr_genesets_neg.data");
		if(file14.exists()){
			file14.delete();
		}
		File file15 = new File("corr_genesets_pos.data");
		if(file15.exists()){
			file15.delete();
		}
		File file16 = new File("ep_distance_neg.data");
		if(file16.exists()){
			file16.delete();
		}
		File file17 = new File("ep_distance_pos.data");
		if(file17.exists()){
			file17.delete();
		}
		File file18 = new File("ep_avg_distance_pos.data");
		if(file18.exists()){
			file18.delete();
		}
		File file19 = new File("ep_avg_distance_neg.data");
		if(file19.exists()){
			file19.delete();
		}
		File file20 = new File("emerging_patterns_neg_pos.data");
		if(file20.exists()){
			file20.delete();
		}

		File file23 = new File("sorted_ep_avg_distance_pos.data");
		if(file23.exists()){
			file23.delete();
		}
		File file24 = new File("sorted_ep_avg_distance_neg.data");
		if(file24.exists()){
			file24.delete();
		}
		File file25 = new File("top-genes-infogain.data");
		if(file25.exists()){
			file25.delete();
		}
		File file26 = new File("top-genedata-transposed.data");
		if(file26.exists()){
			file26.delete();
		}
		File file27 = new File("top-gene-numbered.data");
		if(file27.exists()){
			file27.delete();
		}
		File file28 = new File("genesets_pos.data");
		if(file28.exists()){
			file28.delete();
		}
		File file29 = new File("genesets_neg.data");
		if(file29.exists()){
			file29.delete();
		}
		File file30 = new File("sorted_corr_genesets_pos.data");
		if(file30.exists()){
			file30.delete();
		}
		File file31 = new File("sorted_corr_genesets_neg.data");
		if(file31.exists()){
			file31.delete();
		}
		File file32 = new File("sorted_ep_distance gene_nums_neg.data");
		if(file32.exists()){
			file32.delete();
		}
		File file33 = new File("sorted_ep_distance gene_nums_pos.data");
		if(file33.exists()){
			file33.delete();
		}
		
	}
}
