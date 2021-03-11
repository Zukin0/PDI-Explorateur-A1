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
		
	//création des polices
	private Font titleFont = new Font("Century Goth", Font.BOLD, 40);
	private Font simpleFont = new Font("Century Goth", Font.BOLD, 35);
	
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
		
		//image
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
        
        g.drawImage(clock, 20, 80, 50, 50, (ImageObserver) this);
        g.drawImage(money, 20,135, 50, 50, (ImageObserver) this);
        g.drawImage(treasure,20, 190, 50, 50, (ImageObserver) this);
        g.drawImage(fight, 20, 245, 50, 50, (ImageObserver) this);
        g.drawImage(animals, 20, 300,50, 50, (ImageObserver) this);
        g.drawImage(dead, 20, 355, 50, 50, (ImageObserver) this);
        g.drawImage(heart, 20, 410, 50, 50, (ImageObserver) this);
        g.drawImage(heart, 20, 465, 50, 50, (ImageObserver) this);
        g.drawImage(heart, 20, 520, 50,50, (ImageObserver) this);
        g.drawImage(heart, 20, 575, 50,50, (ImageObserver) this);
        g.drawImage(heart, 20, 630, 50, 50, (ImageObserver) this);
        g.drawImage(heart, 20,685, 50,50, (ImageObserver) this);
	}

	public void keyPressed(int k) {}

	public void keyReleased(int k) {}

	public void mouseClicked(MouseEvent m) {}

	public void mousePressed(MouseEvent m) {}

	public void mouseReleased(MouseEvent m) {}

	public void mouseEntered(MouseEvent m) {}

	public void mouseExited(MouseEvent m) {}

	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}
}
