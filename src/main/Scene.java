package main;

import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;

import graphics.Light;
import graphics.Material;
import util.Color;
import util.Vector3D;

public class Scene {
	Color backgroundColor;
	
	Light ambientLight;
	List<Light> lightList;
	
	public static Material testMaterial = new Material();
	public static Material soapilmMaterial = new Material();
	
	public void Scene(){}
	
	public void SetupScene(){
		backgroundColor = new Color(0,0,0);
		SetupLights();
		SetupMaterials();
		
	}
	
	private void SetupLights(){
		//ambient
		ambientLight = new Light();
		ambientLight.setType(Light.AMBIENT);
		ambientLight.setAmbient(new Color(0.2, 0.2, 0.2, 1));
		
		//light 1
		Light light1 = new Light();
		light1.setType(Light.SPOT_LIGHT_ALL_DIRECTION);
		light1.setDiffuse(new Color(0.1, 0.1, 0.1, 1));
		light1.setSpecular(new Color(0.2, 0.0, 0.0, 1));
		light1.setPos(new Vector3D(-30, 0, 30));
		
		lightList.add(light1);
	}
	
	private void SetupMaterials(){
		testMaterial.setAmbient(new Color(0.3, 0.5, 1, 1));
		testMaterial.setDiffuse(new Color(0.5, 0.7, 1, 1));
		testMaterial.setSpecular(new Color(0.5, 0.7, 1, 1));
		testMaterial.setShineness(0.5);
		testMaterial.setRfrcIdx(1.0);
	}
}
