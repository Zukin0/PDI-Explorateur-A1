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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import ihm.Game;
import ihm.GamePanel;

public class SelectionState extends GameState implements ImageObserver {
	
	private int nbMinExplorateurs = 0;
	private int nbMaxExplorateurs = 0;
	private int nbExplorateurs = 0;
	
	private int nbMike = 0;
	private int nbRemy = 0;
	private int nbJoe = 0;
	private int nbDora = 0;
	private int difficultySelected = 3;
	private int strategySelected = 3;
	
	private int money = 0; 
	
	/*
	 * Autres couleurs :
	 * BLEU (Color.blue)
	 * JAUNE (Color.orange)
	 */
	
	//création des couleurs nécessaires à l'interface
	private Color BEIGE = new Color(255,250,240);
	private Color DARK_BEIGE = new Color(193, 146, 115);
	private Color ORANGE = new Color(255, 151, 0);
	private Color LIGHT_ORANGE = new Color(254, 234, 209);
	private Color LIGHT_GREEN = new Color(209, 254, 210);
	private Color GREEN = new Color(87, 213, 0);
	private Color LIGHT_BLUE = new Color(209, 234, 254);
	private Color PINK = new Color(255, 2, 148);
	private Color PURPLE = new Color(238, 209, 254);
	
	//création des polices
	private Font titleFont = new Font("Century Goth", Font.BOLD, 40);
	private Font buttonFont = new Font("Arial", Font.PLAIN, 33);
	private Font textFont = new Font("Arial", Font.BOLD, 20);
	private Font infosFont = new Font("Arial", Font.BOLD, 15);
	private Font categoryFont = new Font("Arial", Font.BOLD, 33);
	
	private BufferedImage imageMoney=null;
	private BufferedImage imageMike=null;
	private BufferedImage imageRemy=null;
	private BufferedImage imageJoe=null;
	private BufferedImage imageDora=null;
	private BufferedImage boots=null;
	private BufferedImage binoculars=null;
	private BufferedImage weapon=null;
	
	/*
	 * difficultés : facile, moyen, difficile
	 * explorateurs : Mike, Remy, Joe, Dora
	 * Equipements : bottes, jumelles, arme
	 * Simulation
	 */
	
	public SelectionState(GameStateManager gsm) {
		super(gsm);
	}


	public void init() {}
	
	public void tick() {}
	
