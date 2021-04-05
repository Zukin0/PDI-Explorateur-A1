package game;
import java.util.ArrayList;
import java.util.HashMap;

import character.Character;
import character.Equipment;
import character.Explorer;
import character.WildAnimals;
import character.builders.WildAnimal.BearBuilder;
import character.builders.WildAnimal.EagleBuilder;
import character.builders.WildAnimal.WolfBuilder;
import character.builders.WildAnimal.core.WaBuilder;
import character.builders.WildAnimal.core.WaDirector;
import character.builders.equipment.BinocularBuilder;
import character.builders.equipment.BootsBuilder;
import character.builders.equipment.MachetteBuilder;
import character.builders.equipment.core.EqBuilder;
import character.builders.equipment.core.EqDirector;
import character.builders.explorers.DoraBuilder;
import character.builders.explorers.JoeBuilder;
import character.builders.explorers.MikeBuilder;
import character.builders.explorers.RemyBuilder;
import character.builders.explorers.core.ExBuilder;
import character.builders.explorers.core.ExDirector;
import data.Position;
import data.Size;
import data.Treasure;
import thread.ExplorerThread;
import thread.WildAnimalsThread;

/**
 * @brief Class of the Simulation/Game launched
 * It will initiate and store all the instances, lists and maps of instances and elements of the game
 * 
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 *
 */
public class Simulation {

	public static HashMap<String,Explorer> explorers = new HashMap<String, Explorer>();
	public static ArrayList<Thread> threads = new ArrayList<Thread>();
	public static HashMap<String,Character> characters = new HashMap<String, Character>();
	public static HashMap<String,WildAnimals> animals = new HashMap<String, WildAnimals>();
	
	public static HashMap<String,Treasure> treasures = new HashMap<String, Treasure>();
	public static ArrayList<String> listExp = new ArrayList<String>();
	
	public static ArrayList<String> toRemove = new ArrayList<String>();
	
	private ExDirector exCreator;
	private WaDirector waCreator;
	private EqDirector eqCreator;
	
	private ExBuilder bDora;
	private ExBuilder bMike;
	private ExBuilder bRemy;
	private ExBuilder bJoe;
	
	private WaBuilder bWolf;
	private WaBuilder bBear;
	private WaBuilder bEagle;
	
	private EqBuilder bMachette;
	private EqBuilder bBinocular;
	private EqBuilder bBoots;
	
	private Difficulty difficulty;
	public static int strategy;
	
	public Simulation(Difficulty difficulty, int strategy, ArrayList<String> listExp, HashMap<String,ArrayList<String>> exEquipment) {
		this.listExp = listExp;
		this.strategy = strategy;
		this.difficulty = difficulty;
		initBuilders();
		createExplorers(listExp, exEquipment);
		createAnimals();
		createTreasures();
		addListCharacters();
	}
	
	/**
	 *  @brief Initiate and create all builders for futur purposes
	 */
	public void initBuilders() {
		/*Create the builder director */
		exCreator = new ExDirector() ;
		waCreator = new WaDirector();
		eqCreator = new EqDirector();
		
		/*Create specific builder */
		/* Explorers */
		bDora = new DoraBuilder() ;
		bJoe = new JoeBuilder();
		bMike = new MikeBuilder();
		bRemy = new RemyBuilder();
		
		/*Animals*/
		bWolf = new WolfBuilder();
		bBear = new BearBuilder();
		bEagle = new EagleBuilder();
		
		/* Equipment */
		bMachette = new MachetteBuilder();
		bBinocular = new BinocularBuilder();
		bBoots = new BootsBuilder();
	}
	
