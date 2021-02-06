package character;

public abstract class WaBuilder {
	
	protected WildAnimals animal ;
	public WildAnimals getA() { return animal; }
	
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
