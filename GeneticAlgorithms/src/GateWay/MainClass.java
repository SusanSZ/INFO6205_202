package GateWay;

import java.io.IOException;
import java.util.Scanner;

import GASample.*;
import Utils.Color;
import Utils.ImageReader;

public class MainClass {
	public static void main(String args[]) {
		//TODO complete the entering method
		/*
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter the image file name: ");
		String filename = sc.nextLine();
		System.out.print("Please enter resize width: ");
		int width = sc.nextInt();
		System.out.print("Please enter resize height: ");
		int height = sc.nextInt();
		System.out.println("\n\nTarget image setup done. Configure environment now~");
		System.out.print("\tPlease enter the number of genes: ");
		int num_genes = sc.nextInt();
		System.out.print("\tPlease enter the number of initial population: ");
		int init_pop = sc.nextInt();
		System.out.print("\tHow big is the population you want to maintain during evolution: ");
		int maintained_popsize = sc.nextInt();
		System.out.println("\tTo which generation would the application stop: ");
		int terminate_gen = sc.nextInt();
		System.out.println("\tTo which score would the application stop(best score is 0, all other scores are small than 0): ");
		int terminate_score = sc.nextInt();
		System.out.print("\nConfiguring ongoing, this might take sometime.Please be patient");
		*/
		
		int width = 200;
		int height = 200;
		int num_genes = 2000;
		String filename = "test.png";
		int init_pop = 200;
		int maintained_popsize = 500;
		int terminate_gen = 100000;
		int terminate_score = 0;
		
		GAHelper helper = new GAHelper(width, height, num_genes);
		Color[][] targetcolors = null;
		try {
			targetcolors = ImageReader.getTargetColorArray("./targetimg/" + filename, width, height);
		} catch (IOException e) {
			System.out.println("Warning: the image does not exists. Application will terminate right away.");
			return;
		}
		Picture targetPic = Picture.createTargetPicture(targetcolors);
		Env e = helper.configureEnv(init_pop, targetPic);
		e.setPopsize(maintained_popsize);
		e.setTerminateGen(terminate_gen);
		e.setTerminateScore(terminate_score);
		System.out.print("Configuring finished! Environment setup!");
		System.out.println("...");
		System.out.println("...");
		System.out.println("...");
		System.out.println("...");
		System.out.println("The environment will start in a while, several tips before it starts.");
		System.out.println("\t1).Each time when generation goes to 10^n, the best individual will be stored, stored location would be \"./genimgs/\"");
		System.out.println("\tum.....maybe no more tips regarding the application\n...\n...\n...\nGENERATION START!");
		
		System.out.println("printing the best of first generation");
		Picture bestofone = (Picture)e.getBestIndividual();
		bestofone.toFile("bestofone.png");
		while(!e.shouldTerminate()) {
			e.startNextGeneration();
			int current_gen = e.getGeneration();
			if(current_gen%10 == 0) {
				System.out.println("now this is the " + current_gen + " generation of this environment");
				String outpngname = "bestof_" + current_gen + ".png";
				System.out.println("The best individual of this generation will be stored as \"" + outpngname + "\"");
				Picture best = (Picture)e.getBestIndividual();
				best.toFile(outpngname);
			}
		}
		System.out.println("Environment has come to the terminate point.");
		
	}
}