	//présentation de la fenêtre
	public void draw(Graphics g) {
		
		//background
		g.setColor(BEIGE);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		//title
        g.setColor(Color.black);
        g.setFont(titleFont);
        g.drawString("CHOISISSEZ VOS PARAMETRES",20, 60);
        
        //money
        g.setColor(Color.black);
        g.fillRect(760, 25, 450, 50);
        g.setColor(BEIGE);
        g.setFont(categoryFont);
        g.drawString("VOTRE ARGENT : "+money+" $",770, 60);
        try {
  			imageMoney = ImageIO.read(new File("/Users/julia/Desktop/L3/S6/PDI/ressources/money.png"));
  		} catch (IOException e) {
  			System.out.println("no image");
  			e.printStackTrace();
  		}
        g.drawImage(imageMoney, 1130, 0, 110, 110, (ImageObserver) this);
        
        //title difficulty
        //g.drawOval(x, y, width, height);
        g.setColor(LIGHT_ORANGE);
        g.fillOval(50, 100, 250, 80);
        g.setColor(Color.black);
        g.setFont(categoryFont);
        g.drawString("DIFFICULTE :",70, 150);
        
        //text difficulty
        g.setColor(Color.LIGHT_GRAY);
		g.fillRect(50, 213, 20, 20);
		g.fillRect(50, 273, 20, 20);
		g.fillRect(50, 333, 20, 20);
		g.setColor(Color.black);
        g.setFont(textFont);
        g.drawString("FACILE",80, 230);
        g.drawString("MOYEN",80, 290);
        g.drawString("DIFFICILE",80,350);
        
        //title strategy
        g.setColor(PURPLE);
        g.fillOval(50, 420, 250, 80);
        g.setColor(Color.black);
        g.setFont(categoryFont);
        g.drawString("STRATEGIE :",70,470);
        
        //text strategies
        g.setColor(Color.LIGHT_GRAY);
		g.fillRect(50, 533, 20, 20);
		g.fillRect(50, 593, 20, 20);
		g.fillRect(50, 653, 20, 20);
		g.setColor(Color.black);
        g.setFont(textFont);
        g.drawString("INTELLIGENTE",80, 550);
        g.drawString("COMBAT",80, 610);
        g.drawString("FUITE",80,670);
        
        //title explorers
        g.setColor(LIGHT_GREEN);
        g.fillOval(440, 100, 340, 80);
        g.setColor(Color.black);
        g.setFont(categoryFont);
        g.drawString("EXPLORATEURS :",470, 150);
        
        //images explorers
  		try {
  			imageMike = ImageIO.read(new File("/Users/julia/Desktop/L3/S6/PDI/ressources/mike.png"));
  			imageRemy = ImageIO.read(new File("/Users/julia/Desktop/L3/S6/PDI/ressources/remy.png"));
  			imageJoe = ImageIO.read(new File("/Users/julia/Desktop/L3/S6/PDI/ressources/joe.png"));
  			imageDora = ImageIO.read(new File("/Users/julia/Desktop/L3/S6/PDI/ressources/dora.png"));
  		} catch (IOException e) {
  			System.out.println("no image");
  			e.printStackTrace();
  		}
        g.drawImage(imageMike, 350, 190, 150, 150, (ImageObserver) this);
        g.drawImage(imageRemy, 550, 190, 150, 150, (ImageObserver) this);
        g.drawImage(imageJoe, 750, 190, 150, 150, (ImageObserver) this);
        g.drawImage(imageDora, 930, 190, 150, 150, (ImageObserver) this);
        
        //text explorers
        g.setColor(DARK_BEIGE);
        g.setFont(textFont);
        g.drawString("STRONG MIKE",350,360);
        g.drawString("FAST REMY",570,360);
        g.drawString("BIG JOE",770,360);
        g.drawString("DORA",970,360);
        
        //point d'attaque(mike) , point de vie(joe) , vitesse de déplacement(remy) , nombres équipements possibles (dora) 
        //mike
        g.setColor(Color.black);
        g.setFont(infosFont);
        g.drawString("Spécialité : attaque",350,385);
        g.drawString("Prix : ?",350,410);
        g.drawString("Sélection : ",350,435);
        //remy
        g.setFont(infosFont);
        g.drawString("Spécialité : vitesse",570,385);
        g.drawString("Prix : ?",570,410);
        g.drawString("Sélection : ",570,435);
        //joe
        g.setFont(infosFont);
        g.drawString("Spécialité : vie",770,385);
        g.drawString("Prix : ?",770,410);
        g.drawString("Sélection : ",770,435);
        //dora
        g.setFont(infosFont);
        g.drawString("Spécialité : équipements",915,385);
        g.drawString("Prix : ?",915,410);
        g.drawString("Sélection : ",915,435);
        
        //title equipements
        g.setColor(LIGHT_BLUE);
        g.fillOval(440, 460, 310, 80);
        g.setColor(Color.black);
        g.setFont(categoryFont);
        g.drawString("EQUIPEMENTS :",470,510);
        
        //images equipments
  		try {
  			weapon = ImageIO.read(new File("/Users/julia/Desktop/L3/S6/PDI/ressources/weapon.png"));
  			boots = ImageIO.read(new File("/Users/julia/Desktop/L3/S6/PDI/ressources/boots.png"));
  			binoculars = ImageIO.read(new File("/Users/julia/Desktop/L3/S6/PDI/ressources/binoculars.png"));
  		} catch (IOException e) {
  			System.out.println("no image");
  			e.printStackTrace();
  		}
        g.drawImage(weapon,350, 550, 100, 100, (ImageObserver) this);
        g.drawImage(binoculars, 550, 550, 100, 100, (ImageObserver) this);
        g.drawImage(boots, 750, 550, 100, 100, (ImageObserver) this);
        
        //text equipments
        g.setColor(DARK_BEIGE);
        g.setFont(textFont);
        g.drawString("ARME",365,670);
        g.drawString("JUMELLES",550,670);
        g.drawString("BOTTES",760,670);
        //weapon
        g.setColor(Color.black);
        g.setFont(infosFont);
        g.drawString("Spécialité : attaque",330,700);
        g.drawString("Prix : ?",330,725);
        //binoculars
        g.setFont(infosFont);
        g.drawString("Spécialité : vitesse",550,700);
        g.drawString("Prix : ?",550,725);
        //boots
        g.setFont(infosFont);
        g.drawString("Spécialité : vie",750,700);
        g.drawString("Prix : ?",750,725);
        
        //White board
        g.setColor(Color.black);
        g.fillRect(1100, 145, 180, 467);
        g.setColor(Color.white);
		g.fillRect(1105, 150, 170, 457);
		g.setColor(DARK_BEIGE);
		g.setFont(textFont);
        g.drawString("EQUIPEMENTS",1120, 178);
        g.setColor(Color.black);
        //y + 30
        g.drawString("Mike : ",1120, 208);
        g.drawString("- ",1120, 238);
        g.drawString(" ",1120, 268);
        g.drawString("Remy : ",1120, 298);
        g.drawString("- ",1120, 328);
        g.drawString(" ",1120, 358);
        g.drawString("Joe : ",1120, 388);
        g.drawString("- ",1120, 418);
        g.drawString(" ",1120, 448);
        g.drawString("Dora : ",1120, 478);
        g.drawString("- ",1120, 508);
        g.drawString("- ",1120, 538);
        g.drawString(" ",1120, 568);
        
        //bouton
        g.setColor(Color.black);
		g.fillRect(1100, 645, 180, 67);
        g.setColor(DARK_BEIGE);
		g.fillRect(1105, 650, 170, 57);
		g.setColor(Color.black);
		g.setFont(buttonFont);
        g.drawString("Simulation",1110, 688);
        
        switch(difficultySelected) {
        case 0 :
        	//easy
        	g.setColor(Color.black);
    		g.fillRect(50, 213, 20, 20);
    		nbMinExplorateurs = 2;
    		nbMaxExplorateurs = 8;
    		money = 10;
        	break;
        case 1 :
        	//medium
        	g.setColor(Color.black);
        	g.fillRect(50, 273, 20, 20);
        	nbMinExplorateurs = 2;
        	nbMaxExplorateurs = 8;
        	money = 10;
        	break;
        case 2 :
        	//hard
        	g.setColor(Color.black);
        	g.fillRect(50, 333, 20, 20);
        	nbMinExplorateurs = 2;
        	nbMaxExplorateurs = 8;
        	money = 10;
        	break;
        }
        
        switch(strategySelected) {
        case 0 :
        	//intelligent
        	g.setColor(Color.black);
        	g.fillRect(50, 533, 20, 20);
        	break;
        case 1 :
        	//combat
        	g.setColor(Color.black);
        	g.fillRect(50, 593, 20, 20);
        	break;
        case 2 :
        	//fuite
        	g.setColor(Color.black);
        	g.fillRect(50, 653, 20, 20);
        	break;
        }
        
        if (nbMike > 0) {
        	g.setFont(textFont);
        	g.setColor(GREEN);
            g.drawString("STRONG MIKE",350,360);
            g.setColor(Color.black);
            g.setFont(infosFont);
            g.drawString("Sélection : "+nbMike,350,435);
        }
        if (nbRemy > 0) {
        	g.setFont(textFont);
        	g.setColor(Color.blue);
        	g.drawString("FAST REMY",570,360);
        	g.setColor(Color.black);
            g.setFont(infosFont);
            g.drawString("Sélection : "+nbRemy,570,435);
        }
        if (nbJoe > 0) {
        	g.setFont(textFont);
        	g.setColor(ORANGE);
        	g.drawString("BIG JOE",770,360);
        	g.setColor(Color.black);
            g.setFont(infosFont);
            g.drawString("Sélection : "+nbJoe,770,435);
        }
        if (nbDora > 0) {
        	g.setFont(textFont);
        	g.setColor(PINK);
        	g.drawString("DORA",970,360);
        	g.setColor(Color.black);
            g.setFont(infosFont);
            g.drawString("Sélection : "+nbDora,915,435);
        }
        
	}

