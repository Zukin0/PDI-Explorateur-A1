package character.builders.equipment;

import character.builders.equipment.core.EqBuilder;

/**
 * @brief Builder class for boots : equipement
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 */

public class BootsBuilder  extends EqBuilder{
	
	/**
	 * @brief Put the stats of boots to the equipement created 
	 */
	
	
	public void upEquipment() {
		
		equipment.setName("Bottes");
		equipment.setPower(30);
		equipment.setPrice(0);
	}
}
