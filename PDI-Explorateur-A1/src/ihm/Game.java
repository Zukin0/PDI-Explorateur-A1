package ihm;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 * @brief Class that initialize the frame for the graphical interface
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 *
 */
public class Game {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("EXPLORATEURS INTELLIGENTS ET COMMUNICANTS");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.add(new GamePanel(), BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true); 
		
	}
}