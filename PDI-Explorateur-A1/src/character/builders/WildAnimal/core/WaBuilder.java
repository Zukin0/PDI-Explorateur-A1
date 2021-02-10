package character.builders.WildAnimal.core;

import character.WildAnimals;

public abstract class WaBuilder {
	
	//here we got a variable who want create
	protected WildAnimals animal ;
	public WildAnimals getA() { return animal; }
	
	//we create an empty version of these object
	public void createAnimal() {
		animal = new WildAnimals() ;
	}
	
	//put its objectMap propriety 
	public abstract void upMapObject();
	
	//put its character stats
	public abstract void upCharacter();
	
	//Finalize put specificities values for animals
	public abstract void upAnimals();	
}
