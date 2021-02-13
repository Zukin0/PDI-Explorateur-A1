package character.builders.WildAnimal;

import character.builders.WildAnimal.core.WaBuilder;
import data.Position;
import data.Size;

public class WolfBuilder extends WaBuilder{

	public void upMapObject() {
		animal.setSize(new Size(15, 20));
		animal.setPosition(new Position(0,0));
		animal.setMovable(true);
		animal.setName("Wolf");	
	}

	public void upCharacter() {
		animal.setLifePoint(100);
		animal.setLifePointMax(100);
		
		//more attack points
		animal.setAttackPoint(8);
		animal.setAttackPointMax(10);
		
		animal.setSpeed(12);
		animal.setAura(5);
	}

	public void upAnimals() {
		
	}

}
