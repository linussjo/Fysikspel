/**
 * Handerar och räknar ut de hastigheter som finns i spelet.
 * 
 * @author BG5
 *
 */
public class Velocity {
	
	private float x;
	private float y;	
	
	public Velocity(float x, float y){
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	//Räknar ut nya hastigheter.
	public static Velocity combineVelocities(Velocity v1, Velocity v2){
		
		float combinedX = v1.getX() + v2.getX();
		float combinedY = v1.getY() + v2.getY();
		
		
		return new Velocity(combinedX, combinedY);
	}
}
