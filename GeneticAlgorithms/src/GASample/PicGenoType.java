package GASample;

import GAFrame.Gene;
import GAFrame.GenoType;
import GAFrame.PhenoType;
import Utils.Color;

public class PicGenoType extends GenoType {
	
	private final int picsize_x;
	private final int picsize_y;
	
	public PicGenoType(int gene_size, int x, int y) {
		super(gene_size);
		picsize_x = x;
		picsize_y = y;
	}
	
	public PicGenoType(int x, int y) {
		super(1);
		picsize_x = x;
		picsize_y = y;
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
			for(int x = cords[0]; x < cords[1]; x++) {
				for(int y = cords[2]; y < cords[3]; y++) {
					pixels[x][y] = Color.addColorToBackground(pixels[x][y], g.getColor());
				}
			}
		}
		return new PicPhenoType(pixels);
	}

	@Override
	public GenoType cross(GenoType that, int mutation_pos_percentage) {
		if(this.crossoverable(that)) {
			PicGene[] genes_1 = (PicGene[])this.getGenes();
			PicGene[] genes_2 = (PicGene[])that.getGenes();
			int N = genes_1.length;
			PicGene[] new_genes = new PicGene[N];
			for(int i = 0;i<N;i++) {
				PicGene new_gene = PicGene.produce(genes_1[i],genes_2[i]);
				new_genes[i] = new_gene;
			}
			PicGenoType new_picgenotype = new PicGenoType(this.picsize_x,this.picsize_y);
			new_picgenotype.setGenes(new_genes);
			return new_picgenotype;
		}
		return null;
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
