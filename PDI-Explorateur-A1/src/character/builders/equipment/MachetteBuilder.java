package character.builders.equipment;

import character.builders.equipment.core.EqBuilder;

/**
 * @brief Builder class for machette : equipement
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 */

public class MachetteBuilder extends EqBuilder{
	
	/**
	 * @brief Put the stats of machette to the equipement created 
	 */
	
	public void upEquipment() {
		
		equipment.setName("Machettes");
		equipment.setPower(10);
		equipment.setPrice(0);
	}
}
