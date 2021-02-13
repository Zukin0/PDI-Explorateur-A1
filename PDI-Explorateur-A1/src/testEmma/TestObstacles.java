package testEmma;

import character.Explorer;
import character.builders.explorers.DoraBuilder;
import character.builders.explorers.core.ExBuilder;
import character.builders.explorers.core.ExDirector;
import data.Obstacles;
import data.Position;
import data.Size;

public class TestObstacles {

	//rencontre avec les obstacles fixes et modulables
	
	public static void meetObstacles(Explorer e, Obstacles o) {
		String name = o.getName();
		switch(name) {
		case "water":
			//prendre de l'eau et comm avec se potes
			break;
		case "mud":
			//ralentir si on a pas les bottes
			break;
		case "tree":
			//changer de direction
			break;
		case "stone": 
			//changer de direction
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
		
		meetObstacles(e,water);
		meetObstacles(e,mud);
		meetObstacles(e,stone);
		meetObstacles(e,tree);
	}
	
}
