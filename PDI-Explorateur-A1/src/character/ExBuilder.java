package character;

import data.Position;
import data.Size;

public abstract class ExBuilder {
	
	//here we got a variable who want create
	protected Explorer e ;
	public Explorer getE() { return e; }

	//we create an empty version of these object
	public void createExplorer() {
		e = new Explorer();
	}
	
	//put its objectMap propriety 
	public abstract void upMapObject();
	
	//put its character stats
	public abstract void upCharacter();
	
	//Finalize put specificities values for explorer
	public abstract void upExplorer();	
}
