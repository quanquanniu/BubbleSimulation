package graphics;

import java.util.List;

import util.Vector3D;

public class BSPNode {
	public static int X_SPLIT = 1;
	public static int Y_SPLIT = 2;
	public static int Z_ZPLIT = 3;
	
	int type;
	Box box;
	BSPNode child1, child2;// child1 < cutting < child2
	double cuttingSurface;
	
	public BSPNode(int type, Box box){
		this.type = type;
		this.box = box;
	}
	
	public boolean hit(Vector3D start, Vector3D direction, double mint, double maxt){
		return box.hit(start, direction, mint, maxt);
	}
	
	
}
