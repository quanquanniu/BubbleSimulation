package bubble;

import graphics.Triangle;

import java.util.List;

import com.sun.org.apache.xerces.internal.impl.dv.xs.YearDV;

import util.Vector3D;

public class Bubble {

	private List<Triangle> triangles;
	private Vector3D central;
	private double radius;
	
	public Bubble(){}
	
	public Bubble(List<Triangle> triangles){
		this.triangles = triangles;
		for(Triangle triangle : this.triangles){
			triangle.setBubble(this);
		}
	}
	
	

	public void Draw(){
		//TODO
	}
	
	public double getThickness(Vector3D pt){
		//compute by pt.y & central.y
		//300nm ~ 2000 nm
		double min = 300;
		double max = 2000;
		double relative = (pt.y - (central.y - radius)) / (radius * 2);
		if(relative < 0) relative = 0;
		if(relative > 1) relative = 1;
		double thickness = (max - min) * relative + min;
		return thickness;
	}
	
	
	public List<Triangle> getTriangles() {
		return triangles;
	}

	public void setTriangles(List<Triangle> triangles) {
		this.triangles = triangles;
	}

	public Vector3D getCentral() {
		return central;
	}

	public void setCentral(Vector3D central) {
		this.central = central;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	
	
}
