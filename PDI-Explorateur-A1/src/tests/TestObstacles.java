package tests;

import java.util.ArrayList;
import java.util.HashMap;

import character.Character;
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
import treatment.CharacterTreatment;

public class TestObstacles {
	
	private static HashMap<String,Explorer> explorers = new HashMap<String,Explorer>();

	//rencontre avec les obstacles fixes et modulables
	
	public static void meetObstacles(Explorer e, String nameObs) {
		
//		HashMap<String,Explorer> explorers = Simulation.explorers;
		
		//String name = o.getName();
		int oldSpeed = e.getSpeed();
		int newSpeed = e.getSpeed()*(int)50/100;
		
		//penser a faire un while pour la boue pour reprendre sa vitesse normale
		
		switch(nameObs) {
		case "water":
			//take water and ask who need some
			System.out.println("j'ai trouvé de l'eau qui est mal en point ?");
			needWater(explorers);
			CharacterTreatment.changeDir(e);
			break;
		case "mud":
			//slow down
			if (!hasEquipment(e)) {
				System.out.println("j'ai pas de bottes alors je ralenti");
				e.setSpeed(newSpeed);
			}else {
				System.out.println("ouf j'ai des bottes");
			}
			e.setSpeed(oldSpeed);
			System.out.println("Je sors de la boue je retrouve ma vitesse : "+e.getSpeed());
			break;
		case "tree":
			//change direction
			System.out.println("je rencontre un arbre alors je change de direction");
			CharacterTreatment.changeDir(e);
			break;
		case "stone": 
			//changedirection
			System.out.println("je rencontre une roche alors je change de direction");
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
				if (equipments.getName().equals("bottes")) {
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
		int minPV = 200;
		int pv = 0;
		Explorer applicant=null;
		for (Explorer explorer : explorers.values()){
			pv = explorer.getLifePoint();
			if (pv<minPV) {
				minPV=pv;
				applicant = explorer;
			}
		}
		int gain = applicant.getLifePoint()*(int)50/100;
		System.out.println("bonjour je suis "+ applicant.getName() +" et j'ai besoin d'eau j ai plus que : "+applicant.getLifePoint());
		if (applicant.getLifePoint()+gain< applicant.getLifePointMax()) {
			applicant.setLifePoint(applicant.getLifePoint()+gain);
		}else {
			applicant.setLifePoint(applicant.getLifePointMax());
		}
		
		System.out.println("maintenant j ai : "+applicant.getLifePoint());
	}
	
	public static void main (String[] args) {
		
		//create explorer
		ExDirector creatorE = new ExDirector();
		
		ExBuilder bDora = new DoraBuilder();
		creatorE.setExplorerBuilder(bDora);
		creatorE.BuildExplorer();
		Explorer e = creatorE.getExplorer();
		
		ExBuilder bDora3 = new DoraBuilder();
		creatorE.setExplorerBuilder(bDora3);
		creatorE.BuildExplorer();
		Explorer e4 = creatorE.getExplorer() ;
		
		ExBuilder bJoe = new JoeBuilder();
		creatorE.setExplorerBuilder(bJoe);
		creatorE.BuildExplorer();
		Explorer e2 = creatorE.getExplorer();
		
		ExBuilder bDora2 = new DoraBuilder();
		creatorE.setExplorerBuilder(bDora2);
		creatorE.BuildExplorer();
		Explorer e3 = creatorE.getExplorer() ;
		e3.setName("Dora2");
		
		
		explorers.put("Dora1", e);
		explorers.put("Dora2", e3);
		explorers.put("Dora3", e4);
		explorers.put("Joe1", e2);
		
		//changer les pv
		e3.setLifePoint(16);
		
		String nameB = "bottes";
		String powerB = "ne perd pas de tps";
		int priceB = 10;
		Equipment bottes  = new Equipment(nameB,powerB,priceB);
		
		String nameJ = "jumelles";
		String powerJ = "voit loin";
		int priceJ = 10;
		Equipment jumelles  = new Equipment(nameJ,powerJ,priceJ);
		
		String nameH = "hache";
		String powerH = "je suis plus fort";
		int priceH = 10;
		Equipment hache  = new Equipment(nameH,powerH,priceH);
		
		ArrayList<Equipment> equipDora = new ArrayList<Equipment>();
		
		equipDora.add(hache);
		equipDora.add(jumelles);
		
		e.setEquiment(equipDora);
		
		meetObstacles(e,"water");
		meetObstacles(e,"mud");
		meetObstacles(e,"stone");
		meetObstacles(e,"tree");
		
	}
	
}
