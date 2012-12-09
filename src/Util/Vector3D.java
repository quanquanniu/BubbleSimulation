package util;

import javax.swing.text.Position;

public class Vector3D {
	public double x;
	public double y;
	public double z;
	
	public Vector3D(){
		this.x = 0;
		this.y = 0; 
		this.z = 0;
	}
	
	public Vector3D(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static Vector3D LinearSum(Vector3D[] vecs, Double[] coes){
		Vector3D newVec = new Vector3D();
		for(int i = 0; i < vecs.length; i++){
			newVec.x += vecs[i].x * coes[i];
			newVec.y += vecs[i].y * coes[i];
			newVec.z += vecs[i].z * coes[i];
		}
		return newVec;
	}
	
	
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
	
}
