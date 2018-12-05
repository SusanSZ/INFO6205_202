package Utils;

import java.io.File;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

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
	    
	    BufferedImage resized = resize(img, 280, 210);
	    
	    int w = resized.getWidth();
	    int h = resized.getHeight();
	    int[][] targetPixel = new int[w][h];

	    for( int i = 0; i < w; i++ )
	        for( int j = 0; j < h; j++ )
	            targetPixel[i][j] = img.getRGB( i, j );
	    return targetPixel;
	}
	
	public static Color[][] getTargetColorArray(String targetPath, int width, int height) throws IOException{
    	File f = new File(targetPath);
    	BufferedImage img = ImageIO.read(f);
	    BufferedImage resized = resize(img, width, height);
	    int w = resized.getWidth();
	    int h = resized.getHeight();
	    Color[][] targetcolors = new Color[width][height];
	    for( int i = 0; i < w; i++ ) {
	        for(int j = 0; j < h; j++ ) {
	            int pixel = img.getRGB( i, j );
	            int alpha = (pixel & 0xff000000) >>> 24;
	    		int red = (pixel & 0x00ff0000) >> 16;
	    		int green = (pixel & 0x0000ff00) >> 8;
	    		int blue = pixel & 0x000000ff;
	            targetcolors[i][j].set(alpha, red, green, blue);
	        }
	    }
	    return targetcolors;
	}
	
	private static BufferedImage resize(BufferedImage img, int width, int height) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
}
