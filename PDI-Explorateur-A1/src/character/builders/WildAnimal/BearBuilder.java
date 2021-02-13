package character.builders.WildAnimal;

import character.builders.WildAnimal.core.WaBuilder;
import data.Position;
import data.Size;

public class BearBuilder extends WaBuilder{

	public void upMapObject() {
		animal.setSize(new Size(15, 20));
		animal.setPosition(new Position(0,0));
		animal.setMovable(true);
		animal.setName("Bear");	
	}

	public void upCharacter() {
		//more life points
		animal.setLifePoint(150);
		animal.setLifePointMax(150);
		
		animal.setAttackPoint(80);
		animal.setAttackPointMax(100);
		
		animal.setSpeed(12);
		animal.setAura(5);
	}

	public void upAnimals() {
		animal.setTerritorySize(new Size(50,50));
	}

}
