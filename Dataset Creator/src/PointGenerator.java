import java.util.ArrayList;
import java.io.File; // Import the File class
import java.io.IOException;
import java.io.PrintWriter;

public class PointGenerator {

	private float min = -1;
	private float max = 1;
	private ArrayList<Point> points = new ArrayList<Point>();
	private ArrayList<Point> points1 = new ArrayList<Point>();
	private ArrayList<Point> c1 = new ArrayList<Point>();
	private ArrayList<Point> c2 = new ArrayList<Point>();
	private ArrayList<Point> c3 = new ArrayList<Point>();
	private ArrayList<Point> c4 = new ArrayList<Point>();

	public void pointsCreator() {
		for (int i = 0; i < 8000; i++) {
			Point point = new Point(getRandomNumber(), getRandomNumber());
			points.add(point);

		}

	}

	public ArrayList<Point> getPoints1() {
		return points1;
	}

	public void pointsCreator1(int n, float d, float e, float f, float g) {
		for (int i = 0; i < n; i++) {
			Point point = new Point(getRandomNumber1(d, f), getRandomNumber1(e, g));
			points1.add(point);

		}

	}

	public float getRandomNumber1(float min1, float max1) {
		return (float) Math.random() * (max1 - min1) + min1;
	}

	public float getRandomNumber() {
		return (float) Math.random() * (this.max - this.min) + this.min;
	}

	public void printPoints() {
		for (Point p : this.points) {
			System.out.println(p.getX1() + " , " + p.getX2());
		}
	}

	public void printPointsc1() {
		System.out.println("Total Points in 1 category: " + c1.size());
		for (Point p : this.c1) {
			System.out.println(p.getX1() + " , " + p.getX2());
		}
	}

	public void printPointsc2() {
		System.out.println("Total Points in 2 category: " + c2.size());
		for (Point p : this.c2) {
			System.out.println(p.getX1() + " , " + p.getX2());
		}
	}

	public void printPointsc3() {
		System.out.println("Total Points in 3 category: " + c3.size());
		for (Point p : this.c3) {
			System.out.println(p.getX1() + " , " + p.getX2());
		}
	}

	public void printPointsc4() {
		System.out.println("Total Points in 4 category: " + c4.size());
		for (Point p : this.c4) {
			System.out.println(p.getX1() + " , " + p.getX2());
		}
	}

	public void printPointsPerCategory() {

		System.out.println("Total Points in 1 category: " + c1.size());
		System.out.println("Total Points in 2 category: " + c2.size());
		System.out.println("Total Points in 3 category: " + c3.size());
		System.out.println("Total Points in 4 category: " + c4.size());

	}

	public ArrayList<Point> getC1() {
		return c1;
	}

	public ArrayList<Point> getC2() {
		return c2;
	}

	public ArrayList<Point> getC3() {
		return c3;
	}

	public ArrayList<Point> getC4() {
		return c4;
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void classification() {
		for (Point p : points) {
			if ((Math.pow(p.getX1() - (float) 0.5, 2) + Math.pow(p.getX2() - (float) 0.5, 2) < 0.16)
					|| (Math.pow(p.getX1() + (float) 0.5, 2) + Math.pow(p.getX2() + (float) 0.5, 2) < 0.16)) {
				c1.add(p);
			} else if ((Math.pow(p.getX1() - (float) 0.5, 2) + Math.pow(p.getX2() + (float) 0.5, 2) < 0.16)
					|| (Math.pow(p.getX1() + (float) 0.5, 2) + Math.pow(p.getX2() - (float) 0.5, 2) < 0.16)) {
				c2.add(p);
			} else if ((p.getX1() > 0 && p.getX2() > 0) || (p.getX1() < 0 && p.getX2() < 0)) {
				c3.add(p);
			} else if ((p.getX1() < 0 && p.getX2() > 0) || (p.getX1() > 0 && p.getX2() < 0)) {
				c4.add(p);

			}
		}

	}

	public void writePointsToFile() {
		int c = 0;
		try {
			File myObj = new File("dataset.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		try {
			 PrintWriter myWriter = new  PrintWriter("dataset.txt");
			for (Point p : points) {
				if (c1.contains(p)) {
					c = 1;
				} else if (c2.contains(p)) {
					c = 2;
				} else if (c3.contains(p)) {
					c = 3;
				} else if (c4.contains(p)) {
					c = 4;
				}

				myWriter.println(Float.toString(p.getX1() )+ "," +Float.toString(p.getX2()) + "," + Integer.toString(c) );

			}
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public void classification1() {
		for (Point p : points1) {
			if ((Math.pow(p.getX1() - (float) 0.5, 2) + Math.pow(p.getX2() - (float) 0.5, 2) < 0.16)
					|| (Math.pow(p.getX1() + (float) 0.5, 2) + Math.pow(p.getX2() + (float) 0.5, 2) < 0.16)) {
				c1.add(p);
			} else if ((Math.pow(p.getX1() - (float) 0.5, 2) + Math.pow(p.getX2() + (float) 0.5, 2) < 0.16)
					|| (Math.pow(p.getX1() + (float) 0.5, 2) + Math.pow(p.getX2() - (float) 0.5, 2) < 0.16)) {
				c2.add(p);
			} else if ((p.getX1() > 0 && p.getX2() > 0) || (p.getX1() < 0 && p.getX2() < 0)) {
				c3.add(p);
			} else if ((p.getX1() < 0 && p.getX2() > 0) || (p.getX1() > 0 && p.getX2() < 0)) {
				c4.add(p);

			}
		}

	}

}
