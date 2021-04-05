package character.builders.WildAnimal;

import character.builders.WildAnimal.core.WaBuilder;
import data.Position;
import data.Size;

/**
 * @brief Builder class for Bear : wildAnimals
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 */
public class BearBuilder extends WaBuilder{

	/**
	 * @brief takes the created wildAnimals and puts the Bear mapObject stats on it
	 */
	public void upMapObject() {
		animal.setSize(new Size(15, 20));
		animal.setPosition(new Position(250,300));
		animal.setMovable(true);
		animal.setName("Bear");	
	}

	/**
	 * @brief takes the created wildAnimals and puts the Bear character stats on it
	 */
	public void upCharacter() {
		//more life points
		animal.setLifePoint(150);
		animal.setLifePointMax(150);
		
		animal.setAttackPoint(6);
		animal.setAttackPointMax(10);
		
		animal.setSpeed(12);
		animal.setAura(5);
	}

	/**
	 * @brief takes the created wildAnimals and puts the Bear specific animals stats on it
	 */
	public void upAnimals() {
		animal.setTerritorySize(new Size(50,50));
	}

}
