package GAFrame;

public abstract class Individual {
	protected GenoType genes;
	protected PhenoType pheno;
	
	public Individual(GenoType g) {
		genes = g;
	}
}
