package bubble;

import graphics.Triangle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import com.sun.xml.internal.bind.v2.util.EditDistance;


import Util.Vector3D;
import javax.media.opengl.GL;
//http://dspace.xmu.edu.cn/dspace/bitstream/2288/6469/1/Loop%E7%BB%86%E5%88%86%E6%9B%B2%E9%9D%A2%E7%B2%BE%E7%A1%AE%E6%B1%82%E5%80%BC%E6%96%B0%E5%85%AC%E5%BC%8F.pdf
public class SubdivisionSurface {//Loop
   private List<Vector3D> verticles;
   private List<SubTriangle> triangles;
   
   private static final double X = 0.5257311;
   private static final double Z = 0.8506508;
   
   public SubdivisionSurface(){}
   
   public SubdivisionSurface(int radius, int divisionDepth){//
	   InitRegPoly20(radius);
   }
   
   private void InitRegPoly20(int radius){
	   double x = radius * X;
	   double z = radius * Z;
	   Vector3D[] vec = {new Vector3D(-x, 0, z), new Vector3D(x, 0, z), new Vector3D(-x, 0, -z), new Vector3D(x,0,-z),
			   new Vector3D(0,z,x), new Vector3D(0,z,-x), new Vector3D(0,-z,x), new Vector3D(0,-z,-x),
			   new Vector3D(z,x,0), new Vector3D(-z,x,0), new Vector3D(z,-x,0), new Vector3D(-z,-x,0)};
	   verticles = Arrays.asList(vec);
	   
	   SubTriangle[] tri = {new SubTriangle(1,4,0), new SubTriangle(4,9,0), new SubTriangle(4,5,9),new SubTriangle(8,5,4), new SubTriangle(1,8,4),
			   new SubTriangle(1,10,8), new SubTriangle(10,3,8), new SubTriangle(8,3,5), new SubTriangle(3,2,5), new SubTriangle(3,7,2),
			   new SubTriangle(3,10,7), new SubTriangle(10,6,7), new SubTriangle(6,11,7), new SubTriangle(6,0,11), new SubTriangle(6,1,0),
			   new SubTriangle(10,1,6), new SubTriangle(11,0,9), new SubTriangle(2,11,9), new SubTriangle(5,2,9), new SubTriangle(11,2,7)};
	   triangles = Arrays.asList(tri);
   }
   
   public List<Triangle> exportToTraigles(GL gl){
	   return null;
   }
   
   public void Draw(GL gl){
	   gl.glBegin(GL.GL_TRIANGLES);
	   /*color info here*/
	   gl.glColor3f(0.9f, 0.5f, 0.2f);
	   for(SubTriangle triangle : triangles){
		   gl.glVertex3d(verticles.get(triangle.index[0]).x, verticles.get(triangle.index[0]).y, verticles.get(triangle.index[0]).z);
		   gl.glVertex3d(verticles.get(triangle.index[1]).x, verticles.get(triangle.index[1]).y, verticles.get(triangle.index[1]).z);
		   gl.glVertex3d(verticles.get(triangle.index[2]).x, verticles.get(triangle.index[2]).y, verticles.get(triangle.index[2]).z);
		   
	   }
	   gl.glEnd();
   }
   
   private class SubTriangle{
	   public int[] index = new int[3];//verticle index
	   
	   public SubTriangle(){}
	   
	   public SubTriangle(int x, int y, int z){//index
		   index[0] = x;
		   index[1] = y;
		   index[2] = z;
	   }
   }
   
   
}

	 
 
 
