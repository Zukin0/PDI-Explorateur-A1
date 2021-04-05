package character.builders.equipment.core;

import character.Equipment;
/**
 * @brief Director class for an equipement
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 */
public class EqDirector {
	
	private EqBuilder wEq ;
	
	public void setEquipmentBuilder(EqBuilder wEq) { this.wEq = wEq; }
	public Equipment getEquipment() { return wEq.getEq(); }
	
	/**
	 * @brief Create and build an equipement depending on the builder chosen
	 */
	public void BuildEquipment() {
		wEq.createEquipment();
		wEq.upEquipment();
	}	

}
