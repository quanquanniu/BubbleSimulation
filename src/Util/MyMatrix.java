package util;

import Jama.Matrix;

public class MyMatrix {

	public MyMatrix(){}
	
	
	/*
	 * solve Ax=b, where A->3*3
	 */
	public static Vector3D SolveEquation(Matrix A, Vector3D b){
		double a, r, t;
		double aDet = A.det();
		if(aDet == 0) return null;
		
		double[][] aMatArray = {{b.x, A.get(0, 1), A.get(0,2)}, 
								{b.y, A.get(1, 1), A.get(1,2)}, 
								{b.z, A.get(2, 1), A.get(2,2)}};
		Matrix aMat = new Matrix(aMatArray, 3, 3);
		
		double[][] rMatArray = {{A.get(0,0), b.x, A.get(0, 2)},
								{A.get(1,0), b.y, A.get(1, 2)},
								{A.get(2,0), b.z, A.get(2, 2)}};
		Matrix rMat = new Matrix(rMatArray, 3, 3);
		
		double[][] tMatArray = {{A.get(0, 0), A.get(0, 1), b.x},
								{A.get(1, 0), A.get(1, 1), b.y},
								{A.get(2, 0), A.get(2, 1), b.z}};
		Matrix tMat = new Matrix(tMatArray, 3, 3);
		
		a = aMat.det() / aDet;//determinant
		r = rMat.det() / aDet;
		t = tMat.det() / aDet;
		return new Vector3D(a, r, t);
	}
}
