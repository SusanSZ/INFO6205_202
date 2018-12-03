package GAFrame;

public abstract class Individual {
	private GenoType genes;
	private PhenoType pheno;
	
	public Individual(GenoType g) {
		genes = g;
	}
}
