package treatment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import character.*;
import character.Character;
import character.builders.WildAnimal.WolfBuilder;
import character.builders.WildAnimal.core.WaBuilder;
import character.builders.WildAnimal.core.WaDirector;
import character.builders.explorers.DoraBuilder;
import character.builders.explorers.JoeBuilder;
import character.builders.explorers.MikeBuilder;
import character.builders.explorers.core.ExBuilder;
import character.builders.explorers.core.ExDirector;
import game.Simulation;
import data.Position;

public class MeetAnimal{
	
	static int nbFights = 0;
	
	public static void meetAnimals(Explorer e, WildAnimals a) {
		
		HashMap<String,Explorer> explorers = Simulation.explorers;
		HashMap<String,Character> characters = Simulation.characters;
		HashMap<String,WildAnimals> animals = Simulation.animals;
		ArrayList<String> toRemove = Simulation.toRemove;
		String outcome = null;
		String action = null;
		Explorer helper = null;
		
		int simulation = Simulation.strategy;
		
		//recover probabilities
		float pf = e.getProbaFight();
		float pc = e.getProbaCall();
		float min = 0;
		float max = 100;
		
		Random r = new Random(); 
		float n = min + r.nextFloat() * (max - min);
		
		switch(simulation) {
		
		case 0 : //SMART MODE
			if (n>=0 && n<=pf) {
				//[0-pf]
				action= "fight";
			}else if(n>pf && n<=pf+pc) {
				//]pf-pf+pc]
				action= "call";
			}else {
				//]pf+pc-100]
				action= "escape";
			}
			break;
		case 1 : //FIGHT MODE
			action= "fight";
			break;
		case 2 : //ESCAPE MODE
			if (n>=0 && n<=pc) {
				//[0-pc]
				action= "call";
			}else {
				//]pc-100]
				action= "escape";
			}
			break;
		}
		
		switch(action) {
		case "fight":
			//fight 
			outcome = fight(e, a);
			if (outcome == "deathExplo" || outcome.equals("deathBoth")) {
				deathExplorer(e, simulation, explorers, characters,toRemove);
			}
			if(outcome == "deathAnimal" || outcome.equals("deathBoth")) {
				deathAnimal(a, toRemove);
			}
			break;
		case "call":
			//call a friend
			System.out.println(e.getName() + " : Besoin d'aide, ma position : "+
								e.getPosition().getX()+", "+e.getPosition().getY()+"\n");
			helper = findHelper(e, explorers);
			e.setWaiting(true);
			helper.setHelping(true);
			if(helper.isNearExp()==true) {
				//change explorer capacities to fight : add explorer and helper life points and attack points
				e.setAttackPoint(e.getAttackPoint()+helper.getAttackPoint());
				e.setLifePoint(e.getLifePoint()+helper.getLifePoint());
				outcome = fight(e, a); 
				//if he dies : kill both of explorers
				//otherwise : distribute explorer capacities
				if (outcome == "deathExplo" || outcome.equals("deathBoth")) {
					deathExplorer(e, simulation, explorers, characters,toRemove);
					deathExplorer(helper, simulation, explorers, characters,toRemove);
				}else {
					int attack = e.getAttackPoint()/2;
					int life = e.getLifePoint()/2;
					e.setAttackPoint(attack);
					e.setLifePoint(life);
					helper.setAttackPoint(attack);
					helper.setLifePoint(life);
					e.setWaiting(false);
					helper.setHelping(false);
					helper.setNearExp(false);
					CharacterTreatment.changeDir(e);
					CharacterTreatment.changeDir(helper);
				}
				if(outcome == "deathAnimal" || outcome.equals("deathBoth")) {
					deathAnimal(a,toRemove);
				}
			}
			//attendre sinon
			break;
		case "escape":
			//go away for n seconds
			System.out.println(e.getName() + " :Un animal sauvage.. JE FUIS");
			escapeDir(e,e.getDir());
			e.setEscaping(true);
			break;
		}
	}
	
	
	public static String fight(Explorer e, WildAnimals a) {
		int apE = e.getAttackPoint();
		int lpE = e.getLifePoint();
		int apA = a.getAttackPoint();
		int lpA = a.getLifePoint();
		String outcome = null;
		nbFights++;
		
		//fight while explorer !dead and animal !dead
		while (lpE!=0 && lpA!=0) {
			if(apA>lpE || apE>lpA){
				if(apA>lpE && apE>lpA) {
					lpE = 0;
					lpA = 0;
				}
				else if(apE>lpA) {
					lpA = 0;
					lpE = lpE-apA;
				}
				else if(apA>lpE) {
					lpE = 0;
					lpA = lpA-apE;
				}
			}else {
				lpE = lpE-apA;
				lpA = lpA-apE;
			}
			e.setLifePoint(lpE);
			a.setLifePoint(lpA);
		}
		
		//fight's outcome
		if(lpE==0 && lpA==0) {
			//explorer and animal are dead
			//comm
			System.out.println("Je suis "+e.getName()+" et je vais mourir et j'ai tue un animal\n");
			outcome = "deathBoth";
		}
		else if(lpA==0) {
			//animal is dead
			System.out.println("Je suis "+e.getName()+" et j'ai tue un animal il me reste : "+e.getLifePoint()+" points de vie\n");
			outcome = "deathAnimal";
		}
		else if(lpE==0) {
			//explorer is dead
			//comm
			System.out.println("Je suis "+e.getName()+" et je vais mourir\n");
			outcome = "deathExplo";
		}
		
		return outcome;
	}
	
	public static void escapeDir(Explorer e, int dir) {
		switch(dir) {
		case 0:
			//down
			e.setDir(1);
			break;
		case 1:
			//up
			e.setDir(0);
			break;
		case 2:
			//left
			e.setDir(3);
			break;
		case 3:
			//right
			e.setDir(2);
			break;
		}
	}
	
