package treatment;

import java.util.Arrays;
import character.Character;
import character.Explorer;
import character.WildAnimals;
import data.MapObjects;
import data.Position;
import data.Treasure;
import game.Simulation;
import ihm.GamePanel;
import thread.ExplorerThread;

/**
 * @brief this class is about the character's movement
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 * */
public class CharacterTreatment {
	
	/**
	 * @brief changes the moving direction of a character
	 * 
	 * @param a the Character
	 * */
	public static void changeDir(Character a) {
		int dir = (int)(Math.random() * 4);
		String str = "";
		switch(dir) {
		
		//Haut
		case 0 : str = " Haut : 0 => Y-Speed";
		break;
		
		//Bas
		case 1 : str = " Bas : 1 => Y+Speed";
		break;
		
		//Droite
		case 2 : str = " Droite : 2 => X+Speed";
		break;
		
		//Gauche
		case 3 : str = " Gauche : 3 => X-Speed";
		break;
		}
		a.setDir(dir);
	}
	
	/**
	 * @brief realized the progress of a Character on the map
	 * 
	 * @param a the Character
	 * */
	public static void move(Character a) {
		Position posG = a.getPosition();
		int posX = posG.getX();
		int posY = posG.getY();
		int speed = a.getSpeed();
		switch(a.getDir()) {
		
		//Up
		case 0 : posG.setY(posY-speed);
		break;
		
		//Down
		case 1 : posG.setY(posY+speed);
		break;
		
		//Right
		case 2 : posG.setX(posX+speed);
		break;
		
		//Left
		case 3 : posG.setX(posX-speed);
		break;
		}
	}
	
	/**
	 * @brief method called when an explorer has to help another
	 * 
	 * @param e the Explorer who needs help
	 * @param helper the Explorer that is going to help the other
	 * */
	public static void goHelp(Explorer e, Explorer helper) {
		if (e!=null) {
			int xFinish = e.getPosition().getX();
			int yFinish = e.getPosition().getY();
			int xStart = helper.getPosition().getX();
			int yStart = helper.getPosition().getY();
		
			int x = xFinish - xStart;
			int y = yFinish - yStart;
			if(Math.abs(x) > Math.abs(y)) {
				if(x < 0) {
					//left
					helper.setDir(3);
				} else {
					//right
					helper.setDir(2);
				}
			}else {
				if(y < 0) {
					//up
					helper.setDir(0);
				} else {
					//down
					helper.setDir(1);
				}
			}
		
			if(Math.abs(x) <=  Math.max(e.getAura(),helper.getAura())&& Math.abs(y) <= Math.max(e.getAura(),helper.getAura())) {
				System.out.println(helper.getName() + " : IS CLOSE TO " + e.getName());
				helper.setNearExp(true);
			}
		}
		
	}
	
	/**
	 * @brief predicts the future position of the character
	 * 
	 * @param c the Character
	 * */
	public static Position predictPos(Character c) {
		Position pos = c.getPosition();
		int speed = c.getSpeed();
		int futurX = pos.getX(),futurY = pos.getY();
		switch(c.getDir()) {
		
		//Up
		case 0 : futurY = pos.getY()-speed;
		break;
		
		//Down
		case 1 : futurY = pos.getY()+speed;
		break;
		
		//Right
		case 2 : futurX = pos.getX()+speed;
		break;
		
		//Left
		case 3 : futurX = pos.getX()-speed;
		break;
		}
		
		return new Position(futurX,futurY);
		
	}
	
	/**
	 * @brief 
	 * */
	public static boolean isPointInsideRect(Position pChar, Position pRect, int w, int h) {
			int xChar = pChar.getX();
			int yChar = pChar.getY();
			int xRect = pRect.getX();
			int yRect = pRect.getY();
			if((xChar >= xRect  &&  xChar <= (xRect+w))  
				&& (yChar >= yRect  &&  yChar <= (yRect+h))) {
				return true;
			}
			return false;
	}
	
	/**
	 * @brief know if the Character is at the borders of the  window
	 * 
	 * @param pChar the current position of the Character
	 * @param w
	 * @param h
	 * 
	 * @return false if is not
	 * @return true otherwise
	 * */
	public static boolean isBorderWindow(Position pChar, int w, int h) {
		int xChar = pChar.getX();
		int yChar = pChar.getY();
		
		if(xChar+w >= GamePanel.WIDTH || xChar < 0 || yChar+h >= GamePanel.HEIGHT || yChar < 0) {
			return true;
		}
		return false;
	}
	
	
	public static boolean contains(int[] arr, final int key) {
	    return Arrays.stream(arr).anyMatch(i -> i == key);
	}
	
	/**
	 * @brief method used to check if an Character meets another or a treasure
	 * 
	 * @param pChar the Explorer
	 * @param mC is a MapObject can be a WildAnimals or a Treasure
	 * @param eT is the Explorer's thread
	 * */
	public static void auraCheck(Character pChar, MapObjects mC, ExplorerThread eT) {
		
		//Calcul distance between two entity	
		double dis = Math.sqrt(Math.pow(pChar.getPosition().getX() - mC.getPosition().getX(), 2) + Math.pow(pChar.getPosition().getY() - mC.getPosition().getY(), 2));	
		
		//Checking
		if(pChar.getAura() >= dis) {
			if(mC.getClass() == WildAnimals.class) {
				MeetAnimal.meetAnimals((Explorer) pChar, (WildAnimals) mC);
			}	
			if(mC.getClass() == Treasure.class) {
				eT.find();
				Simulation.toRemove.add(mC.getName());
			}
		}
	}
	
	/**
	 * @brief
	 * 
	 * @param pChar the Explorer
	 * */
	public static boolean explorerSpawnable(Explorer pChar) {
				
		//Calcul distance between two entity
		for(Character mC : Simulation.characters.values()) {
			if(!mC.getName().equals(pChar.getName())) {
				double dis = Math.sqrt(Math.pow(pChar.getPosition().getX() - mC.getPosition().getX(), 2) + Math.pow(pChar.getPosition().getY() - mC.getPosition().getY(), 2));
				
				//Checking
				if(mC.getClass() == Explorer.class) {
					if((pChar.getAura() + mC.getAura()) >= dis) {
						return false;
					}
				}
				else {
					if(pChar.getAura() >= dis) {
						return false;
					}
				}

			}
		}
		for(Treasure t : Simulation.treasures.values()) {
			double dis = Math.sqrt(Math.pow(pChar.getPosition().getX() - t.getPosition().getX(), 2) + Math.pow(pChar.getPosition().getY() - t.getPosition().getY(), 2));
			
			//Checking
			if(pChar.getAura() >= dis)
				return false;
		}
		return true;
		
	}
	
	/**
	 * @brief know if the Explorer can move or will meet an aura
	 * 
	 * @param e the Explorer
	 * 
	 * @return true if it is OK (will not meet an aura)
	 * @return false otherwise
	 * */
	public static boolean isFarEnough(Explorer e) {
		int futurX = CharacterTreatment.predictPos(e).getX();
		int futurY = CharacterTreatment.predictPos(e).getY();
		
		for(Explorer eTmp : Simulation.explorers.values()) {
			if(!e.getName().equals(eTmp.getName())) {
				double dis = Math.sqrt(Math.pow(futurX - eTmp.getPosition().getX(), 2)
						+ Math.pow(futurY - eTmp.getPosition().getY(), 2));
				//System.out.println(e.getName() + " : " + dis + " FROM " + eTmp.getName());
				if((e.getAura() + eTmp.getAura()) >= dis) {
					changeDir(e);
					return false;
				}
			}
		}
		return true;
	}
}
