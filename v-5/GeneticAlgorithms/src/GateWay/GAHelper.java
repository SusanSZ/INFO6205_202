 package GateWay;

import java.util.Random;

import GAFrame.Individual;
import GASample.*;

public class GAHelper {
	
	private int pixels_x;
	private int pixels_y;
	private int gene_size;
	
	public GAHelper(int px, int py, int gene_size) {
		pixels_x = px;
		pixels_y = py;
		Picture.setXY(px, py);
		this.gene_size = gene_size;
	}
	
	/** this function randomly generate a picgene
	 * 
	 * @return a random picgene
	 */
	public PicGene picGene_generator(int mark) {
		Random ran = new Random();
		int x1 = ran.nextInt(pixels_x);
		int y1 = ran.nextInt(pixels_y);
		int x2 = ran.nextInt(pixels_x);
		int y2 = ran.nextInt(pixels_y);
		int x3 = ran.nextInt(pixels_x);
		int y3 = ran.nextInt(pixels_y);
		while(PicGene.is_colinear(x1, x2, x3, y1, y2, y3)) {
			x3 = ran.nextInt(pixels_x);
			y3 = ran.nextInt(pixels_y);
		}
		int alpha = 50 + ran.nextInt(100);
		int red = ran.nextInt(256);
		int green = ran.nextInt(256);
		int blue = ran.nextInt(256);
		return new PicGene(x1, x2, x3, y1, y2, y3, alpha, red, green, blue);
	}//���������������
	
	public PicGenoType picGenoType_generator() {
		PicGenoType genotype = new PicGenoType();
		PicGene[] genes = new PicGene[gene_size];
		for(int i = 0; i < gene_size; i++) {
			genes[i] = picGene_generator(i);
		}
		genotype.setGenes(genes);
		return genotype;
	}//�������δ����ʾ��ͼ��
	
	/** configuration of initial environment
	 * 
	 * @param init_pop, the initial size of the population
	 * @return the generated Environment
	 */
	public Env configureEnv(int init_pop, Individual target_ind) {
		Env new_env = new Env();
		new_env.set_target(target_ind);
		for(int i = 0; i<init_pop; i++) {
			Picture p = new Picture(picGenoType_generator());
			p.express();
			new_env.add_ind(p);
		}
		return new_env;
	}//
}
