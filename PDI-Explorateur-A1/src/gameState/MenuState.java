package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import ihm.Background;
import ihm.Game;
import ihm.GamePanel;

public class MenuState extends GameState implements ImageObserver {
	
	private int currentSelection = 0; //quelle option du menu on a selectionné
	//liste des options possible à selectionner sur le menu
	private String[] options = {
			"Simulation",
			"Recap",
			"Quit"
	};

	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {}

	public void tick() {}

	//présentation de la fenêtre
	public void draw(Graphics g) {
		BufferedImage image=null;
		try {
			image = ImageIO.read(new File("/Users/julia/Desktop/L3/S6/PDI/ressources/sable.jpeg"));
		} catch (IOException e) {
			System.out.println("pas d'image ");
			e.printStackTrace();
		}
        g.drawImage(image, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, (ImageObserver) this);
        
		for (int i = 0; i<options.length; i++) {
			if (i==currentSelection) {
				g.setColor(Color.green);
			}
			else {
				g.setColor(Color.black);
			}
			//g.drawLine(x1, y1, x2, y2);
			g.setFont(new Font("Arial", Font.PLAIN, 72));
			//g.drawString(str, x, y);
			g.drawString(options[i], GamePanel.WIDTH/2-150, 100 + i * 100);
		}
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
			case 2 : 
				System.out.println("recap");
				break;
			case 3 :
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

}



	/*private Background background;
	private int currentChoice = 0;
	private String[] options = {
			"Start",
			"Recap"
	};
	
	private Color titleColor;
	private Font titleFont;
	private Font font;	
	private boolean isSelected = false;
	
	public MenuState() {	
		try {
			background = new Background("/sable.png",1); 
			background.setVector(-0.1, 0);
			
			titleColor = new Color(128, 0, 0);
			titleFont = new Font("Century Goth", Font.PLAIN, 100);
			font = new Font("Arial", Font.PLAIN, 80);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void select() {
		switch (currentChoice) {
		case 0 :
			GameStateManager.setState(GameStateManager.SELECTIONSTATE);
			break;
		case 1 :
			GameStateManager.setState(GameStateManager.RECAPSTATE);
			break;
		}
	}
	
	public void init() {
	}
	
	public void tick() {
		background.update();
		//InputGame.menu();
		if(isSelected) {
			select();
		}
	}
	
	public void render(Graphics2D g) {
		//DRAW BACKGROUND
		background.draw(g);
		
		//DRAW TITLE
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("EXPLORATEURS AUTONOMES ET COMMUNICANTS", Game.WIDTH/2 - 250, Game.HEIGHT/2 - 120);
		
		//DRAW MENU OPTIONS
		g.setFont(font);
		for(int i = 0; i<options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.BLACK);
			}
			else {
				g.setColor(Color.ORANGE);
			}
			g.drawString(options[i], Game.WIDTH/2 - 100, Game.HEIGHT/2 + i *80);
		}
	}

	public Background getBg() {
		return background;
	}

	public void setBg(Background background) {
		this.background = background;
	}

	public int getCurrentChoice() {
		return currentChoice;
	}

	public void setCurrentChoice(int currentChoice) {
		this.currentChoice = currentChoice;
	}

	public String[] getOptions() {
		return options;
	}

	public void setOptions(String[] options) {
		this.options = options;
	}

	public Color getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(Color titleColor) {
		this.titleColor = titleColor;
	}

	public Font getTitleFont() {
		return titleFont;
	}

	public void setTitleFont(Font titleFont) {
		this.titleFont = titleFont;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
}*/
