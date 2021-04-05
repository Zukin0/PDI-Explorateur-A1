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
import treatment.CharacterTreatment;
import treatment.MeetAnimal;
import treatment.ObstacleTreatment;

/**
 * @brief Class for thread of an Explorer
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 */

public class ExplorerThread implements Runnable{
	private Explorer e;
	private TileMap tilemap;
	volatile int treasureFind ;
	
	int timer;
	
	public ExplorerThread(Explorer e) {
		this.e = e; 
		tilemap = SimulationState.tilemap;
		treasureFind = 0;
	}
	
	/**
	 * @brief fuinction contain all the action will do an explorer during his thread running
	 */
	public void run() {
		int cpt = 0;
		CharacterTreatment.changeDir(e);
		while(!e.isDead()) {		
			if(!e.isWaiting() && !e.isHelping()) {
				for(WildAnimals a : Simulation.animals.values()) {
					CharacterTreatment.auraCheck(e, a, this);
				}
				for(Treasure t : Simulation.treasures.values()) {
					CharacterTreatment.auraCheck(e, t, this);
				}
			}
			SimulationUtility.unitTime();
			/* Explorer is fleeing */
			if(e.isEscaping() == true) {
				//System.out.println(e.getName() + " : JE FUIS PENDANT " + Constant.NUMBER_ESCAPE_ITERATIONS + " ms VERS : " + e.getDir());
				if(cpt != Constant.NUMBER_ESCAPE_ITERATIONS) {
					if(!collision(e) && CharacterTreatment.isFarEnough(e)) {
						CharacterTreatment.move(e);
					}
					cpt++;

				}
				else {
					System.out.println(e.getName() + " : J'ARRETE DE FUIR");
					e.setEscaping(false);
					cpt = 0;
				}
			}
			/** Explorer needs help, he waits to be helped */
			else if (e.isWaiting()) {
				//System.out.println(e.getName() + " : J'ATTEND MON AMI PENDANT "+Constant.NUMBER_WAIT_ITERATIONS+" ms");
				if(cpt != Constant.NUMBER_WAIT_ITERATIONS) {
					cpt++;
				}
				else {
					System.out.println(e.getName() + " : J'ARRETE D'ATTENDRE");
					WildAnimals a = null;
					for(WildAnimals tmp : Simulation.animals.values()) {
						if(tmp.getFightAgainst() != null) {
							if(tmp.getFightAgainst().equals(e.getName())) {
								a = tmp;
							}
						}
					}
					System.out.println(e.getName() + " : FIGHT 3");
					String outcome = MeetAnimal.fight(e, a);
					if(outcome != null) {
						if (outcome == "deathExplo" || outcome.equals("deathBoth")) {
							a.setFightAgainst("");
							MeetAnimal.deathExplorer(e);
						}
						if(outcome == "deathAnimal" || outcome.equals("deathBoth")) {
							MeetAnimal.deathAnimal(a);
						}
						for(Explorer eTmp : Simulation.explorers.values()) {
							if(eTmp.getHelpingWho() != null) {
								if(eTmp.getHelpingWho().equals(e.getName())) {
									Explorer helper = eTmp;
									helper.setHelping(false);
									helper.setHelpingWho("");
									break;
								}
							}
						}
					}
					e.setWaiting(false);
					cpt = 0;
				}
			}
			/** Explorer is rescuing a friend */
			else if (e.isHelping()) {
				//System.out.println(e.getName() + " : I HELP");
				Explorer eInDanger = Simulation.explorers.get(e.getHelpingWho());
				WildAnimals a = null;
				if(eInDanger != null) {
					for(WildAnimals tmp : Simulation.animals.values()) {
						if(tmp.getFightAgainst() != null) {
							if(tmp.getFightAgainst().equals(eInDanger.getName())) {
								a = tmp;
							}
						}
					}
				}
				if(cpt != Constant.NUMBER_HELP_ITERATIONS && e.isHelping() && eInDanger != null) {
					if(!collision(e) && CharacterTreatment.isFarEnough(e)) {
						if(e.isNearExp()) {
							System.out.println(e.getName() + " : DUO FIGHT 1");
							MeetAnimal.duoFight(e, eInDanger);
						}
						else {
							CharacterTreatment.goHelp(eInDanger, e);
							CharacterTreatment.move(e);
						}
						cpt++;
					}
					else {
						
						System.out.println(e.getName() + " : J'ARRETE DE CHERCHER");
						eInDanger.setWaiting(false);
						eInDanger.setWaitingWho("");
						
						e.setHelping(false);
						e.setNearExp(false);
						e.setHelpingWho("");
						System.out.println(e.getName() + " : FIGHT 5");
						String outcome = MeetAnimal.fight(eInDanger, a);
						if (outcome == "deathExplo" || outcome.equals("deathBoth")) {
							a.setFightAgainst("");
							MeetAnimal.deathExplorer(eInDanger);
						}
						if(outcome == "deathAnimal" || outcome.equals("deathBoth")) {
							MeetAnimal.deathAnimal(a);
						}
						cpt = 0;
					}
				}
				else {
					System.out.println(e.getName() + " : J'ARRETE DE CHERCHER");
					e.setHelping(false);
					e.setNearExp(false);
					e.setHelpingWho("");
					
					if(eInDanger != null) {
						eInDanger.setWaiting(false);
						eInDanger.setWaitingWho("");
						System.out.println(e.getName() + " : FIGHT 6");
						String outcome = MeetAnimal.fight(eInDanger, a);
						if (outcome == "deathExplo" || outcome.equals("deathBoth")) {
							a.setFightAgainst("");
							MeetAnimal.deathExplorer(e);
						}
						if(outcome == "deathAnimal" || outcome.equals("deathBoth")) {
							MeetAnimal.deathAnimal(a);
						}
						
					}
					cpt = 0;
				}
			}
			/** Default mode */
			else {
				if(cpt == Constant.NUMBER_EXPLORE_ITERATIONS) {
					CharacterTreatment.changeDir(e);
					cpt = 0;
				}
				if(!collision(e) && CharacterTreatment.isFarEnough(e)) {
					CharacterTreatment.move(e);
				}
				cpt++;
			}
		}
		System.out.println(e.getName() + " : Stop moving");
	}
	
	/**
	 * @brief function will check if an explorer is collide an element of a Tile Map and do the right action depends on the object collide.
	 * @param e Explorer which checked
	 * @return Boolean
	 */
	public boolean collision(Explorer e) {
		boolean slowed = false;
		
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
			ObstacleTreatment.meetObstacles(e, "blocked");
			return true;
		}
		else {
			switch(tile1) {
			case 6 :
				ObstacleTreatment.meetObstacles(e, "mud");
				slowed = true;
				break;
			case 13 :
				ObstacleTreatment.meetObstacles(e, "water");
				break;
			}
			
			switch(tile2) {
			case 6 :
				ObstacleTreatment.meetObstacles(e, "mud");
				slowed = true;
				break;
			case 13 :
				ObstacleTreatment.meetObstacles(e, "water");
				break;
			}	
			
			if(!slowed) {
				e.setSpeed(e.getBaseSpeed());
			}
			return false;
		}
	}
	
	/**
	 * @brief function will improve the volatile variable treasure find to scale the speed of the explorers
	 */
	public synchronized void find() {
		treasureFind++;
		//System.out.println("Tresor trouver on en est a : " + treasureFind);
	}
}
