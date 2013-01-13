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
import sun.nio.cs.ext.TIS_620;
import util.Color;
import util.GLImage;
import util.Vector3D;

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
	private BubbleRayTracer rayTracer;
	private GLImage glImage;
	private Vector3D eyePt;
	private int width;
	private int height;

	public SimpleRayTracer(GLCapabilities capabilities, int width, int height) {
		this.width = width;
		this.height = height;
		glImage = new GLImage(width, height);
		rayTracer = new BubbleRayTracer();
		eyePt = new Vector3D(0, 0, 100);

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

		double frameZ = 50;
		for (int j = 0; j < height; j++)
		for (int i = 0; i < width; i++) {
		//	int	j = height / 2 - 2;
		//	int	i = width / 2 ;
				double x = (eyePt.z - frameZ) * (i - width / 2) / eyePt.z / 2 ;
				double y = (eyePt.z - frameZ) * (-j + height / 2) / eyePt.z / 2;
				Vector3D framePt = new Vector3D(x, y, frameZ);
				Vector3D direction = Vector3D.Substract(framePt, eyePt);
				//Color color = rayTracer.Trace(framePt,
				//		Vector3D.Normalize(direction), 0.1, 100, eyePt, 1);*/
				Color color = rayTracer.Trace(framePt, Vector3D.Normalize(direction), 0.1, 2000, 1);
				
				glImage.setPixel(i, j, color.ToColor255());
				
		}

		ByteBuffer colorValues = glImage.getBuffer();
		colorValues.rewind();

		GL gl = drawable.getGL();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glRasterPos2i(0, 0);
		gl.glDrawPixels(width, height, GL.GL_RGB, GL.GL_UNSIGNED_BYTE,
				colorValues);
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
		display();
	}

	public static void main(String[] args) {
		int WIDTH = 600;
		int HEIGHT = 400;
		GLCapabilities capabilities = createGLCapabilities();
		SimpleRayTracer canvas = new SimpleRayTracer(capabilities, WIDTH,
				HEIGHT);
		JFrame frame = new JFrame("Mini JOGL Demo (breed)");
		frame.getContentPane().add(canvas, BorderLayout.CENTER);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		canvas.requestFocus();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}