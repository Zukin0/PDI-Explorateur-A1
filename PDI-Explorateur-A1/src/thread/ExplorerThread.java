package thread;


import character.CharacterTreatment;
import character.Explorer;
import data.*;
import game.SimulationUtility;

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
		while(true) {
			SimulationUtility.unitTime();		
			/*
			 * Change direction every X iterations
			 */
			if(e.isEscaping() == true) {
				cpt = 0;
				while(cpt != Constant.NUMBER_ESCAPE_ITERATIONS) {
					SimulationUtility.unitTime();		
					CharacterTreatment.move(e);
					cpt++;

				}
				e.setEscaping(false);
				cpt = 0;
			}
			else {
				if(cpt == Constant.NUMBER_EXPLORE_ITERATIONS) {
					CharacterTreatment.changeDir(e);
					cpt = 0;
				}
				CharacterTreatment.move(e);
				cpt++;
			}
		}
	}
	
	public void collsion() {
		
	}

}
