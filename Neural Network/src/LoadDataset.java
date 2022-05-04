import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadDataset {
	private float[][] data;
	private int[][] target;

	public LoadDataset(File fileName) {
		data = new float[8000][2];
		target = new int[8000][4];
		String[] array = new String[3];
		int i = 0;
		try {
			Scanner s = new Scanner(fileName);

			while (s.hasNext()) {

			
				String line = s.nextLine();
				array = line.split(",");
				data[i][0] = Float.parseFloat(array[0]);
				data[i][1] = Float.parseFloat(array[1]);
				for (int j = 0; j < 4; j++) {
					target[i][j] = 0;
				}
				target[i][Integer.parseInt(array[2]) - 1] = 1;
				i++;

			}
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public float[][] getData() {
		return data;
	}

	public void setData(float[][] data) {
		this.data = data;
	}

	public int[][] getTarget() {
		return target;
	}

	public void setTarget(int[][] target) {
		this.target = target;
	}

}