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
import tests.TestObstacles;
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
				System.out.println(e.getName() + " : JE FUIS PENDANT " + Constant.NUMBER_ESCAPE_ITERATIONS + " ms VERS :" + e.getDir());
				while(cpt != Constant.NUMBER_ESCAPE_ITERATIONS) {
					SimulationUtility.unitTime();	
					if(!CharacterTreatment.isBorderWindow(CharacterTreatment.predictPos(e), e.getSize().getWidth(), e.getSize().getHeight())) {
						//CharacterTreatment.move(e);
						if(!side(e)) {
							//CharacterTreatment.move(e);
							CharacterTreatment.moveE(e);
						}else if(side(e)) {
							CharacterTreatment.changeDir(e);
						}
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
						if(!side(e)) {
							//CharacterTreatment.move(e);
							CharacterTreatment.moveE(e);
						}else if(side(e)) {
							CharacterTreatment.changeDir(e);
						}
					}
					
					/*
					 * FIN TESTING BRUTE
					 */
					//CharacterTreatment.move(e);
				}
				cpt++;
			}
		}
	}
	
	/*collision obstacle et trésors
	 * test EMMA
	 * pb a gérer : si jamais sa pos arriver après un obstacle il traverse qd meme je dois tester tout le chemin 
	 * genre si il avance de deux cases je dois teste + et ++
	 * faudrait faire un test sur son chemin du style
	 * while j avance et que j ai pas un obs et que j ai pas atteint objectif (pos suivante) alors c bon
	 * des que je trouve un obs mettre false*/
//public static boolean collision(Explorer e) {
//		boolean collision  = false;
//		//division par 32 car les explorateurs ont une position en pixels alors que la map est un tableau 20x28
////		int posX = (CharacterTreatment.predictPos(e).getX()-TileMap.offsetX)/37;
////		int posY = (CharacterTreatment.predictPos(e).getY()-TileMap.offsetY)/37;
//		
//		int posX = CharacterTreatment.predictPos(e).getX();
//		int posY = CharacterTreatment.predictPos(e).getY();
//		
//		int cWidth = e.getSize().getWidth();
//		int cHeight = e.getSize().getHeight();
//		
//		int xLeft = (int) (posX - TileMap.offsetX) / 37;
//		int xRight = (int) (posX + cWidth - TileMap.offsetX)/ 37;
//		int yTop = (int) (posY- TileMap.offsetY) / 37;
//		int yBottom = (int) (posY + cHeight- TileMap.offsetY) / 37;
		
		//System.out.println(e.getName() + " : " + e.getDir() + ", posX : " + xLeft + ", posY : " + yTop);
		
//		switch(e.getDir()) {
//		
//		//Up
//		case 0 : System.out.println(e.getName() + " : Up, posX : " + xLeft + ", posY : " + yTop);
//		break;
//		
//		//Down
//		case 1 : System.out.println(e.getName() + " : Down, posX : " + xLeft + ", posY : " + yBottom);
//		break;
//		
//		//Right
//		case 2 : System.out.println(e.getName() + " : Right, posX : " + xRight+ ", posY : " + yTop);
//		break;
//		
//		//Left
//		case 3 : System.out.println(e.getName() + " : Left, posX : " + xLeft+ ", posY : " + yTop);
//		break;
//		}

		
//		System.out.println(TileMap.getType(5, 6));
		
		/*if (TileMap.map[posX][posY]!=7) {
			//connaitre l'obstacle
			switch(TileMap.map[posX][posY]) {
			case 6 :
				System.out.println(e.getName() + " : Boue");
				//TestObstacles.meetObstacles(e, "mud");
				break;
			case 11 :
				System.out.println(e.getName() + " : Rocher");
				//TestObstacles.meetObstacles(e, "stone");
				break;
			case 12 :
				System.out.println(e.getName() + " : Arbres");
				//TestObstacles.meetObstacles(e, "tree");
				break;
			case 13 :
				System.out.println(e.getName() + " : Eau");
				//TestObstacles.meetObstacles(e, "water");
				break;
			case 14 :
				System.out.println(e.getName() + " : Tresor");
				//TestObstacles.meetObstacles(e, "treasure");
				break;
			}
			collision = true;
		}/*else {
			collision=false;
		}*/
//		return collision;
//		//division par 32 car les explorateurs ont une position en pixels alors que la map est un tableau 20x28
//		int futurPosX = CharacterTreatment.predictPos(e).getX()/32;
//		int futurPosY = CharacterTreatment.predictPos(e).getY()/32;
//		if (TileMap.map[futurPosX][futurPosY]==7) {
//			return false;
//		}else {
//			//connaitre l'obstacle
//			switch(TileMap.map[futurPosX][futurPosY]) {
//			case 6 :
//				System.out.println("6");
//				TestObstacles.meetObstacles(e, "mud");
//				break;
//			case 11 :
//				System.out.println("11");
//				TestObstacles.meetObstacles(e, "stone");
//				break;
//			case 12 :
//				System.out.println("12");
//				TestObstacles.meetObstacles(e, "tree");
//				break;
//			case 13 :
//				System.out.println("13");
//				TestObstacles.meetObstacles(e, "water");
//				break;
//			case 14 :
//				TestObstacles.meetObstacles(e, "treasure");
//				break;
//			}
//			return true;
//		}
//	}
	
	//je suis sur les bords ?
	public static boolean side(Explorer e) {
		boolean side=false;
		int dir = e.getDir();
		int posX = e.getPosition().getX()/32;
		int posY = e.getPosition().getY()/32;
		if(((posX==1)&&(dir==3))|| ((posX==26)&&(dir==2)) || ((posY==1)&&(dir==0)) || ((posY==15)&&(dir==1))){
			side=true;
		}
		return side;
	}

}
