package GASample;

import GAFrame.*;
import Utils.Color;
import Utils.ImagePainter;

public class Picture extends Individual implements Comparable<Picture>{

	public boolean isExpressed = false;
	
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
	
	public void express() {
		pheno =  geno.expression();
		isExpressed = true;
	}
	
	
	public int compareTo(Picture that) {
		//check whether genes has expressed 
		if(!this.isExpressed) this.express();
		if(!that.isExpressed) that.express();
		//TODO implement compare method
		Color[][] color1 = ((PicPhenoType)this.pheno).getcolors();
		Color[][] color2 = ((PicPhenoType)that.pheno).getcolors();
		int grade = 0;
		for(int i = 0; i < ((PicPhenoType)this.pheno).getcolors().length; i++)
			for(int j = 0; j < ((PicPhenoType)this.pheno).getcolors()[0].length; j++) {
				int alpha = Math.abs((color1[i][j]. & 0xFF) - (color2[i][j].to8bit() & 0xFF));
				int green = Math.abs(((color1[i][j].>>>8) & 0xFF) - ((color2[i][j].>>>8) & 0xFF));
				int blue = Math.abs(((color1[i][j].>>>16) & 0xFF) - ((color2[i][j].>>>16) & 0xFF));
				int red = Math.abs(((color1[i][j].>>>24) & 0xFF) - ((color2[i][j].>>>24) & 0xFF));
				grade +=  -(alpha + red + green + blue);
			}
		return grade;
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
