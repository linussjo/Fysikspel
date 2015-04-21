
public class Acceleration {
	/**
	 * gets the acceleration
	 * The direction of the acceleration in degrees
	 */
	private double direction;
	/**
	 * @return acceleration
	 */
	public double getAcceleration() {
		return acceleration;
	}

	/**
	 * The acceleration as a long
	 */
	private double acceleration;
	/**
	 * gets the direction of the acceleration
	 * @return direction
	 */
	public double getDirection() {
		return direction;
	}

	/**
	 * sets the object to given params
	 * @param direction, The direction of the acceleration in degrees
	 * @param The acceleration as a long
	 */
	public Acceleration(double direction, double acceleration)
	{
		this.direction = direction;
		this.acceleration = acceleration;
	}
	
	/**
	 * calculates and return the acceleration in X axis
	 * @return Acceleration in C axis
	 */
	public double accelerationX()
	{
		return 0;
	}
	
	/**
	 * calculates and return the acceleration in Y axis
	 * @return Acceleration in Y axis
	 */
	public double accelerationY()
	{
		return 0;
	}
}
