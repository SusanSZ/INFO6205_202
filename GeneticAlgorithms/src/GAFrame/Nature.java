package GAFrame;

import java.util.List;

public abstract class Nature {
	
	private List<Individual> population;
	private int generation;
	private int mutation_pos_percentage;
	
	public abstract void Evolution();
}
