package GASample;

import GAFrame.Gene;
import Utils.Color;

public class PicGene extends Gene{

	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private Color color;
	
	public PicGene(int x1,int x2, int y1, int y2, int alpha, int red, int green, int blue, int mark) {
		super(mark);
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		color.set(alpha, red, green, blue);
	}
	
	public PicGene(int x1,int x2, int y1, int y2, Color color, int mark) {
		super(mark);
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.color = color;
	}
	
	public int[] get_cord() {
		int[] cords = new int[4];
		cords[0] = x1;
		cords[1] = y1;
		cords[2] = x2;
		cords[3] = y2;
		return cords;
	}
	
	public Color getColor() {
		return color;
	}
	
	@Override
	public void mutation() {
		
	}

	public static PicGene produce(PicGene g1, PicGene g2) {
		if(g1.getMark() == g2.getMark()) {
			int x1 = (g1.x1 + g2.x1)/2;
			int x2 = (g1.x2 + g2.x2)/2;
			int y1 = (g1.y1 + g2.y1)/2;
			int y2 = (g1.y2 + g2.y2)/2;
			Color c = Color.avgColor(g1.color, g2.color);
			return new PicGene(x1,x2,y1,y2,c,g1.getMark());
		}
		else throw new IllegalArgumentException("g1 and g2 Gene mark is different.");
	}
}
