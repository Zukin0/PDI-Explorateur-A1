package character.builders.WildAnimal.core;

import character.WildAnimals;

public class WaDirector {
	
	private WaBuilder wB ;
	
	public void setWildAnimalsBuilder(WaBuilder wB) { this.wB = wB; }
	public WildAnimals getAnimal() { return wB.getA(); }
	
	public void BuildWildAnimals() {
		wB.createAnimal();
		wB.upMapObject();
		wB.upCharacter();
		wB.upAnimals();
	}	

}
