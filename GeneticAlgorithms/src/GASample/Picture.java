package GASample;

import GAFrame.*;

public class Picture extends Individual implements Comparable<Picture>{

	public Picture(PicGenoType g) {
		super(g);
	}
	
	/** export this picture to a .jpg file
	 * 
	 */
	public void toFile() {
		//TODO implement toFile function
	}
	
	//TODO implement compare method
	public int compareTo(Picture that) {
		return 0;
	}


}
