package character.builders.explorers.core;

import character.Explorer;
import character.builders.explorers.DoraBuilder;
import character.builders.explorers.JoeBuilder;

public class ExDirector {
	//Here, we have our Builder explorer 
	private ExBuilder eB ;
	
	//This function allow to set a type of builder
	public void setExplorerBuilder(ExBuilder eB) { this.eB = eB; }
	//this function return the explorer created
	public Explorer getExplorer() { return eB.getE(); }
	
	//Function which build an explorer
	public void BuildExplorer() {
		eB.createExplorer();
		eB.upMapObject();
		eB.upCharacter();
		eB.upExplorer();
	}
	
	//Example creation of an explorer
	public static void main (String[] args) {
		
		//Create the builder director
		ExDirector creator = new ExDirector() ;
		//Create specifique builder
		ExBuilder bDora = new DoraBuilder() ;
		
		//Set the builder type and create the explorer this type
		creator.setExplorerBuilder(bDora);
		creator.BuildExplorer();
		
		//Finally store the explorer created.
		
		Explorer e = creator.getExplorer() ;
		
		creator.BuildExplorer();
		Explorer e2 = creator.getExplorer() ;
		
		System.out.println(e);
		System.out.println(e2);
		e2.setAttackPoint(50);
		System.out.println("---------------------------------");
		System.out.println(e);
		System.out.println(e2);
	}
}
