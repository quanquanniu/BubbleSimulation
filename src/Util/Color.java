package util;

import com.sun.swing.internal.plaf.basic.resources.basic;

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
	
	public Color Clone(){
		return new Color(r, g, b, a);
	}
	
	public Color ToColor255(){
		int R = (int)(r * 255);
		int G = (int)(g * 255);
		int B = (int)(b * 255);
		Color color = new Color(R, G, B);
		return color;
	}
	public void Scale(double scale){
		r = r * scale;
		g = g * scale;
		b = b * scale;
		if(r > 1) r = 1;
		if(g > 1) g = 1;
		if(b > 1) b = 1;
	}
	public static Color DotMultiply(Color A, Color B){
		return new Color(A.r * B.r, A.g * B.g, A.b * B.b);
	}
	
	public static Color Add(Color A, Color B){
		double sumR = A.r + B.r;
		double sumG = A.g + B.g;
		double sumB = A.b + B.b;
		if(sumR > 1) sumR = 1;
		if(sumG > 1) sumG = 1;
		if(sumB > 1) sumB = 1;
		return new Color(sumR, sumG, sumB);
	}
	public void Print(String description){
		System.out.println(description + " color = " + r + "\t" + g + "\t" + b);
	}
}
