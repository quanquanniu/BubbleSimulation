package main;

import graphics.HitRecord;
import graphics.Light;
import graphics.Material;
import graphics.Triangle;

import java.util.List;

import com.sun.org.apache.xerces.internal.impl.dtd.models.DFAContentModel;

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
					Scene.testMaterial.getAmbient());
			Triangle surface = (Triangle)hitRecord.getSurface();
			
			for(Light light : scene.getLightList()){
				Color diffuse = getDiffuseColor(hitRecord.getHitPt(), light, surface.getNormal(), Scene.testMaterial);
				if(diffuse != null){
					color = Color.Add(color, diffuse);
				}
			}
			
			color.ToColor255().Print();
		}
		return color;
	}
	
	private Color getDiffuseColor(Vector3D pt, Light light, Vector3D normal, Material material){
		Color diffuse = null;
		Vector3D lightDirection ;
		if(light.getType() == Light.SPOT_LIGHT_ALL_DIRECTION){
			lightDirection = Vector3D.Substract(light.getPos(), pt);
			double cos = Vector3D.cos(lightDirection, normal);
			
			if(cos > 0){
				diffuse = Color.DotMultiply(light.getDiffuse(), material.getDiffuse());
				diffuse.Scale(cos);
				System.out.println("cos = " + cos);
			}
		}
		return diffuse;
	}

	private Color getSpecularColor(Vector3D pt, Light light, Vector3D normal, Material material){
		Color specular = null;
		return null;
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
