package treatment;

import java.util.HashMap;

import character.Equipment;
import character.Explorer;
import game.Simulation;

/**
 * @brief Class Singleton of Treatment when facing an obstacle
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 *
 */
public class ObstacleTreatment {
	
	/**
	 * @brief Treatment when an explorer has encountered an obstacle
	 * 
	 * @param e The explorer
	 * @param nameObs The name of the obstacle
	 */
	public static void meetObstacles(Explorer e, String nameObs) {
		
		HashMap<String,Explorer> explorers = Simulation.explorers;
		
		int newSpeed = (int)(e.getBaseSpeed()/2);
		
		switch(nameObs) {
		case "water":
			needWater(explorers);
			break;
		case "mud":
			if (!hasEquipment(e)) {
				e.setSpeed(newSpeed);
			}
			break;
		case "blocked":
			CharacterTreatment.changeDir(e);
			break;
		}
		
	}
	
	/**
	 * @brief Check if an explorer has boots in his inventory
	 * 
	 * @param e The explorer to check
	 * @return false on failure
	 * @return true on success
	 */
	public static boolean hasEquipment(Explorer e) {
		boolean botte = false;
		if (e.getEquipment().isEmpty()) {
			botte = false;
		}else {
			for (Equipment equipments : e.getEquipment()) {
				if (equipments.getName().equals("Bottes")) {
					botte = true;
					break;
				}
				else {
					botte = false;
				}
			}
		}
		return botte;
	}
	
	/**
	 * @brief Function called when an explorer founds water, it will heal the most injured explorer
	 * @param explorers HashMap of explorers instances
	 */
	public static void needWater(HashMap<String,Explorer> explorers) {
		int minPV = 150;
		int pv = 0;
		Explorer applicant=null;
		for (Explorer explorer : explorers.values()){
			pv = explorer.getLifePoint();
			if (pv<minPV) {
				minPV=pv;
				applicant = explorer;
			}
		}
		if(applicant != null) {
			int gain = applicant.getLifePoint()*(int)50/100;
			if ((applicant.getLifePoint()+gain)< applicant.getLifePointMax()) {
				applicant.setLifePoint(applicant.getLifePoint()+gain);
			}
			else {
				applicant.setLifePoint(applicant.getLifePointMax());
			}
		}
	}
	
}
