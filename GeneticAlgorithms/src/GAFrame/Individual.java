package GAFrame;

public abstract class Individual {
	protected GenoType geno;
	protected PhenoType pheno;
	
	public Individual(GenoType g) {
		geno = g;
	}
	
	public abstract void global_mutation(int num_to_mutate);
	
	public abstract void minor_mutation(int num_to_mutate);
	
}