	public static Explorer findHelper(Explorer e, HashMap<String,Explorer> explorers) {
		
		double min = 100000;
		double dis =0;
		Explorer helper = null;
		
		for (Explorer explorer : explorers.values()){
			if(!explorer.equals(e)) {
				dis = Math.sqrt(Math.pow(e.getPosition().getX() - explorer.getPosition().getX(), 2) + Math.pow(e.getPosition().getY() - explorer.getPosition().getY(), 2));
				if(dis<min) {
					min=dis;
					helper = explorer;
				}	
			}
		}
		System.out.println("coucou je suis "+ helper.getName()+" et je viens t'aider");
		return helper;
		
	}
	
//	public static void goHelp(Explorer e, Explorer helper) {
//		int xFinish = e.getPosition().getX();
//		int yFinish = e.getPosition().getY();
//		if (helper.getPosition().getX()>xFinish) {
//			while (helper.getPosition().getX()!=xFinish+1) {
//				helper.setDir(3);
//			}
//		}
//		if (helper.getPosition().getX()<xFinish) {
//			while (helper.getPosition().getX()!=xFinish-1) {
//				helper.setDir(2);
//			}
//		}
//		if (helper.getPosition().getY()>yFinish) {
//			while (helper.getPosition().getY()!=yFinish+1) {
//				helper.setDir(0);
//			}
//		}
//		if (helper.getPosition().getY()>yFinish) {
//			while (helper.getPosition().getY()!=yFinish-1) {
//				helper.setDir(1);
//			}
//		}
//	}
	
	public static Position direction(Explorer e) {
		int x = e.getPosition().getX();
		int y = e.getPosition().getY() ;
		Position pos = new Position(x,y);
		return pos;
	}
	
	public static void deathExplorer(Explorer e, int simulation, HashMap<String,Explorer> explorers, HashMap<String,Character> characters,ArrayList<String> toRemove) {

		float gain = 0;
		int gain2 =0;

		e.setDead(true);
		toRemove.add(e.getName());
		
		switch(simulation) {
		case 0:
			//lower probaFight for every explorers
			for (Explorer explorer : explorers.values()){
				gain = explorer.getProbaFight()*(float)10/100;
				if (explorer.getProbaFight()-gain>=0 && explorer.getProbaCall()+(gain/2)<=100 && explorer.getProbaCall()+(gain/2)<=100){
					explorer.setProbaFight(explorer.getProbaFight()-gain);
					explorer.setProbaCall(explorer.getProbaCall()+(gain/2));
					explorer.setProbaEscape(explorer.getProbaEscape()+(gain/2));
				}
			}
			break;
		case 1:
			//higher attackPoint for every explorers
			for (Explorer explorer : explorers.values()) {
				gain2 = explorer.getAttackPoint()*(int)20/100;
				if (explorer.getAttackPoint()+gain2<=explorer.getAttackPointMax()) {
					explorer.setAttackPoint(explorer.getAttackPoint()+gain2);
				}
			}
			break;
		case 2:
			//higher vision for every explorers
			for (Explorer explorer : explorers.values()) {
				explorer.setAura(explorer.getAura()+2);
			}
			break;
		}
	}
	
	public static void deathAnimal(WildAnimals a,ArrayList<String> toRemove) {
		a.setDead(true);
		toRemove.add(a.getName());
	}

	public static void main (String[] args) {
		//TEST
		
		//creat explorers
		ExDirector creatorE = new ExDirector();
		
		ExBuilder bDora = new DoraBuilder();
		creatorE.setExplorerBuilder(bDora);
		creatorE.BuildExplorer();
		Explorer e = creatorE.getExplorer() ;
		
		ExBuilder bJoe = new JoeBuilder();
		creatorE.setExplorerBuilder(bJoe);
		creatorE.BuildExplorer();
		Explorer e2 = creatorE.getExplorer();
		
		ExBuilder bDora2 = new DoraBuilder();
		creatorE.setExplorerBuilder(bDora2);
		creatorE.BuildExplorer();
		Explorer e3 = creatorE.getExplorer() ;
		e3.setName("Dora2");
		
		ExBuilder bMike = new MikeBuilder();
		creatorE.setExplorerBuilder(bMike);
		creatorE.BuildExplorer();
		//Explorer e4 = creatorE.getExplorer() ;
		
		System.out.println("--------DORA 1--------\nattack point : "+e.getAttackPoint()+"\nlife point : "+e.getLifePoint()+"\n");
		System.out.println("--------JOE--------\nattack point : "+e2.getAttackPoint()+"\nlife point : "+e2.getLifePoint()+"\n");
		System.out.println("--------DORA 2--------\nattack point : "+e3.getAttackPoint()+"\nlife point : "+e3.getLifePoint()+"\n");
		
		//creat animal
		WaDirector creatorA = new WaDirector();
		
		WaBuilder bWolf = new WolfBuilder();
		creatorA.setWildAnimalsBuilder(bWolf);
		creatorA.BuildWildAnimals();
		WildAnimals a = creatorA.getAnimal();
		System.out.println("--------WOLF--------\nattack point : " + a.getAttackPoint()+"\nlife point : "+a.getLifePoint()+"\n");
		
//		ArrayList<Explorer> explorers = new ArrayList<Explorer>() ;
//		explorers.add(e);
//		explorers.add(e2);
//		explorers.add(e3);
//		explorers.add(e4);
		
		
		//strategy 	0 : smart
		//			1 : fight
		//			2 : escape
		meetAnimals(e, a);
	}
	
	public static int getNbFights() {
		return nbFights;
	}
	
}
