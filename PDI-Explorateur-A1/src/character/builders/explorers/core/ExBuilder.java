package character.builders.explorers.core;

import character.Explorer;
import data.Position;
import data.Size;

/**
 * @brief Abstract Builder class for an explorer
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 */

public abstract class ExBuilder {
	
	//here we got a variable who want create
	protected Explorer explorer ;
	public Explorer getE() { return explorer; }
	
	/**
	 * @brief Create an empty version of these explorer
	 */
	public void createExplorer() {
		explorer = new Explorer();
	}
	
	/**
	 * @brief takes the created explorer and puts the chosen mapObject stats on it
	 */
	public abstract void upMapObject();
	
	/**
	 * @brief takes the created explorer and puts the chosen character stats on it
	 */
	public abstract void upCharacter();
	
	/**
	 * @brief takes the created explorer and puts the chosen explorer spécific stats on it
	 */
	public abstract void upExplorer();	
}
