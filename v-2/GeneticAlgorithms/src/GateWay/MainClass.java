package GateWay;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvWriter;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvWriterSettings;

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
		
		int num_genes = 100;
		String filename = "test.png";
		int init_pop = 2500;
		int maintained_popsize = 5000;
		int terminate_gen = 50000;
		int terminate_score = 95;
		
		Color[][] targetcolors = null;
		try {
			targetcolors = ImageReader.getTargetColorArray("./targetimg/" + filename);
		} catch (IOException e) {
			System.out.println("Warning: the image does not exists. Application will terminate right away.");
			return;
		}
		int width = targetcolors.length;
		int height = targetcolors[0].length;
		GAHelper helper = new GAHelper(width, height, num_genes);
		Picture targetPic = Picture.createTargetPicture(targetcolors);
		targetPic.toFile("targetPic.png");
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
		System.out.println("\t2).um.....maybe no more tips regarding the application\n...\n...\n...\nGENERATION START!");
		String outpath = "output1.csv";
		File csvfile = new File(outpath);
		CsvWriterSettings settings=new CsvWriterSettings();
		CsvWriter csvWriter = new CsvWriter(csvfile, Charset.forName("UTF-8"), settings);
		while(!e.shouldTerminate()) {
			int mutationcount = e.startNextGeneration();//！！！进化过程
			int current_gen = e.getGeneration();//代数
			if(current_gen == 1 || current_gen == 20 || current_gen == 50 || current_gen == 100 || current_gen == 200 || current_gen == 500 || current_gen%1000 == 0) {
				System.out.println("\nnow this is the " + current_gen + " generation of this environment");
				System.out.println("\tcurrent mutate possibility is: " + e.getMutePos());
				System.out.println("\tMutation happens: " + mutationcount +" times.");
				System.out.println("\tBest score is :" + e.getBestScore());
				System.out.println("\tMid score is : " + e.getMidScore());
				String outpngname = "bestof_" + current_gen + ".png";
				System.out.println("\tThe best individual of this generation will be stored as \"" + outpngname + "\"");
				Picture best = (Picture)e.getBestIndividual();
				best.toFile(outpngname);
			}
			Object[] content = {current_gen, e.getBestScore(),e.getMidScore(), mutationcount};
			csvWriter.writeRow(content);
		}
		System.out.println("Environment has come to the terminate point.");
		csvWriter.close();
	}
}
