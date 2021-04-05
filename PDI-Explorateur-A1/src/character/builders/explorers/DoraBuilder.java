package character.builders.explorers;

import character.builders.explorers.core.ExBuilder;
import data.Position;
import data.Size;

/**
 * @brief Builder class for Dora : explorer
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 */

public class DoraBuilder extends ExBuilder{
	
	/**
	 * @brief takes the created explorer and puts the dora mapObject stats on it
	 */
	
			public void upMapObject() {
				explorer.setSize(new Size(15, 20));
				explorer.setPosition(new Position(-100,-100));
				explorer.setMovable(true);
				explorer.setName("Dora");	
			}

		
			/**
			 * @brief takes the created explorer and puts the dora character stats on it
			 */
			public void upCharacter() {
				explorer.setLifePoint(100);
				explorer.setLifePointMax(100);
				
				explorer.setAttackPoint(3);
				explorer.setAttackPointMax(10);
				
				explorer.setBaseSpeed(6);
				explorer.setSpeed(6);
				explorer.setAura(50);
			}

			/**
			 * @brief takes the created explorer and puts the Dora explorer spécific stats on it
			 */
			public void upExplorer() {
				explorer.setProbaCall(20);
				explorer.setProbaEscape(30);
				explorer.setProbaFight(50);
				
				explorer.setEquimentMax(2);
				
				explorer.setPrice(2000);
			}
			
}
