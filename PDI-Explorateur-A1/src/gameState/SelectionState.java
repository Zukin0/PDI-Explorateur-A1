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
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import character.builders.explorers.core.ExDirector;
import game.Difficulty;
import game.Simulation;
import ihm.Game;
import ihm.GamePanel;

public class SelectionState extends GameState implements ImageObserver {
	
	private boolean machette = false;
	
	private int nbMinExplorateurs = 3;
	private int nbMaxExplorateurs = 6;
	private int nbExplorateurs = 0;
	private int nbTreasures = 0;
	private int nbAnimals = 0;
	
	private int nbMike = 0;
	private int nbRemy = 0;
	private int nbJoe = 0;
	private int nbDora = 0;
	
	/* Diffculty variables */
	private Difficulty dif;
	private boolean changeDif = false;
	private int difficultySelected = 3;
	
	private int strategySelected = 3;
	
	private int priceExplorers = 0;
	private int priceWeapon = 0;
	private int priceBoots = 0;
	private int priceBinoculars = 0;
	
	
	/* Character Selection for Equipment choice */
	private boolean isSelectedDora = false;
	private boolean isSelectedMike = false;
	private boolean isSelectedRemy = false;
	private boolean isSelectedJoe = false;
	
	private String doraEquipment = "";
	private String mikeEquipment = "";
	private String remyEquipment = "";
	private String joeEquipment = "";
	
	private int isSelectedTab = -1;
	
	private int indice = 0;
	private String[] tabExplorers = {" ", " ", " ", " ", " ", " "};
	
	private ArrayList<String> listExplorers; //{"Dora1","Joe1"}
	/* List equipment for each explorer */
	private HashMap<String,ArrayList<String>> exEquipements;
	
	private Simulation sim;
	
	private int money;
	
	private boolean alreadyEquiped = false;
	
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
	private BufferedImage bootsIcon=null;
	private BufferedImage binocularsIcon=null;
	private BufferedImage weaponIcon=null;
	
	/*
	 * difficultés : facile, moyen, difficile
	 * explorateurs : Mike, Remy, Joe, Dora
	 * Equipements : bottes, jumelles, arme
	 * Simulation
	 */
	
	public SelectionState(GameStateManager gsm) {
		super(gsm);
		init();
	}


	public void init() {
		listExplorers = new ArrayList<String>();
		exEquipements = new HashMap<String,ArrayList<String>>();
		dif = new Difficulty();
	}
	
	public void resetAll() {
		money = dif.getMoney();
		nbDora = 0;
		nbMike = 0;
		nbJoe = 0;
		nbRemy = 0;
		nbExplorateurs = 0;
		isSelectedTab = -1;
		listExplorers.clear();
		exEquipements.clear();
	}
	
	public void tick() {}
	
	//présentation de la fenêtre
	public void draw(Graphics g) {
		
	////////////////////////////TOP OF THE FRAME/////////////////////////////////////
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
  			imageMoney = ImageIO.read(new File("ressources/money.png"));
  		} catch (IOException e) {
  			System.out.println("no image");
  			e.printStackTrace();
  		}
        g.drawImage(imageMoney, 1140, 0, 110, 110, (ImageObserver) this);
        
////////////////////////////DIFFICULTY/////////////////////////////////////
        //title difficulty
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
        
        /* reset selection if difficulty is changed */
        if(changeDif) {
            switch(difficultySelected) {
            case 0 :
            	//easy
        		dif.changeDif(2, 3, 100); // param : nbTreasure, nbAnimals, money
        		resetAll();
            	break;
            case 1 :
            	//medium
        		dif.changeDif(4, 6, 130); // param : nbTreasure, nbAnimals, money
        		resetAll();
            	break;
            case 2 :
            	//hard
        		dif.changeDif(8, 9, 160); // param : nbTreasure, nbAnimals, money
        		resetAll();
            	break;
            }
            changeDif = false;
        }
        /* draw difficulty rectangle selected */
        else if(difficultySelected != 3) {
        	g.setColor(Color.black);
    		g.fillRect(50, 213 + (difficultySelected * 60), 20, 20);
        }

        
////////////////////////////STRATEGY/////////////////////////////////////
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
        
