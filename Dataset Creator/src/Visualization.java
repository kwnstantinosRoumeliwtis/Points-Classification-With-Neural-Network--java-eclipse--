
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.Scanner;

public class Visualization extends JPanel {
	private static PointGenerator gen;
	private int n;

	public Visualization(PointGenerator gen, int n) {
		this.gen = gen;
		this.n = n;

	}

	protected void paintComponent(Graphics g) {
		if (n == 0) {

			super.paintComponent(g);
			Graphics2D g1 = (Graphics2D) g;

			g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g1.setPaint(Color.GREEN);

			for (Point p : gen.getC1()) {
				double x1 = p.getX1();
				double y1 = -p.getX2();
				g1.fill(new Ellipse2D.Double(x1 * 240 + 240, y1 * 228 + 228.5, 3, 3));
			}
			g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g1.setPaint(Color.MAGENTA);
			for (Point p : gen.getC2()) {
				double x1 = p.getX1();
				double y1 = -p.getX2();
				g1.fill(new Ellipse2D.Double(x1 * 240 + 243, y1 * 228 + 231.5, 3, 3));
			}
			g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g1.setPaint(Color.RED);
			for (Point p : gen.getC3()) {
				double x1 = p.getX1();
				double y1 = -p.getX2();
				g1.fill(new Ellipse2D.Double(x1 * 240 + 243, y1 * 228 + 231.5, 3, 3));
			}
			g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g1.setPaint(Color.BLUE);
			for (Point p : gen.getC4()) {
				double x1 = p.getX1();
				double y1 = -p.getX2();
				g1.fill(new Ellipse2D.Double(x1 * 240 + 243, y1 * 228 + 231.5, 3, 3));

			}

		} else {
			super.paintComponent(g);
			Graphics2D g1 = (Graphics2D) g;

			g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g1.setPaint(Color.BLUE);
			for (Point p : gen.getPoints1()) {
				double x1 = p.getX1();
				double y1 = -p.getX2();
				g1.fill(new Ellipse2D.Double(x1 * 240 + 3, y1 * 228 + 460, 4, 4));

			}

		}

	}

}
