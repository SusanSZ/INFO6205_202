package GASample;

import GAFrame.*;
import Utils.Color;
import Utils.ImagePainter;

public class Picture extends Individual implements Comparable<Picture>{

	public static int PIXEL_X;
	public static int PIXEL_Y;
	public boolean isExpressed;
	
	public static void setXY(int x, int y) {
		PIXEL_X = x;
		PIXEL_Y = y;
	}
	
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
		Picture.setXY(colors.length, colors[0].length);
		p.pheno = new PicPhenoType(colors);
		return p;
	}
	
	/**draw this image to a png file
	 * 
	 * @param filename, the output filename, can be anything similar to sample.png
	 */
	public void toFile(String filename) {
		if(!isExpressed)express();
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
				int alpha_diff = Math.abs((color1[i][j].getAlpha()) - (color2[i][j].getAlpha()));
				int color_diff = Color.HSVdist(color1[i][j], color2[i][j]);
				if(alpha_diff < 5) grade += 1;
				if(color_diff < 3000) grade += 1;
				if(color_diff < 500) grade += 1;
				if(color_diff < 100) grade += 1;
			}
		return grade;
	}
	
	public double dist(Picture that) {
		int grade = this.compareTo(that);
		int total_pixels = PIXEL_X * PIXEL_Y;
		double total_score = grade * 1.0 / (total_pixels * 4);
		return Math.pow(total_score, 3);
	}
	
	public static Picture crossover(Picture p1, Picture p2, int mutation_pos) {
		//TODO implement crossover between pictures
		PicGenoType new_geno = (PicGenoType)p1.geno.cross(p2.geno, mutation_pos);
		Picture new_pic = new Picture(new_geno);
		return new_pic;
	}
	
}