        switch(strategySelected) {
        case 0 :
        	//intelligent
        	g.setColor(Color.black);
        	g.fillRect(50, 533, 20, 20);
        	priceWeapon = 10;
        	priceBinoculars = 20;
        	priceBoots = 10;
        	priceExplorers = 20;
        	break;
        case 1 :
        	//combat
        	g.setColor(Color.black);
        	g.fillRect(50, 593, 20, 20);
        	priceWeapon = 20;
        	priceBinoculars = 10;
        	priceBoots = 10;
        	priceExplorers = 10;
        	break;
        case 2 :
        	//fuite
        	g.setColor(Color.black);
        	g.fillRect(50, 653, 20, 20);
        	priceWeapon = 10;
        	priceBinoculars = 10;
        	priceBoots = 20;
        	priceExplorers = 10;
        	break;
        }
        
////////////////////////////EXPLORERS/////////////////////////////////////
        //title explorers
        g.setColor(LIGHT_GREEN);
        g.fillOval(440, 100, 340, 80);
        g.setColor(Color.black);
        g.setFont(categoryFont);
        g.drawString("EXPLORATEURS :",470, 150);
        
        //images explorers
  		try {
  			imageMike = ImageIO.read(new File("ressources/mike.png"));
  			imageRemy = ImageIO.read(new File("ressources/remy.png"));
  			imageJoe = ImageIO.read(new File("ressources/joe.png"));
  			imageDora = ImageIO.read(new File("ressources/dora.png"));
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
        g.drawString("Specialite : attaque",350,385);
        g.drawString("Prix : "+priceExplorers+" $",350,410);
        g.drawString("Selection : ",350,435);
        //remy
        g.setFont(infosFont);
        g.drawString("Specialite : vitesse",570,385);
        g.drawString("Prix : "+priceExplorers+" $",570,410);
        g.drawString("Selection : ",570,435);
        //joe
        g.setFont(infosFont);
        g.drawString("Specialite : vie",770,385);
        g.drawString("Prix : "+priceExplorers+" $",770,410);
        g.drawString("Selection : ",770,435);
        //dora
        g.setFont(infosFont);
        g.drawString("Specialite : equipements",915,385);
        g.drawString("Prix : "+priceExplorers+" $",915,410);
        g.drawString("Selection : ",915,435);
        
////////////////////////////EQUIPMENTS/////////////////////////////////////
        //title equipements
        g.setColor(LIGHT_BLUE);
        g.fillOval(440, 460, 310, 80);
        g.setColor(Color.black);
        g.setFont(categoryFont);
        g.drawString("EQUIPEMENTS :",470,510);
        
        //images equipments
  		try {
  			weapon = ImageIO.read(new File("ressources/weapon-.png"));
  			boots = ImageIO.read(new File("ressources/boots-.png"));
  			binoculars = ImageIO.read(new File("ressources/binoculars-.png"));
  			weaponIcon = ImageIO.read(new File("ressources/weapon.png"));
  			bootsIcon = ImageIO.read(new File("ressources/boots.png"));
  			binocularsIcon = ImageIO.read(new File("ressources/binoculars.png"));
  		} catch (IOException e) {
  			System.out.println("no image");
  			e.printStackTrace();
  		}
        g.drawImage(weapon,330, 530, 140, 140, (ImageObserver) this);
        g.drawImage(binoculars, 530, 530, 140, 140, (ImageObserver) this);
        g.drawImage(boots, 730, 520, 140, 140, (ImageObserver) this);
        
        //text equipments
        g.setColor(DARK_BEIGE);
        g.setFont(textFont);
        g.drawString("ARME",365,670);
        g.drawString("JUMELLES",550,670);
        g.drawString("BOTTES",760,670);
        //weapon
        g.setColor(Color.black);
        g.setFont(infosFont);
        g.drawString("Apporte des points d'attaque",330,700);
        g.drawString("Prix : "+priceWeapon+" $",330,725);
        //binoculars
        g.setFont(infosFont);
        g.drawString("Allonge la vision",550,700);
        g.drawString("Prix : "+priceBinoculars+" $",550,725);
        //boots
        g.setFont(infosFont);
        g.drawString("Empêche l'ensevelissement",750,700);
        g.drawString("Prix : "+priceBoots+" $",750,725);
        
////////////////////////////WHITE BOARD/////////////////////////////////////
        g.setColor(Color.black);
        g.fillRect(1100, 145, 180, 467);
        g.setColor(Color.white);
		g.fillRect(1105, 150, 170, 457);
		g.setColor(DARK_BEIGE);
		g.setFont(textFont);
        g.drawString("EQUIPEMENTS",1120, 178);
        g.setColor(Color.black);
        
        /* Draw Explorer's names and change color when selected */
        for(int i = 0; i<listExplorers.size(); i++) {
        	String name = listExplorers.get(i);
  
        	/*Add the explorer's equipement when selected*/
        	/* Icones start at height 205 and are 60 pixels separeted */
        	if ((exEquipements.get(name))!=null) {
        		ArrayList<String> tmp = exEquipements.get(name);
        		int nbEq = 0, center = 0;
        		for (String equipements : tmp) {
        			nbEq++;
        			/* When another item is added (size = 2) => No center */
        			if(tmp.size() > 1) {
        				center = 0; 
        			}
        			/* When only 1 item is added => Center */
        			else { 
        				center = 1;
        			}
        			if (equipements.equals("Bottes")) {
        				g.drawImage(bootsIcon, 1190 + (40*(nbEq-1) + (20*center)), 210 + 60*i, 40, 40, (ImageObserver) this);
        			}
        			else if (equipements.equals("Machettes")) {
        				g.drawImage(weaponIcon, 1190 + (40*(nbEq-1) + (20*center)), 210 + 60*i, 40, 40, (ImageObserver) this);
        			}
        			else if (equipements.equals("Jumelles")) {
        				g.drawImage(binocularsIcon, 1190 + (40*(nbEq-1) + (20*center)), 210 + 60*i, 40, 40, (ImageObserver) this);
        			}
        		}
        	}
        	
        	/* Names start at height 238 and are 60 pixels separeted */
        	if(isSelectedTab == i) { // If a character is selected
        		g.setColor(Color.red);
        		g.drawString(name,1120, 238 + 60*i);

        	}
        	else {
            	g.drawString(name,1120, 238 + 60*i);
        	}
        	g.setColor(Color.black);

        }
        
        //bouton
        g.setColor(Color.black);
		g.fillRect(1100, 645, 180, 67);
        g.setColor(DARK_BEIGE);
		g.fillRect(1105, 650, 170, 57);
		g.setColor(Color.black);
		g.setFont(buttonFont);
        g.drawString("Simulation",1110, 688);
        
        if (nbMike > 0) {
        	g.setFont(textFont);
        	g.setColor(GREEN);
            g.drawString("STRONG MIKE",350,360);
            g.setColor(Color.black);
            g.setFont(infosFont);
            g.drawString("Selection : "+nbMike,350,435);
            isSelectedMike = true;
        } else if (nbMike <=0){
        	isSelectedMike = false;
        }
        if (nbRemy > 0) {
        	g.setFont(textFont);
        	g.setColor(Color.blue);
        	g.drawString("FAST REMY",570,360);
        	g.setColor(Color.black);
            g.setFont(infosFont);
            g.drawString("Selection : "+nbRemy,570,435);
            isSelectedRemy = true;
        } else if (nbRemy <= 0){
        	isSelectedRemy = false;
        }
        if (nbJoe > 0) {
        	g.setFont(textFont);
        	g.setColor(ORANGE);
        	g.drawString("BIG JOE",770,360);
        	g.setColor(Color.black);
            g.setFont(infosFont);
            g.drawString("Selection : "+nbJoe,770,435);
            isSelectedJoe = true;
        } else if (nbJoe <= 0) {
        	isSelectedJoe = false;
        }
        if (nbDora > 0) {
        	g.setFont(textFont);
        	g.setColor(PINK);
        	g.drawString("DORA",970,360);
        	g.setColor(Color.black);
            g.setFont(infosFont);
            g.drawString("Selection : "+nbDora,915,435);
            isSelectedDora = true;
        } else if (nbDora <= 0) {
        	isSelectedDora = false;
        }
        
	}

