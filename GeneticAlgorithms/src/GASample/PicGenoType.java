package GASample;

import GAFrame.GenoType;
import GAFrame.PhenoType;

public class PicGenoType extends GenoType {
	
	private int picsize_x;
	private int picsize_y;
	
	public PicGenoType() {
	}
	
	public void setSize(int picsize_x, int picsize_y) {
		this.picsize_x = picsize_x;
		this.picsize_y = picsize_y;
	}
	
	@Override
	public PhenoType expression() {
		return null;
	}

	@Override
	public GenoType crossover(GenoType that, int mutation_pos_percentage) {
		if(this.crossoverable(that)) {
			PicGene[] genes_1 = (PicGene[])this.getGenes();
			PicGene[] genes_2 = (PicGene[])that.getGenes();
			int N = genes_1.length;
			PicGene[] new_genes = new PicGene[N];
			for(int i = 0;i<N;i++) {
				PicGene new_gene = PicGene.produce(genes_1[i],genes_2[i]);
				new_genes[i] = new_gene;
			}
			PicGenoType new_picgenotype = new PicGenoType();
			new_picgenotype.setGenes(new_genes);
			return new_picgenotype;
		}
		return null;
	}

	@Override
	public boolean crossoverable(GenoType that) {
		if(that instanceof PicGenoType) 
			return  this.picsize_x == ((PicGenoType)that).picsize_x && 
					this.picsize_y == ((PicGenoType)that).picsize_y &&
					this.getGeneNums() == that.getGeneNums();
		else return false;
	}
	
}