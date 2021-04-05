package character.builders.equipment;

import character.builders.equipment.core.EqBuilder;

/**
 * @brief Builder class for binocular : equipement
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 */

public class BinocularBuilder extends EqBuilder{
	
	/**
	 * @brief Put the stats of binocular to the equipement created 
	 */
	
	public void upEquipment() {
		
		equipment.setName("Jumelles");
		equipment.setPower(15);
		equipment.setPrice(0);
	}

}
