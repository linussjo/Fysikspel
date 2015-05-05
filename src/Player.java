
public class Player extends Rectangle {
	private boolean inAir;
	
	Player()
	{
		super(100,0,50,50,50);
		this.getCollideNumber(Collision.FLOOR);
		this.getCollideNumber(Collision.LEFTWALL);
		this.getCollideNumber(Collision.RIGHTWALL);
		this.getCollideNumber(Collision.OBSTACLE);
		this.setColliderNumber(Collision.PLAYER);
	}

	/**
	 * @return the inAir
	 */
	public boolean isInAir() {
		return inAir;
	}

	/**
	 * @param inAir the inAir to set
	 */
	public void setInAir(boolean inAir) {
		this.inAir = inAir;
	}
}
