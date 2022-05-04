
public class Neuron {
	private float error;
	private float inputs[];
	private float weights[];
	private float output = 0;
	private float outValue = 0;
	private float dir = 0;
	private float partialDirative[];
	private String function;

	public Neuron(String function, int n) {
		this.function = function;
		inputs = new float[n];
		weights = new float[n];
		partialDirative = new float[n];
	}

	public float getPd(int k) {
		return partialDirative[k];
	}

	public void setPd(float pd, int k) {
		this.partialDirative[k] = pd;
	}

	public float getError() {
		return error;
	}

	public void setError(float error) {
		this.error = error;
	}

	public float getRandomNumber1() {
		return (float) Math.random() * (1 - (-1)) + -1;
	}

	public void initWeights() {
		for (int i = 0; i < inputs.length; i++) {
			weights[i] = getRandomNumber1();

		}
	}

	public float getWeights(int k) {
		return weights[k];
	}

	public float[] getWeights() {
		return weights;
	}

	public void setWeights(float weights, int k) {
		this.weights[k] = weights;
	}

	public void calculateOutput() {
		output=0;
		for (int i = 0; i < inputs.length; i++) {
			output = output + inputs[i] * weights[i];
			

		}
		this.outValue = this.output;
		if (function.equals("Relu")) {
			output = Math.max(0, output);
		} else if (function.equals("Tanh")) {
			output = ((float) Math.pow(2.71828, output) - (float) Math.pow(2.71828, -output))
					/ ((float) Math.pow(2.71828, output) + (float) Math.pow(2.71828, -output));
		} else if (function.equals("Log")) {
			output = 1 / (float) (1 + Math.pow(2.71828, -output));

		}

	}

	public float getOutput() {
		return output;
	}

	public void setOutput(float output) {
		this.output = output;
	}

	public float getInputs(int k) {
		return inputs[k];
	}

	public void setInputs(float[] inputs) {
		this.inputs = inputs;
	}

	public void errorOfNeurono(float t) {
		this.error = output * (1 - output) * (output - t);

	}

	public void calculateDirative() {
		if (this.function.equals("Log")) {
			this.dir = this.output * (1 - this.output);
		} else if (this.function.equals("Relu")) {
			if (this.outValue < 0) {
				this.dir = 0;
			} else {
				this.dir = 1;
			}
		} else if (this.function.equals("Tanh")) {
			this.dir = this.output * (1 - this.output);
		}

	}

	public void errorOfNeurono(Neuron[] a, int k) {
		float sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum = sum + a[i].getWeights()[k] * a[i].getError();

		}
		this.error = this.dir * sum;

	}
}
