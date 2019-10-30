package model;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Model {

	private static final double SIN45 = 1 / Math.sqrt(2);
	
	public static final int FIELD_WIDTH = 400;
	public static final int FIELD_HEIGHT = 400;;
	
	private Player player;
	private boolean leftHeld, rightHeld, upHeld, downHeld;
	private ArrayList<ScreenObject> sList = new ArrayList<ScreenObject>();
	
	public Model() {
		player = new Player(new Vector(0, 10000, 0));
		leftHeld = false;
		rightHeld = false;
		upHeld = false;
		downHeld = false;
		
		setupObjects();
	}
	
	private void setupObjects() {
		ScreenObject o1 = new ScreenObject(new Vector(-100,0,0));
		ScreenObject o2 = new ScreenObject(new Vector(100,0,0));
		ScreenObject o3 = new ScreenObject(new Vector(0,0,100));
		ScreenObject o4 = new ScreenObject(new Vector(0,0,-100));
		
		o1.setColor(Color.RED);
		o2.setColor(Color.GREEN);
		o3.setColor(Color.BLUE);
		
		sList.add(o1);
		sList.add(o2);
		sList.add(o3);
		sList.add(o4);
	}
	
	public ArrayList<ScreenObject> getBlitList() {
		return sList;
	}
	
	public Vector getPlayerPosition() {
		return player.getVector();
	}
	
	public void handleKeyPressed(int keycode) {
		if(keycode == KeyEvent.VK_A)
			leftHeld = true;
		else if(keycode == KeyEvent.VK_D)
			rightHeld = true;
		else if(keycode == KeyEvent.VK_W)
			upHeld = true;
		else if(keycode == KeyEvent.VK_S)
			downHeld = true;
	}
	
	public void handleKeyReleased(int keycode) {
		if(keycode == KeyEvent.VK_A)
			leftHeld = false;
		else if(keycode == KeyEvent.VK_D)
			rightHeld = false;
		else if(keycode == KeyEvent.VK_W)
			upHeld = false;
		else if(keycode == KeyEvent.VK_S)
			downHeld = false;
	}

	public void update() {
		Vector change = updatePlayerVector();
		
		for(ScreenObject so: sList) {
			so.update(change);
		}
	}
	
	public Vector updatePlayerVector() {
		
		Vector mvt = new Vector();
		
		if((leftHeld && rightHeld) || (!leftHeld && !rightHeld)) {
			if(upHeld)
				mvt.setZ(Player.ZSPEED);
			else if(downHeld)
				mvt.setZ(-Player.ZSPEED);
		}
		else if((upHeld && downHeld) || (!upHeld && !downHeld)) {
			if(leftHeld)
				mvt.setX(-Player.XSPEED);
			else if(rightHeld)
				mvt.setX(Player.XSPEED);
		}
		else {
			if(upHeld) {
				if(leftHeld) {
					mvt.setX(-Player.XSPEED * SIN45);
					mvt.setZ(Player.ZSPEED * SIN45);
				}
				else if(rightHeld) {
					mvt.setX(Player.XSPEED * SIN45);
					mvt.setZ(Player.ZSPEED * SIN45);
				}
				else 
					mvt.setZ(Player.ZSPEED);
			}
			else if(downHeld) {
				if(leftHeld) {
					mvt.setX(-Player.XSPEED * SIN45);
					mvt.setZ(-Player.ZSPEED * SIN45);
				}
				else if(rightHeld) {
					mvt.setX(Player.XSPEED * SIN45);
					mvt.setZ(-Player.ZSPEED * SIN45);
				}
				else 
					mvt.setZ(-Player.ZSPEED);
			}	
		}
		
		return player.update(mvt);
	}
}