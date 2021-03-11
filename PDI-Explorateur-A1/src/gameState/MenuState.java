package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import game.Simulation;
import ihm.Background;
import ihm.Game;
import ihm.GamePanel;

public class MenuState extends GameState implements ImageObserver {
	
	private int nbMike = 2;
	private int nbRemy = 2;
	private int nbJoe = 2;
	private int nbDora = 2;
	private Simulation sim;
	private int tabEx[] = {nbDora, nbJoe, nbRemy, nbMike};
	
	//création des couleurs nécessaires à l'interface
	private Color BEIGE = new Color(255,250,240);
	private Color DARK_BEIGE = new Color(193, 146, 115);
	
	//création des polices et image
	private Font titleFont = new Font("Century Goth", Font.BOLD, 48);
	private Font selectionFont = new Font("Arial", Font.PLAIN, 50);	
	private Font textFont_bold = new Font("Arial", Font.BOLD, 30);
	private Font textFont_italic = new Font("Arial", Font.ITALIC, 25);
	private BufferedImage image=null;
	
	//quelle option du menu on a selectionné
	private int currentSelection = 0; 
	//liste des options possible à selectionner sur le menu
	private String[] options = {
			"Simulation",
			"Recap",
			"Quitter"
	};
	
	private int nbClicks = 0;

	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {}

	public void tick() {}

	//présentation de la fenêtre
	public void draw(Graphics g) {
		
		//background
		g.setColor(BEIGE);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
//		
//		g.setColor(Color.black);
//		g.fillRect(20, 20, 40, 40);
        
        //squares 
        //g.drawRect(x, y, width, height);
		//rules
		g.setColor(Color.black);
		g.fillRect(100, 475, 1100, 250);
        g.setColor(BEIGE);
        g.fillRect(105, 480, 1090, 240);
        //selections
        g.setColor(Color.black);
		g.fillRect(520, 145, 290, 77);
        g.setColor(DARK_BEIGE);
		g.fillRect(525, 150, 280, 67);
		g.setColor(Color.black);
		g.fillRect(520, 245, 290, 77);
		g.setColor(DARK_BEIGE);
		g.fillRect(525, 250, 280, 67);
		g.setColor(Color.black);
		g.fillRect(520, 345, 290, 77);
		g.setColor(DARK_BEIGE);
		g.fillRect(525, 350, 280, 67);
		
		//image
		try {
			image = ImageIO.read(new File("ressources/schema_carte_sans_fond.png"));
		} catch (IOException e) {
			System.out.println("no image ");
			e.printStackTrace();
		}
		//g.setColor(Color.black);
		//g.fillRect(90, 140, 270, 270);
        g.drawImage(image, 100, 150, 250, 250, (ImageObserver) this);
        //g.setColor(Color.black);
		//g.fillRect(940, 140, 270, 270);
        g.drawImage(image, 950, 150, 250, 250, (ImageObserver) this);
        
        //title rules
        g.setColor(DARK_BEIGE);
        g.setFont(textFont_bold);
        g.drawString("REGLES", 120, 515);
        
        //text rules
        g.setColor(Color.black);
        g.setFont(textFont_italic);
        g.drawString("Pour cette simulation, vous allez devoir choisir :", 120, 550);
        g.drawString("- une difficulte,", 130, 580);
        g.drawString("- une strategie,", 130, 610);
        g.drawString("- des explorateurs (leur nombre dependra de la difficulte choisie),", 130, 640);
        g.drawString("- des equipements pour vos explorateurs.", 130, 670);
        g.drawString("Faites attention a votre argent et demarrez !", 120, 700);
        
        //voir le centre de la fenetre
        //g.drawLine(GamePanel.WIDTH/2, 0, GamePanel.WIDTH/2, GamePanel.HEIGHT);
        
        //title
        g.setColor(Color.black);
        g.setFont(titleFont);
        //g.drawString(str, x, y);
        g.drawString("EXPLORATEURS AUTONOMES ET COMMUNICANTS",30, 80);
        
        //selection
        g.setFont(selectionFont);
		for (int i = 0; i<options.length; i++) {
			if (i==currentSelection) {
				switch (i) {
				case 0 :
					g.setColor(BEIGE);
					g.drawString(options[i], 550, 200);
					break;
				case 1 : 
					g.setColor(BEIGE);
					g.drawString(options[i], 590, 300);
					break;
				case 2 :
					g.setColor(BEIGE);
					g.drawString(options[i], 590, 400);
					break;
				}
			}
			else {
				switch (i) {
				case 0 :
					g.setColor(Color.black);
					g.drawString(options[i], 550, 200);
					break;
				case 1 : 
					g.setColor(Color.black);
					g.drawString(options[i], 590, 300);
					break;
				case 2 :
					g.setColor(Color.black);
					g.drawString(options[i], 590, 400);
					break;
				}
			}
		}
		//g.drawString("Nb clicks = " + nbClicks, 500, 500);//////////////
	}
	
	//evenements avec le clavier
	public void keyPressed(int k) {
		if (k==KeyEvent.VK_DOWN) {
			currentSelection ++;
			if(currentSelection>= options.length) {
				 currentSelection = 0;
			}
		} else if (k == KeyEvent.VK_UP) {
			currentSelection --;
			if (currentSelection < 0) {
				currentSelection = options.length - 1;
			}
		}
		if (k == KeyEvent.VK_ENTER) {
			switch (currentSelection) {
			case 0 : 
				System.out.println("simulation");
				gsm.gameStates.push(new SelectionState(gsm));
				break;
			case 1 : 
				System.out.println("recap");
				gsm.gameStates.push(new RecapState(gsm));
				break;
			case 2 :
				System.out.println("quit");
				System.exit(0);
				break; 
			}
		}
	} 

	public void keyReleased(int k) {}

	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}

	public void mouseClicked(MouseEvent m) {
		
	}

	public void mousePressed(MouseEvent m) {
		nbClicks ++;
		System.out.println(m.getX() + "," + m.getY());
		
		//faire toutes les possibilités de coordonnées pour les différents choix
		
	}

	public void mouseReleased(MouseEvent m) {}

	public void mouseEntered(MouseEvent m) {}

	public void mouseExited(MouseEvent m) {}

}