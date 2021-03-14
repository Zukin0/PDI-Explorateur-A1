package treatment;

import java.util.Arrays;

import character.Character;
import character.Explorer;
import character.WildAnimals;
import data.MapObjects;
import data.Position;
import data.Size;
import data.Treasure;
import game.Simulation;
import game.TileMap;
import ihm.GamePanel;
import thread.ExplorerThread;

public class CharacterTreatment {
	
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
	
	public static void auraCheck(Character pChar, MapObjects mC, ExplorerThread eT) {
		
		//Calcul distance between two entity	
		double dis = Math.sqrt(Math.pow(pChar.getPosition().getX() - mC.getPosition().getX(), 2) + Math.pow(pChar.getPosition().getY() - mC.getPosition().getY(), 2));	
//		System.out.println("la distance et de : " + dis);
		
		//Checking
		if(pChar.getAura() >= dis) {
			//System.out.println("Dans la zone");
			if(mC.getClass() == WildAnimals.class)
				MeetAnimal.meetAnimals((Explorer) pChar, (WildAnimals) mC);
//			
			if(mC.getClass() == Treasure.class)
				eT.find();
				Simulation.toRemove.add(mC.getName());
		}
		else {
//			System.out.println("Pas dans la zone");
		}	
	}
}
