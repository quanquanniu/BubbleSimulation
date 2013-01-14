package graphics;

import util.Color;

public class Material {
	double rfrcIdx; //refractive index
	double R0;
	
	
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
		double ratio = (rfrcIdx - 1) / (rfrcIdx + 1);
		R0 = Math.pow(ratio, 2);
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
	public double getR0() {
		return R0;
	}
	public void setR0(double r0) {
		R0 = r0;
	}
	
	
	
}
