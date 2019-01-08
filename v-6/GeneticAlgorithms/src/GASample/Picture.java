package GASample;

import java.util.Random;

import GAFrame.*;
import Utils.Color;
import Utils.ImagePainter;

public class Picture extends Individual{

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
	}//创建目标图像
	
	/**draw this image to a png file
	 * 
	 * @param filename, the output filename, can be anything similar to sample.png
	 */
	public void toFile(String filename) {
		if(!isExpressed)express();
		PicPhenoType picpheno = (PicPhenoType)pheno;
		ImagePainter painter = new ImagePainter(picpheno.getcolors());
		painter.output(filename);
	}//将当前图片存储到文件中
	@Override
	public void global_mutation(int num_to_mutate) {
		PicGene[] genes = (PicGene[]) geno.getGenes();
		Random rand = new Random();
		for(int i = 0; i < num_to_mutate; i++) {
			int to_mutate = rand.nextInt(genes.length);
			genes[to_mutate].gene_mutation();
		}
		isExpressed = false;
	}//强变异
	
	@Override
	public void minor_mutation(int num_to_mutate) {
		PicGene[] genes = (PicGene[]) geno.getGenes();
		Random rand = new Random();
		for(int i = 0; i < num_to_mutate; i++) {
			int to_mutate = rand.nextInt(genes.length);
			genes[to_mutate].gene_mutation_minor();
		}
		isExpressed = false;
	}//弱变异
	
	public void express() {
		pheno =  geno.expression();
		isExpressed = true;
	}
	
	public double compareTo(Picture that) {
		if(!this.isExpressed) this.express();
		if(!that.isExpressed) that.express();
		
		Color[][] color1 = ((PicPhenoType)this.pheno).getcolors();
		Color[][] color2 = ((PicPhenoType)that.pheno).getcolors();
		int total_diff = 0;
		int total = Picture.PIXEL_X * Picture.PIXEL_Y * 4;
		for(int i = 0; i < ((PicPhenoType)this.pheno).getcolors().length; i++)
			for(int j = 0; j < ((PicPhenoType)this.pheno).getcolors()[0].length; j++) {
				int alpha_diff = Math.abs((color1[i][j].getAlpha()) - (color2[i][j].getAlpha()));
				int red_diff = Math.abs((color1[i][j].getRed()) - (color2[i][j].getRed()));
				int green_diff = Math.abs((color1[i][j].getGreen()) - (color2[i][j].getGreen()));
				int blue_diff = Math.abs((color1[i][j].getBlue()) - (color2[i][j].getBlue()));
				total_diff += (alpha_diff + red_diff + green_diff + blue_diff);
			}
		return total * 1.0 / total_diff ;
	}//计算两图片相似度，以RGB值计算
	
//	public int compareTo(Picture that) {
//		if(!this.isExpressed) this.express();
//		if(!that.isExpressed) that.express();
//		
//		Color[][] color1 = ((PicPhenoType)this.pheno).getcolors();
//		Color[][] color2 = ((PicPhenoType)that.pheno).getcolors();
//		int grade = 0;
//		for(int i = 0; i < ((PicPhenoType)this.pheno).getcolors().length; i++)
//			for(int j = 0; j < ((PicPhenoType)this.pheno).getcolors()[0].length; j++) {
//				int alpha_diff = Math.abs((color1[i][j].getAlpha()) - (color2[i][j].getAlpha()));
//				int color_diff = Color.HSVdist(color1[i][j], color2[i][j]);
//				if(alpha_diff < 5) grade += 1;
//				if(color_diff < 3000) grade += 1;
//				if(color_diff < 500) grade += 1;
//				if(color_diff < 100) grade += 1;
//			}
//		return grade;
//	}
	
//	public double dist(Picture that) {
//		int grade = this.compareTo(that);
//		int total_pixels = PIXEL_X * PIXEL_Y;
//		double total_score = grade * 1.0 / (total_pixels * 4);
//		return Math.pow(total_score, 3);
//	}
	
	public static Picture crossover(Picture p1, Picture p2) {
		//TODO implement crossover between pictures
		PicGenoType new_geno = (PicGenoType)p1.geno.cross(p2.geno);
		Picture new_pic = new Picture(new_geno);
		return new_pic;
	}//返回两图片交叉后的新图片
	
}
