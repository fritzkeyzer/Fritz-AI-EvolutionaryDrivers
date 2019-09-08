import java.util.*;

public class NeuralNet{
	
	public static int numberOfInputs = 5;
	public static int numberOfHiddenLayers = 1;
	public static int numberOfOutputs = 2;
	public static boolean outputTypeBinary = true;
	
	private static int posInDNA = -1;
	
	private static double [] [] dataSet = new double [numberOfInputs] [numberOfHiddenLayers+2];
	
	
	public static double [] runNet(double [] _dna, double [] _inputs){
		//_dna defines the coefficients and bias values of the neural net formulas
		posInDNA = 0;
		
		//Input layer
		//for each row
		for(int y = 0; y < numberOfInputs; y++){
			dataSet [y] [0] = _inputs[y];
			//System.out.println("    [in "+y+"] = "+dataSet [y] [0]);
		}
		
		//for each hidden layer
		for(int x = 1; x < numberOfHiddenLayers + 1; x++){
			//for each row
			for(int y = 0; y < numberOfInputs; y++){
				double ans = 0;
				
				for (int z = 0; z < numberOfInputs; z++){
					//System.out.println("    xxx "+dataSet [z] [x-1]);
					ans += (dataSet [z] [x-1])*_dna[posInDNA];
					posInDNA++;
				}
				
				//BIAS
				ans += _dna[posInDNA];
				posInDNA ++;
				
				//OUTPUT FUNCTION
				ans = tanh(ans);
				
				//STORING IN DATASET
				dataSet [y] [x] = ans;
				//System.out.println("    [layer "+x+"] [neuron "+y+"] = "+ans);
			}
		}
		
		//Output layer
		for(int y = 0; y < numberOfOutputs; y++){
			double ans = 0;
			
			for (int z = 0; z < numberOfInputs; z++){
				//System.out.println("    xxx "+dataSet [z] [x-1]);
				ans += (dataSet [z] [numberOfHiddenLayers])*_dna[posInDNA];
				posInDNA++;
			}
			
			//BIAS
			ans += _dna[posInDNA];
			
			//OUTPUT FUNCTION
			if (outputTypeBinary){
				ans = bin(ans);
			}
			else{
				ans = tanh(ans);
			}
			posInDNA ++;
			
			//STORING IN DATASET
			dataSet [y] [numberOfHiddenLayers+1] = ans;
			//System.out.println("    [layer output] [output "+y+"] = "+ans);
		}
		
		posInDNA = -1;
		
		double [] outputLayer = new double [numberOfOutputs];
		
		for (int i = 0; i < numberOfOutputs; i++){
			outputLayer [i] = dataSet [i] [numberOfHiddenLayers+1];
		}
		
		return outputLayer;
	}
	
	private static double tanh(double _in){
		return Math.tanh(_in);
	}
	
	private static double bin(double _in){
		if (_in >= 0){
			return 1;
		}
		else{
			return -1;
		}
	}
	
	public static void resetValues(double[] values) {
		  int len = values.length;
		  if (len > 0) {
			values[0] = 0.0;
		  }
		  for (int i = 1; i < len; i += i) {
			System.arraycopy(values, 0, values, i, ((len - i) < i) ? (len - i) : i);
		  }
		}
}