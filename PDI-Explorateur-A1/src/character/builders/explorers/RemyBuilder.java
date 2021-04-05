package character.builders.explorers;

import character.builders.explorers.core.ExBuilder;
import data.Position;
import data.Size;

/**
 * @brief Builder class for Remy : explorer
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 */
public class RemyBuilder extends ExBuilder{

	/**
	 * @brief takes the created explorer and puts the Remy mapObject stats on it
	 */

	public void upMapObject() {
		explorer.setSize(new Size(15, 20));
		explorer.setPosition(new Position(-100,-100));
		explorer.setMovable(true);
		explorer.setName("Remy");	
	}

	/**
	 * @brief takes the created explorer and puts the Remy character stats on it
	 */
	
	public void upCharacter() {
		explorer.setLifePoint(100);
		explorer.setLifePointMax(100);
		
		explorer.setAttackPoint(5);
		explorer.setAttackPointMax(10);
		
		//walks faster than others 
		explorer.setBaseSpeed(8);
		explorer.setSpeed(8);
		explorer.setAura(50);	
	}

	/**
	 * @brief takes the created explorer and puts the Remy explorer spécific stats on it
	 */
	
	public void upExplorer() {
		//escapes more often than others
		explorer.setProbaCall(20);
		explorer.setProbaEscape(60);
		explorer.setProbaFight(20);
		
		explorer.setEquimentMax(1);
		
		explorer.setPrice(1500);
	}

}
