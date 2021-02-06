package character;

import data.Position;
import data.Size;

//Here, we have class that create a Dora Explorer
public class DoraBuilder extends ExBuilder{
	
			public void upMapObject() {
				e.setSize(new Size(15, 20));
				e.setPosition(new Position(0,0));
				e.setMovable(true);
				e.setName("Dora");	
			}

			public void upCharacter() {
				e.setLifePoint(100);
				e.setLifePointMax(100);
				
				e.setAttackPoint(80);
				e.setAttackPointMax(100);
				
				e.setSpeed(12);
				e.setAura(5);
			}

			public void upExplorer() {
				e.setProbaCall(25);
				e.setProbaEscape(30);
				e.setProbaFight(50);
				
				e.setEquimentMax(2);
				
				e.setPrice(2000);
			}
			
}
