public class Simulation{
	private static int lengthOfDNA = (NeuralNet.numberOfInputs*NeuralNet.numberOfInputs+NeuralNet.numberOfInputs)*NeuralNet.numberOfHiddenLayers + NeuralNet.numberOfInputs*NeuralNet.numberOfOutputs + NeuralNet.numberOfOutputs;
	
	private static Car car = new Car();
	
	public static int GetLengthOfDNA(){
		return lengthOfDNA;
	}
	
	public static double RunSim(DNA _dna, double _renderRatio){
		
		//reset car
		car.reset(_dna);
		int i = 0;
		
		
		//GraphicsHandler.start();
		
		
		
		//do sim loop, until car is not alive
		while(car.getAlive()){
			
			if (!UserInput.d()){
				car.update(20);								//!!!! fixed timestep found in arg here!!!!!
			}
			
			
			if (_renderRatio > 0){
				if ((Math.IEEEremainder(i, _renderRatio)) == 0){
					GraphicsHandler.start();
					SceneRender.drawLines(Track.getCheckPoints());
					SceneRender.drawLines(Track.getLines());
					SceneRender.drawLines(car.getEyeLines());
					SceneRender.drawLines(car.getLines());
					GraphicsHandler.end();
				}
			}
			
			if (UserInput.a()){
				GraphicsHandler.start();
				SceneRender.drawLines(Track.getCheckPoints());
				SceneRender.drawLines(Track.getLines());
				SceneRender.drawLines(car.getEyeLines());
				SceneRender.drawLines(car.getLines());
				GraphicsHandler.end();
			}
			i++;
		}
		
		if (UserInput.a())
		{
			GraphicsHandler.start();
			SceneRender.drawLines(Track.getCheckPoints());
			SceneRender.drawLines(Track.getLines());
			SceneRender.drawLines(car.getLines());
			GraphicsHandler.end();
		}
	
		
		
		//return a fitness (performance)
		return car.getFitness();
	}
}