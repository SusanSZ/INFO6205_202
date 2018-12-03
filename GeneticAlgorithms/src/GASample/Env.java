package GASample;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import GAFrame.Individual;
import GAFrame.Nature;
import Utils.PicComparator;

public class Env extends Nature{
	@Override
	public void evolution() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void elimination() {
		population.sort(getPictureComparator());
		int size = population.size();
		List<Individual> new_pop = population.subList(size/3, size);
		population = new ArrayList<>(new_pop);
	}
	
	public Comparator getPictureComparator() {
		return new PicComparator(target_ind);
	}

}
