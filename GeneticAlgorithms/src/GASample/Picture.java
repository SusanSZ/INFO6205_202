package GASample;

import GAFrame.*;
import Utils.ImagePainter;

public class Picture extends Individual implements Comparable<Picture>{

	public Picture(PicGenoType g) {
		super(g);
	}
	
	/**draw this image to a png file
	 * 
	 * @param filename, the output filename, can be anything similar to sample.png
	 */
	public void toFile(String filename) {
		PicPhenoType picpheno = (PicPhenoType)pheno;
		ImagePainter painter = new ImagePainter(picpheno.getcolors());
		painter.output(filename);
	}
	
	//TODO implement compare method
	public int compareTo(Picture that) {
		return 0;
	}


}
