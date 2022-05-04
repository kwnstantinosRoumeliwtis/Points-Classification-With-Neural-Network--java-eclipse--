import javax.swing.JFrame;

public class Dataset {
	private static void delay(int sec) {
		try {
			Thread.currentThread().sleep(1000 * sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void visualize() {

		PointGenerator gen = new PointGenerator();
		gen.pointsCreator();
		gen.classification();
		gen.pointsCreator1(150, (float) 0.75, (float) 0.75, (float) 1.25, (float) 1.25);
		gen.pointsCreator1(150, (float) 0, (float) 0, (float) 0.5, (float) 0.5);
		gen.pointsCreator1(150, (float) 0, (float) 1.5, (float) 0.5, (float) 2);
		gen.pointsCreator1(150, (float) 1.5, (float) 0, (float) 2, (float) 0.5);
		gen.pointsCreator1(150, (float) 1.5, (float) 1.5, (float) 2, (float) 2);
		gen.pointsCreator1(75, (float) 0.6, (float) 0, (float) 0.8, (float) 0.4);
		gen.pointsCreator1(75, (float) 0.6, (float) 1.6, (float) 0.8, (float) 2);
		gen.pointsCreator1(75, (float) 1.2, (float) 0, (float) 1.4, (float) 0.4);
		gen.pointsCreator1(75, (float) 1.2, (float) 1.6, (float) 1.4, (float) 2);
		gen.pointsCreator1(150, (float) 0, (float) 0, (float) 2, (float) 2);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Visualization(gen, 0));
		frame.setSize(500, 500);
		frame.setLocation(200, 200);
		frame.setVisible(true);
		delay(1);

		JFrame frame1 = new JFrame();
		gen.classification1();
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.add(new Visualization(gen, 1));
		frame1.setSize(500, 500);
		frame1.setLocation(700, 200);
		frame1.setVisible(true);
		gen.writePointsToFile();

	}

	public static void main(String[] args) {
		
		visualize();

	}

}
