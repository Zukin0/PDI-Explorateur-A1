 import java.util.ArrayList;

import character.*;
import character.Character;
import character.builders.WildAnimal.WolfBuilder;
import character.builders.WildAnimal.core.WaBuilder;
import character.builders.WildAnimal.core.WaDirector;
import character.builders.explorers.DoraBuilder;
import character.builders.explorers.JoeBuilder;
import character.builders.explorers.core.ExBuilder;
import character.builders.explorers.core.ExDirector;
import data.*;
import game.SimulationUtility;
import thread.ExplorerThread;
import thread.WildAnimalsThread;

public class TestAlexandre {	
	
	private ArrayList<Thread> threads = new ArrayList<Thread>();
	private ArrayList<Character> characters = new ArrayList<Character>();
	private int nbExplorer = 2;
	private int nbAnimals = 5;
	private ExDirector creator;
	private WaDirector waCreator;
	private ExBuilder bDora;
	private ExBuilder bJoe;
	private WaBuilder bWolf;
	
	public TestAlexandre() {		
		
		initBuilders();
		createCharacters();
		createThreads();
		
	}
	
	public void initBuilders() {
		//Create the builder director
		creator = new ExDirector() ;
		waCreator = new WaDirector();
		
		//Create specifique builder
		bDora = new DoraBuilder() ;
		bJoe = new JoeBuilder();
		bWolf = new WolfBuilder();
	}
	
	public void createCharacters() {
		//Set the builder type and create the explorer this type
//		creator.setExplorerBuilder(bDora);
//		creator.BuildExplorer();
//		Explorer e1 = creator.getExplorer();
//		characters.add(e1);
//		
		creator.setExplorerBuilder(bJoe);
		creator.BuildExplorer();
		Explorer e2 = creator.getExplorer();
		characters.add(e2);
		System.out.println(e2.getDir());
		
//		waCreator.setWildAnimalsBuilder(bWolf);
//		waCreator.BuildWildAnimals();
//		WildAnimals w1 = waCreator.getAnimal();
//		characters.add(w1);
	}
	
	public void createThreads() {
		for(Character c : characters) {
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

	public static void main(String args[]) {
		new TestAlexandre();

	}
}
