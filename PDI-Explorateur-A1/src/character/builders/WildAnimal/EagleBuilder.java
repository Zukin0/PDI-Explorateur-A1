package character.builders.WildAnimal;

import character.builders.WildAnimal.core.WaBuilder;
import data.Position;
import data.Size;

/**
 * @brief Builder class for Eagle : wildAnimals
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 */
public class EagleBuilder extends WaBuilder{

	/**
	 * @brief takes the created wildAnimals and puts the Eagle mapObject stats on it
	 */
	public void upMapObject() {
		animal.setSize(new Size(15, 20));
		animal.setPosition(new Position(600,300));
		animal.setMovable(true);
		animal.setName("Eagle");	
	}
	
	/**
	 * @brief takes the created wildAnimals and puts the Eagle character stats on it
	 */

	public void upCharacter() {
		animal.setLifePoint(100);
		animal.setLifePointMax(100);
		
		animal.setAttackPoint(4);
		animal.setAttackPointMax(10);
		
		animal.setSpeed(12);
		//higher aura than orthers
		animal.setAura(7);
	}

	/**
	 * @brief takes the created wildAnimals and puts the Eagle specific animals stats on it
	 */
	public void upAnimals() {
		animal.setTerritorySize(new Size(200,200));
	}

}