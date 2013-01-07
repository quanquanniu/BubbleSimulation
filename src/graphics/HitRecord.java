package graphics;

import util.Vector3D;

public class HitRecord {
	private Vector3D hitPt;
	private Surface surface;
	private double distance;
	
	public HitRecord(){}

	public HitRecord(Vector3D hitPt, Surface surface, double distance){
		this.hitPt = hitPt;
		this.surface = surface;
		this.distance = distance;
	}
	
	public Vector3D getHitPt() {
		return hitPt;
	}

	public void setHitPt(Vector3D hitPt) {
		this.hitPt = hitPt;
	}

	public Surface getSurface() {
		return surface;
	}

	public void setSurface(Surface surface) {
		this.surface = surface;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	
}	
