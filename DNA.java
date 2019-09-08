


public class DNA{
	private static int numberOfDNA = 0;
	private int id;
	private double randomRange = 2;
	private double[] values;

	public DNA(int _length){
		id = numberOfDNA;
		numberOfDNA++;
		values = new double[_length];
		//System.out.println(id);
	}
	
	public void randomise(){
		for (int i = 0; i < values.length; i++){
			values[i] = (Math.random()-0.5)*2*randomRange;
		}
	}
	
	public double[] getValues(){
		return values;
	}
	
	public void createChildOf(DNA _mother, DNA _father){
		
		//choose a crossover point:
		
		//int xOverPoint = Math.random()*(values.length-3) + 1;
		
		
		for (int i = 0; i < values.length; i++){
			
			//precentage chance to choose either mother or fathers dna
			if (Math.random() >= 0.5){
				values [i] = _mother.getValues()[i];
			}
			else {
				values [i] = _father.getValues()[i];
			}
			
			//pick a random dna value to mutate:
			int randToChange = (int)Math.random()*(values.length-1);
			
			values[randToChange] += (Math.random()-0.5)*2*randomRange;
		}
		
		//inbreeding check:
		double dnaDelta = 0;
			for (int j = 0; j < values.length; j++){
				dnaDelta += Math.abs(_mother.getValues()[j]-_father.getValues()[j]);
			}
		//System.out.println(dnaDelta);
		if (dnaDelta < 5){
			System.out.println("		Inbreeding has occurred");
			
			//number of dna values to mutate:
			int mutateNumber = (int)(0.1*values.length);
			for (int j = 0; j < mutateNumber; j++){
				//pick a random dna value to mutate:
				int randToChange = (int)Math.random()*(values.length-1);
			
				values[randToChange] = (Math.random()-0.5)*2*randomRange;
			}
		}
	}
	
}