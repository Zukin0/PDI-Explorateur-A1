package character.builders.equipment.core;

import character.Equipment;

public class EqDirector {
	
	private EqBuilder wEq ;
	
	public void setEquipmentBuilder(EqBuilder wEq) { this.wEq = wEq; }
	public Equipment getEquipment() { return wEq.getEq(); }
	
	public void BuildEquipment() {
		wEq.createEquipment();
		wEq.upEquipment();
	}	

}
