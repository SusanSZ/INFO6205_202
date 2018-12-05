package UnitTests;

import java.io.IOException;
import java.util.Random;

import org.junit.*;

import GASample.*;
import GateWay.GAHelper;
import Utils.Color;
import Utils.ImagePainter;
import Utils.ImageReader;

public class TestPainter{
	//@Test
	public void draw() {
		Random r = new Random();
		int width = 640;
		int height = 480;
		Color[][] pixels = new Color[width][height];
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				pixels[i][j] = new Color();
				pixels[i][j].set(r.nextInt(255),r.nextInt(255),r.nextInt(255),r.nextInt(255));
			}
		}
		ImagePainter painter = new ImagePainter(pixels);
		painter.output("test.png");
	}
	
	//@Test
	public void testExpression() {
		GAHelper helper = new GAHelper(10, 10, 10);
		PicGenoType g1 = helper.picGenoType_generator();
		Picture p = new Picture(g1);
		p.express();
	}
	
	@Test
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