	/**
	 * @brief Create all the explorers and equipment selected in the selection menu.
	 * It will assigned the equipment to the proper explorer
	 * 
	 * @param listExp List of Explorer to build, filled in the selection menu
	 * @param exEquipment Map of Equipment of each explorer to build, filled in the selection menu
	 */
	public void createExplorers(ArrayList<String> listExp, HashMap<String,ArrayList<String>> exEquipment) {
		/* Loop through explorer list, change Builder when needed */
		for(String nameEx : listExp) {
			if(nameEx.contains("Dora")) {
				exCreator.setExplorerBuilder(bDora);
			}
			else if(nameEx.contains("Mike")) {
				exCreator.setExplorerBuilder(bMike);
			}
			else if(nameEx.contains("Joe")) {
				exCreator.setExplorerBuilder(bJoe);
			}
			else if(nameEx.contains("Remy")) {
				exCreator.setExplorerBuilder(bRemy);
			}
			/* Create proper explorer */
			exCreator.BuildExplorer();
			Explorer e = exCreator.getExplorer();
			e.setName(nameEx);
			
			/* Gather right equipmentName list for the explorer and create <Equipment> List*/
			if(exEquipment != null) {
				ArrayList<Equipment> eqList = new ArrayList<Equipment>();
				for(String equipment : exEquipment.get(nameEx)) {
					switch(equipment) {
					case "Machettes" : eqCreator.setEquipmentBuilder(bMachette);
						break;
					case "Jumelles" : eqCreator.setEquipmentBuilder(bBinocular);
						break;
					case "Bottes" : eqCreator.setEquipmentBuilder(bBoots);
						break;
					}
					eqCreator.BuildEquipment();
					Equipment eq = eqCreator.getEquipment();
					eqList.add(eq);
					
					switch(eq.getName()) {
					case "Machettes" : e.setAttackPoint(e.getAttackPoint() + eq.getPower());;
						break;
					case "Jumelles" : e.setAura(e.getAura() + eq.getPower());
						break;
					}
				}
				e.setEquiment(eqList);
			}
			/* Add Equipment List to Explorer and add to instance's HashMap */
			explorers.put(e.getName(),e);
		}
	}
	
	/**
	 * @brief Create all the animals randomly. The number of animal created is based on the difficulty
	 */
	public void createAnimals() {
		int nbAnimals = difficulty.getAnimalsNB();
		String name = "";
		int nbWolf = 1, nbEagle = 1, nbBear = 1;
		for(int i=0; i<nbAnimals; i++) {
			/* Number between 0 and 2 => [0,2] */
			int rand = (int) (Math.random() * 3) ;
			switch(rand) {
				case 0 : 
					waCreator.setWildAnimalsBuilder(bWolf);
					nbWolf++;
					name = "Wolf" + nbWolf;
					break;
				case 1 :
					waCreator.setWildAnimalsBuilder(bBear);
					nbBear++;
					name = "Bear" + nbBear;
					break;
				case 2 : 
					waCreator.setWildAnimalsBuilder(bEagle);
					nbEagle++;
					name = "Eagle" + nbEagle;
					break;
			}
			waCreator.BuildWildAnimals();
			WildAnimals w1 = waCreator.getAnimal();
			w1.setName(name);
			animals.put(w1.getName(),w1);
		}
	}
	
	/**
	 * @brief Creates all the treasures instances randomly. The number of treasure created is based on the difficulty
	 */
	public void createTreasures() {
		int nbTreasures = difficulty.getTreasureNB();
		for(int i=1;i<=nbTreasures;i++) {
			String name = "treasure" + i;
			Treasure t = new Treasure(name,new Size(30,30),new Position(0,0),true);
			
			treasures.put(name,t);
		}
	}
	
	/**
	 * @brief Place all the instances of Explorers and WildAnimals in a commun Character Hashmap
	 * The map will be used for more efficient searching algorithm
	 */
	public void addListCharacters() {
		for(Explorer e : explorers.values()) {
			characters.put(e.getName(),e);
		}
		for(WildAnimals wa : animals.values()) {
			characters.put(wa.getName(),wa);
		}
	}
	
	/**
	 * @brief Creates and starts all the threads for all the characters created
	 */
	public void createThreads() {
		for(Character c : characters.values()) {
			Thread t = null;
			if(SimulationUtility.isInstance(c, Explorer.class)) {
				t = new Thread(new ExplorerThread((Explorer)c));
			}
			else {
				t = new Thread(new WildAnimalsThread((WildAnimals)c));
			}
			threads.add(t);
			
	}
		
		for (Thread t : threads) {
			t.start();
		}
	}
	
	public HashMap<String,Explorer> getExplorers(){
		return explorers;
	}
	
	public Difficulty getDifficulty() {
		return difficulty;
	}
	
	public int getStrategy() {
		return strategy;
	}
	
	public void setExplorers(HashMap<String,Explorer> explorers) {
		Simulation.explorers = explorers;
	}
	
	public void setEquimentMax(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	
	public void setPrice(int strategy) {
		this.strategy = strategy;
	}
}
