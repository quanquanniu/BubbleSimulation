package bubble;

import graphics.Triangle;

import java.util.List;

import util.Vector3D;

public class Bubble {

	private List<Triangle> triangles;
	private Vector3D central;
	private double radius;
	
	public Bubble(){}
	
	public Bubble(List<Triangle> triangles){
		this.triangles = triangles;
	}

	public void Draw(){
		//TODO
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
