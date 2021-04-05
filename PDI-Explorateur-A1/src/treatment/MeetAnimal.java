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
	private static HashMap<String,Explorer> explorers = Simulation.explorers;
	private static HashMap<String,Character> characters = Simulation.characters;
	private static HashMap<String,WildAnimals> animals = Simulation.animals;
	private static ArrayList<String> toRemove = Simulation.toRemove;
	private static int simulation = Simulation.strategy;

	public static void meetAnimals(Explorer e, WildAnimals a) {

		String outcome = null;
		String action = null;
		Explorer helper = null;
		
		//recover probabilities
		float pf = e.getProbaFight();
		float pc = e.getProbaCall();
		float min = 0;
		float max = 100;
		
		Random r = new Random(); 
		float n = min + r.nextFloat() * (max - min);
		
		switch(simulation) {
		
		case 0 : 
			//SMART MODE
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
		case 1 : 
			//FIGHT MODE
			action= "fight";
			break;
		case 2 : 
			//ESCAPE MODE
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
			if(a.getFightAgainst().equals("")) {
				System.out.println(e.getName() + " : FIGHT 1");
				outcome = fight(e, a);
				if (outcome.equals("deathExplo") || outcome.equals("deathBoth")) {
					deathExplorer(e);
				}
				if(outcome.equals("deathAnimal") || outcome.equals("deathBoth")) {
					deathAnimal(a);
				}
			}
			break;
		case "call":
			//call a friend
			if(a.getFightAgainst().equals("")) {
				System.out.println(e.getName() + " : Besoin d'aide, ma position : "+
						e.getPosition().getX()+", "+e.getPosition().getY());
				helper = findHelper(e, explorers);
				if(helper != null) {
					System.out.println(helper.getName() + ": JE VAIS AIDER " + e.getName());
					a.setFightAgainst(e.getName());
					
					e.setEscaping(false);
					e.setWaiting(true);
					e.setWaitingWho(helper.getName());
					
					helper.setHelpingWho(e.getName());
					helper.setHelping(true);
					
					a.setFightAgainst(e.getName());
				}
				else {
					System.out.println(e.getName() + " : FIGHT 2");
					outcome = fight(e, a);
					if (outcome == "deathExplo" || outcome.equals("deathBoth")) {
						a.setFightAgainst("");
						deathExplorer(e);
					}
					if(outcome == "deathAnimal" || outcome.equals("deathBoth")) {
						deathAnimal(a);
					}
				}
			}
			//attendre sinon
			break;
		case "escape":
			//go away for n seconds
			System.out.println(e.getName() + " : JE FUIS");
			escapeDir(e,e.getDir());
			e.setEscaping(true);
			break;
		}
	}
	
	public static void duoFight(Explorer helper, Explorer victim) {
		String outcome = null;
		WildAnimals a = null;
		for(WildAnimals cpt : Simulation.animals.values()) {
			if(cpt.getFightAgainst() != null) {
				if(cpt.getFightAgainst().equals(victim.getName())) {
					a = cpt;
				}
			}
		}
		if(a!=null) {
			//change explorer capacities to fight : add explorer and helper life points and attack points
			victim.setAttackPoint(victim.getAttackPoint()+helper.getAttackPoint());
			victim.setLifePoint(victim.getLifePoint()+helper.getLifePoint());
			outcome = fight(victim, a); 

			
			if (outcome.equals("deathExplo") || outcome.equals("deathBoth")) {
				a.setFightAgainst("");
				deathExplorer(victim);
				deathExplorer(helper);
			}
			else {
				int attack = victim.getAttackPoint()/2;
				int life = victim.getLifePoint()/2;
				victim.setAttackPoint(attack);
				victim.setLifePoint(life);
				helper.setAttackPoint(attack);
				helper.setLifePoint(life);
				CharacterTreatment.changeDir(victim);
				CharacterTreatment.changeDir(helper);
			}
			if(outcome == "deathAnimal" || outcome.equals("deathBoth")) {
				deathAnimal(a);
			}
		}
		victim.setWaiting(false);
		victim.setWaitingWho("");
		
		helper.setHelping(false);
		helper.setHelpingWho("");
		helper.setNearExp(false);
	}
	
	public static String fight(Explorer e, WildAnimals a) {
		String outcome = null;
		if(a != null) {
			int apE = e.getAttackPoint();
			int lpE = e.getLifePoint();
			int apA = a.getAttackPoint();
			int lpA = a.getLifePoint();
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
				System.out.println(e.getName()+" : TRADE KILL");
				outcome = "deathBoth";
			}
			else if(lpA==0) {
				//animal is dead
				System.out.println(e.getName()+" WON FIGHT, LIFE LEFT : "+e.getLifePoint()+" HP");
				outcome = "deathAnimal";
			}
			else if(lpE==0) {
				//explorer is dead
				//comm
				System.out.println(e.getName()+" : LOST FIGHT");
				outcome = "deathExplo";
				a.setFightAgainst("");
			}
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
			if(!explorer.equals(e) && !explorer.isWaiting() && !explorer.isEscaping()) {
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
	
	//pas sure que cette méthode soit utiliisée
	public static Position direction(Explorer e) {
		int x = e.getPosition().getX();
		int y = e.getPosition().getY() ;
		Position pos = new Position(x,y);
		return pos;
	}
	
	public static void deathExplorer(Explorer e) {
		float gain = 0;
		int gain2 =0;

		if(e.isHelping()) {
			Explorer victim = Simulation.explorers.get(e.getHelpingWho());
			victim.setWaiting(false);
			victim.setWaitingWho("");
		}
		
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
				explorer.setAura(explorer.getAura()+5);
			}
			break;
		}
	}

	public static void deathAnimal(WildAnimals a) {
		System.out.println(a.getName() + " : DEAD");
		a.setDead(true);
		toRemove.add(a.getName());
	}
	
}