	public void keyPressed(int k) {}

	public void keyReleased(int k) {}

	public void mouseClicked(MouseEvent m) {}


	public void mousePressed(MouseEvent m) {
		//System.out.println(m.getX() + "," + m.getY() + "\n");
		//difficulte
		if ((m.getX()>= 50 && m.getX()<= 70 && m.getY()>=213 && m.getY()<= 233)) {
			System.out.println("DIFFICULTE FACILE CHOISIE");
			difficultySelected = 0;
		}
		else if ((m.getX()>= 50 && m.getX()<= 70 && m.getY()>=275 && m.getY()<= 295)) {
			System.out.println("DIFFICULTE MOYENNE CHOISIE");
			difficultySelected = 1;
		}
		else if ((m.getX()>= 50 && m.getX()<= 70 && m.getY()>=335 && m.getY()<= 355)) {
			System.out.println("DIFFICULTE DIFFICILE CHOISIE");
			difficultySelected = 2;
		}
		
		//strategie
		else if ((m.getX()>= 50 && m.getX()<= 70 && m.getY()>=535 && m.getY()<= 555)) {
			System.out.println("STRATEGIE INTELLIGENTE CHOISIE");
			strategySelected = 0;
		}
		else if ((m.getX()>= 50 && m.getX()<= 70 && m.getY()>=595 && m.getY()<= 615)) {
			System.out.println("STRATEGIE DE COMBAT CHOISIE");
			strategySelected = 1;
		}
		else if ((m.getX()>= 50 && m.getX()<= 70 && m.getY()>=655 && m.getY()<= 675)) {
			System.out.println("STRATEGIE DE FUITE CHOISIE");
			strategySelected = 2;
		}
		
		//explorateur
		else if ((m.getX()>= 445 && m.getX()<= 475 && m.getY()>=250 && m.getY()<=275)) {
			System.out.println("MIKE CHOISI");
			nbMike ++;
			nbExplorateurs ++;
			if (nbExplorateurs > nbMaxExplorateurs) {
				nbExplorateurs --;
				nbMike--;
			}
		}
		else if ((m.getX()>= 365 && m.getX()<= 395 && m.getY()>=250 && m.getY()<=275)) {
			System.out.println("MIKE CHOISI");
			nbMike --;
			nbExplorateurs --;
			if (nbMike<0) {
				nbMike = 0;
			}
		}
		else if ((m.getX()>= 650 && m.getX()<= 685 && m.getY()>=250 && m.getY()<= 275)) {
			System.out.println("REMY CHOISI");
			nbRemy ++;
			nbExplorateurs ++;
			if (nbExplorateurs > nbMaxExplorateurs) {
				nbExplorateurs --;
				nbRemy--;
			}
		}
		else if ((m.getX()>= 565 && m.getX()<= 595 && m.getY()>=250 && m.getY()<= 280)) {
			System.out.println("REMY CHOISI");
			nbRemy --;
			nbExplorateurs --;
			if (nbRemy<0) {
				nbRemy = 0;
			}
		}
		else if ((m.getX()>= 860 && m.getX()<= 895 && m.getY()>=255 && m.getY()<= 280)) {
			System.out.println("JOE CHOISI");
			nbJoe ++;
			nbExplorateurs ++;
			if (nbExplorateurs > nbMaxExplorateurs) {
				nbExplorateurs --;
				nbJoe--;
			}
		}
		else if ((m.getX()>= 755 && m.getX()<= 790 && m.getY()>=255 && m.getY()<= 280)) {
			System.out.println("JOE CHOISI");
			nbJoe --;
			nbExplorateurs --;
			if (nbJoe<0) {
				nbJoe = 0;
			}
		}
		else if ((m.getX()>= 1025 && m.getX()<= 1060 && m.getY()>=250 && m.getY()<= 280)) {
			System.out.println("DORA CHOISIE");
			nbDora ++;
			nbExplorateurs ++;
			if (nbExplorateurs > nbMaxExplorateurs) {
				nbExplorateurs --;
				nbDora--;
			}
		}
		else if ((m.getX()>= 840 && m.getX()<= 970 && m.getY()>=250 && m.getY()<= 280)) {
			System.out.println("DORA CHOISIE");
			nbDora --;
			nbExplorateurs --;
			if (nbDora<0) {
				nbDora = 0;
			}
		}
		
		//equipement
		else if ((m.getX()>= 330 && m.getX()<= 465 && m.getY()>=550 && m.getY()<= 635)) {
			System.out.println("MACHETTES CHOISIES");
		}
		else if ((m.getX()>= 545 && m.getX()<= 650 && m.getY()>=560 && m.getY()<= 630)) {
			System.out.println("JUMELLES CHOISIES");
		}
		else if ((m.getX()>= 750 && m.getX()<= 850 && m.getY()>=560 && m.getY()<= 635)) {
			System.out.println("BOTTES CHOISIE");
		}
		
		//simulation
		else if ((m.getX()>= 1080 && m.getX()<= 1280 && m.getY()>=645 && m.getY()<= 715)) {
			System.out.println("DEBUT DE LA SIMULATION");
			if ((nbExplorateurs >= nbMinExplorateurs) && (nbExplorateurs <= nbMaxExplorateurs)&&(strategySelected != 3)
					&&(difficultySelected != 3)){
				gsm.gameStates.push(new SimulationState(gsm));
			}
		}
	}

	public void mouseReleased(MouseEvent m) {}

	public void mouseEntered(MouseEvent m) {}

	public void mouseExited(MouseEvent m) {}

	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}
	
	//public void render(Graphics2D g) {}
}