	public void keyPressed(int k) {}

	public void keyReleased(int k) {}

	public void mouseClicked(MouseEvent m) {}


	public void mousePressed(MouseEvent m) {
		System.out.println("Mouse position : " + m.getX() + "," + m.getY() + "\n");
		
////////////////////////////DIFFICULTY/////////////////////////////////////
		if ((m.getX()>= 50 && m.getX()<= 70 && m.getY()>=213 && m.getY()<= 233)) {
			System.out.println("DIFFICULTE FACILE CHOISIE");
			difficultySelected = 0;
			changeDif = true;
		}
		else if ((m.getX()>= 50 && m.getX()<= 70 && m.getY()>=275 && m.getY()<= 295)) {
			System.out.println("DIFFICULTE MOYENNE CHOISIE");
			difficultySelected = 1;
			changeDif = true;
		}
		else if ((m.getX()>= 50 && m.getX()<= 70 && m.getY()>=335 && m.getY()<= 355)) {
			System.out.println("DIFFICULTE DIFFICILE CHOISIE");
			difficultySelected = 2;
			changeDif = true;
		}
		
////////////////////////////STRATEGY/////////////////////////////////////
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
		
////////////////////////////EXPLORERS/////////////////////////////////////

		else if (m.getX()>= 445 && m.getX()<= 475 && m.getY()>=250 && m.getY()<=275 && difficultySelected != 3 && strategySelected != 3) { // Ajout  Mike
			if ((nbExplorateurs+1 <= nbMaxExplorateurs)&&(money>= priceExplorers)){
				nbMike++;
				nbExplorateurs++;
				String name = "Mike" + nbMike;
				listExplorers.add(name);
				exEquipements.put(name, new ArrayList<String>());
				money = money - priceExplorers;
			}
		}
		else if (m.getX()>= 365 && m.getX()<= 395 && m.getY()>=250 && m.getY()<=275 && isSelectedMike == true) {
			String name = "Mike" + nbMike;
			listExplorers.remove(name);
			exEquipements.remove(name);
			nbMike--;
			nbExplorateurs--;
			money = money + priceExplorers;
		}
		else if (m.getX()>= 650 && m.getX()<= 685 && m.getY()>=250 && m.getY()<= 275 && difficultySelected != 3 && strategySelected != 3) {
			if (nbExplorateurs+1 <= nbMaxExplorateurs) {
				if (money>=priceExplorers) {
					nbRemy++;
					nbExplorateurs++;
					String name = "Remy" + nbRemy;
					listExplorers.add(name);
					exEquipements.put(name, new ArrayList<String>());
					money = money - priceExplorers;
				}
			}
		}
		else if (m.getX()>= 565 && m.getX()<= 595 && m.getY()>=250 && m.getY()<= 280 && isSelectedRemy == true) {
			String name = "Remy" + nbRemy;
			listExplorers.remove(name);
			exEquipements.remove(name);
			nbRemy--;
			nbExplorateurs--;
			money = money + priceExplorers;
		}
		else if (m.getX()>= 860 && m.getX()<= 895 && m.getY()>=255 && m.getY()<= 280 && difficultySelected != 3 && strategySelected != 3) {
			if (nbExplorateurs+1 <= nbMaxExplorateurs) {
				if (money>=priceExplorers) {
					nbJoe++;
					nbExplorateurs++;
					String name = "Joe" + nbJoe;
					listExplorers.add(name);
					exEquipements.put(name, new ArrayList<String>());
					money = money - priceExplorers;
				}
			}
		}
		else if (m.getX()>= 755 && m.getX()<= 790 && m.getY()>=255 && m.getY()<= 280 && isSelectedJoe == true) {
			String name = "Joe" + nbJoe;
			listExplorers.remove(name);
			exEquipements.remove(name);
			nbJoe--;
			nbExplorateurs--;
			money = money + priceExplorers;
		}
		else if (m.getX()>= 1025 && m.getX()<= 1060 && m.getY()>=250 && m.getY()<= 280 && difficultySelected != 3 && strategySelected != 3) {
			if (nbExplorateurs+1 <= nbMaxExplorateurs) {
				if (money>=priceExplorers) {
					nbDora++;
					nbExplorateurs++;
					String name = "Dora" + nbDora;
					listExplorers.add(name);
					exEquipements.put(name, new ArrayList<String>());
					money = money - priceExplorers;
				}
			}
		}
		else if (m.getX()>= 840 && m.getX()<= 970 && m.getY()>=250 && m.getY()<= 280 && isSelectedDora == true) {
			String name = "Dora" + nbDora;
			listExplorers.remove(name);
			exEquipements.remove(name);
			nbDora--;
			nbExplorateurs--;
			money = money + priceExplorers;
		}
		
////////////////////////////EQUIPMENTS/////////////////////////////////////
		/* Machette */
		/* Add */
		else if (m.getX()>= 440 && m.getX()<= 465 && m.getY()>=580 && m.getY()<= 610 && difficultySelected != 3 && strategySelected != 3) {
			if ((isSelectedTab != -1)&&(money>=priceWeapon)) {
				int maxEquipment;
				String name = listExplorers.get(isSelectedTab);
				ArrayList<String> equipmentTmp = exEquipements.get(name);
				if(name.contains("Dora")) {
					maxEquipment = 2;
				} else {
					maxEquipment = 1;
				}
				if(equipmentTmp.size() < maxEquipment) {
					/* Check if equipment not already taken */
					for(String equipment : equipmentTmp) {
						if(equipment.equals("Machettes")) {
							alreadyEquiped = true;
						}
					}
					if(alreadyEquiped) {
						System.out.println("Objet deja pris");
						alreadyEquiped = false;
					}
					else {
						System.out.println("Add Machettes");
						equipmentTmp.add("Machettes");
						System.out.println("Machettes pour "+ name);
						money = money-priceWeapon;
					}
				}
				else {
					System.out.println("Inventaire Plein");
				}
			}
		}
		/* Remove */
		else if (m.getX()>= 338 && m.getX()<= 363 && m.getY()>=578 && m.getY()<= 610) {
			if (isSelectedTab != -1) {
				money = money + priceWeapon;
				System.out.println("Remove Machettes");
				String name = listExplorers.get(isSelectedTab);
				ArrayList<String> equipmentTmp = exEquipements.get(name);
				if (equipmentTmp.size()!=0) {
					equipmentTmp.remove("Machettes");
					money = money + priceWeapon;
				}
			}
		}
		
		/* Jumelles - Binoculars */
		/* Add */
		else if (m.getX()>= 640 && m.getX()<= 665 && m.getY()>=576 && m.getY()<= 609 && difficultySelected != 3 && strategySelected != 3) {
			System.out.println("Add Binoculars");
			if ((isSelectedTab != -1)&&(money>=priceBinoculars)) {
				int maxEquipment;
				String name = listExplorers.get(isSelectedTab);
				ArrayList<String> equipmentTmp = exEquipements.get(name);
				if(name.contains("Dora")) {
					maxEquipment = 2;
				} else {
					maxEquipment = 1;
				}
				if(equipmentTmp.size() < maxEquipment) {
					/* Check if equipment not already taken */
					for(String equipment : equipmentTmp) {
						if(equipment.equals("Jumelles")) {
							alreadyEquiped = true;
						}
					}
					if(alreadyEquiped) {
						System.out.println("Objet deja pris");
						alreadyEquiped = false;
					}
					else {
						equipmentTmp.add("Jumelles");
						money = money - priceBinoculars;
					}
				}
				else {
					System.out.println("Inventaire Plein");
				}
			}
		}
		/* Remove */
		else if (m.getX()>= 537 && m.getX()<= 562 && m.getY()>=576 && m.getY()<= 609 ) {
			if (isSelectedTab != -1) {
				String name = listExplorers.get(isSelectedTab);
				ArrayList<String> equipmentTmp = exEquipements.get(name);
				if (equipmentTmp.size()!=0) {
					equipmentTmp.remove("Jumelles");
					money = money + priceBinoculars;
				}
			}
		}
		
		/* Boots */
		/* Add */
		else if (m.getX()>= 838 && m.getX()<= 862 && m.getY()>=576 && m.getY()<= 602 && difficultySelected != 3 && strategySelected != 3) {
			if ((isSelectedTab != -1)&&(money>=priceBoots)) {
				int maxEquipment;
				String name = listExplorers.get(isSelectedTab);
				ArrayList<String> equipmentTmp = exEquipements.get(name);
				if(name.contains("Dora")) {
					maxEquipment = 2;
				} else {
					maxEquipment = 1;
				}
				if(equipmentTmp.size() < maxEquipment) {
					/* Check if equipment not already taken */
					for(String equipment : equipmentTmp) {
						if(equipment.equals("Bottes")) {
							alreadyEquiped = true;
						}
					}
					if(alreadyEquiped) {
						System.out.println("Objet deja pris");
						alreadyEquiped = false;
					}
					else {
						equipmentTmp.add("Bottes");
						money = money - priceBoots;
					}
				}
				else {
					System.out.println("Inventaire Plein");
				}
			}
		}
		/* Remove */
		else if (m.getX()>= 741 && m.getX()<= 765 && m.getY()>=576 && m.getY()<= 603) {
			if (isSelectedTab != -1) {
				money = money + priceBoots;
				String name = listExplorers.get(isSelectedTab);
				ArrayList<String> equipmentTmp = exEquipements.get(name);
				if (equipmentTmp.size()!=0) {
					equipmentTmp.remove("Bottes");
					money = money + priceBoots;
				}
			}
		}
		
////////////////////////////ZONE BLANCHE/////////////////////////////////////
		else if (m.getX()>= 1105 && m.getX()<= 1275 && m.getY()>=150 && m.getY()<= 607) {
			for(int i=0; i<6; i++) {
				/* Verify each name zone if it is selected, then "break" if true */
				if (m.getX()>= 1115 && m.getX()<= 1180 && m.getY()>=(215+60*i) && m.getY()<= (245+60*i)) { 
					if(isSelectedTab==i) { // Unselect explorer i 
						System.out.println("Unselect Explorer n�"+ (i+1) + ": Index => "+ i);
						isSelectedTab = -1;
					}
					else {
						System.out.println("Select Explorer n�"+ (i+1) + ": Index => "+ i);
						isSelectedTab=i;
					}
					break;
				}
			}
		}		
////////////////////////////SIMULATION/////////////////////////////////////
		else if (m.getX()>= 1080 && m.getX()<= 1280 && m.getY()>=645 && m.getY()<= 715) {
			System.out.println("DEBUT DE LA SIMULATION");
			if ((nbExplorateurs >= nbMinExplorateurs) && (nbExplorateurs <= nbMaxExplorateurs)&&(strategySelected != 3)
					&&(difficultySelected != 3)){
				sim = new Simulation(dif, strategySelected, listExplorers, exEquipements);
				SimulationState simulationState = new SimulationState(gsm);
				gsm.gameStates.push(simulationState);
				sim.createThreads();
			}
		}
	}
	
	public int getNbTreasures() {
		return nbTreasures;
	}

	public void mouseReleased(MouseEvent m) {}

	public void mouseEntered(MouseEvent m) {}

	public void mouseExited(MouseEvent m) {}

	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}
	
	//public void render(Graphics2D g) {}
}
