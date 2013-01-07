package main;

import graphics.HitRecord;
import graphics.Light;
import graphics.Triangle;

import java.util.List;

import util.Color;
import util.Vector3D;

public class RayTracer {
	private Scene scene;

	public RayTracer() {
		scene = new Scene();
		scene.SetupScene();
	}

	public Color Trace(Vector3D start, Vector3D direction, double tmin,
			double tmax) {
		Color color = scene.getBackgroundColor();
		// is hit
		HitRecord hitRecord = getHitRecord(start, direction, tmin, tmax);
		if (hitRecord != null) {
			color = Color.DotMultiply(scene.getAmbientLight().getAmbient(),
					scene.getTestMaterial().getAmbient());
		}
		return color;
	}

	private HitRecord getHitRecord(Vector3D start, Vector3D direction,
			double tmin, double tmax) {
		HitRecord record = null;
		for (Triangle triangle : scene.getTriangleList()) {
			if (triangle.hit(start, direction, tmin, tmax) == true) {
				HitRecord hitRecord = triangle.hitNearestPoint(start,
						direction, tmin, tmax);
				if (record == null)
					record = hitRecord;
				else if (hitRecord.getDistance() < record.getDistance())
					record = hitRecord;
			}
		}
		return record;
	}
}
