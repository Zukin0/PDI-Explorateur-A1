package thread;

import java.util.ArrayList;

import character.Explorer;
import character.WildAnimals;
import data.Constant;
import data.Position;
import game.SimulationUtility;
import game.TileMap;
import gameState.SimulationState;
import tests.TestObstacles;
import treatment.CharacterTreatment;

public class WildAnimalsThread implements Runnable{
	private WildAnimals a;
	private TileMap tilemap;
	
	public WildAnimalsThread(WildAnimals a) {
		this.a = a;
		tilemap = SimulationState.tilemap;
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
			
			if(isInsideTerritory(CharacterTreatment.predictPos(a)) && !collision(a)) {
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
	
	public boolean collision(WildAnimals e) {
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
			CharacterTreatment.changeDir(e);
			return true;
		}
		else {
			return false;
		}
	}
}
