package model;

public class ScreenObject {

	private Vector position;
	
	public ScreenObject(Vector position) {
		this.position = position;
	}
	
	public void update(Vector vector) {
		position.addVector(vector);
	}
	
	public Vector getVector() {
		return position;
	}
}
