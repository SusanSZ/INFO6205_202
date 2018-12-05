package GASample;

import java.util.Random;

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
		color = new Color();
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
		cords[0] = Math.min(x1, x2);
		cords[1] = Math.max(x1, x2);
		cords[2] = Math.min(y1, y2);
		cords[3] = Math.max(y1, y2);
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
			Random rd = new Random();
			int x1 = Math.min(g1.x1, g2.x1) + rd.nextInt(Math.abs(g1.x1 - g2.x1)+1);
			int x2 = Math.min(g1.x2, g2.x2) + rd.nextInt(Math.abs(g1.x2 - g2.x2)+1);
			int y1 = Math.min(g1.y1, g2.y1) + rd.nextInt(Math.abs(g1.y1 - g2.y1)+1);
			int y2 = Math.min(g1.y2, g2.y2) + rd.nextInt(Math.abs(g1.y2 - g2.y2)+1);
			Color c = Color.avgColor(g1.color, g2.color);
			return new PicGene(x1,x2,y1,y2,c,g1.getMark());
		}
		else throw new IllegalArgumentException("g1 and g2 Gene mark is different.");
	}
}
