package game;
import java.util.HashMap;

import character.Explorer;

public class Simulation {

	private HashMap<Integer,Explorer> explorers;
	private Difficulty difficulty;
	private int strategy;
	
	public Simulation(HashMap<Integer,Explorer> explorers, Difficulty difficulty, int strategy) {
		this.explorers = explorers;
		this.difficulty = difficulty;
		this.strategy = strategy;
	}
	
	public HashMap<Integer,Explorer> getExplorers(){
		return explorers;
	}
	
	public Difficulty getDifficulty() {
		return difficulty;
	}
	
	public int getStrategy() {
		return strategy;
	}
	
	public void setExplorers(HashMap<Integer,Explorer> explorers) {
		this.explorers = explorers;
	}
	
	public void setEquimentMax(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	
	public void setPrice(int strategy) {
		this.strategy = strategy;
	}
}
