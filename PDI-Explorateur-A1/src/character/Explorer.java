package character;

import java.util.ArrayList;
import data.*;

public class Explorer extends Character{

	private float probaFight;
	private float probaEscape;
	private float probaCall;
	private ArrayList<Equipment> equipment;
	private int equipmentMax;
	private int price;
	private boolean isEscaping;
	private boolean isDead;
	
	public Explorer(String name, Size size, Position position, boolean movable, int lifePoint, int lifePointMax,
			int attackPoint, int attackPointMax, int speed, int aura, float probaFight, float probaEscape, float probaCall,
			ArrayList<Equipment> equipment, int equipmentMax, int price, boolean isEscaping) {
		super(name, size, position, movable, lifePoint, lifePointMax, attackPoint, attackPointMax, speed, aura);
		this.probaFight = probaFight;
		this.probaEscape = probaEscape;
		this.probaCall = probaCall;
		this.equipment = equipment;
		this.equipmentMax = equipmentMax;
		this.price = price;
		this.isEscaping = isEscaping;
		this.setDead(false);
	}
	
	public Explorer() {
		super("", new Size(0, 0), new Position(0,0), true, 0, 0, 0, 0, 0, 0);
		probaFight = 0 ;
		probaEscape = 0 ; 
		probaCall = 0;
		equipment = new ArrayList<Equipment>() ;
		equipmentMax = 0;
		price = 0 ;	
		isEscaping = false;
		setDead(false);
	}
	
	public String toString() {
		return  "---------------------------- START -------------------------------- \n" +
				"Tu viens de crée : " + getName() + "\n" + 
				"Il est de la taille : " + getSize() + "\n" +
				"Deplacable : " + getMovable() + "\n" +
				"Ses points de vie max : " + getLifePointMax() + " actuelle : " + getLifePoint() + "\n" +
				"Ses points d'attaque : " + getAttackPoint() + "\n" + 
				"Sa vitesse : " + getSpeed() + "\n" + 
				"La taille de son aura : " + getAura() + "\n" + 
				"Ses probabilités : C-" + getProbaCall() + " F-" + getProbaFight() + " E-" + getProbaEscape() + "\n" +
				"Sa taille d'inventaire : " + getEquipmentMax() + "\n" +
				"Son prix : " + getPrice() + "\n" +
				"---------------------------- END ---------------------------------- \n";				
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
	public boolean isEscaping() {
		return isEscaping;
	}
	public boolean isDead() {
		return isDead;
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
	public void setEscaping(boolean isEscaping) {
		this.isEscaping = isEscaping;
	}
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
}
