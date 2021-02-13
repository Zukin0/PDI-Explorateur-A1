package character.builders.explorers;

import character.builders.explorers.core.ExBuilder;
import data.Position;
import data.Size;

public class JoeBuilder extends ExBuilder{

	public void upMapObject() {
		explorer.setSize(new Size(15, 20));
		explorer.setPosition(new Position(0,0));
		explorer.setMovable(true);
		explorer.setName("Joe");	
	}

	public void upCharacter() {
		explorer.setLifePoint(100);
		explorer.setLifePointMax(100);
		
		explorer.setAttackPoint(80);
		explorer.setAttackPointMax(100);
		
		explorer.setSpeed(12);
		explorer.setAura(5);
	}

	public void upExplorer() {
		explorer.setProbaCall(25);
		explorer.setProbaEscape(30);
		explorer.setProbaFight(50);
		
		explorer.setEquimentMax(2);
		
		explorer.setPrice(2000);
	}

}
