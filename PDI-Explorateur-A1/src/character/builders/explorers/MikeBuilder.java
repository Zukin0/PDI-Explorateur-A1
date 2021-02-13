package character.builders.explorers;

import character.builders.explorers.core.ExBuilder;
import data.Position;
import data.Size;

//Here, we have class that create a Mike Explorer
public class MikeBuilder extends ExBuilder{
	
			public void upMapObject() {
				explorer.setSize(new Size(15, 20));
				explorer.setPosition(new Position(0,3));
				explorer.setMovable(true);
				explorer.setName("Mike");	
			}

			public void upCharacter() {
				explorer.setLifePoint(100);
				explorer.setLifePointMax(100);
				
				//more attack point
				explorer.setAttackPoint(9);
				explorer.setAttackPointMax(10);
				
				explorer.setSpeed(12);
				explorer.setAura(5);
			}

			public void upExplorer() {
				explorer.setProbaCall(20);
				explorer.setProbaEscape(30);
				explorer.setProbaFight(50);
				
				explorer.setEquimentMax(1);
				
				explorer.setPrice(1500);
			}
			
}
