package character.builders.equipment.core;

import character.Equipment;

/**
 * @brief Abstract builder class for an equipement
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 */

public abstract class EqBuilder {
		//here we got a variable who want create
		protected Equipment equipment ;
		public Equipment getEq() { return equipment; }
		
		/**
		 * @brief Create an empty version of these object
		 */
		public void createEquipment() {
			equipment = new Equipment() ;
		}
		
		/**
		 * @brief takes the created equipement and puts the chosen stats on it
		 */
		public abstract void upEquipment();	
}
