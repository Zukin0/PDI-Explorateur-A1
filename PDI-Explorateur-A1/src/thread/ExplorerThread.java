package thread;


import character.Explorer;
import character.WildAnimals;
import character.builders.WildAnimal.WolfBuilder;
import character.builders.WildAnimal.core.WaBuilder;
import character.builders.WildAnimal.core.WaDirector;
import data.*;
import game.Simulation;
import game.SimulationUtility;
import game.TileMap;
import gameState.SimulationState;
import tests.TestObstacles;
import treatment.CharacterTreatment;
import treatment.MeetAnimal;

public class ExplorerThread implements Runnable{
	private Explorer e;
	private TileMap tilemap;
	
	int timer;
	
	public ExplorerThread(Explorer e) {
		this.e = e; 
		tilemap = SimulationState.tilemap;
	}
	
	public void run() {
		int cpt = 0;
		CharacterTreatment.changeDir(e);
		while(!e.isDead()) {
			SimulationUtility.unitTime();		
			/* Explorer is fleeing */
			if(e.isEscaping() == true) {
				cpt = 0;
				System.out.println(e.getName() + " : JE FUIS PENDANT " + Constant.NUMBER_ESCAPE_ITERATIONS + " ms VERS :" + e.getDir());
				while(cpt != Constant.NUMBER_ESCAPE_ITERATIONS) {
					SimulationUtility.unitTime();
					if(!collision(e)) {
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
				if(!collision(e)) {

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
						MeetAnimal.meetAnimals(e, wa);
					}
					else {
						CharacterTreatment.move(e);
					}
				}
				cpt++;
			}
		}
	}
	
	public boolean collision(Explorer e) {
		int tMapX = tilemap.getX();
		int tMapY = tilemap.getY();
		int tileSize = tilemap.getTileSize();

		int futurXLeft = CharacterTreatment.predictPos(e).getX();
		int futurXRight = futurXLeft + e.getSize().getWidth();
		int futurYTop = CharacterTreatment.predictPos(e).getY();
		int futurYBottom = futurYTop + e.getSize().getHeight();
		
		int row1 = 0,row2 = 0,col1 = 0,col2 = 0;
		
		switch(e.getDir()) {
		
		//Up => Need Top/left and Top/Right corners
		case 0 : 
			row1 = (int)(futurYTop-5-tMapY)/tileSize;
			col1 = (int)(futurXLeft-5-tMapX)/tileSize;
			
			row2 = (int)(futurYTop-5-tMapY)/tileSize;
			col2 = (int)(futurXRight-5-tMapX)/tileSize;
			break;
		
		//Down => Need Down/left and Down/Right corners
		case 1 : 
			row1 = (int)(futurYBottom-5-tMapY)/tileSize;
			col1 = (int)(futurXLeft-5-tMapX)/tileSize;
			
			row2 = (int)(futurYBottom-5-tMapY)/tileSize;
			col2 = (int)(futurXRight-5-tMapX)/tileSize;
			break;
		
		//Right => Need Top/Right and Bottom/Right corners
		case 2 : 
			row1 = (int)(futurYTop-5-tMapY)/tileSize;
			col1 = (int)(futurXRight-5-tMapX)/tileSize;
			
			row2 = (int)(futurYBottom-5-tMapY)/tileSize;
			col2 = (int)(futurXRight-5-tMapX)/tileSize;
			break;
		
		//Left => Need Top/left and Bottom/Left corners
		case 3 :
			row1 = (int)(futurYTop-5-tMapY)/tileSize;
			col1 = (int)(futurXLeft-5-tMapX)/tileSize;
			
			row2 = (int)(futurYBottom-5-tMapY)/tileSize;
			col2 = (int)(futurXLeft-5-tMapX)/tileSize;
			break;
		}
		
		int tile1 = tilemap.getPosition(row1,col1);
		int tile2 = tilemap.getPosition(row2,col2);
		
		if(CharacterTreatment.contains(TileMap.blockTile, tile1) || CharacterTreatment.contains(TileMap.blockTile, tile2)) {
			//System.out.println("Blocked tile1 : " + tile1);
			TestObstacles.meetObstacles(e, "blocked");
			return true;
		}
		else {
			switch(tile1) {
			case 6 :
				TestObstacles.meetObstacles(e, "mud");
				break;
			case 13 :
				TestObstacles.meetObstacles(e, "water");
				break;
			}
			
			switch(tile2) {
			case 6 :
				TestObstacles.meetObstacles(e, "mud");
				break;
			case 13 :
				TestObstacles.meetObstacles(e, "water");
				break;
			}
			return false;
		}
	}
}
