package treatment;
import character.Character;
import character.Explorer;
import data.Position;
import data.Size;
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
		//System.out.println("\nCHANGED DIRECTION => " + a.getName() + " : " + str + "\n");
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
		//System.out.println(a.getName() + " : " +  posG.toString());
	}
	
	public static void moveE(Explorer e) {
		Position posG = e.getPosition();
		int posX = posG.getX();
		int posY = posG.getY();
		int speed = e.getSpeed();
		int movement = 1;
		
		while ((movement<=speed)/*&&(!ExplorerThread.collision(e))*/) {
			switch(e.getDir()) {
			
			//Up
			case 0 : posG.setY(posY-movement);
			break;
			
			//Down
			case 1 : posG.setY(posY+movement);
			break;
			
			//Right
			case 2 : posG.setX(posX+movement);
			break;
			
			//Left
			case 3 : posG.setX(posX-movement);
			break;
			}
			movement++;
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
	
	public void suppCharacter(Character c) {
		
	}
}
