import java.awt.Point;

import javax.xml.bind.Marshaller.Listener;

/**
 * Vad ett obstacle för egenskaper ett obstacle har.
 * @author Per
 *
 */
public class Obstacle extends PhysicRectangle{
	public float boinkFactor;
	
	/**
	 * @return the boinkFactor
	 */
	public float getBoinkFactor() {
		return boinkFactor;
	}

	/**
	 * @param boinkFactor the boinkFactor to set
	 */
	public void setBoinkFactor(float boinkFactor) {
		this.boinkFactor = boinkFactor;
	}

	public Obstacle(int x, int y, int width, int height, float boinkFactor) {
		super(x, y, width, height, 1000000);
		this.setHasPhysics(false);
		this.setColliderNumber(Collision.BOINKOBSTACLE);
		this.boinkFactor = boinkFactor;
		
	}

	@Override
	public void collide(Rectangle r, double updateTime) {
		if(r instanceof Brownie){
			notifyListerner();
		}
	}

}
