package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import game.Simulation;
import game.SimulationUtility;
import character.Character;
import character.Explorer;
import character.WildAnimals;
import ihm.Game;
import ihm.GamePanel;
import thread.ExplorerThread;
import thread.WildAnimalsThread;

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
		if(sim.explorers.size() == 0) {
			System.out.println("TOUS LES EXPLORATEURS SONT MORT : FIN DE LA PARTIE");
			System.exit(0);
		}
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
			if(SimulationUtility.isInstance(c, Explorer.class)) {
				String name = c.getName().substring(0,c.getName().length()-1);
				switch(name) {
				
				case "Dora" : g.setColor(Color.pink);
					break;
				case "Mike" : g.setColor(Color.green);
					break;
				case "Joe" : g.setColor(Color.orange);
					break;
				case "Remy" : g.setColor(Color.blue);
					break;
				}
//				g.setColor(Color.green);
			}
			else {
				g.setColor(Color.black);
				WildAnimals wa = (WildAnimals)c;
				g.drawRect(wa.getPosTerr().getX(),wa.getPosTerr().getY(), wa.getTerritorySize().getHeight(), wa.getTerritorySize().getHeight());
				g.setColor(Color.red);
			}
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
