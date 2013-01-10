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
			double tmax, Vector3D eyePt) {
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
				Color specular = getSpecularColor(hitRecord.getHitPt(), light, surface.getNormal(), Scene.testMaterial, eyePt);
				if(specular != null){
					color = Color.Add(color, specular);
				}
			}
  			color.ToColor255().Print();
		}
		return color;
	}
	
	private Color getDiffuseColor(Vector3D pt, Light light, Vector3D normal, Material material){
		Color diffuse = null;
		if(light.getType() == Light.SPOT_LIGHT_ALL_DIRECTION){
			Vector3D toLightDirection = Vector3D.Substract(light.getPos(), pt);
			double cos = Vector3D.cos(toLightDirection, normal);
			
			if(cos > 0){
				diffuse = Color.DotMultiply(light.getDiffuse(), material.getDiffuse());
				diffuse.Scale(cos);
				System.out.println("cos = " + cos);
			}
		}
		return diffuse;
	}

	private Color getSpecularColor(Vector3D pt, Light light, Vector3D normal, Material material, Vector3D eyePt){
		Color specular = null;
		if(light.getType() == Light.SPOT_LIGHT_ALL_DIRECTION){
			Vector3D toLightDirection = Vector3D.Substract(light.getPos(), pt);
			Vector3D toEyeDirection = Vector3D.Substract(eyePt, pt);
			Vector3D H = Vector3D.Add(toLightDirection, toEyeDirection);
			double cos = Vector3D.cos(normal, H);
			if(cos > 0){
				System.out.println("\t\t\t\tcos = " + Math.pow(cos, material.getShineness()));
				specular = Color.DotMultiply(light.getSpecular(), material.getSpecular());
				specular.Scale(Math.pow(cos, material.getShineness()));
			}
		}
		return specular;
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
