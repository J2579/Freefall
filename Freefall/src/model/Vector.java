package model;

public class Vector {
	
	private double x, y, z;
	
	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Vector() {
		this(0,0,0);
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getZ() {
		return z;
	}
	public void setZ(double z) {
		this.z = z;
	}
	public void addVector(Vector vector) {
		setX(getX() + vector.getX());
		setY(getY() + vector.getY());
		setZ(getZ() + vector.getZ());
	}
	
}
