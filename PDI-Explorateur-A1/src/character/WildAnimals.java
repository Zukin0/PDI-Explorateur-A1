package character;
import java.util.ArrayList;
import data.Position;
import data.Size;

public class WildAnimals extends Character{
	
	private Size territorySize;
	private Position posTerr;

	public WildAnimals(String name, Size size, Position position, boolean movable, int lifePoint, int lifePointMax,
			int attackPoint, int attackPointMax, int speed, int aura, int widthTerritory, int heightTerritory) {
		super(name, size, position, movable, lifePoint, lifePointMax, attackPoint, attackPointMax, speed, aura);
		setTerritorySize(new Size(widthTerritory,heightTerritory));
		posTerr = new Position(position.getX(), position.getY());
	}
	
	public WildAnimals() {
		super("", new Size(0, 0), new Position(0,0), true, 0, 0, 0, 0, 0, 0);
	}

	public Size getTerritorySize() {
		return territorySize;
	}
	
	public Position getPosTerr() {
		return posTerr;
	}

	public void setTerritorySize(Size territorySize) {
		this.territorySize = territorySize;
	}

	public void setPosTerr(Position posTerr) {
		this.posTerr = posTerr;
	}
	
	

}
