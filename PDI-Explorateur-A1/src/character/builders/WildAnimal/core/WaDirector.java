package character.builders.WildAnimal.core;

import character.WildAnimals;

/**
 * @brief Director class for an wildAnimals
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 */

public class WaDirector {
	
	private WaBuilder wB ;
	
	public void setWildAnimalsBuilder(WaBuilder wB) { this.wB = wB; }
	public WildAnimals getAnimal() { return wB.getA(); }
	
	/**
	 * @brief Create and build all the stats of an wildAnimals depending on the builder chosen
	 */
	
	public void BuildWildAnimals() {
		wB.createAnimal();
		wB.upMapObject();
		wB.upCharacter();
		wB.upAnimals();
	}	

}
