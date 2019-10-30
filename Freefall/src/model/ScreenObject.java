package model;

import java.awt.Color;

public class ScreenObject {

	private Vector position;
	private Color color = Color.BLACK;
	
	public ScreenObject(Vector position) {
		this.position = position;
	}
	
	public Vector update(Vector vector) {
		position.addVector(vector);
		
		return new Vector(); //this value should not be used
	}
	
	public Vector getVector() {
		return position;
	}
	
	public void setColor(Color c) {
		this.color = c;
	}
	
	public Color getColor() {
		return color;
	}
}
