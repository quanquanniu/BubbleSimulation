package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Image {
	BufferedImage srcImg;
	int width;
	int height;
	double[][] data;
	
	public Image(){}
	public void OpenImg(String path) {
		try {
			srcImg = ImageIO.read(new File(path));
			width = srcImg.getWidth();
			height = srcImg.getHeight();
			System.out.println("width: " + width + "   height:" + height);
			data = new double[width*height][3];
			for(int i = 0; i < width; i++)
				for(int j = 0; j < height; j++){
					int index = j * width + i;
					 int pixel = srcImg.getRGB(i, j);
					 	int r = (pixel & 0xff00) >> 16;
						int g = (pixel & 0xff00) >> 8;//g
						int b = (pixel & 0xff);//b
						data[index][0] = ((double) r) / 255;
						data[index][1] = ((double) g) / 255;
						data[index][2] = ((double) b) / 255;
						data[index][0] += 0.3;
						if(data[index][0] > 1) data[index][0] = 1;
						data[index][1] += 0.1;
						if(data[index][1] > 1) data[index][1] = 1;
						data[index][2] += 0.1;
						if(data[index][2] > 1) data[index][2] = 1;
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void OpenImg(String path, int left, int right, int up, int down){
		try {
			srcImg = ImageIO.read(new File(path));
			width = right - left;
			height = down - up;
			System.out.println("width: " + width + "   height:" + height);
			data = new double[width*height][3];
			for(int i = left; i < right; i++)
				for(int j = up; j < down; j++){
					int index = (j-up) * width + i - left;
					 int pixel = srcImg.getRGB(i, j);
					 int r = (pixel & 0xff00) >> 16;
						int g = (pixel & 0xff00) >> 8;//g
						int b = (pixel & 0xff);//b
						data[index][0] = ((double) r) / 255;
						data[index][1] = ((double) g) / 255;
						data[index][2] = ((double) b) / 255;
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Color getPixelColor(int x, int y){
		int index = y * width + x;
		return new Color(data[index][0], data[index][1], data[index][2]);
	}
	
	public Color getPixelColorRelPos(double x, double y){ // 0 < x,y < 1
		int imgX = (int)(x * width);
		int imgY = (int)(y * height);
		if(imgX < 0) imgX = 0;
		if(imgX >= width) imgX = width - 1;
		if(imgY < 0) imgY = 0;
		if(imgY >= height) imgY = height -1;
		return getPixelColor(imgX, imgY);
	}
	
	
	
	
	
	public BufferedImage getSrcImg() {
		return srcImg;
	}
	public void setSrcImg(BufferedImage srcImg) {
		this.srcImg = srcImg;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public double[][] getData() {
		return data;
	}
	public void setData(double[][] data) {
		this.data = data;
	}

	
}
