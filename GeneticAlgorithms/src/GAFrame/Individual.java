package GAFrame;

public abstract class Individual {
	private GenoType[] genes;
	private PhenoType pheno;
	
	public abstract Fitness fitness();
}