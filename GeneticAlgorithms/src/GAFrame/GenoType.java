package GAFrame;

public abstract class GenoType {
	private Gene[] genes;
	
	public GenoType(int size) {
		genes = new Gene[size];
	}
	
	/** The function will be called during evolution
	 * 
	 * @param mutation_pos_percentage -> the possibility(%) that a gene will mutate during crossover
	 * @return a new generated GenoType
	 */
	public abstract GenoType cross(GenoType that,int mutation_pos_percentage);
	
	/** This function defines the how to transform GenoType to PhenoType
	 * 
	 * @return the PhenoType of this GenoType
	 */
	public abstract PhenoType expression();
	
	public void setGenes(Gene[] genes) {
		this.genes = genes;
	}
	
	public Gene[] getGenes() {
		return genes;
	}
	
	public int getGeneNums() {
		return genes.length;
	}

	public abstract boolean crossoverable(GenoType that);
}
