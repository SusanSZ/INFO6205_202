package GAFrame;

public abstract class Individual {
	protected GenoType geno;
	protected PhenoType pheno;
	
	public Individual(GenoType g) {
		geno = g;
	}
	
	public abstract void ind_mutation(int num_to_mutate);
	
}
