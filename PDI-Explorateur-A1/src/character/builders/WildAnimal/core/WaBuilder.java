package character.builders.WildAnimal.core;

import character.WildAnimals;

/**
 * @brief Abstract builder class for wildAnimals
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 */

public abstract class WaBuilder {
	
	//here we got a variable who want create
	protected WildAnimals animal ;
	public WildAnimals getA() { return animal; }
	
	/**
	 * @brief Create an empty version of these wildAnimals
	 */
	public void createAnimal() {
		animal = new WildAnimals() ;
	}
	
	/**
	 * @brief takes the created wildAnimals and puts the chosen mapObject stats on it
	 */
	public abstract void upMapObject();
	
	/**
	 * @brief takes the created wildAnimals and puts the chosen character stats on it
	 */
	public abstract void upCharacter();
	
	/**
	 * @brief takes the created wildAnimals and puts the chosen specific animals stats on it
	 */
	public abstract void upAnimals();	
}
