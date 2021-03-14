package tests;

import java.util.ArrayList;
import java.util.HashMap;

import character.Character;
import character.Equipment;
import character.Explorer;
import character.builders.explorers.DoraBuilder;
import character.builders.explorers.core.ExBuilder;
import character.builders.explorers.core.ExDirector;
import data.Obstacles;
import data.Position;
import data.Size;
import game.Simulation;
import thread.ExplorerThread;
import treatment.CharacterTreatment;

public class TestObstacles {

	//rencontre avec les obstacles fixes et modulables
	
	public static void meetObstacles(Explorer e, String nameObs) {
		
		HashMap<String,Explorer> explorers = Simulation.explorers;
		HashMap<String,Character> characters = Simulation.characters;
		
		//String name = o.getName();
		int oldSpeed = e.getSpeed();
		int newSpeed = e.getSpeed()*(int)50/100;
		boolean botte = false;
		
		//penser a faire un while pour la boue pour reprendre sa vitesse normale
		
		switch(nameObs) {
		case "water":
			//prendre de l'eau et comm avec se potes
			System.out.println(e.getName() + " :j'ai trouvé de l'eau qui a besoin");
			CharacterTreatment.changeDir(e);
			break;
		case "mud":
			//ralentir si on a pas les bottes
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
				if (botte==false) {
					System.out.println(e.getName() + " :j'ai pas de bottes alors je ralenti");
					e.setSpeed(newSpeed);
				}else {
					System.out.println(e.getName() + " :ouf j'ai des bottes");
				}
			}
			e.setSpeed(oldSpeed);
			System.out.println(e.getName() + " : Je sors de la boue je retrouve ma vitesse : "+e.getSpeed());
			break;
		case "tree":
			//changer de direction
			System.out.println(e.getName() + " : je rencontre un arbre alors je change de direction");
			CharacterTreatment.changeDir(e);
			break;
		case "stone": 
			//changer de direction
			System.out.println(e.getName() + " : je rencontre une roche alors je change de direction");
			CharacterTreatment.changeDir(e);
			break;
		case "treasure": 
			//traitement a faire
			//recuperer
			//comm avec others
			break;
		}
		
	}
	
	public static void main (String[] args) {
		
		//create explorer
		ExDirector creatorE = new ExDirector();
		
		ExBuilder bDora = new DoraBuilder();
		creatorE.setExplorerBuilder(bDora);
		creatorE.BuildExplorer();
		Explorer e = creatorE.getExplorer();
		
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
