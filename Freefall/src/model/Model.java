package model;

import java.awt.event.KeyEvent;

public class Model {

	private static final double SIN45 = 1 / Math.sqrt(2);
	
	public static final int FIELD_WIDTH = 400;
	public static final int FIELD_HEIGHT = 400;;
	
	private Player player;
	boolean leftHeld, rightHeld, upHeld, downHeld;
	
	public Model() {
		player = new Player(new Vector(0, 10000, 0));
		leftHeld = false;
		rightHeld = false;
		upHeld = false;
		downHeld = false;
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
		updatePlayerVector();
		
		//Update everything else here
	}
	
	public void updatePlayerVector() {
		
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
		
		player.update(mvt);
	}
}