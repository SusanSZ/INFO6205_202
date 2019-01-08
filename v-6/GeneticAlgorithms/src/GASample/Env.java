package GASample;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import GAFrame.Individual;
import GAFrame.Nature;
import Utils.PicComparator;
//��ʾ�����е�һ��
//��������λ��Ӧ�ȡ������Ӧ�ȡ�������Ŀ�������Ŀ��score�������ʡ���Ⱥ�����б�Ŀ��ͼƬ����Ⱥ����
//*û�н�����
public class Env extends Nature{
	
	private double midscore;
	private double bestscore;
	@Override
	public int evolution(int popsize) {
		// TODO Auto-generated method stub
		setMidScore(getMidScore());
		setBestScore(getBestScore());
		Random r = new Random();
		this.setMutationPos();
		int num_to_mutate = (mutation_pos_percentage/10) * (mutation_pos_percentage/10);
		int mutation_count = 0;
		List<Individual> new_born = new ArrayList<>();//�����ɵĸ����б�
		List<Integer> unselected = new ArrayList<>();//ѡ��ǰ����Ⱥ�����б��б���Ԫ��Ϊ����ı�ţ���1,2,3����
		for(int i = 0; i < population.size(); i++) {
			unselected.add(i);
		}
		while(population.size() + new_born.size() < popsize) {
			int n1 = r.nextInt(unselected.size());
			int n2 = r.nextInt(unselected.size());
			while(n1 == n2) n2 = r.nextInt(population.size());
			Picture p1 = (Picture) population.get(n1);
			Picture p2 = (Picture) population.get(n2);
			Picture new_pic = Picture.crossover(p1, p2);
			if(r.nextInt(101) < mutation_pos_percentage) {
				if(midscore < 0.035)
					new_pic.global_mutation(num_to_mutate);
				else
					new_pic.minor_mutation(num_to_mutate);
				mutation_count ++;
			}
			new_born.add(new_pic);
			unselected.remove((Integer)n1);
			unselected.remove((Integer)n2);
		}
		population.addAll(new_born);
		is_population_sorted = false;
		generation++;
		return mutation_count;
	}//�������̣���������/10��^2  Ϊһ��ͼƬ�������α���ĸ��������ط���������¸��������

	@Override
	public void elimination() {
		population.sort(getPictureComparator());
		is_population_sorted = true;
		int size = population.size();
		List<Individual> new_pop = population.subList(size/3, size);
		population = new ArrayList<>(new_pop);
	}//��̭����̭��Ӧ�������1/3�ĸ���
	
	@Override
	public Individual getBestIndividual() {
		int size = population.size();
		return getTargetIndividual(size-1);
	}
	
	private void setMidScore(double s) {
		midscore = s;
	}
	
	private void setBestScore(double s) {
		bestscore = s;
	}
	
	public boolean shouldTerminate() {
		if(terminate_generation <= generation) return true;
		//if(getBestScore() > terminate_score) return true;
		return false;
	}
	
	public int startNextGeneration() {
		elimination();
		int mutation_count =  evolution(maintained_popsize);
		return mutation_count;
	}//��̭����һ���������̣����ر������
	
	public double getBestScore() {
		Picture best = (Picture)getBestIndividual();
		double bestscore = best.compareTo((Picture)target_ind);
		return bestscore;
	}//������Ӧ�����ֵ
	
	public double getMidScore() {
		Picture mid = (Picture)getTargetIndividual(population.size()/2);
		double midscore = mid.compareTo((Picture)target_ind);
		return midscore;
	}//������Ӧ���м�ֵ
	

	
	private void setMutationPos() {
		mutation_pos_percentage = 30;
	}//����������Ӧ
	
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
