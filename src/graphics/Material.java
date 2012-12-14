package graphics;

import util.Color;

public class Material {
	double rfrcIdx; //refractive index
	Color ambient;
	Color diffuse;
	Color specular;
	double shineness; //ns
	
	public Material(){}
	
	
	
	public double getRfrcIdx() {
		return rfrcIdx;
	}
	public void setRfrcIdx(double rfrcIdx) {
		this.rfrcIdx = rfrcIdx;
	}
	public Color getAmbient() {
		return ambient;
	}
	public void setAmbient(Color ambient) {
		this.ambient = ambient;
	}
	public Color getDiffuse() {
		return diffuse;
	}
	public void setDiffuse(Color diffuse) {
		this.diffuse = diffuse;
	}
	public Color getSpecular() {
		return specular;
	}
	public void setSpecular(Color specular) {
		this.specular = specular;
	}
	public double getShineness() {
		return shineness;
	}
	public void setShineness(double shineness) {
		this.shineness = shineness;
	}
	
	
	
}
