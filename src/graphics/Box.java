package graphics;

import Util.Vector3D;

public class Box {
	private Vector3D minPoint;
	private Vector3D maxPoint;
	
	public Box(){}
	
	public Box(Vector3D minPoint, Vector3D maxPoint){
		this.minPoint = minPoint;
		this.maxPoint = maxPoint;
	}
}
