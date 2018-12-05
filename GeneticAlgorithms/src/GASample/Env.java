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
		this.setMutationPos();
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
		List<Individual> new_pop = population.subList(0, size/3);
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
		if(getBestScore() > terminate_score) return true;
		return false;
	}
	
	public void startNextGeneration() {
		if(generation < terminate_generation) {
			elimination();
			evolution(maintained_popsize);
		}
	}
	
	public double getBestScore() {
		Picture best = (Picture)getBestIndividual();
		double bestscore = best.dist((Picture)target_ind);
		return bestscore;
	}
	
	public double getMidScore() {
		Picture mid = (Picture)getTargetIndividual(population.size()/2);
		double midscore = mid.dist((Picture)target_ind);
		return midscore;
	}
	
	private void setMutationPos() {
		double midscore = getMidScore();
		if(midscore <= 0.1) mutation_pos_percentage = 70;
		else if(midscore <= 0.3) mutation_pos_percentage = 60;
		else if(midscore <= 0.5) mutation_pos_percentage = 50;
		else if(midscore <= 0.7) mutation_pos_percentage = 30;
		else if(midscore <= 0.9) mutation_pos_percentage = 20;
		else mutation_pos_percentage = 10;
	}
	
	public Comparator getPictureComparator() {
		return new PicComparator(target_ind);
	}

	@Override
	public Individual getTargetIndividual(int target) {
		if(!is_population_sorted) {
			population.sort(getPictureComparator());
			is_population_sorted = true;
		}
		return population.get(target);
	}

	

}
