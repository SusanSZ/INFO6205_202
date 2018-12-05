package GASample;

import java.util.Arrays;
import java.util.Random;

import GAFrame.Gene;
import GAFrame.GenoType;
import GAFrame.PhenoType;
import Utils.Color;

public class PicGenoType extends GenoType {
	
	private int picsize_x;
	private int picsize_y;
	
	public PicGenoType(int gene_size) {
		super(gene_size);
		picsize_x = Picture.PIXEL_X;
		picsize_y = Picture.PIXEL_Y;
	}
	
	public PicGenoType() {
		super(1);
		picsize_x = Picture.PIXEL_X;
		picsize_y = Picture.PIXEL_Y;
	}
	
	
//	public void setSize(int picsize_x, int picsize_y) {
//		this.picsize_x = picsize_x;
//		this.picsize_y = picsize_y;
//	}
	
	@Override
	public PhenoType expression() {
		Color[][] pixels = new Color[picsize_x][picsize_y];
		for(int i = 0; i < picsize_x; i++) 
			for(int j = 0; j < picsize_y; j++) {
				pixels[i][j] = new Color();
				pixels[i][j].set(0,0,0,0);
			}
		
		for(int i = 0; i < this.getGeneNums(); i++) {
			PicGene g = (PicGene)genes[i];
			int[] cords = g.get_cord();
			for(int x = cords[0]; x < cords[2]; x++) {
				if(x <= cords[3]) {
					double tan1 = (cords[5] - cords[1]) * 1.0 / (cords[4] - cords[0]);
					double tan2 = (cords[3] - cords[1]) * 1.0 / (cords[2] - cords[0]);
					double bigger_tan = Math.max(tan1, tan2);
					double smaller_tan = Math.min(tan1, tan2);
					int start = PicGene.getEdge(smaller_tan, cords[0], cords[1], x);
					int end = PicGene.getEdge(bigger_tan, cords[0], cords[1], x);
					if(start <= 0) start = 0;
					else if(start >= Picture.PIXEL_X) start = Picture.PIXEL_X - 1;
					if(end <= 0) end = 0;
					else if(end >= Picture.PIXEL_Y) end = Picture.PIXEL_Y - 1;
					for( int y = start; y <= end; y++) {
						pixels[x][y] = Color.addColorToBackground(pixels[x][y], g.getColor());
					}
				}
				else {
					double tan1 = (cords[5] - cords[3]) * 1.0 / (cords[4] - cords[2]);
					double tan2 = (cords[1] - cords[3]) * 1.0 / (cords[0] - cords[2]);
					double bigger_tan = Math.max(tan1, tan2);
					double smaller_tan = Math.min(tan1, tan2);
					int start = PicGene.getEdge(bigger_tan, cords[2], cords[3], x);
					int end = PicGene.getEdge(smaller_tan, cords[2], cords[3], x);
					if(start <= 0) start = 0;
					else if(start >= Picture.PIXEL_X) start = Picture.PIXEL_X - 1;
					if(end <= 0) end = 0;
					else if(end >= Picture.PIXEL_Y) end = Picture.PIXEL_Y - 1;
					for( int y = start; y <= end; y++) {
						pixels[x][y] = Color.addColorToBackground(pixels[x][y], g.getColor());
					}
				}
			}
		}
		return new PicPhenoType(pixels);
	}
	
	private static void shuffle(PicGene[] genes) {
		int N = genes.length;
		for(int i = 0; i < N; i++) {
			Random rand = new Random();
			int r = rand.nextInt(i+1);
			swap(genes, i, r);
		}
	}
	
	private static void swap(PicGene[] genes, int a, int b) {
		PicGene temp = genes[a];
		genes[a] = genes[b];
		genes[b] = temp;
	}

	@Override
	public GenoType cross(GenoType that, int mutation_pos_percentage) {
		PicGene[] genes_1 = (PicGene[])this.getGenes();
		PicGene[] genes_2 = (PicGene[])that.getGenes();
		shuffle(genes_1);
		shuffle(genes_2);
		int N = genes_1.length;
		Random rand = new Random();
		int from_1 = rand.nextInt(N+1);
		int from_2 = N - from_1;
		PicGene[] fromone = Arrays.copyOfRange(genes_1, 0, from_1);
		PicGene[] fromtwo = Arrays.copyOfRange(genes_2, 0, from_2);
		PicGene[] new_genes = new PicGene[fromone.length+fromtwo.length];
		System.arraycopy(fromone, 0, new_genes, 0, fromone.length);
		System.arraycopy(fromtwo, 0, new_genes, fromone.length, fromtwo.length);
		shuffle(new_genes);
		for(PicGene gene : new_genes) {
			int r = rand.nextInt(101);
			if(r <= mutation_pos_percentage)
				gene.mutation();
		}
		PicGenoType new_picgenotype = new PicGenoType();
		new_picgenotype.setGenes(new_genes);
		return new_picgenotype;
	}

	@Override
	public boolean crossoverable(GenoType that) {
//		if(that instanceof PicGenoType) 
//			return  this.picsize_x == ((PicGenoType)that).picsize_x && 
//					this.picsize_y == ((PicGenoType)that).picsize_y &&
//					this.getGeneNums() == that.getGeneNums();
//		else return false;
		return true;
	}
	
}
