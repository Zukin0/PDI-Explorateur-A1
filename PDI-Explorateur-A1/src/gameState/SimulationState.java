package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
import data.Position;
import data.Size;
import data.Treasure;
import ihm.GamePanel;

public class SimulationState extends GameState implements ImageObserver{
	
	private TileMap tilemap;
	private int speed = 1;
	
	//création des couleurs nécessaires à l'interface
	private Color BEIGE = new Color(255,250,240);
	
	//création des polices
	private Font categoryFont = new Font("Arial", Font.BOLD, 22);
	private Font whiteBoardFont = new Font("Arial", Font.PLAIN, 20);
	
	private Simulation sim;
	
	//initialisation des images
	/*Explorer*/
	private BufferedImage imageDora=null;
	private BufferedImage imageJoe=null;
	private BufferedImage imageMike=null;
	private BufferedImage imageRemy=null;
	
	/*Animals*/
	private BufferedImage imageWolf=null;
	private BufferedImage imageBear=null;
	private BufferedImage imageEagle=null;
	
	/*Others*/
	private BufferedImage time=null;
	private BufferedImage heart=null;
	private BufferedImage treasure=null;
	
	/*Boolean*/
	private boolean treasurePlaced;
	
	public SimulationState(GameStateManager gsm) {
		super(gsm);
		init();
		initImageFiles();
		treasurePlacement();
		animalsPlacement();
	}


	public void init() {
		tilemap = new TileMap();
		tilemap.loadMap("/textMap.txt");
		tilemap.loadTiles("/tileset.png");
		tilemap.setPosition(10, 10);
		tilemap.setTileSize(GamePanel.HEIGHT/20); /* 750/20 = 37,5 => int(37.5) = 37 */
		
	}
	
	public void initImageFiles() {
        try {
  			heart = ImageIO.read(new File("ressources/icone_coeur.png"));
  			time = ImageIO.read(new File("ressources/icone_temps.png"));
  			treasure = ImageIO.read(new File("ressources/tresor.png"));
  		} catch (IOException e) {
  			System.out.println("no image");
  			e.printStackTrace();
  		}
	}
	
	public void treasurePlacement() {
		HashMap<String,Treasure> ts = Simulation.treasures;
		int nbCol = tilemap.getNbCols();
		int nbRows = tilemap.getNbRows();
		int tMapX = tilemap.getX();
		int tMapY = tilemap.getY();
		int tileSize = tilemap.getTileSize();
		
		for (HashMap.Entry<String, Treasure> entry : ts.entrySet()) {
			Treasure t = entry.getValue();
			treasurePlaced = false;
			while(!treasurePlaced) {
				/* Random position X [startPosition X ; Map.width] */
				int x = (int) (tMapX + Math.random() * (tMapX+(nbRows-1)*tileSize+5));
				
				/* Random position Y [startPosition Y; Map.height] */
				int y = (int) (tMapY + Math.random() * (tMapY+(nbCol-1)*tileSize+5));
				
				/* Calcul d�duit de la classe TileMap sur le placement des tiles */
				/* Col = (xPoS - OffSetX)/tileSize, Row = (YPoS - OffSetY)/tileSize */
				int row = (int)(x-5-tMapX)/tileSize;
				int col = (int) (y-5-tMapY)/tileSize;
				

				if(tilemap.getPosition(row,col) == 7) {
					t.setPosition(new Position(col*tileSize+5+tMapX,row*tileSize+5+tMapY));
					treasurePlaced = true;
				}
			}
		}
				
	}
	
	public void animalsPlacement() {
		HashMap<String,WildAnimals> waMap = Simulation.animals;
		
		for(HashMap.Entry<String,WildAnimals> entry : waMap.entrySet()) {
			
		}
	}
	
	public void tick() {
	}

