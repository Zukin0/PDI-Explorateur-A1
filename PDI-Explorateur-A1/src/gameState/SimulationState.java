package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import data.Constant;
import data.MapObjects;
import data.Position;
import data.Size;
import data.Treasure;
import ihm.GamePanel;
import time.RealTime;
import time.Time;

public class SimulationState extends GameState implements ImageObserver{
	
	/* TileMap */
	public static TileMap tilemap;
	private int nbCol;
	private int nbRows;
	private int tMapX;
	private int tMapY;
	private int tileSize;
	private int speed = 1;
	
	//création des couleurs nécessaires à l'interface
	private Color BEIGE = new Color(255,250,240);
	private Color DARK_BEIGE = new Color(193, 146, 115);
	
	//création des polices
	private Font categoryFont = new Font("Arial", Font.BOLD, 22);
	private Font whiteBoardFont = new Font("Arial", Font.PLAIN, 20);
	private Font buttonFont = new Font("Arial", Font.PLAIN, 33);
	
	/*Variables for the white board and recap*/
	private int nbMaxTreasures = Simulation.treasures.size();
	private int nbCurrentTreasures = 0;
	private int nbFights = 0;
	private int nbAnimalsDead = 0;
	private int money = 0;
	private int nbExplorersDead = 0;
	
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
	private BufferedImage death=null;
	private BufferedImage treasure=null;
	
	/*Boolean*/
	private boolean treasurePlaced;
	private boolean animalPlaced;
	
	
	/* Chronometer */
	private RealTime timer = new RealTime();
	
