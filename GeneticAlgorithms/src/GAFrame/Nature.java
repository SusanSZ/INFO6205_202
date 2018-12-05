package GAFrame;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Nature {
	
	protected List<Individual> population;
	protected int generation;
	protected int mutation_pos_percentage;
	protected Individual target_ind;
	protected int terminate_generation;
	protected int maintained_popsize;
	protected int terminate_score;
	protected boolean is_population_sorted = false;
	
	public Nature() {
		population = new ArrayList<Individual>();
	}
	
	public int getGeneration() {
		return generation;
	}
	
	public void setTerminateGen(int tg) {
		this.terminate_generation = tg;
	}
	
	public void setPopsize(int ps) {
		this.maintained_popsize = ps;
	}
	
	public void setTerminateScore(int ts) {
		this.terminate_score = ts;
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
	
	public abstract Individual getBestIndividual();
	
	public abstract void evolution(int popsize);
	
	public abstract void elimination();
}
