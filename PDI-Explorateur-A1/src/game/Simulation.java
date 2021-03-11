package game;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import character.Character;
import character.Explorer;
import character.WildAnimals;
import character.builders.WildAnimal.WolfBuilder;
import character.builders.WildAnimal.core.WaBuilder;
import character.builders.WildAnimal.core.WaDirector;
import character.builders.explorers.DoraBuilder;
import character.builders.explorers.JoeBuilder;
import character.builders.explorers.MikeBuilder;
import character.builders.explorers.RemyBuilder;
import character.builders.explorers.core.ExBuilder;
import character.builders.explorers.core.ExDirector;
import thread.ExplorerThread;
import thread.WildAnimalsThread;

public class Simulation {

	public static HashMap<String,Explorer> explorers = new HashMap<String, Explorer>();
	public static ArrayList<Thread> threads = new ArrayList<Thread>();
	public static HashMap<String,Character> characters = new HashMap<String, Character>();
	public static HashMap<String,WildAnimals> animals = new HashMap<String, WildAnimals>();
	
	private ExDirector creator;
	private WaDirector waCreator;
	
	private ExBuilder bDora;
	private ExBuilder bMike;
	private ExBuilder bRemy;
	private ExBuilder bJoe;
	
	private WaBuilder bWolf;
	
//	public static ArrayList<Explorer> explorers = new ArrayList<Explorer>() ;
	private int difficulty;
	public static int strategy;
	
	public Simulation(int difficulty, int strategy, ArrayList<String> listExp, HashMap<String,ArrayList<String>> exEquipment) {
		this.difficulty = difficulty;
		this.strategy = strategy;
		initBuilders();
		createExplorers(listExp, exEquipment);
		createAnimals(/*tabAni*/);
		addListCharacters();
	}
	
	public void initBuilders() {
		//Create the builder director
		creator = new ExDirector() ;
		waCreator = new WaDirector();
		
		//Create specifique builder
		bDora = new DoraBuilder() ;
		bJoe = new JoeBuilder();
		bMike = new MikeBuilder();
		bRemy = new RemyBuilder();
		bWolf = new WolfBuilder();

	}
	
	public void createExplorers(ArrayList<String> listExp, HashMap<String,ArrayList<String>> exEquipment) {
		String name = "";
		for(int i = 0;i<4;i++) {
			switch(i) {
			case 0: 
				creator.setExplorerBuilder(bDora);
				name = "Dora";
				break;
			case 1: 
				creator.setExplorerBuilder(bJoe);
				name = "Joe";
				break;
			case 2: 
				creator.setExplorerBuilder(bRemy);
				name = "Remy";
				break;
			case 3: 
				creator.setExplorerBuilder(bMike);
				name = "Mike";
				break;
		
			}
			System.out.println(name);
//			for(int j = 1;j<=tabEx[i]; j++) {
//				creator.BuildExplorer();
//				Explorer e = creator.getExplorer();
//				e.setName(name + j);
//				explorers.put(e.getName(),e);
//			}
		}
	}
	
	public void createAnimals(/*int[] tabAni*/) {
		waCreator.setWildAnimalsBuilder(bWolf);
		waCreator.BuildWildAnimals();
		WildAnimals w1 = waCreator.getAnimal();
		w1.setName("Wolf1");
		animals.put(w1.getName(),w1);
	}
	
	public void addListCharacters() {
		for(Explorer e : explorers.values()) {
			characters.put(e.getName(),e);
		}
		for(WildAnimals wa : animals.values()) {
			characters.put(wa.getName(),wa);
		}
	}
	
	public void createThreads() {
		for(Character c : characters.values()) {
			System.out.println("Testing");
			Thread t = null;
			if(SimulationUtility.isInstance(c, Explorer.class)) {
				System.out.println("Explorer");
				t = new Thread(new ExplorerThread((Explorer)c));
			}
			else {
				System.out.println("Animal");
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
	
	public int getDifficulty() {
		return difficulty;
	}
	
	public int getStrategy() {
		return strategy;
	}
	
	public void setExplorers(HashMap<String,Explorer> explorers) {
		this.explorers = explorers;
	}
	
	public void setEquimentMax(int difficulty) {
		this.difficulty = difficulty;
	}
	
	public void setPrice(int strategy) {
		this.strategy = strategy;
	}
}
