package character.builders.WildAnimal;

import character.builders.WildAnimal.core.WaBuilder;
import data.Position;
import data.Size;

/**
 * @brief Builder class for Wolf : wildAnimals
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 */
public class WolfBuilder extends WaBuilder{

	/**
	 * @brief takes the created wildAnimals and puts the Wolf mapObject stats on it
	 */
	public void upMapObject() {
		animal.setSize(new Size(15, 20));
		animal.setPosition(new Position(300,100));
		animal.setMovable(true);
		animal.setName("Wolf");	
	}
	
	/**
	 * @brief takes the created wildAnimals and puts the Wolf character stats on it
	 */

	public void upCharacter() {
		animal.setLifePoint(100);
		animal.setLifePointMax(100);
		
		//more attack points
		animal.setAttackPoint(8);
		animal.setAttackPointMax(10);
		
		animal.setSpeed(2);
		animal.setAura(5);
		

	}
	
	/**
	 * @brief takes the created wildAnimals and puts the Wolf specific animals stats on it
	 */

	public void upAnimals() {
		animal.setTerritorySize(new Size(100,100));
	}

}
