package gameState;

import java.awt.Graphics;
import java.awt.Graphics2D;

//c'est une classe abstraite qui va définir toutes les méthodes que nos états implémenteront obligatoirement 

public abstract class GameState {
	
	protected GameStateManager gsm;
	
	public GameState (GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public abstract void init();
	
	public abstract void tick();
	
	public abstract void draw (Graphics g);
	
	public abstract void keyPressed (int k);
	
	public abstract void keyReleased (int k);
	
	//public abstract void render(Graphics2D g);
}
