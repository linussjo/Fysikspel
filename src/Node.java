import java.awt.Point;


public abstract class Node {
	
	/**
	 * The nodes position in x and y
	 */
	private Point position;
	/**
	 * The mass of the node
	 */
	private long mass;
	/**
	 * The velocity of the node
	 */
	private long velocity;
	/**
	 * The direction of the nodes force in degrees
	 */
	private long direction;
	
	public Node(Point pos)
	{
		this.position = pos;
	}
	
	public Node(int x, int y)
	{
		this.position = new Point(x,y);
	}
	
	
}
