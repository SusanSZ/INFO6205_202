package Utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import GASample.Picture;
//��ͼ��������ļ�����

public class ImagePainter {
	private String outfile = "out.png";
	private int width;
	private int height;
	private Color[][] pixels;
	
	public ImagePainter(Color[][] colors) {
		pixels = colors;
		height = colors[0].length;
		width = colors.length;
	}//���캯��������洢������ARGBֵ�ö�ά���飬�õ�ͼƬ�ĳ���
	
	private BufferedImage get_image() {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				int value = pixels[i][j].to8bit();
				image.setRGB(i, j, value);
			}
		}
		return image;
	}//����ͼ�񣬸�ʽΪBufferedImage
	
	public void output(String filename) {
		File outputfile = new File("./outputimages/" + filename);
		try {
			ImageIO.write(get_image(), "png", outputfile);
		}catch (IOException e) { 
			System.out.println(e.getMessage());
		}
	}//��ͼƬ�������ļ�����
}
