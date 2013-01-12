package main;

import util.Color;
import util.Vector3D;

public class BubbleRayTracer {
	private Scene scene;

	public BubbleRayTracer() {
		scene = new Scene();
		scene.SetupScene();
	}
	
	public Color Trace(Vector3D start, Vector3D direction, double tmin,
			double tmax){
		Color color;
		color = scene.skyBox.HitColor(start, direction);
		return color;
	}
}
