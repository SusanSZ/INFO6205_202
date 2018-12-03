package GAFrame;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Nature {
	
	protected List<Individual> population;
	private int generation;
	private int mutation_pos_percentage;
	protected Individual target_ind;
	
	public Nature() {
		population = new ArrayList<Individual>();
	}
	
	public void set_target(Individual ind) {
		target_ind = ind;
	}
	
	public void set_mutation_pos(int pos) {
		mutation_pos_percentage = pos;
	}
	
	public void add_ind(Individual ind) {
		population.add(ind);
	}
	
	public abstract void evolution();
	
	public abstract void elimination();
}
