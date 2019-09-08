

public class Pool{
	
	private int lengthOfDNA = Simulation.GetLengthOfDNA();
	private DNA[] dnas;
	private double[] fitnesses;
	private int generation = -1;
	
	public Pool(int _sizeOfPool){
		dnas = new DNA[_sizeOfPool];
		fitnesses = new double[_sizeOfPool];
		
		//initialise each dna
		//randomise each dna
		//test each dna
		for (int i = 0; i < _sizeOfPool; i++){
			dnas[i] = new DNA(lengthOfDNA);
			dnas[i].randomise();
			fitnesses[i] = Simulation.RunSim(dnas[i], 0);
			//System.out.println("	DNA: "+i+" Fitness: "+fitnesses[i]);
		}
		
		for (int i = 0; i <2000; i++){
			
			startingGen();
		}
	}
	
	private void startingGen(){
		generation = 0;
		//find worst of current gen:
		double worstFit = -1;
		int worstID = -1;
		for (int i = 0; i<fitnesses.length; i++){
			if (fitnesses[i] < worstFit || worstID == -1){
				worstFit = fitnesses[i];
				worstID = i;
			}
		}
		
		//create a random new dna:
		DNA newbie = new DNA(lengthOfDNA);
		newbie.randomise();
		
		double newbieFit = Simulation.RunSim(newbie, 0);
		
		if (newbieFit > worstFit){
			System.out.println("	Generation: "+generation+" ["+newbieFit + " Replacing: " +worstFit+"]");
			fitnesses[worstID] = newbieFit;
			dnas[worstID] = newbie;
		}
		
		
	}
	
	public void NextGen(){
		generation ++;
		//find worst of current gen:
		double worstFit = -1;
		int worstID = -1;
		for (int i = 0; i<fitnesses.length; i++){
			if (fitnesses[i] < worstFit || worstID == -1){
				worstFit = fitnesses[i];
				worstID = i;
			}
		}
		
		//find two parents
		int mom = (int)(Math.random()*(dnas.length));
		DNA mother = dnas[mom];
		//random father
		int dad = (int)(Math.random()*(dnas.length));
		while (dad == mom){
			dad = (int)(Math.random()*(dnas.length));
		}
		DNA father = dnas[dad];
		
		//perform crossover and mutation
		DNA offspring = new DNA(lengthOfDNA);
		offspring.createChildOf(mother, father);
		
		//test the two new offspring
		double offspringFitness = Simulation.RunSim(offspring, 0);
		
		//reinsertion if > worst of current gen
		if (offspringFitness > worstFit){
			System.out.println("	Generation: "+generation+" ["+offspringFitness + " Replacing: " +worstFit+"]");
			fitnesses[worstID] = offspringFitness;
			dnas[worstID] = offspring;
		}
		
	}
	
	public void showBest(){
		
		//find best
		double bestFit = -1;
		int bestID = -1;
		for (int i = 0; i<fitnesses.length; i++){
			if (fitnesses[i] > bestFit || bestID == -1){
				bestFit = fitnesses[i];
				bestID = i;
			}
		}
		
		System.out.println("Best in pool: "+Simulation.RunSim(dnas[bestID], 1));
		
	}
}