
public class Acceleration {

	private float x;
	private float y;
	/**
	 * sets the object to given params
	 * @param direction, The direction of the acceleration in degrees
	 * @param The acceleration as a long
	 */
	public Acceleration(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * calculates and return the acceleration in X axis
	 * @return Acceleration in C axis
	 */
	public float getX()
	{
		return this.x;
	}
	
	/**
	 * calculates and return the acceleration in Y axis
	 * @return Acceleration in Y axis
	 */
	public float getY()
	{
		return this.y;
	}
}
