package character.builders.equipment.core;

import character.Equipment;

public abstract class EqBuilder {
	//here we got a variable who want create
		protected Equipment equipment ;
		public Equipment getEq() { return equipment; }
		
		//we create an empty version of these object
		public void createEquipment() {
			equipment = new Equipment() ;
		}
		
		//Finalize put specificities values for equipment
		public abstract void upEquipment();	
}
