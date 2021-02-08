package character.builders.explorers.core;

import character.Explorer;
import data.Position;
import data.Size;

public abstract class ExBuilder {
	
	//here we got a variable who want create
	protected Explorer explorer ;
	public Explorer getE() { return explorer; }

	//we create an empty version of these object
	public void createExplorer() {
		explorer = new Explorer();
	}
	
	//put its objectMap propriety 
	public abstract void upMapObject();
	
	//put its character stats
	public abstract void upCharacter();
	
	//Finalize put specificities values for explorer
	public abstract void upExplorer();	
}
