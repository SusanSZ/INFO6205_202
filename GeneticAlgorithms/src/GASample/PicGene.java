package GASample;

import GAFrame.Gene;

public class PicGene extends Gene{

	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private int red;
	private int blue;
	private int green;
	private int alpha;
	
	
	
	public PicGene(int x1,int x2, int y1, int y2, int alpha, int red, int green, int blue) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.red = red;
		this.blue = blue;
		this.green = green;
		this.alpha = alpha;
	}
	
	@Override
	public void mutation() {
		
	}

	public static PicGene produce(PicGene g1, PicGene g2) {
		int x1 = (g1.x1 + g2.x1)/2;
		int x2 = (g1.x2 + g2.x2)/2;
		int y1 = (g1.y1 + g2.y1)/2;
		int y2 = (g1.y2 + g2.y2)/2;
		int red = (g1.red + g2.red)/2;
		int blue = (g1.blue + g2.blue)/2;
		int green = (g1.green +g2.green)/2;
		int alpha = (g1.alpha +g2.alpha)/2;
		return new PicGene(x1,x2,y1,y2,alpha,red,green,blue);
	}
}
