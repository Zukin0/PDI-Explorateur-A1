package character.builders.WildAnimal;

import character.builders.WildAnimal.core.WaBuilder;
import data.Position;
import data.Size;

public class WolfBuilder extends WaBuilder{

	@Override
	public void upMapObject() {
		// TODO Auto-generated method stub
		animal.setSize(new Size(15, 20));
		animal.setPosition(new Position(0,0));
		animal.setMovable(true);
		animal.setName("Wolf");	
	}

	@Override
	public void upCharacter() {
		// TODO Auto-generated method stub
		animal.setLifePoint(100);
		animal.setLifePointMax(100);
		
		animal.setAttackPoint(80);
		animal.setAttackPointMax(100);
		
		animal.setSpeed(12);
		animal.setAura(5);
	}

	@Override
	public void upAnimals() {
		// TODO Auto-generated method stub
		
	}

}
