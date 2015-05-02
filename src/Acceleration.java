
public class Acceleration {

	/**
	 * The acceleration in x axis
	 */
	private float x;
	/**
	 * The acceleration in y axis
	 */
	private float y;
	/**
	 * Sets an acceleration by given x and y values
	 * @param float, x - The acceleration in x axis
	 * @param float, y - The acceleration in y axis
	 */
	public Acceleration(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * calculates and return the acceleration in X axis
	 * @return Acceleration in X axis
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
