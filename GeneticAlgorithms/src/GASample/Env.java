package GASample;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import GAFrame.Individual;
import GAFrame.Nature;
import Utils.PicComparator;

public class Env extends Nature{
	
	@Override
	public void evolution(int popsize) {
		// TODO Auto-generated method stub
		Random r = new Random();
		List<Individual> new_born = new ArrayList<>();
		while(population.size() + new_born.size() < popsize) {
			int n1 = r.nextInt(population.size());
			int n2 = r.nextInt(population.size());
			while(n1 == n2) n2 = r.nextInt(population.size());
			Picture p1 = (Picture) population.get(n1);
			Picture p2 = (Picture) population.get(n2);
			Picture new_pic = Picture.crossover(p1, p2, mutation_pos_percentage);
			new_born.add(new_pic);
		}
		population.addAll(new_born);
		is_population_sorted = false;
		generation++;
	}

	@Override
	public void elimination() {
		population.sort(getPictureComparator());
		is_population_sorted = true;
		int size = population.size();
		List<Individual> new_pop = population.subList(size/3, size);
		population = new ArrayList<>(new_pop);
	}
	
	@Override
	public Individual getBestIndividual() {
		if(!is_population_sorted) {
			population.sort(getPictureComparator());
			is_population_sorted = true;
		}
		return population.get(0);
	}
	
	public boolean shouldTerminate() {
		if(terminate_generation <= generation) return true;
		//if(((Picture)getBestIndividual()).compareTo((Picture)target_ind) > terminate_score) return true;
		return false;
	}
	
	public void startNextGeneration() {
		if(generation < terminate_generation) {
			elimination();
			evolution(maintained_popsize);
		}
	}
	
	public Comparator getPictureComparator() {
		return new PicComparator(target_ind);
	}

	

}
