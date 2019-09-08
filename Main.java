/*
Fritz Keyzer
Monday, January 15 2018 - 10:37 AM

Fourth GA experiment

My plan is to improve on v3 by adding a graph showing improvement over time. 
*/

/*
This is the top of the hierarchy...


*/


public class Main{
	private static int numberOfPools = 1;
	private static Pool[] pools;
	private static int poolSize = 50;
	
	public static void main(String[] args){
		PoolCycle();
		
	}
	
	private static void PoolCycle(){
		
		//pool init:
		pools = new Pool[numberOfPools];
		for (int i = 0; i < numberOfPools; i ++){
			System.out.println("Initialising Pool: "+i);
			pools[i] = new Pool(poolSize);
		}
		
		
		for (int i = 0; i < numberOfPools; i ++){
			System.out.println("Running Generations on Pool: "+i);
			
			//Pool cycle (to be performed either (indefinitely) OR (until some dna reaches a fitness target) OR (certain number of times)):
			for (int j = 0; j<20000; j++){
				pools[i].NextGen();
				
				if (UserInput.s()){
					j = 999999999;
				}
			}
		}
		
		
		
		//ie sim is done at this point...
		while (true){
			//show off best of each pool?
			
			for (int i = 0; i < numberOfPools; i ++){
				System.out.print("Pool: "+i+" - ");
				
				pools[i].showBest();
			}
		}
	}
}