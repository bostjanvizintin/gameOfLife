package tilen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import tilen.ActionListeners.PorazdeljenoActionListener;
import tilen.ActionListeners.VzporednoActionListener;
import tilen.ActionListeners.ZaporednoActionListener;



public class GameOfLife extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static int width = 70;
	public static int height = width / 16 * 9;
	public static int scale = 20;
	public static final int UPDATES_TIME = 100;

	private Thread thread;
	private JFrame frame;
	private boolean running = false;

	private Logic l;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public GameOfLife() {
		Dimension size = new Dimension(width*scale, height*scale);
		setPreferredSize(size);
	
		
		l = new Logic(width, height, 1);
		frame = new JFrame();
		

	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}	


	public synchronized void stop() {
		running = false;
		/*
		  try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		 */
	}

	public void run() {
		long time = System.currentTimeMillis()-UPDATES_TIME;
		while (running) {		
			if(System.currentTimeMillis() - time > UPDATES_TIME) {
				update();
				render();
				time +=UPDATES_TIME;
			}
			
			
		}
		stop();
	}

	public void update() {
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		l.render();
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = l.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0,0,getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	public void zaporedno() {
		if(this.running) return;
		System.out.println("zaporedno");
		l.setType(1);
		this.start();
	}
	
	public void vzporedno() {
		if(this.running) return;
		System.out.println("vzporedno");
		l.setType(2);
		this.start();
	}
	
	public void porazdeljeno() {
		if(this.running) return;
		System.out.println("porazdeljeno");
		l.setType(3);
		this.start();
	}

	public static void main(String[] args) {
		GameOfLife game = new GameOfLife();
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		JButton buttons[] = new JButton[5];
		
		buttons[0] = new JButton("Nastavi vzorec");
		buttons[1] = new JButton("Zaporedno");
		buttons[2] = new JButton("Vzporedno");
		buttons[3] = new JButton("Porazdeljeno");
		buttons[4] = new JButton("Stop");

		buttons[1].addActionListener(new ZaporednoActionListener(game));
		buttons[2].addActionListener(new VzporednoActionListener(game));
		buttons[3].addActionListener(new PorazdeljenoActionListener(game));
		buttons[4].addActionListener(new StopActionListener(game));
		
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
		
		game.frame.setResizable(false);
		game.frame.setTitle("Rain");
		game.frame.setLayout(new FlowLayout());
		panel.add(game);
		game.frame.add(panel);

		for (int i = 0; i < buttons.length; i++) {
			panel2.add(buttons[i]);
		}
		
		game.frame.add(panel2);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
	}
}
