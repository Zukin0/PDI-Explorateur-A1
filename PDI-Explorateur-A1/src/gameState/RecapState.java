package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import data.Treasure;
import character.Character;
import game.Simulation;
import ihm.GamePanel;

/**
 * @brief This class defines the recap state
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 *
 */

public class RecapState extends GameState implements ImageObserver{
	
	/**Initialization of necessary colors and fonts*/
	private Color BEIGE = new Color(255,250,240);
	private Color DARK_BEIGE = new Color(193, 146, 115);
	private Font titleFont = new Font("Century Goth", Font.BOLD, 40);
	private Font texteFont = new Font("Century Goth", Font.PLAIN, 20);
	private Font buttonFont = new Font("Arial", Font.PLAIN, 33);
	
	/**Variables for the white board and recap*/
	private String time = "0";
	private String currentMoney = "0";
	private String nbCurrentTreasures = "0";
	private String nbFights = "0";
	private String nbAnimalsDead ="0";
	private String nbExplorersDead = "0";
	private String lifeExplo1 = "0";
	private String lifeExplo2 = "0";
	private String lifeExplo3 = "0";
	private String lifeExplo4 = "0";
	private String lifeExplo5 = "0";
	private String lifeExplo6 = "0";
	private int nbExplo = 2;
	
	/**Images*/
	private BufferedImage map=null;
	private BufferedImage heart=null;
	private BufferedImage clock=null;
	private BufferedImage treasure=null;
	private BufferedImage money=null;
	private BufferedImage fight=null;
	private BufferedImage animals=null;
	private BufferedImage dead=null;
	
	/**
	 * @brief Constructor
	 * @param gsm
	 */
	public RecapState(GameStateManager gsm) {
		super(gsm);
		readFile("ressources/donnees_money.txt");
		readFile("ressources/donnees_sim.txt");
	}

	public void init() {}
	
	public void tick() {}
	
	/**
	 * @brief Method that reset all the simulation's parameters
	 * 
	 */
	public void resetSimulation() {
		for(Character c : Simulation.characters.values()) {
			Simulation.toRemove.add(c.getName());
			c.setDead(true);
		}
		for(Treasure t : Simulation.treasures.values()) {
			Simulation.toRemove.add(t.getName());
		}
		Simulation.threads.clear();
		Simulation.listExp.clear();
	}
	
	/**
	 * @brief Method that removes treasures, explorers or and animals from their hashmap when dead
	 */
	public void clearAll() {
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
		Simulation.toRemove.clear();
	}
	
