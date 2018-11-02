import java.awt.*;
import javax.swing.*;

public class BounceBallApp extends JApplet {
	public BounceBallApp() {
		add(new BallControl());
	}

	public static void main(String[] args) {
		BounceBallApp applet = new BounceBallApp();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("BounceBallApp");
		frame.add(applet, BorderLayout.CENTER);
		frame.setSize(1000, 550);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
	}
}
