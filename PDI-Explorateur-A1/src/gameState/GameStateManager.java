package gameState;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Stack;

/**
 * @brief This class initialize all the methods for the FIFO stack that contains all the states 
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 *
 */

public class GameStateManager {
	
	/** LIFO stack */
	public Stack<GameState> gameStates; 
	
	public GameStateManager () {
		gameStates = new Stack<GameState>();
		gameStates.push(new MenuState(this));
	}
	
	public void tick() {
		gameStates.peek().tick();
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