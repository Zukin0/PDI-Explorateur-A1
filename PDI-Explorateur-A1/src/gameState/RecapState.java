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

import ihm.Game;
import ihm.GamePanel;

public class RecapState extends GameState implements ImageObserver{
	
	//création des couleurs nécessaires à l'interface
	private Color BEIGE = new Color(255,250,240);
	private Color DARK_BEIGE = new Color(193, 146, 115);
		
	//création des polices
	private Font titleFont = new Font("Century Goth", Font.BOLD, 40);
	private Font simpleFont = new Font("Century Goth", Font.BOLD, 35);
	private Font texteFont = new Font("Century Goth", Font.PLAIN, 20);
	private Font buttonFont = new Font("Arial", Font.PLAIN, 33);
	
	private BufferedImage map=null;
	private BufferedImage heart=null;
	private BufferedImage clock=null;
	private BufferedImage treasure=null;
	private BufferedImage money=null;
	private BufferedImage fight=null;
	private BufferedImage animals=null;
	private BufferedImage dead=null;
	
	public RecapState(GameStateManager gsm) {
		super(gsm);
	}

	public void readFile(String nom) {
		
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
		g.drawString("RECAPITULATIF DE LA SIMULATION",20, 60);
		
		g.setColor(Color.black);
		g.setFont(simpleFont);
		g.drawString("LES LOGS DE LA SIMULATION",650, 325);
		
		//images
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
        
        g.drawImage(clock, 20, 80, 60, 60, (ImageObserver) this);
        g.drawImage(money, 20,135, 60, 60, (ImageObserver) this);
        g.drawImage(treasure,20, 190, 60, 60, (ImageObserver) this);
        g.drawImage(fight, 20, 245, 60, 60, (ImageObserver) this);
        g.drawImage(animals, 20, 300,60, 60, (ImageObserver) this);
        g.drawImage(dead, 20, 355, 60, 60, (ImageObserver) this);
        g.drawImage(heart, 20, 410, 60, 60, (ImageObserver) this);
        g.drawImage(heart, 20, 465, 60, 60, (ImageObserver) this);
        g.drawImage(heart, 20, 520,60,60, (ImageObserver) this);
        g.drawImage(heart, 20, 575,60,60, (ImageObserver) this);
        g.drawImage(heart, 20, 630, 60, 60, (ImageObserver) this);
        g.drawImage(heart, 20,685, 60,60, (ImageObserver) this);
        
        //texte
        g.setColor(Color.black);
		g.setFont(texteFont);
		g.drawString("Votre simulation a duré 7 minutes et 19 secondes",95, 120);
		g.drawString("Il vous reste 10 $",95, 175);
		g.drawString("Vous avez trouvé 3 trésors !",95, 230);
		g.drawString("Vous avez combattu 7 fois",95, 285);
		g.drawString("Vous avez tué 5 animaux, bravo !",95, 340);
		g.drawString("2 de vos explorateurs sont morts ...",95, 395);
		g.drawString("Il reste 3 points de vie à Dora1 ",95, 450);
		g.drawString("Il reste 1 points de vie à Dora2",95, 505);
		g.drawString("Mike1 est mort",95, 560);
		g.drawString("Il reste 7 points de vie à Joe1",95, 615);
		g.drawString("Mike2 est mort",95, 670);
		g.drawString("Il reste 4 points de vie à Remy1",95, 725);
		
		g.drawString("Blablabla.....",650, 360);
		
		//button
        g.setColor(Color.black);
		g.fillRect(1080, 650, 180, 67);
        g.setColor(DARK_BEIGE);
		g.fillRect(1085, 655, 170, 57);
		g.setFont(buttonFont);
		g.setColor(Color.black);
        g.drawString("Menu",1125, 695);
	}

	public void keyPressed(int k) {}

	public void keyReleased(int k) {}

	public void mouseClicked(MouseEvent m) {}

	public void mousePressed(MouseEvent m) {
		if (m.getX()>= 1080 && m.getX()<= 1280 && m.getY()>=645 && m.getY()<= 715) {
			while (!(gsm.gameStates.isEmpty())) {
				gsm.gameStates.pop();
			}
			if((gsm.gameStates.isEmpty())) {
				System.out.println("/////STACK EMPTY");
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