	public void draw(Graphics g) {
		//background
		g.setColor(BEIGE);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
///////////////////////////MAP////////////////////////////////////////////////
		/* Map Border */
		g.setColor(Color.black);
		g.fillRect(3,3, 1040, 744);
		
		/* Map */
		tilemap.draw(g);
		
		/* Dynamic explorer's image loading, changes when explorer's direction changes */
		for(Character c : sim.characters.values()) {
			String name = c.getName().substring(0,c.getName().length()-1);
			String dynFileName ="";
			switch(c.getDir()) {
			case 0 : /* Up */
				dynFileName = "dos";
				break;
			case 1 : /* Down */
				dynFileName = "face";
				break;
			case 2 : /* Right */
				dynFileName = "droite";
				break;
			case 3 : /* Left */
				dynFileName = "gauche";
				break;
			
			}
			try {
	  			imageDora = ImageIO.read(new File("ressources/dora_" + dynFileName + ".png"));
	  			imageRemy = ImageIO.read(new File("ressources/remy_" + dynFileName + ".png"));
	  			imageMike = ImageIO.read(new File("ressources/mike_" + dynFileName + ".png"));
	  			imageJoe = ImageIO.read(new File("ressources/joe_" + dynFileName + ".png"));
	  			
	  			imageWolf = ImageIO.read(new File("ressources/loup_" + dynFileName + ".png"));
	  			imageBear = ImageIO.read(new File("ressources/ours_" + dynFileName + ".png"));
	  			imageEagle = ImageIO.read(new File("ressources/aigle_" + dynFileName + ".png"));
	  		} catch (IOException e) {
	  			System.out.println("Error Image File couldn't load");
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
			case "Wolf" : 
				g.drawImage(imageWolf, c.getPosition().getX(), c.getPosition().getY(), (ImageObserver)this);
				break;
			case "Bear" : 
				g.drawImage(imageBear, c.getPosition().getX(), c.getPosition().getY(), (ImageObserver)this);
				break;
			case "Eagle" : 
				g.drawImage(imageEagle, c.getPosition().getX(), c.getPosition().getY(), (ImageObserver)this);
				break;
			}
			if(SimulationUtility.isInstance(c, WildAnimals.class)) {
				g.setColor(Color.black);
				WildAnimals wa = (WildAnimals)c;
				g.drawRect(wa.getPosTerr().getX(),wa.getPosTerr().getY(), wa.getTerritorySize().getHeight(), wa.getTerritorySize().getHeight());
			}
//			g.setColor(Color.red);
//			g.drawRect(c.getPosition().getX(), c.getPosition().getY(), c.getSize().getWidth(), c.getSize().getHeight());
		}
		
		/* Draw Treasures */
		for(HashMap.Entry<String,Treasure> entry : Simulation.treasures.entrySet()) {
			Treasure t = entry.getValue();
			g.drawImage(treasure, t.getPosition().getX(), t.getPosition().getY(), t.getSize().getWidth(), t.getSize().getHeight(),(ImageObserver)this);
		}
		
//////////////////////CADRE BLANC////////////////////////////////////////////
		g.setColor(Color.black);
		g.fillRect(1050 , 3, 245, 744);
		g.setColor(BEIGE);
		g.fillRect(1052 , 5, 241, 740);
		g.setColor(Color.black);
        g.setFont(categoryFont);
        g.drawString("VOTRE SIMULATION",1060, 30);

        g.drawImage(time, 1050, 40, 90, 80, (ImageObserver)this);
        g.drawImage(heart, 1050, 130, 90, 80, (ImageObserver)this);
        g.drawImage(heart, 1050, 220, 90, 80, (ImageObserver)this);
        g.drawImage(heart, 1050, 310, 90, 80, (ImageObserver)this);
        g.drawImage(heart, 1050, 400, 90, 80, (ImageObserver)this);
        g.drawImage(heart, 1050, 490, 90, 80, (ImageObserver)this);
        g.drawImage(heart, 1050, 580, 90, 80, (ImageObserver)this);
        g.drawImage(treasure, 1060, 660, 80, 80, (ImageObserver)this);
        
        g.drawString("07:04", 1170, 90);
        g.setFont(whiteBoardFont);
        g.drawString("Dora1 : 3/8", 1145, 175);
        g.drawString("Dora2 : DEAD", 1145, 265);
        g.drawString("Mike : 8/8", 1145, 355);
        g.drawString("Joe : 8/8", 1145, 445);
        g.drawString("Remy1 : 1/8", 1145, 535);
        g.drawString("Remy2 : DEAD", 1145, 625);
        g.drawString("Tresors : 0", 1145, 715);
	}

	public void keyPressed(int k) {}

	public void keyReleased(int k) {}

	public void mouseClicked(MouseEvent m) {}


	public void mouseReleased(MouseEvent m) {}

	public void mouseEntered(MouseEvent m) {}

	public void mouseExited(MouseEvent m) {}


	public Simulation getSim() {
		return sim;
	}


	public void setSim(Simulation sim) {
		this.sim = sim;
	}
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}
	public void mousePressed(MouseEvent m) {
		
	}
}
