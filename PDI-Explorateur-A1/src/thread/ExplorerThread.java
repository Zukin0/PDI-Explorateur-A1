package thread;


import character.Explorer;
import character.WildAnimals;
import character.builders.WildAnimal.WolfBuilder;
import character.builders.WildAnimal.core.WaBuilder;
import character.builders.WildAnimal.core.WaDirector;
import data.*;
import game.SimulationUtility;
import treatment.CharacterTreatment;
import treatment.MeetAnimal;

public class ExplorerThread implements Runnable{
	Explorer e;
	
	int timer;
	
	public ExplorerThread(Explorer e) {
		this.e = e; 
	}
	
	public void run() {
		int cpt = 0;
		CharacterTreatment.changeDir(e);
		// need while(!dead & running)
		while(!e.isDead()) {
			SimulationUtility.unitTime();		
			/*
			 * Change direction every X iterations
			 */
			if(e.isEscaping() == true) {
				cpt = 0;
				System.out.println("JE FUIS VERS :" + e.getDir());
				while(cpt != Constant.NUMBER_ESCAPE_ITERATIONS) {
					SimulationUtility.unitTime();		
					CharacterTreatment.move(e);
					cpt++;

				}
				System.out.println("C'EST BON JSUIS PLUS UNE TAPETTE");
				e.setEscaping(false);
				cpt = 0;
			}
			else {
				if(cpt == Constant.NUMBER_EXPLORE_ITERATIONS) {
					CharacterTreatment.changeDir(e);
					cpt = 0;
				}
				
//				Position futurPos = CharacterTreatment.predictPos(e);
//				WildAnimals wa = CollisionAnimal(futurPos);
//				if( wa != null ) { 
//					MeetAnimal.meetAnimals(e, wa);
//				}
//				else if(isCollisionObstacle()) {
//					
//				}
//				else {
//					CharacterTreatment.move(e);
//				}
				
				/*
				 * TESTING BRUTE LE TEMPS D'AVOIR LE CODE DE YOHAN
				 */
				WaDirector creatorA = new WaDirector();
				WaBuilder bWolf = new WolfBuilder();
				creatorA.setWildAnimalsBuilder(bWolf);
				creatorA.BuildWildAnimals();
				WildAnimals wa = creatorA.getAnimal();
				int rand = (int)(Math.random() * 100);
				if( wa != null && rand == 0) { 
					MeetAnimal.meetAnimals(e, wa, 2);
				}
				else {
					CharacterTreatment.move(e);
				}
				
				/*
				 * FIN TESTING BRUTE
				 */
				//CharacterTreatment.move(e);
				
				cpt++;
			}
		}
	}
	
	public void collsion() {
		
	}

}
