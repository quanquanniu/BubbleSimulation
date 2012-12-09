package graphics;

import util.Vector3D;

public class Box extends Surface{
	private Vector3D minPoint;
	private Vector3D maxPoint;
	
	public Box(){}
	
	public Box(Vector3D minPoint, Vector3D maxPoint){
		this.minPoint = minPoint;
		this.maxPoint = maxPoint;
	}
}
