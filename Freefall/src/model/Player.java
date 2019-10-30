package model;

public class Player extends ScreenObject {

	public static final int XSPEED = 5;
	public static final int ZSPEED = 5;	
	
	public Player(Vector position) {
		super(position);
	}
	

	@Override
	public void update(Vector vector) {
		
		Vector position = super.getVector();
		
		if(Math.abs(position.getX() + vector.getX()) > Model.FIELD_WIDTH / 2)
			vector.setX(0);
		if(Math.abs(position.getY() + vector.getY()) < 0)
			vector.setY(0);
		if(Math.abs(position.getZ() + vector.getZ()) > Model.FIELD_HEIGHT / 2)
			vector.setZ(0);
		
		super.update(vector);
	}
}
