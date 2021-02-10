package gameState;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Stack;

import ihm.Game;

//Classe qui permet de répertorier les différents états et les gères avec une pile LIFO

public class GameStateManager {
	
	//pile LIFO
	public static Stack<GameState> gameStates; //on peut aussi utiliser une array liste comme avait fait Yohan
	
	public GameStateManager () {
		gameStates = new Stack<GameState>();
		gameStates.push(new MenuState(this));
	}
	
	public void tick() {
		gameStates.peek().tick(); //peek() prend le gameState en haut de la pile
	}
	
	public void draw (Graphics g) {
		gameStates.peek().draw(g);
	}
	
	public void KeyPressed (int k) {
		gameStates.peek().keyPressed(k);
	}
	
	public void keyReleased (int k) {
		gameStates.peek().keyReleased(k);
	}
}


















	/*private static ArrayList<GameState> gameStates;

	private static int currentState;
	
	public static final int MENUSTATE = 0;
	public static final int SELECTIONSTATE = 1;
	public static final int SIMULATIONSTATE = 2;
	public static final int RECAPSTATE = 3;
	
	public GameStateManager(Game game) {
		gameStates = new ArrayList<GameState>();
		
		currentState = MENUSTATE;
		gameStates.add(new MenuState());
		gameStates.add(new SelectionState(game));
		gameStates.add(new SimulationState(game));
		gameStates.add(new RecapState(game));
	}
	
	public static void setState(int state) {
		currentState = state;
		gameStates.get(currentState).init();
	}
	
	public void tick() {
		gameStates.get(currentState).tick();
	}
	
	public void render(Graphics2D g) {
		gameStates.get(currentState).render(g);
	}
	
	public static int getCurrentState() {
		return currentState;
	}
	
	public static ArrayList<GameState> getGameStates() {
		return gameStates;
	}

	public void setGameStates(ArrayList<GameState> gameStates) {
		this.gameStates = gameStates;
	}

	public static int getMenustate() {
		return MENUSTATE;
	}

	public static int getSimulationState() {
		return SIMULATIONSTATE;
	}

	public static GameState getGameState() {
		return gameStates.get(SIMULATIONSTATE);
	}
	
	public static void setCurrentState(int currentState) {
		GameStateManager.currentState = currentState;
	}
}*/
