package Utils;

import java.io.File;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
//从文件中读取图片并改变其大小
public class ImageReader {
	
	public int[][] getTargetPixel(String path) throws IOException{
	    BufferedImage img = null;
	    File f = null;
	    
	    try{
	    	f = new File(path);
	    	img = ImageIO.read(f);
	    }catch(IOException e){
	    	System.out.println(e);
	    }
	    
	    BufferedImage resized = resize(img, 200);
	    
	    int w = resized.getWidth();
	    int h = resized.getHeight();
	    int[][] targetPixel = new int[w][h];

	    for( int i = 0; i < w; i++ )
	        for( int j = 0; j < h; j++ )
	            targetPixel[i][j] = img.getRGB( i, j );
	    return targetPixel;
	}//打开目标文件，转化为200像素大小，返回存储RGB值的二维数组
	
	public static Color[][] getTargetColorArray(String targetPath) throws IOException{
    	File f = new File(targetPath);
    	BufferedImage img = ImageIO.read(f);
	    int w = img.getWidth();
	    int h = img.getHeight();
	    Color[][] targetcolors = new Color[w][h];
	    for( int i = 0; i < w; i++ ) {
	        for(int j = 0; j < h; j++ ) {
	            int pixel = img.getRGB( i, j );
	            int alpha = (pixel & 0xff000000) >>> 24;
	    		int red = (pixel & 0x00ff0000) >> 16;
	    		int green = (pixel & 0x0000ff00) >> 8;
	    		int blue = pixel & 0x000000ff;
	            targetcolors[i][j] = new Color();
	            targetcolors[i][j].set(alpha, red, green, blue);
	        }
	    }
	    return targetcolors;
	}//打开目标文件，返回存储各像素color信息的二维数组
	
	private static BufferedImage resize(BufferedImage img, int width) {
		Image temp = img.getScaledInstance(width, -1, Image.SCALE_SMOOTH);
		int height = temp.getHeight(null);
        BufferedImage resized = new BufferedImage(width, height, img.getType());
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(temp, 0, 0, width, height, null);
        g2d.dispose();
        return resized;
    }//将图片压缩为宽200像素
}
