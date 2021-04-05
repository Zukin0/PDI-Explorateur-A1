package treatment;

import java.util.ArrayList;
import java.util.HashMap;

import character.Equipment;
import character.Explorer;
import character.builders.explorers.DoraBuilder;
import character.builders.explorers.JoeBuilder;
import character.builders.explorers.core.ExBuilder;
import character.builders.explorers.core.ExDirector;
import data.Obstacles;
import data.Position;
import data.Size;
import game.Simulation;
import thread.ExplorerThread;

public class ObstacleTreatment {
	
	//private static HashMap<String,Explorer> explorers = new HashMap<String,Explorer>();

	//rencontre avec les obstacles fixes et modulables
	
	public static void meetObstacles(Explorer e, String nameObs) {
		
		HashMap<String,Explorer> explorers = Simulation.explorers;
		
		int oldSpeed = e.getBaseSpeed();
		int newSpeed = (int)(e.getBaseSpeed()/2);
		
		switch(nameObs) {
		case "water":
			//take water and ask who need some
			//System.out.println("j'ai trouvé de l'eau qui est mal en point ?");
			needWater(explorers);
			break;
		case "mud":
			//slow down
			if (!hasEquipment(e)) {
				//System.out.println("j'ai pas de bottes alors je ralenti");
				e.setSpeed(newSpeed);
			}
			//System.out.println("Je sors de la boue je retrouve ma vitesse : "+e.getSpeed());
			break;
		case "blocked":
			//tree, stone or side
			//change direction
			//System.out.println("je suis bloquée je change de direction");
			CharacterTreatment.changeDir(e);
			break;
		case "treasure": 
			//traitement a faire
			//recuperer
			//comm avec others
			break;
		}
		
	}
	
	public static boolean hasEquipment(Explorer e) {
		boolean botte = false;
		if (e.getEquipment().isEmpty()) {
			botte = false;
		}else {
			for (Equipment equipments : e.getEquipment()) {
				if (equipments.getName().equals("Bottes")) {
					botte = true;
					break;
				}else {
					botte = false;
				}
			}
		}
		return botte;
	}
	
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
			//System.out.println("bonjour je suis "+ applicant.getName() +" et j'ai besoin d'eau j ai plus que : "+applicant.getLifePoint());
			if ((applicant.getLifePoint()+gain)< applicant.getLifePointMax()) {
				applicant.setLifePoint(applicant.getLifePoint()+gain);
			}else {
				applicant.setLifePoint(applicant.getLifePointMax());
			}
		}
	}
	
}
