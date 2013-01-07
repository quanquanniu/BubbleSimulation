package util;

import java.nio.ByteBuffer;

import com.sun.opengl.util.BufferUtil;
import com.sun.xml.internal.ws.util.ByteArrayBuffer;

public class GLImage {

	private int width;
	private int height;
	private byte[] pixels;

	public GLImage() {
	}

	public GLImage(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new byte[width * height * 3];
	}

	// left to right, bottom to top
	public void setPixel(int w, int h, Color pixel) {
		int index = (width * 3) * (height - h -1) + w * 3;
		pixels[index] = (byte) pixel.r;
		pixels[index + 1] = (byte) pixel.g;
		pixels[index + 2] = (byte) pixel.b;
	}
	
	public ByteBuffer getBuffer(){
		ByteBuffer colorValues = BufferUtil.newByteBuffer(height * width * 3);
		colorValues.put(pixels);
		return colorValues;
	}
}
