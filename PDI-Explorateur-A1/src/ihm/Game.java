package ihm;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gameState.GameStateManager;

public class Game /*extends Canvas implements Runnable*/ {
	
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






























	
	/*private static final String NAME = "EXPLORATEURS AUTONOMES ET COMMUNICANTS";
	private JFrame frame;
	
	
	//dimensions de la fenêtre
	public static final int WIDTH = 1300;
	public static final int HEIGHT = 750;
	public boolean running = false;

	
	//private InputHandler input;
	
	//game state manager
	private GameStateManager gsm;
	//image
	private BufferedImage image;
	private Graphics2D g;
	private int state ;
	//private Player p;
	
	public Game() {
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		frame = new JFrame(NAME);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		setFocusable(true);
		requestFocus();

		init();
		start();
	}

	public void init() {
		//input = new InputHandler(this);
		gsm = new GameStateManager(this);
		
		image = new BufferedImage (WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
	}

	public synchronized void start() {
		running = true;
		new Thread(this).start();
	}

	public synchronized void stop() {
		running = false;
	}

	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;

		int ticks = 0;
		int frames = 0;

		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;

			while (delta >= 1) {
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (shouldRender) {
			frames++;
				render();
			}
			if(GameStateManager.getCurrentState() == GameStateManager.SIMULATIONSTATE) {
				if (System.currentTimeMillis() - lastTimer >= 1000) {
					SpellTreatment.spellTimer();
					p = PlayerChoice.selected();
					if(p.getLifePoint()+1 >= p.getLifePointMax()) {
						p.setLifePoint(p.getLifePointMax());
					}
					else {
						p.setLifePoint(p.getLifePoint()+1);
					}
					if(p.getManaPoint()+5 >= p.getManaPointMax()) {
						p.setManaPoint(p.getManaPointMax());
					}
					else {
						p.setManaPoint(p.getManaPoint()+5);
					}
					lastTimer += 1000;
					System.out.println(ticks + " ticks / " + frames + " frames");
					frames = 0;
					ticks = 0;
				}
			}

		}
	}

	public void tick() {
		gsm.tick();
	}


	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		gsm.render(g);
		Graphics g2 = bs.getDrawGraphics();
		g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		g2.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game ga = new Game();
	}

}*/
