package UnitTests;

import java.util.Random;

import org.junit.*;

import Utils.Color;
import Utils.ImagePainter;

public class TestPainter{
	@Test
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
}