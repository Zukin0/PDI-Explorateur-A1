package character.builders.explorers;

import character.builders.explorers.core.ExBuilder;
import data.Position;
import data.Size;

public class JoeBuilder extends ExBuilder{

	public void upMapObject() {
		explorer.setSize(new Size(15, 20));
		explorer.setPosition(new Position(250,300));
		explorer.setMovable(true);
		explorer.setName("Joe");	
	}

	public void upCharacter() {
		explorer.setLifePoint(150);
		explorer.setLifePointMax(150);
		
		explorer.setAttackPoint(5);
		explorer.setAttackPointMax(10);
		
		explorer.setSpeed(6);
		explorer.setAura(5);
	}

	public void upExplorer() {
		explorer.setProbaCall(55);
		explorer.setProbaEscape(25);
		explorer.setProbaFight(20);
		
		explorer.setEquimentMax(1);
		
		explorer.setPrice(1500);
	}

	
}
