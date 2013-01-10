package main;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ListModel;

import bubble.Bubble;
import bubble.SubdivisionSurface;

import com.sun.org.apache.bcel.internal.generic.NEW;

import graphics.Light;
import graphics.Material;
import graphics.Triangle;
import util.Color;
import util.Vector3D;

public class Scene {
	Color backgroundColor;

	Light ambientLight;
	List<Light> lightList;
	List<Bubble> bubbleList;
	//export from bubblelist, and will be replaced by BSP tree later
	List<Triangle> triangleList;
	
	public static Material testMaterial = new Material();
	public static Material soapilmMaterial = new Material();
	
	public Scene() {
	}

	public void SetupScene() {
		backgroundColor = new Color(100.0/255, 200.0/255, 200.0/255);
		SetupLights();
		SetupMaterials();
		SetupBubbleList();
		triangleList = new ArrayList<>();
		for(Bubble bubble : bubbleList){
			triangleList.addAll(bubble.getTriangles());
		}
		
	}

	private void SetupLights() {
		lightList = new ArrayList<Light>();
		// ambient
		ambientLight = new Light();
		ambientLight.setType(Light.AMBIENT);
		ambientLight.setAmbient(new Color(0.2, 0.2, 0.2, 1));

		// light 1
		Light light1 = new Light();
		light1.setType(Light.SPOT_LIGHT_ALL_DIRECTION);
		light1.setDiffuse(new Color(1, 1, 1, 1));
		light1.setSpecular(new Color(1, 1, 1, 1));
		light1.setPos(new Vector3D(-30, 0, 50));

		lightList.add(light1);
	}

	private void SetupMaterials() {
		testMaterial.setAmbient(new Color(0.3, 0.5, 1, 1));
		testMaterial.setDiffuse(new Color(0.5, 0.7, 1, 1));
		testMaterial.setSpecular(new Color(0.7, 0.7, 1, 1));
		testMaterial.setShineness(3);
		testMaterial.setRfrcIdx(1.0);
	}

	private void SetupBubbleList() {
		bubbleList = new ArrayList<>();
		SubdivisionSurface subface = new SubdivisionSurface(40, 1);

		Bubble bubble = new Bubble(subface.exportToTraigles());
		bubble.setCentral(subface.getCentralPoint());
		bubble.setRadius(subface.getRadius());
		bubbleList.add(bubble);
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Light getAmbientLight() {
		return ambientLight;
	}

	public void setAmbientLight(Light ambientLight) {
		this.ambientLight = ambientLight;
	}

	public static Material getTestMaterial() {
		return testMaterial;
	}

	public static void setTestMaterial(Material testMaterial) {
		Scene.testMaterial = testMaterial;
	}

	public static Material getSoapilmMaterial() {
		return soapilmMaterial;
	}

	public static void setSoapilmMaterial(Material soapilmMaterial) {
		Scene.soapilmMaterial = soapilmMaterial;
	}

	public List<Light> getLightList() {
		return lightList;
	}

	public void setLightList(List<Light> lightList) {
		this.lightList = lightList;
	}

	public List<Bubble> getBubbleList() {
		return bubbleList;
	}

	public void setBubbleList(List<Bubble> bubbleList) {
		this.bubbleList = bubbleList;
	}

	public List<Triangle> getTriangleList() {
		return triangleList;
	}

	public void setTriangleList(List<Triangle> triangleList) {
		this.triangleList = triangleList;
	}

}
