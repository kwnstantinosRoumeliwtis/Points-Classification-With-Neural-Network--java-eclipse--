import java.io.File;

public class Network {
	private static final String datasetPath = "C:\\Users\\kwnst\\eclipse-workspace\\Dataset Creator\\dataset.txt";
	private static final String aFunction = "Relu";
	private static final String aFunctionOutput = "Log";
	private static final int finalEpoch = 2500;
	private static final int numberOfPoints = 1500;
	private static final int input = 2;
	private static final int nH1 = 20;
	private static final int nH2 = 15;
	private static final int out = 4;

	private static Neuron[][] neuronMatrix = new Neuron[3][];

	public static void createNetwork() {
		neuronMatrix[0] = new Neuron[nH1];
		for (int j = 0; j < nH1; j++) {
			neuronMatrix[0][j] = new Neuron(aFunction, input + 1);
			neuronMatrix[0][j].initWeights();
		}

		neuronMatrix[1] = new Neuron[nH2];
		for (int j = 0; j < nH2; j++) {
			neuronMatrix[1][j] = new Neuron(aFunction, nH1 + 1);
			neuronMatrix[1][j].initWeights();
		}

		neuronMatrix[2] = new Neuron[out];
		for (int j = 0; j < out; j++) {
			neuronMatrix[2][j] = new Neuron(aFunctionOutput, nH2 + 1);
			neuronMatrix[2][j].initWeights();
		}

		
	}

	public static float[] forwardPass(float[] x) {
		for (int i = 0; i < nH1; i++) {
			neuronMatrix[0][i].setInputs(x);
		}
		for (int i = 0; i < neuronMatrix.length; i++) {

			float[] inputmatrix = new float[neuronMatrix[i].length + 1 ];

			for (int j = 0; j < neuronMatrix[i].length; j++) {
				neuronMatrix[i][j].calculateOutput();
			}
			inputmatrix[0] = 1;
			for (int j = 0; j < neuronMatrix[i].length; j++) {
				inputmatrix[j + 1] = neuronMatrix[i][j].getOutput();
			}

			for (int j = 0; j < neuronMatrix[i + 1].length; j++) {
				neuronMatrix[i + 1][j].setInputs(inputmatrix);

			}
			if (i == neuronMatrix.length - 2) {
				float[] y = new float[neuronMatrix[i + 1].length];
				for (int j = 0; j < neuronMatrix[i + 1].length; j++) {
					neuronMatrix[i + 1][j].calculateOutput();
					y[j] = neuronMatrix[i + 1][j].getOutput();

				}
				return y;

			}
		}
		return null;

	}

	public static void backPro(float[] target) {
		for (int i = neuronMatrix.length - 1; i >= 0; i--) {
			if (i == neuronMatrix.length - 1) {
				for (int j = 0; j < neuronMatrix[i].length; j++) {
					neuronMatrix[i][j].errorOfNeurono(target[j]);

				}
			} else {
				for (int j = 0; j < neuronMatrix[i].length; j++) {
					neuronMatrix[i][j].calculateDirative();
					neuronMatrix[i][j].errorOfNeurono(neuronMatrix[i + 1], j);

				}

			}

		}
		for (int i = 0; i < neuronMatrix.length; i++) {
			for (int j = 0; j < neuronMatrix[i].length; j++) {
				for (int k = 0; k < neuronMatrix[i][j].getWeights().length; k++)
					neuronMatrix[i][j].setPd(neuronMatrix[i][j].getError() * neuronMatrix[i][j].getInputs(k), k);

			}
		}

		for (int x = 0; x < neuronMatrix.length; x++) {
			for (int j = 0; j < neuronMatrix[x].length; j++) {
				for (int k = 0; k < neuronMatrix[x][j].getWeights().length; k++) {

					neuronMatrix[x][j].setWeights(
							(float) neuronMatrix[x][j].getWeights(k) - (float) 0.001 * neuronMatrix[x][j].getPd(k), k);
				}

			}
		}

	}

	public static void main(String[] args) {
		File data = new File(datasetPath);
		createNetwork();
		LoadDataset m = new LoadDataset(data);
        int bestEpoch=0;
		int bestAccuracy = 0;
		for (int epochs = 0; epochs < finalEpoch; epochs++) {
			System.out.println("-------");

			int sum = 0;

			for (int i = 0; i < numberOfPoints; i++) {
				float max1 = 0, max2 = 0;
				int target = 0, target1 = 0;
				float[] inputPoint = { (float) 1, (float) m.getData()[i][0], (float) m.getData()[i][1] };
				float[] outputTarget = {};
				outputTarget = forwardPass(inputPoint);
				float[] category = { (float) m.getTarget()[i][0], (float) m.getTarget()[i][1], (float) m.getTarget()[i][2],
						m.getTarget()[i][3] };
				for (int l = 0; l < 4; l++) {
					if (outputTarget[l] > max1) {
						max1 = outputTarget[l];
						target = l;

					}
					if (category[l] > max2) {
						max2 = category[l];
						target1 = l;
					}

				}
				if (target1 == target) {
					sum++;
				}
				if (sum >= bestAccuracy) {
					bestAccuracy = sum;
					bestEpoch=epochs;
				}
		
				backPro(category);

				
				for (int h = 0; h < 4; h++) {
					System.out.print(category[h] + ",");

				}
				System.out.print("---------------");
				for (int h = 0; h < 4; h++) {
					System.out.print(neuronMatrix[2][h].getOutput() + ",");

				}
				System.out.println();
				
				if (i == numberOfPoints-1) {
					System.out.println("accuracy:"+sum+""+"/"+numberOfPoints+" "+100*sum/numberOfPoints+"% in epoch: "+epochs);
					System.out.println("best accuracy:"+bestAccuracy+"/"+numberOfPoints+" "+100*bestAccuracy/numberOfPoints+"% in epoch: "+bestEpoch);
					
				}

			}

		}
		
	}
}