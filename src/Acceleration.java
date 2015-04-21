
public class Acceleration {
	/**
	 * The direction of the acceleration in degrees
	 */
	private long direction;
	/**
	 * The acceleration as a long
	 */
	private long acceleration;
	
	/**
	 * sets the object to given params
	 * @param direction, The direction of the acceleration in degrees
	 * @param The acceleration as a long
	 */
	public Acceleration(long direction, long acceleration)
	{
		this.direction = direction;
		this.acceleration = acceleration;
	}
}
