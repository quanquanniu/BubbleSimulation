package util;

public class Color {
	public double r;
	public double g;
	public double b;
	public double a;
	
	public Color(){}
	
	public Color(double r, double g, double b){
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public Color(double r, double g, double b, double a){
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public Color ToColor255(){
		int R = (int)(r * 255);
		int G = (int)(g * 255);
		int B = (int)(b * 255);
		Color color = new Color(r, g, b);
		return color;
	}
	
	
}
