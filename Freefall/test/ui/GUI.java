package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import model.Model;
import model.Vector;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener, KeyListener {
	
	private static final int TICK_RATE = 60; //60 ticks per second
	private static final int WIDTH = 500;  //GUI is 400px wide
	private static final int HEIGHT = 500; //GUI is 400px tall

	private Timer timer;
	private ModelWindow window;
	private Model model;
	
	public static void main(String[] args) {
		GUI s = new GUI();
		s.run();
	}
	
	public void run() {
		
		setTitle("Model Test");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit button stops program

		//Add graphics window to GUI
		window = new ModelWindow(WIDTH, HEIGHT);
		add(window); 
		
		//Show the window
		setVisible(true);
		
		//Create the window's "Buffer"
		window.createAndSetBuffer();
		
		addKeyListener(this);
		
		model = new Model();
		
		//Create the timer
		timer = new Timer(1000 / TICK_RATE, this); //Timer will tick TICK_RATE times per second
	
		//Start the timer
		timer.setRepeats(true);
		timer.start(); 
		requestFocus();
	}

	/**
	 * Because our class GUI as a whole implements ActionListener, we have to override
	 * actionPerformed. This means that if we pass in GUI as an actionListener to something,
	 * whenever that 'something' activates, it will send an actionEvent here. We check that
	 * the action signal comes from the timer, and call the code we need.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//If the timer gave us an action signal
		if(e.getSource().equals(timer)) {
			
			model.update();
			window.update();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		model.handleKeyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		model.handleKeyReleased(e.getKeyCode());
	}

	private class ModelWindow extends DoubleBufferedCanvas {

		public ModelWindow(int width, int height) {
			super(width, height); 
		}

		@Override
		public void draw(Graphics g) {
			g.setColor(Color.BLACK);
			
			Vector v = model.getPlayerPosition();
		//	String s = (int)v.getX() + "," + (int)v.getY() + "," + (int)v.getZ();
		//	g.drawString(s, 20, 100);
		//	System.out.println(s);
			
			g.drawRect(((int)v.getX() + (GUI.WIDTH / 2)) - 50, ((GUI.HEIGHT) / 2) - (int)v.getZ() - 50, 50, 50);
		}
	}
}