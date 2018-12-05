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
		Random r = new Random();
		int mark = r.nextInt(5);
		switch(mark) {
			case 0:mutatealpha();break;
			case 1:
			case 2:mutatelocation();break;
			case 3:
			case 4:mutatecolor();break;
		}
	}

	private void mutatecolor() {
		Random r = new Random();
		color.setBlue(r.nextInt(256));
		color.setGreen(r.nextInt(256));
		color.setRed(r.nextInt(256));
	}
	
	private void mutatealpha() {
		Random r = new Random();
		color.setAlpha(r.nextInt(256));
	}

	private void mutatelocation() {
		Random r = new Random();
		y2 = r.nextInt(Picture.PIXEL_Y);
		y1 = r.nextInt(Picture.PIXEL_Y);
		x2 = r.nextInt(Picture.PIXEL_X);
		x1 = r.nextInt(Picture.PIXEL_X);
		while(x1 == x2) x2 = r.nextInt(Picture.PIXEL_X);
		while(y1 == y2) y2 = r.nextInt(Picture.PIXEL_Y);
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
