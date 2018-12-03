package GASample;

import GAFrame.*;
import Utils.ImagePainter;

public class Picture extends Individual implements Comparable<Picture>{

	public Picture(PicGenoType g) {
		super(g);
	}
	
	/**draw this image to a png file
	 * 
	 * @param filename, the output filename, can be anything similar to sample.png
	 */
	public void toFile(String filename) {
		PicPhenoType picpheno = (PicPhenoType)pheno;
		ImagePainter painter = new ImagePainter(picpheno.getcolors());
		painter.output(filename);
	}
	
	//TODO implement compare method
	public int compareTo(Picture that) {
		return 0;
	}
	
	public static Picture crossover(Picture p1, Picture p2, int mutation_pos) {
		//TODO implement crossover between pictures
		PicGenoType new_geno = (PicGenoType)p1.geno.cross(p2.geno, mutation_pos);
		Picture new_pic = new Picture(new_geno);
		return new_pic;
	}
	
	public Gene get_marked_gene(int mark) {
		Gene[] genes = geno.getGenes();
		for(int i = 0; i < genes.length; i++) {
			if(genes[i].getMark() == mark)
				return genes[i];
		}
		return null;
	}
}
