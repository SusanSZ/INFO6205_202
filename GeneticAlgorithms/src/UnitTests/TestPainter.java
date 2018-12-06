package UnitTests;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.junit.*;

import GAFrame.Individual;
import GASample.*;
import GateWay.GAHelper;
import Utils.Color;
import Utils.ImagePainter;
import Utils.ImageReader;

public class TestPainter{
	//@Test
	public void testdraw() {
		Random r = new Random();
		GAHelper helper = new GAHelper(100, 100, 10);
		int width = 100;
		int height = 100;
		Env e = helper.configureEnv(10, null);
		List<Individual> pictures = e.getPopulation();
		int filecount = 0;
		for(Individual i : pictures) {
			Picture p = (Picture)i;
			p.toFile(filecount+".png");
			filecount++;
		}
	}
	@Test
	public void testCross() {
		GAHelper helper = new GAHelper(100, 100, 10);
		PicGenoType pg1 = helper.picGenoType_generator();
		PicGenoType pg2 = helper.picGenoType_generator();
		Picture p1 = new Picture(pg1);
		Picture p2 = new Picture(pg2);
		p1.toFile("pg1.png");
		p2.toFile("pg2.png");
		Picture new_pic = Picture.crossover(p1, p2);
		new_pic.toFile("nomutate.png");
		for(int i = 0; i < 10; i++) {
			new_pic.ind_mutation(1);
			new_pic.toFile("mutate" + i +".png");
		}
	}
	
	//@Test
	public void testExpression() {
		GAHelper helper = new GAHelper(10, 10, 10);
		PicGenoType g1 = helper.picGenoType_generator();
		Picture p = new Picture(g1);
		p.express();
	}
	
	//@Test
	public void testReadandResize() {
		Color[][] targetcolors = null;
		try {
			targetcolors = ImageReader.getTargetColorArray("./targetimg/" + "test.png");
		} catch (IOException e) {
			System.out.println("Warning: the image does not exists. Application will terminate right away.");
			return;
		}
		Picture targetPic = Picture.createTargetPicture(targetcolors);
		targetPic.toFile("testinput.png");
	}
}




