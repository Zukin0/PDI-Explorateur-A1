package character;

import java.util.ArrayList;
import data.*;

public class Explorer extends Character implements Runnable{

	private float probaFight;
	private float probaEscape;
	private float probaCall;
	private ArrayList<Equipment> equipment;
	private int equipmentMax;
	private int price;
	
	private int dir;
	
	public Explorer(String name, Size size, Position position, boolean movable, int lifePoint, int lifePointMax,
			int attackPoint, int attackPointMax, int speed, int aura, float probaFight, float probaEscape, float probaCall,
			ArrayList<Equipment> equipment, int equipmentMax, int price) {
		super(name, size, position, movable, lifePoint, lifePointMax, attackPoint, attackPointMax, speed, aura);
		this.probaFight = probaFight;
		this.probaEscape = probaEscape;
		this.probaCall = probaCall;
		this.equipment = equipment;
		this.equipmentMax = equipmentMax;
		this.price = price;
	}
	
	public void run() {
		int cpt = 0;
		changeDir();
		while(true) {
			if(cpt > 20) {
				changeDir();
				cpt = 0;
				System.out.println("CHANGED DIR");
			}
			move();
			cpt++;
			System.out.println("Compteur " + getName() + ":" + cpt);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void changeDir() {
		dir = (int)(Math.random() * 4);
		String str = "My name is : " + getName();
		switch(dir) {
		
		//Haut
		case 0 : str += " 0";
		break;
		
		//Bas
		case 1 : str += " 1";
		break;
		
		//Droite
		case 2 : str += " 2";
		break;
		
		//Gauche
		case 3 : str += " 3";
		break;
		}
		System.out.println(str);
	}

	public void move() {
		Position posG = getPosition();
		int posX = getPosition().getX();
		int posY = getPosition().getY();
		switch(dir) {
		
		//Up
		case 0 : posG.setY(posY-1);
		break;
		
		//Down
		case 1 : posG.setY(posY+1);
		break;
		
		//Right
		case 2 : posG.setX(posX+1);
		break;
		
		//Left
		case 3 : posG.setX(posX-1);
		break;
		}
		System.out.println(getName() + " : " +  getPosition().toString());
	}
	public float getProbaFight() {
		return probaFight;
	}
	public float getProbaEscape() {
		return probaEscape;
	}
	public float getProbaCall() {
		return probaCall;
	}
	public ArrayList<Equipment> getEquipment() {
		return equipment;
	}
	public int getEquipmentMax() {
		return equipmentMax;
	}
	public int getPrice() {
		return price;
	}
	
	public void setProbaFight(float probaFight) {
		this.probaFight = probaFight;
	}
	public void setProbaEscape(float probaEscape) {
		this.probaEscape = probaEscape;
	}
	public void setProbaCall(float probaCall) {
		this.probaCall = probaCall;
	}
	public void setEquiment(ArrayList<Equipment> equipment) {
		this.equipment = equipment;
	}
	public void setEquimentMax(int equipmentMax) {
		this.equipmentMax = equipmentMax;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
