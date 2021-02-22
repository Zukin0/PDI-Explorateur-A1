package thread;

import java.util.ArrayList;

import character.WildAnimals;
import data.Constant;
import data.Position;
import game.SimulationUtility;
import treatment.CharacterTreatment;

public class WildAnimalsThread implements Runnable{
	WildAnimals a;
	
	public WildAnimalsThread(WildAnimals a) {
		this.a = a;
	}
	
	public void run() {
		int cpt = 0;
		// need while(!dead & running)
		a.setPosTerr(new Position(a.getPosition().getX()-(a.getTerritorySize().getWidth()/2), a.getPosition().getY()-(a.getTerritorySize().getHeight()/2)));
		CharacterTreatment.changeDir(a);
		while(true) {
			SimulationUtility.unitTime();		
			/*
			 * Change direction every X iterations
			 */
			if(cpt == Constant.NUMBER_EXPLORE_ITERATIONS) {
				CharacterTreatment.changeDir(a);
				cpt = 0;
			}
			
			if(isInsideTerritory(CharacterTreatment.predictPos(a))) {
				CharacterTreatment.move(a);
			}
			cpt++;
		}
	}
	
	public boolean isInsideTerritory(Position predictedPos) {
		int width = a.getSize().getWidth();
		int height = a.getSize().getHeight();
		int xChar = predictedPos.getX();
		int yChar = predictedPos.getY();
		
		/*
		 * Create each corner of the body of the Character
		 */
		ArrayList<Position> posChar = new ArrayList<Position>();
		Position pos1 = predictedPos;
		Position pos2 = new Position(xChar+width, yChar);
		Position pos3 = new Position(xChar+width, yChar+height);
		Position pos4 = new Position(xChar, yChar+height);
		posChar.add(pos1);
		posChar.add(pos2);
		posChar.add(pos3);
		posChar.add(pos4);
		
		
		/*
		 * Check if each corner of the animal body is inside the Rectangle.
		 * If not, the animal is at the edge of his Territory and tries to go outside of it.
		 */
		for(Position p : posChar) {
			if(!CharacterTreatment.isPointInsideRect(p,a.getPosTerr(),a.getTerritorySize().getWidth(),a.getTerritorySize().getHeight())) {
				return false;
			}
		}
		return true;
	}
}
