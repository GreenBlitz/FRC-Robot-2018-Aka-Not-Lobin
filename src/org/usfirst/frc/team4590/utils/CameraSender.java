package org.usfirst.frc.team4590.utils;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class CameraSender {

	private int m_srcWidth, m_srcHeight, m_outWidth, m_outHeight, m_cameraFPS;

	/**
	 * Default constructor - its streaming values are:
	 * <p>
	 * Source Width => 640 Source Height => 480 Output Width => 320 Output
	 * Height => 240 FPS => 30
	 */
	public CameraSender() {
		this(640, 480, 320, 240, 30);
	}

	public CameraSender(int wSrc, int hSrc, int wOut, int hOut, int cameraFPS) {
		m_srcWidth = wSrc;
		m_srcHeight = hSrc;
		m_outWidth = wOut;
		m_outHeight = hOut;
		m_cameraFPS = cameraFPS;
	}

	public void setOutput(int width, int height) {
		m_outWidth = width;
		m_outHeight = height;
	}

	public void setFPS(int fps) {
		m_cameraFPS = fps;
	}

	private CameraThread m_thread;

	public void start() {
		m_thread = new CameraThread();
		m_thread.start();
	}

	public <T> boolean a(T t) {
		return true;
	}

	class CameraThread extends Thread {
		public CameraThread() {
			// cameraFeed = new VideoCapture(0);

			// cameraFeed.set(3, m_outWidth);
			// cameraFeed.set(4, m_outHeight);
			// cameraFeed.set(propId, value)
			camera = new UsbCamera("/dev/video0", 0);
			
			cameraFeed = new CvSink("/dev/video0");
		
			camera.setBrightness(25);
			camera.setExposureAuto();
			cameraFeed.setSource(camera);
			videoFeed = CameraServer.getInstance().putVideo("Minimized Camera", m_outWidth, m_outHeight);
		}

		Mat source = Mat.eye(m_srcHeight, m_srcWidth, CvType.CV_8UC3);
		Mat output = Mat.eye(m_outHeight, m_outWidth, CvType.CV_8UC3);
		CvSink cameraFeed;
		CvSource videoFeed;
		UsbCamera camera;

		public void run() {
			long lastStart = System.currentTimeMillis();
			while (true) {
				
				cameraFeed.grabFrame(source);

				Imgproc.resize(source, output, new Size(m_outWidth, m_outHeight));
				videoFeed.putFrame(output);
				
			}

		}

	}
}
