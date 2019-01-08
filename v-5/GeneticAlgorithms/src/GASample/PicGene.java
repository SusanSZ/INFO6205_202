package GASample;

import java.util.Random;

import GAFrame.Gene;
import Utils.Color;
//表示三角形——三点坐标、颜色
//三角形变异方式
public class PicGene extends Gene{

	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private int x3;
	private int y3;
	private Color color;
	
	
	public static boolean is_colinear(int x1,int x2, int x3, int y1, int y2, int y3) {
		double tan1 = (y1 - y2) * 1.0 / (x1 - x2);
		double tan2 = (y1 - y3) * 1.0 / (x1 - x3);
		if(tan1 - tan2 < 0.01) return true;
		else return false;
	}//三角形三顶点共线返回true 否则返回false
	
	public static int getEdge(double tan, int x, int y, int x_target) {
		double y_target = tan * (x_target - x) + y;
		return (int)y_target;
	}//已知x,y,斜率,x_target，返回y_target
	
	public PicGene(int x1,int x2, int x3, int y1, int y2, int y3, int alpha, int red, int green, int blue) {
		super();
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;
		color = new Color();
		color.set(alpha, red, green, blue);
	}//构造函数（输入A,R,G,B）
	
	public PicGene(int x1,int x2, int x3, int y1, int y2, int y3, Color color) {
		super();
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;
		this.color = color;
	}//构造函数（输入color）
	
	public int[] get_cord() {
		int[] cords = new int[6];
		int x_max = Math.max(Math.max(x1, x2), x3);
		int x_min = Math.min(Math.min(x1, x2), x3);
		if(x_min == x1) {
			if(x_max == x2) {
				cords[0] = x1;
				cords[1] = y1;
				cords[2] = x2;
				cords[3] = y2;
				cords[4] = x3;
				cords[5] = y3;
			}
			else{
				cords[0] = x1;
				cords[1] = y1;
				cords[2] = x3;
				cords[3] = y3;
				cords[4] = x2;
				cords[5] = y2;
			}
		}
		else if(x_min == x2) {
			if(x_max == x1) {
				cords[0] = x2;
				cords[1] = y2;
				cords[2] = x1;
				cords[3] = y1;
				cords[4] = x3;
				cords[5] = y3;
			}
			else {
				cords[0] = x2;
				cords[1] = y2;
				cords[2] = x3;
				cords[3] = y3;
				cords[4] = x1;
				cords[5] = y1;
			}
		}
		else {
			if(x_max == x1) {
				cords[0] = x3;
				cords[1] = y3;
				cords[2] = x1;
				cords[3] = y1;
				cords[4] = x2;
				cords[5] = y2;
			}
			else {
				cords[0] = x3;
				cords[1] = y3;
				cords[2] = x2;
				cords[3] = y2;
				cords[4] = x1;
				cords[5] = y1;
			}
		}
		return cords;
	}//按x大小对三个点排序，依次是x最小，x最大， x中间
	
	public Color getColor() {
		return color;
	}
	
	@Override
	public void gene_mutation() {
		mutatealpha();
		mutatelocation();
		mutatecolor();
	}//强变异
	
	public void gene_mutation_minor() {
		mutatecolor_minor();
		mutatelocation_minor();
		mutatealpha_minor();
	}//弱变异
	
	private void mutatecolor_minor() {
		//TODO
		Random rand = new Random();
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();
		r = r + rand.nextInt(100) - rand.nextInt(100);
		g = g + rand.nextInt(100) - rand.nextInt(100);
		b = b + rand.nextInt(100) - rand.nextInt(100);
		if(r < 0) r = 0;
		if(g < 0) g = 0;
		if(b < 0) b = 0;
		if(r > 255) r = 255;
		if(g > 255) g = 255;
		if(b > 255) b = 255;
		this.color.set(color.getAlpha(), r, g, b);
	}//颜色变异，RGB随机（-100,100），alpha不变
	
	private void mutatealpha_minor() {
		Random rand = new Random();
		int alpha = color.getAlpha();
		alpha = alpha + rand.nextInt(25) - rand.nextInt(25);
		if(alpha < 0) alpha = 0;
		if(alpha > 255) alpha = 255;
		this.color.setAlpha(alpha);
	}//透明度变异
	
	private void mutatelocation_minor() {
		Random rand = new Random();
		x1 = x1 * ((85 + rand.nextInt(35))/100);
		x2 = x2 * ((85 + rand.nextInt(35))/100);
		x3 = x3 * ((85 + rand.nextInt(35))/100);
		y1 = y1 * ((85 + rand.nextInt(35))/100);
		y2 = y2 * ((85 + rand.nextInt(35))/100);
		y3 = y3 * ((85 + rand.nextInt(35))/100);
	}//位置变异（0.85,1.19）

	private void mutatecolor() {
		Random r = new Random();
		color.setBlue(r.nextInt(256));
		color.setGreen(r.nextInt(256));
		color.setRed(r.nextInt(256));
	}//颜色变异，无限制
	
	private void mutatealpha() {
		Random r = new Random();
		color.setAlpha(50 + r.nextInt(100));
	}//透明度变异，（50,150）

	private void mutatelocation() {
		Random r = new Random();
		y2 = r.nextInt(Picture.PIXEL_Y);
		y1 = r.nextInt(Picture.PIXEL_Y);
		x2 = r.nextInt(Picture.PIXEL_X);
		x1 = r.nextInt(Picture.PIXEL_X);
		x3 = r.nextInt(Picture.PIXEL_X);
		y3 = r.nextInt(Picture.PIXEL_Y);
		while(is_colinear(x1, x2, x3, y1, y2, y3)) {
			x3 = r.nextInt(Picture.PIXEL_X);
			y3 = r.nextInt(Picture.PIXEL_Y);
		}
	}//坐标变异，无限制
//
//	public static PicGene produce(PicGene g1, PicGene g2) {
//			Random rd = new Random();
//			int x1 = Math.min(g1.x1, g2.x1) + rd.nextInt(Math.abs(g1.x1 - g2.x1)+1);
//			int x2 = Math.min(g1.x2, g2.x2) + rd.nextInt(Math.abs(g1.x2 - g2.x2)+1);
//			int y1 = Math.min(g1.y1, g2.y1) + rd.nextInt(Math.abs(g1.y1 - g2.y1)+1);
//			int y2 = Math.min(g1.y2, g2.y2) + rd.nextInt(Math.abs(g1.y2 - g2.y2)+1);
//			Color c = Color.avgColor(g1.color, g2.color);
//			return new PicGene(x1,x2,y1,y2,c);
//	}

	@Override
	public Gene copy() {
		PicGene gene = new PicGene(x1, x2, x3, y1, y2, y3, color.copy());
		return gene;
	}

	
}
