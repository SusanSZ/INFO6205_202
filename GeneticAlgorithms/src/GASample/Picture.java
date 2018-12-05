package GASample;

import GAFrame.*;
import Utils.Color;
import Utils.ImagePainter;

public class Picture extends Individual implements Comparable<Picture>{

	public boolean isExpressed;
	
	private Picture() {
		super(new PicGenoType());
		isExpressed = true;
	}
	
	public Picture(PicGenoType g) {
		super(g);
		isExpressed = false;
	}
	
	public static Picture createTargetPicture(Color[][] colors) {
		Picture p = new Picture();
		p.pheno = new PicPhenoType(colors);
		return p;
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
		if(!this.isExpressed) this.express();
		if(!that.isExpressed) that.express();
		
		Color[][] color1 = ((PicPhenoType)this.pheno).getcolors();
		Color[][] color2 = ((PicPhenoType)that.pheno).getcolors();
		int grade = 0;
		for(int i = 0; i < ((PicPhenoType)this.pheno).getcolors().length; i++)
			for(int j = 0; j < ((PicPhenoType)this.pheno).getcolors()[0].length; j++) {
				int alpha = Math.abs((color1[i][j].getAlpha()) - (color2[i][j].getAlpha()));
				int green = Math.abs((color1[i][j].getGreen()) - (color2[i][j].getGreen()));
				int blue = Math.abs((color1[i][j].getBlue()) - (color2[i][j].getBlue()));
				int red = Math.abs((color1[i][j].getRed()) - (color2[i][j].getRed()));
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
