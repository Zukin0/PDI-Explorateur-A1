package character.builders.WildAnimal;

import character.builders.WildAnimal.core.WaBuilder;
import data.Position;
import data.Size;

public class EagleBuilder extends WaBuilder{

	public void upMapObject() {
		animal.setSize(new Size(15, 20));
		animal.setPosition(new Position(0,0));
		animal.setMovable(true);
		animal.setName("Eagle");	
	}

	public void upCharacter() {
		animal.setLifePoint(100);
		animal.setLifePointMax(100);
		
		animal.setAttackPoint(80);
		animal.setAttackPointMax(100);
		
		animal.setSpeed(12);
		//higher aura than orthers
		animal.setAura(7);
	}

	public void upAnimals() {
		animal.setTerritorySize(new Size(200,200));
	}

}