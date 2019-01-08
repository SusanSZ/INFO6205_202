package GASample;

import GAFrame.PhenoType;
import Utils.Color;
//存储被表示的个体信息——图片长宽即各像素color
public class PicPhenoType extends PhenoType {
	private int pixels_y;
	private int pixels_x;
	private Color[][] colors;
	
	public PicPhenoType(Color[][] colors) {
		this.colors = colors;
		pixels_x = colors.length;
		pixels_y = colors[0].length;
	}
	
	public Color[][] getcolors(){
		return colors;
	}
}
