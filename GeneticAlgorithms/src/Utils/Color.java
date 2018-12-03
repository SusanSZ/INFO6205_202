package Utils;

public class Color {
	private int alpha;
	private int red;
	private int green;
	private int blue;
	
	public void set(int alpha, int red, int green, int blue) {
		if(alpha > 255 || alpha < 0 || red > 255 || red < 0 || green > 255 || green < 0 || blue > 255 || blue < 0)
			throw new IllegalArgumentException("color out of range, color should between (0 - 255)");
		this.alpha = alpha;
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public int to8bit() {
		return alpha << 24|red<<16|green<<8|blue;
	}
	
	/**
	 * 
	 * @param background: the background color
	 * @param color: the color to be added
	 * @return the final color
	 */
	public static Color addColorToBackground(Color background, Color color) {
		Color new_color = new Color();
		int new_alpha = 1 - (1 - background.alpha) * (1 - color.alpha);
		int new_red = mixColor(background.red,background.alpha,color.red,color.alpha);
		int new_green = mixColor(background.green,background.alpha,color.green,color.alpha);
		int new_blue = mixColor(background.blue,background.alpha,color.blue,color.alpha);
		new_color.set(new_alpha, new_red, new_green, new_blue);
		return new_color;
	}
	
	
	public static Color avgColor(Color c1, Color c2) {
		Color new_color = new Color();
		new_color.set((c1.alpha+c2.alpha)/2, (c1.red+c2.red)/2, (c1.green+c2.green)/2, (c1.blue+c2.blue)/2);
		return new_color;
	}
	
	private static int mixColor(int c1, int a1, int c2, int a2) {
		return (c1 * a1 + c2 * a2 - c1 * a1 * a2) / (a1 - a1 * a2 + a2); 
	}
}
