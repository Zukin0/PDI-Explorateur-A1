package character.builders.explorers.core;

import character.Explorer;
import character.builders.explorers.DoraBuilder;

public class ExDirector {
	private ExBuilder eB ;
	
	public void setExplorerBuilder(ExBuilder eB) { this.eB = eB; }
	public Explorer getExplorer() { return eB.getE(); }
	
	public void BuildExplorer() {
		eB.createExplorer();
		eB.upMapObject();
		eB.upCharacter();
		eB.upExplorer();
	}
	
	public static void main (String[] args) {
		ExDirector creator = new ExDirector() ;
		ExBuilder bDora = new DoraBuilder() ;
		
		creator.setExplorerBuilder(bDora);
		creator.BuildExplorer();
		
		Explorer e = creator.getExplorer() ;
		System.out.println(e);
	}
}
