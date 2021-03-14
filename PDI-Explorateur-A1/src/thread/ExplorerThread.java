package thread;


import character.Explorer;
import character.WildAnimals;
import character.builders.WildAnimal.WolfBuilder;
import character.builders.WildAnimal.core.WaBuilder;
import character.builders.WildAnimal.core.WaDirector;
import data.*;
import game.Simulation;
import game.SimulationUtility;
import treatment.CharacterTreatment;
import treatment.MeetAnimal;

public class ExplorerThread implements Runnable{
	Explorer e;
	volatile int treasureFind ;
	int timer;
	
	public ExplorerThread(Explorer e) {
		this.e = e;
		treasureFind = 0;
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
				System.out.println(e.getName() + " : JE FUIS PENDANT " + Constant.NUMBER_ESCAPE_ITERATIONS + " ms VERS :" + e.getDir());
				while(cpt != Constant.NUMBER_ESCAPE_ITERATIONS) {
					SimulationUtility.unitTime();	
					if(!CharacterTreatment.isBorderWindow(CharacterTreatment.predictPos(e), e.getSize().getWidth(), e.getSize().getHeight())) {
						CharacterTreatment.move(e);
					}
					cpt++;

				}
				System.out.println(e.getName() + " : J'ARRETE DE FUIR");
				e.setEscaping(false);
				cpt = 0;
			}
			else {
				if(cpt == Constant.NUMBER_EXPLORE_ITERATIONS) {
					CharacterTreatment.changeDir(e);
					cpt = 0;
				}
				if(!CharacterTreatment.isBorderWindow(CharacterTreatment.predictPos(e), e.getSize().getWidth(), e.getSize().getHeight())) {
//					Position futurPos = CharacterTreatment.predictPos(e);
//					WildAnimals wa = CollisionAnimal(futurPos);
//					if( wa != null ) { 
//						MeetAnimal.meetAnimals(e, wa);
//					}
//					else if(isCollisionObstacle()) {
//						
//					}
//					else {
//						CharacterTreatment.move(e);
//					}
					
					/*
					 * TESTING BRUTE LE TEMPS D'AVOIR LE CODE DE YOHAN
					 */
//					WaDirector creatorA = new WaDirector();
//					WaBuilder bWolf = new WolfBuilder();
//					creatorA.setWildAnimalsBuilder(bWolf);
//					creatorA.BuildWildAnimals();
//					WildAnimals wa = creatorA.getAnimal();
//					int rand = (int)(Math.random() * 100);
//					if( wa != null && rand == 0) { 
//						MeetAnimal.meetAnimals(e, wa);
//					}
//					else {
//						CharacterTreatment.move(e);
//					}
					for(WildAnimals a : Simulation.animals.values()) {
						CharacterTreatment.auraCheck(e, a, this);
					}
					CharacterTreatment.move(e);
					/*
					 * FIN TESTING BRUTE
					 */
					//CharacterTreatment.move(e);
				}
				cpt++;
			}
		}
	}
	
	public synchronized void find() {
		treasureFind++;
		System.out.println("Trésor trouver on en est a : " + treasureFind);
	}
	
	public void collsion() {
		
	}

}
