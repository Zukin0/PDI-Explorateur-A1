package testEmma;

import java.util.ArrayList;
import java.util.Random;

import character.Explorer;
import character.WildAnimals;
import character.builders.WildAnimal.WolfBuilder;
import character.builders.WildAnimal.core.WaBuilder;
import character.builders.WildAnimal.core.WaDirector;
import character.builders.explorers.DoraBuilder;
import character.builders.explorers.JoeBuilder;
import character.builders.explorers.core.ExBuilder;
import character.builders.explorers.core.ExDirector;

public class MeetAnimal{
	
	public static void meetAnimals(Explorer e, WildAnimals a, int simulation, ArrayList<Explorer> explorers) {
		String outcome = null;
		String action = null;
		float gain = 0;
		int gain2 =0;
		
		//il faudra faire un paramètre qui prend le type de simulation
		
		//recover probabilities
		float pf = e.getProbaFight();
		float pc = e.getProbaCall();
		float min = 0;
		float max = 100;
		
		Random r = new Random(); 
		float n = min + r.nextFloat() * (max - min);
		System.out.println(n);
		
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
		
		System.out.println(action);
		
		switch(action) {
		case "fight":
			//fight 
			outcome = figth(e, a);
			if (outcome == "deathExplo") {
				//remove dead explorer 
				//faut qqch d autre pour le supprimer de la simulation? avec le builder notamment
				//fermer le thread
				explorers.remove(e);
				switch(simulation) {
				case 1:
					//lower probaFight for every explorers
					for (Explorer explorer : explorers){
						gain = explorer.getProbaFight()*(float)10/100;
						System.out.println(gain);
						System.out.println(gain/2);
						if (explorer.getProbaFight()-gain>=0 && explorer.getProbaCall()+(gain/2)<=100 && explorer.getProbaCall()+(gain/2)<=100){
							System.out.println("Je suis "+explorer.getName()+" et j'avais une proba de combat de : "+explorer.getProbaFight());
							System.out.println("Je suis "+explorer.getName()+" et j'avais une proba de fuite de : "+explorer.getProbaEscape());
							System.out.println("Je suis "+explorer.getName()+" et j'avais une proba d'appeler de : "+explorer.getProbaCall()+"\n");
							explorer.setProbaFight(explorer.getProbaFight()-gain);
							explorer.setProbaCall(explorer.getProbaCall()+(gain/2));
							explorer.setProbaEscape(explorer.getProbaEscape()+(gain/2));
							System.out.println("Je suis "+explorer.getName()+" et j'ai une proba de combat de : "+explorer.getProbaFight());
							System.out.println("Je suis "+explorer.getName()+" et j'ai une proba de fuite de : "+explorer.getProbaEscape());
							System.out.println("Je suis "+explorer.getName()+" et j'ai une proba d'appeler de : "+explorer.getProbaCall()+"\n");
						}
					}
					break;
				case 2:
					//higher attackPoint for every explorers
					for (Explorer explorer : explorers) {
						gain2 = explorer.getAttackPoint()*(int)20/100;
						System.out.println(gain2);
						if (explorer.getAttackPoint()+gain2<=explorer.getAttackPointMax()) {
							System.out.println("Je suis "+explorer.getName()+" et j'avais : "+explorer.getAttackPoint()+" points d'attaque\n");
							explorer.setAttackPoint(explorer.getAttackPoint()+gain2);
							System.out.println("Je suis "+explorer.getName()+" et j'ai : "+explorer.getAttackPoint()+" points d'attaque\n");
						}
					}
					break;
				case 3:
					//higher vision for every explorers
					for (Explorer explorer : explorers) {
						System.out.println("Je suis "+explorer.getName()+" et j'avais une vision de : "+explorer.getAura()+"\n");
						explorer.setAura(explorer.getAura()+2);
						System.out.println("Je suis "+explorer.getName()+" et j'ai une vision de : "+explorer.getAura()+"\n");
					}
					break;
				}
			}
			break;
		case "call":
			//comm : call a friend
			System.out.println("Salut besoin d'aide le plus proche peut venir m'aider ? Ma position : "+
								e.getPosition().getX()+", "+e.getPosition().getY()+"\n");
			//arrivée autre explorateur
			break;
		case "escape":
			//go away for n seconds
			System.out.println("un animal sauvage.. JE FUIS");
			changeDir(e,e.getDir());
			e.setEscaping(true);
			break;
		}
	}
	
	//faudra je vois si jamais on est plusieurs a combattre ou si on est face a plusieur animaux
	//au pire on fait une fusion des diff life point et attack point avant l'appel de la méthode
	public static String figth(Explorer e, WildAnimals a) {
		int apE = e.getAttackPoint();
		int lpE = e.getLifePoint();
		int apA = a.getAttackPoint();
		int lpA = a.getLifePoint();
		String outcome = null;
		
		//fight while explo !dead or animal !dead
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
			System.out.println(lpE);
			System.out.println(lpA);
		}
		
		//outcome of the fight
		//le premier cas n'est pas forcément nécessaire je pourrais juste faire un if et un autre if 
		if(lpE==0 && lpA==0) {
			//explo and animal are dead
			//comm
			System.out.println("je suis "+e.getName()+" et je vais mourir et j'ai tué un animal\n");
			outcome = "deathExplo";
		}
		else if(lpA==0) {
			//animal is dead
			System.out.println("je suis "+e.getName()+" et j'ai tué un animal il me reste : "+e.getLifePoint()+" point de vie\n");
			outcome = "deathAnimal";
		}
		else if(lpE==0) {
			//explo is dead
			System.out.println("je suis "+e.getName()+" et je vais mourir\n");
			outcome = "deathExplo";
		}
		
		return outcome;
	}
	
	public static void changeDir(Explorer e, int dir) {
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
		
		ArrayList<Explorer> explorers = new ArrayList<Explorer>() ;
		
		explorers.add(e);
		explorers.add(e2);
		explorers.add(e3);
		
		//strategy 	1 : smart
		//			2 : fight
		//			3 : escape
		meetAnimals(e, a,2, explorers);
	}
	
}
