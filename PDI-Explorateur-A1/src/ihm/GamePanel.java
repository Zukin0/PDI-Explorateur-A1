package ihm;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import gameState.GameStateManager;

/**
 * @brief Class that initialize the game panel
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 *
 */
public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener{

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 1300;
	public static final int HEIGHT = 750 ;
	
	private Thread thread;
	private Boolean isRunning = false;
	
	private int FPS = 60;
	private long targetTime = 1000/FPS; 
	
	private GameStateManager gsm;
	
	/**
	 * @brief Constructor
	 */
	public GamePanel () {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);
		addMouseListener(this);
		setFocusable(true); 
		start();
	}
	
	/**
	 * @brief Method that initializes starts the thread
	 */
	private void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * @brief Method that initializes the time and make everything run
	 */
	public void run() {
		long start, elapsed, wait;
		gsm = new GameStateManager();
		
		while (isRunning) {
			start = System.nanoTime();
			tick();
			repaint();
			
			elapsed = System.nanoTime() - start; //temps écoulé
			wait = targetTime - elapsed / 1000000 ; //divisé pour avoir des ms
			
			if (wait <= 0) {
				wait = 5;
			}
			
			try {
				Thread.sleep(wait);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void tick() {
		gsm.tick();
	}
	
	/**
	 * Method that reset the panel
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.clearRect(0, 0, WIDTH, HEIGHT); 
		gsm.draw(g);
	}

	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		gsm.KeyPressed(e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) {
		gsm.keyReleased(e.getKeyCode());
	}

	public void mouseClicked(MouseEvent m) {
		gsm.mouseClicked(m);
	}

	public void mousePressed(MouseEvent m) {
		gsm.mousePressed(m);
	}

	public void mouseReleased(MouseEvent m) {
		gsm.mouseReleased(m);
	}

	public void mouseEntered(MouseEvent m) {
		gsm.mouseEntered(m);
	}

	public void mouseExited(MouseEvent m) {
		gsm.mouseExited(m);
	}
}
