 import character.*;
import data.*;

public class TestAlexandre {
	
	private Explorer dora;
	private Size sDora;
	private Position pDora;
	
	private Explorer joe;
	private Size sJoe;
	private Position pJoe;
	
	public TestAlexandre() {
		sDora = new Size(30,30);
		pDora = new Position(50,50);
		dora = new Explorer("dora", sDora, pDora, false, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, 0, 0);
		
		sJoe = new Size(30,30);
		pJoe = new Position(50,50);
		joe = new Explorer("Joe", sJoe, pJoe, false, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, 0, 0);
		
//		Thread t1 = new Thread(dora);
//		Thread t2 = new Thread(joe);
//		
//		t1.start();
//		t2.start();
	} 
	
	public static void main(String args[]) {
		new TestAlexandre();

	}
}
