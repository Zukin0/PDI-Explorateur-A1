package character;

import java.util.ArrayList;

import data.Position;
import data.Size;

public class Explorer extends Character{

	private float probaFight;
	private float probaEscape;
	private float probaCall;
	private ArrayList<Equipment> equipment;
	private int equipmentMax;
	private int price;
	
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