	public SimulationState(GameStateManager gsm) {
		super(gsm);
		init();
		initImageFiles();
		treasurePlacement();
		animalsPlacement();
		startTimer();
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
  			death = ImageIO.read(new File("ressources/mort.png"));
  			time = ImageIO.read(new File("ressources/icone_temps.png"));
  			treasure = ImageIO.read(new File("ressources/tresor.png"));
  		} catch (IOException e) {
  			System.out.println("no image");
  			e.printStackTrace();
  		}
	}
	
	public void treasurePlacement() {
		nbCol = tilemap.getNbCols();
		nbRows = tilemap.getNbRows();
		tMapX = tilemap.getX();
		tMapY = tilemap.getY();
		tileSize = tilemap.getTileSize();
		HashMap<String,Treasure> ts = Simulation.treasures;
		
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
		nbCol = tilemap.getNbCols();
		nbRows = tilemap.getNbRows();
		tMapX = tilemap.getX();
		tMapY = tilemap.getY();
		tileSize = tilemap.getTileSize();
		HashMap<String,WildAnimals> waMap = Simulation.animals;
		
		for(HashMap.Entry<String,WildAnimals> entry : waMap.entrySet()) {
			WildAnimals wa = entry.getValue();
			animalPlaced = false;
			while(!animalPlaced) {
				/* Random position X [startPosition X ; Map.width] */
				int x = (int) (tMapX + Math.random() * (tMapX+(nbRows-1)*tileSize+5));
				
				/* Random position Y [startPosition Y; Map.height] */
				int y = (int) (tMapY + Math.random() * (tMapY+(nbCol-1)*tileSize+5));
				
				/* Calcul d�duit de la classe TileMap sur le placement des tiles */
				/* Col = (xPoS - OffSetX)/tileSize, Row = (YPoS - OffSetY)/tileSize */
				int row = (int)(x-5-tMapX)/tileSize;
				int col = (int) (y-5-tMapY)/tileSize;
				

				if(tilemap.getPosition(row,col) == 7) {
					wa.setPosition(new Position(col*tileSize+5+tMapX,row*tileSize+5+tMapY));
					animalPlaced = true;
				}
			}
		}
	}
	
	public void startTimer() {
		Thread t = new Thread(timer);
		t.start();
	}
	
	public void cleanHashMaps() {
		for(String name : Simulation.toRemove) {
			if(name.contains("treasure")) {
				Simulation.treasures.remove(name);
			}
			else if(name.contains("Remy") || name.contains("Mike") || name.contains("Joe") || name.contains("Dora")) {
				Simulation.characters.remove(name);
				Simulation.explorers.remove(name);
			}
			else if(name.contains("Wolf") || name.contains("Bear") || name.contains("Eagle")) {
				Simulation.characters.remove(name);
				Simulation.animals.remove(name);
			}
		}
	}
	
	public void tick() {
		
	}

	public void draw(Graphics g) {
		cleanHashMaps();
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
//				g.drawRect(wa.getPosTerr().getX(),wa.getPosTerr().getY(), wa.getTerritorySize().getHeight(), wa.getTerritorySize().getHeight());
//				g.setColor(Color.red);
			}
			else {
				g.drawOval(c.getPosition().getX()-(c.getAura()/2)+(c.getSize().getWidth()/2), c.getPosition().getY()-(c.getAura()/2)+(c.getSize().getHeight()/2), c.getAura(), c.getAura());
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
		//cadre
		g.setColor(Color.black);
		g.fillRect(1050 , 3, 245, 630);
		g.setColor(Color.white);
		g.fillRect(1052 , 5, 241, 626);
		g.setColor(Color.black);
        g.setFont(categoryFont);
        g.drawString("VOTRE SIMULATION",1060, 30);
        
        //images
        try {
  			heart = ImageIO.read(new File("ressources/icone_coeur.png"));
  			time = ImageIO.read(new File("ressources/icone_temps.png"));
  			treasure = ImageIO.read(new File("ressources/tresor.png"));
  		} catch (IOException e) {
  			System.out.println("no image");
  			e.printStackTrace();
  		}
        g.drawImage(time, 1050, 40, 70, 60, (ImageObserver)this);
        g.drawImage(treasure, 1060, 530, 60, 60, (ImageObserver)this);
        
        //informations
        nbCurrentTreasures = nbMaxTreasures - Simulation.treasures.size();
        
        /* Timer */
        int hours = timer.getHour().getValue();
        int minutes = timer.getMinute().getValue();
        int seconds = timer.getSecond().getValue();
    	String hoursExt = "0";
    	String minutesExt = "0";
    	String secondsExt = "0";
        if(hours >= 10) {
        	hoursExt = "";
        }
        if(minutes >= 10) {
        	minutesExt = "";
        }
        if(seconds >= 10) {
        	secondsExt = "";
        }
        g.drawString(hoursExt + hours + ":" + minutesExt+ minutes + ":" + secondsExt+seconds, 1140, 80);
        
        g.setFont(whiteBoardFont);
        
        /* Explorer's Status */
        int i = 0;
        for(String name : Simulation.listExp) {
        	if(!Simulation.explorers.containsKey(name)) {
        		g.drawString(name+" : DEAD", 1135, 145+i*70);
        		g.drawImage(death, 1055, 110+i*70, 65, 60,(ImageObserver)this);
        		i++;
        	}

        }
		for(Explorer e : Simulation.explorers.values()) {
			g.drawString(e.getName()+" : "+e.getLifePoint()+"/"+e.getLifePointMax(), 1135, 145+i*70);
			g.drawImage(heart, 1050, 110+i*70, 70, 60,(ImageObserver)this);
			i++;
		}
        g.drawString("Tresors : "+nbCurrentTreasures, 1135, 570);
        
        //button
        g.setColor(Color.black);
		g.fillRect(1080, 650, 180, 67);
        g.setColor(DARK_BEIGE);
		g.fillRect(1085, 655, 170, 57);
		g.setFont(buttonFont);
		g.setColor(Color.black);
        g.drawString("Recap",1120, 695);
	}

	public void keyPressed(int k) {}

	public void keyReleased(int k) {}
	
	public void mousePressed(MouseEvent m) {
		if (m.getX()>= 1080 && m.getX()<= 1280 && m.getY()>=645 && m.getY()<= 715) {
			PrintWriter writer;
			try {
				writer = new PrintWriter("ressources/donnees_sim.txt", "UTF-8");
				writer.println("");
				writer.println("07:09=time");
				writer.println("13");
				writer.println(nbCurrentTreasures);
				writer.println(nbFights);
				writer.println(nbAnimalsDead);
				writer.println(nbExplorersDead);
				writer.println("vie");
				writer.println("vie");
				writer.println("vie");
				writer.println("vie");
				writer.println("vie");
				writer.print("vie"); //attention le dernier pas de ln sinon ça va mettre un null
				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			timer.setRunning(false);
			gsm.gameStates.push(new RecapState(gsm));
		}
	}

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

}