	/**
	 * @brief Method that fills the graphical interface
	 */
	public void draw(Graphics g) {
		clearAll();
		
		/**Background*/
		g.setColor(BEIGE);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
				
		/**Titles*/
		g.setColor(Color.black);
		g.setFont(titleFont);
		g.drawString("RECAPITULATIF DE LA SIMULATION",20, 60);
		
		/**Images*/
		try {
			map = ImageIO.read(new File("ressources/schema_carte_sans_fond.png"));
			heart= ImageIO.read(new File("ressources/icone_coeur.png"));
			clock= ImageIO.read(new File("ressources/icone_temps.png"));
			treasure= ImageIO.read(new File("ressources/tresor.png"));
			money= ImageIO.read(new File("ressources/money.png"));
			fight= ImageIO.read(new File("ressources/weapon.png"));
			animals= ImageIO.read(new File("ressources/ours_recap.png"));
			dead= ImageIO.read(new File("ressources/mort.png"));
		} catch (IOException e) {
			System.out.println("no image ");
			e.printStackTrace();
		}
        g.drawImage(map, 1000, 40, 200, 200, (ImageObserver) this);
        
        /**Texts*/
        g.setColor(Color.black);
		g.setFont(texteFont);
		g.drawImage(clock, 50, 80, 60, 60, (ImageObserver) this);
		g.drawString("Temps de votre simulation : "+time,125, 120);
		g.drawImage(money, 50,135, 60, 60, (ImageObserver) this);
		g.drawString("Il vous reste "+currentMoney+" $",125, 175);
		g.drawImage(treasure,50, 190, 60, 60, (ImageObserver) this);
		g.drawString("Vous avez trouvé "+nbCurrentTreasures+" trésors !",125, 230);
		g.drawImage(fight, 50, 245, 60, 60, (ImageObserver) this);
		g.drawString("Vous avez combattu "+nbFights+" fois",125, 285);
		g.drawImage(animals, 50, 300,60, 60, (ImageObserver) this);
		g.drawString("Vous avez tué "+nbAnimalsDead+" animaux, bravo !",125, 340);
		g.drawImage(dead, 50, 355, 60, 60, (ImageObserver) this);
		g.drawString(nbExplorersDead+" de vos explorateurs sont morts ...",125, 395);
		
		g.drawImage(heart, 50, 410, 60, 60, (ImageObserver) this);
        g.drawImage(heart, 50, 465, 60, 60, (ImageObserver) this);
        g.drawString(lifeExplo1,125, 450);
		g.drawString(lifeExplo2,125, 505);
		
		switch(nbExplo) {
		case 3:
			g.drawImage(heart, 50, 520,60,60, (ImageObserver) this);
			g.drawString(lifeExplo3,125, 560);
			break;
		case 4 :
			g.drawImage(heart, 50, 520,60,60, (ImageObserver) this);
			g.drawString(lifeExplo3,125, 560);
			g.drawImage(heart, 50, 575,60,60, (ImageObserver) this);
			g.drawString(lifeExplo4,125, 615);
			
			break;
		case 5 :
			g.drawImage(heart, 50, 520,60,60, (ImageObserver) this);
			g.drawString(lifeExplo3,125, 560);
			g.drawImage(heart, 50, 575,60,60, (ImageObserver) this);
			g.drawString(lifeExplo4,125, 615);
			g.drawImage(heart, 50, 630, 60, 60, (ImageObserver) this);
			g.drawString(lifeExplo5,125, 670);
			break;
		case 6 : 
			g.drawImage(heart, 50, 520,60,60, (ImageObserver) this);
			g.drawString(lifeExplo3,125, 560);
			g.drawImage(heart, 50, 575,60,60, (ImageObserver) this);
			g.drawString(lifeExplo4,125, 615);
			g.drawImage(heart, 50, 630, 60, 60, (ImageObserver) this);
			g.drawString(lifeExplo5,125, 670);
			g.drawImage(heart, 50,685, 60,60, (ImageObserver) this);
			g.drawString(lifeExplo6,125, 725);
			break;
		}
		
		/**Button*/
        g.setColor(Color.black);
		g.fillRect(1080, 650, 180, 67);
        g.setColor(DARK_BEIGE);
		g.fillRect(1085, 655, 170, 57);
		g.setFont(buttonFont);
		g.setColor(Color.black);
        g.drawString("Menu",1125, 695);
	}
	
	/**
	 * @brief Method that reads a file using scanner 
	 * @param s : the file's path
	 */
	public void readFile(String s) {
		try {
			if (s.equals("ressources/donnees_money.txt")) {
				FileInputStream file = new FileInputStream(s);
				Scanner scanner = new Scanner(file); 
				currentMoney = scanner.nextLine();
			    scanner.close(); 
			}
			else if(s.equals("ressources/donnees_sim.txt")) {
				FileInputStream file = new FileInputStream(s);
				Scanner scanner = new Scanner(file); 
				time = scanner.nextLine();
				nbCurrentTreasures = scanner.nextLine();
				nbFights =scanner.nextLine();
				nbAnimalsDead = scanner.nextLine();
				nbExplorersDead = scanner.nextLine();
				lifeExplo1 = scanner.nextLine();
				lifeExplo2 = scanner.nextLine();
				if (scanner.hasNext()) {
					lifeExplo3 = scanner.nextLine();
					nbExplo++;
				}
				if(scanner.hasNext()) {
					lifeExplo4 = scanner.nextLine();
					nbExplo++;
				}
				if(scanner.hasNext()) {
					lifeExplo5 = scanner.nextLine();
					nbExplo++;
				}
				if(scanner.hasNext()) {
					lifeExplo6 = scanner.nextLine();
					nbExplo++;
				}

			    scanner.close(); 
			} 
		}			
			catch (Exception e) {
				e.printStackTrace();
			}
	}

	public void keyPressed(int k) {}

	public void keyReleased(int k) {}

	public void mouseClicked(MouseEvent m) {}

	/**
	 * @brief Method that detects the mouse's clicks to interact with the user and go from one state to another
	 */
	public void mousePressed(MouseEvent m) {
		if (m.getX()>= 1080 && m.getX()<= 1280 && m.getY()>=645 && m.getY()<= 715) {
			resetSimulation();
			while (!(gsm.gameStates.isEmpty())) {
				gsm.gameStates.pop();
			}
			if((gsm.gameStates.isEmpty())) {
			}
			gsm.gameStates.push(new MenuState(gsm));
		}
	}

	public void mouseReleased(MouseEvent m) {}

	public void mouseEntered(MouseEvent m) {}

	public void mouseExited(MouseEvent m) {}

	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}
}
