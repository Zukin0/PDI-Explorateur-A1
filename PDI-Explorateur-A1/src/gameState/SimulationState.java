package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Simulation;
import game.SimulationUtility;
import game.TileMap;
import character.Character;
import character.Explorer;
import character.WildAnimals;
import ihm.Game;
import ihm.GamePanel;
import thread.ExplorerThread;
import thread.WildAnimalsThread;

public class SimulationState extends GameState implements ImageObserver{
	
	private TileMap tilemap;
	private int speed = 1;
	
	//création des couleurs nécessaires à l'interface
	private Color BEIGE = new Color(255,250,240);
	
	//création des polices
	private Font categoryFont = new Font("Arial", Font.BOLD, 22);
	
	private Simulation sim;
	
	//initialisation des images
	private BufferedImage imageDora=null;
	private BufferedImage imageJoe=null;
	private BufferedImage imageMike=null;
	private BufferedImage imageRemy=null;
	private BufferedImage time=null;
	private BufferedImage heart=null;
	private BufferedImage treasure=null;
	
	public SimulationState(GameStateManager gsm) {
		super(gsm);
		init();
	}


	public void init() {
		tilemap = new TileMap();
		tilemap.loadMap("/testMap.txt");
		tilemap.loadTiles("/tileset28.png");
		tilemap.setPosition(10, 10);
		tilemap.setTileSize(GamePanel.HEIGHT/20);
		
	}
	
	public void tick() {
		/*if(sim.explorers.size() == 0) {
			System.out.println("TOUS LES EXPLORATEURS SONT MORT : FIN DE LA PARTIE");
			System.exit(0);
		}*/
	}

	public void draw(Graphics g) {
		//background
		g.setColor(BEIGE);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
						
		//title
		//g.setColor(Color.black);
		//g.setFont(titleFont);
		//g.drawString("SIMULATION",20, 60);
		
		//map
		g.setColor(Color.black);
		g.fillRect(3,3, 1040, 744);
		tilemap.draw(g);
		
		//infos de la simulation
		g.setColor(Color.black);
		g.fillRect(1050 , 3, 245, 744);
		g.setColor(BEIGE);
		g.fillRect(1052 , 5, 241, 740);
		g.setColor(Color.black);
        g.setFont(categoryFont);
        g.drawString("VOTRE SIMULATION",1060, 30);
        try {
  			heart = ImageIO.read(new File("ressources/icone_coeur.png"));
  			time = ImageIO.read(new File("ressources/icone_temps.png"));
  			treasure = ImageIO.read(new File("ressources/tresor.png"));
  		} catch (IOException e) {
  			System.out.println("no image");
  			e.printStackTrace();
  		}
        g.drawImage(time, 1050, 40, 90, 80, (ImageObserver)this);
        g.drawImage(heart, 1050, 130, 90, 80, (ImageObserver)this);
        g.drawImage(heart, 1050, 220, 90, 80, (ImageObserver)this);
        g.drawImage(heart, 1050, 310, 90, 80, (ImageObserver)this);
        g.drawImage(heart, 1050, 400, 90, 80, (ImageObserver)this);
        g.drawImage(heart, 1050, 490, 90, 80, (ImageObserver)this);
        g.drawImage(heart, 1050, 580, 90, 80, (ImageObserver)this);
        g.drawImage(treasure, 1060, 660, 80, 80, (ImageObserver)this);
	
		for(Character c : sim.characters.values()) {
			if(SimulationUtility.isInstance(c, Explorer.class)) {
				String name = c.getName().substring(0,c.getName().length()-1);
				
				try {
		  			imageDora = ImageIO.read(new File("ressources/dora_face.png"));
		  			imageRemy = ImageIO.read(new File("ressources/remy_face.png"));
		  			imageMike = ImageIO.read(new File("ressources/mike_face.png"));
		  			imageJoe = ImageIO.read(new File("ressources/joe_face.png"));
		  		} catch (IOException e) {
		  			System.out.println("no image");
		  			e.printStackTrace();
		  		}
				
				switch(name) {
				
				case "Dora" : 
					g.drawImage(imageDora, c.getPosition().getX(), c.getPosition().getY(), (ImageObserver)this);
					break;
				case "Mike" : 
					g.drawImage(imageMike, c.getPosition().getX(), c.getPosition().getY(), (ImageObserver)this);
					break;
				case "Joe" : 
					g.drawImage(imageJoe, c.getPosition().getX(), c.getPosition().getY(), (ImageObserver)this);
					break;
				case "Remy" : 
					g.drawImage(imageRemy, c.getPosition().getX(), c.getPosition().getY(), (ImageObserver)this);
					break;
				}
			}
			else {
				g.setColor(Color.black);
				WildAnimals wa = (WildAnimals)c;
				g.drawRect(wa.getPosTerr().getX(),wa.getPosTerr().getY(), wa.getTerritorySize().getHeight(), wa.getTerritorySize().getHeight());
				g.setColor(Color.red);
			}
			//g.fillRect(c.getPosition().getX(), c.getPosition().getX(), c.getSize().getWidth(), c.getSize().getHeight());
		}
	}

	public void keyPressed(int k) {}

	public void keyReleased(int k) {}

	public void mouseClicked(MouseEvent m) {}

	public void mousePressed(MouseEvent m) {}

	public void mouseReleased(MouseEvent m) {}

	public void mouseEntered(MouseEvent m) {}

	public void mouseExited(MouseEvent m) {}


	public Simulation getSim() {
		return sim;
	}


	public void setSim(Simulation sim) {
		this.sim = sim;
	}


	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//public void render(Graphics2D g) {}
}
