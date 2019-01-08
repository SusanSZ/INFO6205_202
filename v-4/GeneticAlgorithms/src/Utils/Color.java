package Utils;

import java.util.Random;
//������ɫ��ز���
//��������ɫ�ںϺ������ɫ����������ɫ���HSV����
public class Color {
	private int alpha;
	private int red;
	private int green;
	private int blue;

	public int getRed() {
		return red;
	}
	
	public int getGreen() {
		return green;
	}
	
	public int getAlpha() {
		return alpha;
	}
	
	public int getBlue() {
		return blue;
	}
	
	public void setBlue(int b) {
		blue = b;
	}
	
	public void setAlpha(int a) {
		alpha = a;
	}
	
	public void setRed(int r) {
		red = r;
	}
	
	public void setGreen(int g){
		green = g;
	}
	
	public void set(int alpha, int red, int green, int blue) {
		if(alpha > 255 || alpha < 0 || red > 255 || red < 0 || green > 255 || green < 0 || blue > 255 || blue < 0) {
			System.out.println(alpha);
			System.out.println(red);
			System.out.println(green);
			System.out.println(blue);
			throw new IllegalArgumentException("color out of range, color should between (0 - 255)");
		}
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
		double a1 = background.alpha * 1.0 / 255;
		double a2 = color.alpha * 1.0 / 255;
		double new_alpha = (1 - (1 - a1) * (1 - a2));
		int new_red = (int) (mixColor(background.red,a1,color.red,a2) / new_alpha);
		int new_green = (int) (mixColor(background.green,a1,color.green,a2) / new_alpha);
		int new_blue = (int) (mixColor(background.blue,a1,color.blue,a2) / new_alpha);
		new_color.set((int)(new_alpha*255), new_red, new_green, new_blue);
		return new_color;
	}//����ǰ��ɫ���¼������ɫ�ں�Ϊ����ɫ���ں�ʱ����ɫ���Ե�Ȩ����͸�����й�
	
	public static  int HSVdist(Color c1, Color c2) {
		int[] hsv1 = c1.toHSV();
		int[] hsv2 = c2.toHSV();
		double dh = Math.min(Math.abs(hsv1[0] - hsv2[0]), 360 - Math.abs(hsv1[0] - hsv2[0])) / 180.0;
		double ds = Math.abs(hsv1[1] - hsv2[1]) / 100;
		double dv = Math.abs(hsv1[2] - hsv2[2]) / 255;
		double distance = Math.sqrt(3 * dh * dh + 0.5 * ds * ds + 0.5 * dv * dv);
		int dis_percentage = (int) (distance / 2 * 10000);
		return dis_percentage;
	}//����������ɫ֮���HSV����
	
	public int[] toHSV() {
		int[] hsv = new int[3];
		double r = red * 1.0 / 255;
		double g = green * 1.0 / 255;
		double b = blue * 1.0 / 255;
		double cmax = Math.max(Math.max(r, g),b);
		double cmin = Math.min(Math.min(r, g),b);
		double delta = cmax - cmin;
		if(delta == 0) hsv[0] = 0;
		else if (cmax == r) hsv[0] = (int) (60 * (g - b) / delta);
		else if (cmax == g) hsv[0] = (int) (60 * (b - r)/delta + 120);
		else hsv[0] = (int) (60 * (r - g)/delta + 240);
		if(cmax == 0) hsv[1] = 0;
		else hsv[1] =  (int) ((delta/cmax)*100);
		hsv[2] = (int) (cmax * 255);;
		return hsv;
	}//��RGB��ʽ����ɫת��ΪHSV��ʽ
	
	public static Color avgColor(Color c1, Color c2) {
		Color new_color = new Color();
		new_color.set(randomavg(c1.alpha,c2.alpha), randomavg(c1.red,c2.red), randomavg(c1.green,c2.green), randomavg(c1.blue,c2.blue));
		return new_color;
	}//����������ɫ��ƽ��ֵ����ƽ����ɫ��RGBA�ĸ�ֵ�ֱ�Ϊԭ����������ɫ��Ӧֵ֮������һ����ֵ����Rƽ�� ΪR1 ��R2 ֮��������
	
	private static int randomavg(int c1, int c2) {
		Random r = new Random();
		return Math.min(c1, c2) + r.nextInt(Math.abs(c1 - c2)+1);
	}//���ش�c1 c2֮��һ�������
	
	private static double mixColor(int c1, double a1, int c2, double a2) {
		return c1 * a1 + c2 * a2 * (1 - a1); 
	}//
	
	public Color copy() {
		Color color = new Color();
		color.set(alpha, red, green, blue);
		return color;
	}
}
