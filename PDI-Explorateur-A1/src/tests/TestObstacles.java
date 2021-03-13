package tests;

import java.util.ArrayList;

import character.Equipment;
import character.Explorer;
import character.builders.explorers.DoraBuilder;
import character.builders.explorers.core.ExBuilder;
import character.builders.explorers.core.ExDirector;
import data.Obstacles;
import data.Position;
import data.Size;
import treatment.CharacterTreatment;

public class TestObstacles {

	//rencontre avec les obstacles fixes et modulables
	
	public static void meetObstacles(Explorer e, Obstacles o) {
		
		String name = o.getName();
		int oldSpeed = e.getSpeed();
		int newSpeed = e.getSpeed()*(int)50/100;
		boolean botte = false;
		
		//penser a faire un while pour la boue pour reprendre sa vitesse normale
		
		switch(name) {
		case "water":
			//prendre de l'eau et comm avec se potes
			System.out.println("j'ai trouvé de l'eau qui a besoin");
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
					System.out.println("j'ai pas de bottes alors je ralenti");
					System.out.println("my speed : "+e.getSpeed());
					e.setSpeed(newSpeed);
					System.out.println("my speed : "+e.getSpeed());
				}else {
					System.out.println("ouf j'ai des bottes");
				}
			}
			e.setSpeed(oldSpeed);
			System.out.println("Je sors de la boue je retrouve ma vitesse : "+e.getSpeed());
			break;
		case "tree":
			//changer de direction
			System.out.println("je rencontre un arbre alors je change de direction");
			System.out.println( "avant : "+e.getDir());
			CharacterTreatment.changeDir(e);
			System.out.println( "après : "+e.getDir());
			break;
		case "stone": 
			//changer de direction
			System.out.println("je rencontre une roche alors je change de direction");
			System.out.println( "avant : "+e.getDir());
			CharacterTreatment.changeDir(e);
			System.out.println( "après : "+e.getDir());
			break;
		}
		
	}
	
	public static void main (String[] args) {
		//create obstacles
		//water
		int wWater = 5;
		int hWater = 7;
		Size waterSize = new Size(wWater, hWater);
		
		int xWater = 10;
		int yWater = 10;
		Position posWater = new  Position(xWater, yWater);
		Obstacles water = new Obstacles("water", waterSize, posWater, false, "modulable");
		
		//mud
		int wMud = 2;
		int hMud = 5;
		Size mudSize = new Size(wMud, hMud);
		
		int xMud = 5;
		int yMud = 6;
		Position posMud = new  Position(xMud, yMud);
		Obstacles mud = new Obstacles("mud", mudSize, posMud, false, "modulable");
		
		//stone/trees
		int wStone = 1;
		int hStone = 1;
		Size stoneSize = new Size(wStone, hStone);
		
		int xStone = 5;
		int yStone = 7;
		Position posStone = new  Position(xStone, yStone);
		Obstacles stone = new Obstacles("stone", stoneSize, posStone, false, "fixed");
		
		int wTree = 1;
		int hTree = 1;
		Size treeSize = new Size(wTree, hTree);
		
		int xTree = 4;
		int yTree = 4;
		Position posTree = new  Position(xTree, yTree);
		Obstacles tree = new Obstacles("tree", treeSize, posTree, false, "fixed");
		
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
		
		meetObstacles(e,water);
		meetObstacles(e,mud);
		meetObstacles(e,stone);
		meetObstacles(e,tree);
		System.out.println("hey");
	}
	
}
