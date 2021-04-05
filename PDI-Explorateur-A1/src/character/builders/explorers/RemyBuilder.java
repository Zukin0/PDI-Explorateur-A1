package character.builders.explorers;

import character.builders.explorers.core.ExBuilder;
import data.Position;
import data.Size;

//Here, we have class that create a Remy Explorer
public class RemyBuilder extends ExBuilder{


	public void upMapObject() {
		explorer.setSize(new Size(15, 20));
		explorer.setPosition(new Position(-100,-100));
		explorer.setMovable(true);
		explorer.setName("Remy");	
	}

	
	public void upCharacter() {
		explorer.setLifePoint(100);
		explorer.setLifePointMax(100);
		
		explorer.setAttackPoint(5);
		explorer.setAttackPointMax(10);
		
		//walks faster than others 
		explorer.setBaseSpeed(8);
		explorer.setSpeed(8);
		explorer.setAura(50);	
	}

	
	public void upExplorer() {
		//escapes more often than others
		explorer.setProbaCall(20);
		explorer.setProbaEscape(60);
		explorer.setProbaFight(20);
		
		explorer.setEquimentMax(1);
		
		explorer.setPrice(1500);
	}

}
