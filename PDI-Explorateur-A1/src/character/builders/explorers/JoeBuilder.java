package character.builders.explorers;

import character.builders.explorers.core.ExBuilder;
import data.Position;
import data.Size;

//Here, we have class that create a Joe Explorer
public class JoeBuilder extends ExBuilder{


	public void upMapObject() {
		explorer.setSize(new Size(15, 20));
		explorer.setPosition(new Position(0,1));
		explorer.setMovable(true);
		explorer.setName("Joe");	
	}

	
	public void upCharacter() {
		//more life point than others
		explorer.setLifePoint(150);
		explorer.setLifePointMax(150);
		
		explorer.setAttackPoint(5);
		explorer.setAttackPointMax(10);
		
		explorer.setSpeed(12);
		explorer.setAura(5);	
	}

	
	public void upExplorer() {
		//calls others more often
		explorer.setProbaCall(55);
		explorer.setProbaEscape(25);
		explorer.setProbaFight(20);
		
		explorer.setEquimentMax(1);
		
		explorer.setPrice(1500);
	}

}
