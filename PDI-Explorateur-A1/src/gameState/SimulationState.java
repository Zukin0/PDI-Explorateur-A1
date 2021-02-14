package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import game.Simulation;
import character.Character;
import ihm.Game;
import ihm.GamePanel;

public class SimulationState extends GameState {
	
	//création des couleurs nécessaires à l'interface
	private Color BEIGE = new Color(255,250,240);
	
	//création des polices
	private Font titleFont = new Font("Century Goth", Font.BOLD, 40);
	
	private Simulation sim;
	
	public SimulationState(GameStateManager gsm) {
		super(gsm);
	}


	public void init() {
		
	}
	
	public void tick() {
	
	}

	public void draw(Graphics g) {
		//background
		g.setColor(BEIGE);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
						
		//title
		g.setColor(Color.black);
		g.setFont(titleFont);
		g.drawString("SIMULATION",20, 60);
		
		for(Character c : sim.characters.values()) {
			g.setColor(Color.green);
			g.fillRect(c.getPosition().getX(), c.getPosition().getY(), c.getSize().getWidth(), c.getSize().getHeight());
		}
	}

	public void keyPressed(int k) {
		
	}

	public void keyReleased(int k) {
		
	}


	@Override
	public void mouseClicked(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}


	public Simulation getSim() {
		return sim;
	}


	public void setSim(Simulation sim) {
		this.sim = sim;
	}
	
	//public void render(Graphics2D g) {}
}
