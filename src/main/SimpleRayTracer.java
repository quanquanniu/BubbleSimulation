package main;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

import sun.awt.GlobalCursorManager;

import bubble.SubdivisionSurface;

import com.sun.opengl.util.BufferUtil;
import com.sun.opengl.util.FPSAnimator;
import com.sun.xml.internal.ws.util.ByteArrayBuffer;

/**
 * A minimal JOGL demo.
 * 
 * @author <a href="mailto:kain@land-of-kain.de">Kai Ruhl</a>
 * @since 26 Feb 2009
 */
public class SimpleRayTracer extends GLCanvas implements GLEventListener,
		MouseMotionListener {

	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private GLU glu;
	private Point mousePoint;

	public SimpleRayTracer(GLCapabilities capabilities, int width, int height) {
		addGLEventListener(this);
		addMouseMotionListener(this);
	}

	/**
	 * @return Some standard GL capabilities (with alpha).
	 */
	private static GLCapabilities createGLCapabilities() {
		GLCapabilities capabilities = new GLCapabilities();
		capabilities.setRedBits(8);
		capabilities.setBlueBits(8);
		capabilities.setGreenBits(8);
		capabilities.setAlphaBits(8);
		return capabilities;
	}

	/**
	 * Sets up the screen.
	 * 
	 * @see javax.media.opengl.GLEventListener#init(javax.media.opengl.GLAutoDrawable)
	 */
	public void init(GLAutoDrawable drawable) {
		drawable.setGL(new DebugGL(drawable.getGL()));
		final GL gl = drawable.getGL();

		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glShadeModel(GL.GL_FLAT);
		gl.glPixelStorei(GL.GL_UNPACK_ALIGNMENT, 1);
		glu = new GLU();
	}

	/**
	 * The only method that you should implement by yourself.
	 * 
	 * @see javax.media.opengl.GLEventListener#display(javax.media.opengl.GLAutoDrawable)
	 */
	public void display(GLAutoDrawable drawable) {
		int width = 800;
		int height = 500;
		ByteBuffer colorValues = BufferUtil.newByteBuffer(height * width * 3);
				
		for (int i = 0; i < height * width * 3; i++) {
			if(i % (3*width) < width) colorValues.put((byte)255.0);
			else colorValues.put((byte)100.0);
		}
		colorValues.rewind();

		 GL gl = drawable.getGL();
		    
		    gl.glClear(GL.GL_COLOR_BUFFER_BIT);

		    if (mousePoint != null)
		    {
		      int screeny = height - (int)mousePoint.getY();
		      gl.glRasterPos2i(mousePoint.x, screeny);
		      gl.glPixelZoom(1.0f, 1.0f);
		      gl.glCopyPixels(0, 0, width, height, GL.GL_COLOR);
		    }
		    else gl.glRasterPos2i(0, 0);
		    
		    gl.glDrawPixels(width, height, GL.GL_RGB,
		        GL.GL_UNSIGNED_BYTE, colorValues);

		    gl.glFlush();
	}

	
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		 GL gl = drawable.getGL();
		    
		    gl.glViewport(0, 0, width, height);
		   // height = h;
		    gl.glMatrixMode(GL.GL_PROJECTION);
		    gl.glLoadIdentity();
		    glu.gluOrtho2D(0.0, (double) width, 0.0, (double) height);
		    gl.glMatrixMode(GL.GL_MODELVIEW);
		    gl.glLoadIdentity();

	}

	
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
			boolean deviceChanged) {
		throw new UnsupportedOperationException(
				"Changing display is not supported.");
	}

	public void mouseMoved(MouseEvent mouse) {
		mousePoint = mouse.getPoint();
		//System.out.println("mouse x = " + mousePoint.x);
		display();
	}

	
	public static void main(String[] args) {
		GLCapabilities capabilities = createGLCapabilities();
		SimpleRayTracer canvas = new SimpleRayTracer(capabilities, 800, 500);
		JFrame frame = new JFrame("Mini JOGL Demo (breed)");
		frame.getContentPane().add(canvas, BorderLayout.CENTER);
		frame.setSize(800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		canvas.requestFocus();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}