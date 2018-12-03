package GateWay;

import java.util.Comparator;

import GAFrame.Individual;
import GASample.Picture;

public class PicComparator implements Comparator<Individual> {

	private Individual target_ind;
	
	public PicComparator(Individual target) {
		target_ind = target;
	}
	
	@Override
	public int compare(Individual i1, Individual i2) {
		// TODO Auto-generated method stub
		Picture p1 = (Picture)i1;
		Picture p2 = (Picture)i2;
		Picture target_pic = (Picture)target_ind;
		int c1 = p1.compareTo(target_pic);
		int c2 = p2.compareTo(target_pic);
		if(c1 > c2) return 1;
		else if(c1 < c2) return -1;
		else return 0;
	}
	

}
