package gameState;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
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
	
	public void mouseClicked(MouseEvent e) {
		gameStates.peek().mouseClicked(e);
	}

	public void mousePressed(MouseEvent e) {
		gameStates.peek().mousePressed(e);
	}

	public void mouseReleased(MouseEvent e) {
		gameStates.peek().mouseReleased(e);
	}

	public void mouseEntered(MouseEvent e) {
		gameStates.peek().mouseEntered(e);
	}

	public void mouseExited(MouseEvent e) {
		gameStates.peek().mouseExited(e);
	}
}