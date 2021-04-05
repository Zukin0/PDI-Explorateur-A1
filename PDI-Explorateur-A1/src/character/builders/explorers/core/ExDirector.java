package character.builders.explorers.core;

import character.Explorer;
import character.builders.explorers.DoraBuilder;
import character.builders.explorers.JoeBuilder;

/**
 * @brief Director class for an explorer
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 */

public class ExDirector {
	//Here, we have our Builder explorer 
	private ExBuilder eB ;
	
	//This function allow to set a type of builder
	public void setExplorerBuilder(ExBuilder eB) { this.eB = eB; }
	//this function return the explorer created
	public Explorer getExplorer() { return eB.getE(); }
	
	/**
	 * @brief Create and build all the stats of an explorer depending on the builder chosen
	 */
	public void BuildExplorer() {
		eB.createExplorer();
		eB.upMapObject();
		eB.upCharacter();
		eB.upExplorer();
	}
}
