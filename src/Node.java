import java.awt.Point;
import java.util.HashSet;
import java.util.Set;


public abstract class Node {
	
	/**
	 * The nodes position in x and y
	 */
	private Point position;
	/**
	 * The nodes old position in x and y
	 */
	private Point oldPosition;
	/**
	 * The mass of the node
	 */
	private double mass = 0;
	/**
	 * The velocity of the node
	 */
	private double velocity = 0;
	/**
	 * The direction of the nodes force in degrees
	 */
	private double direction = 0;
	
	private Set<Integer> collideNumbers;
	
	private int colliderNumber;
	
	public Node(Point pos)
	{
		this.position = pos;
		this.collideNumbers = new HashSet<Integer>();
	}
	
	public boolean addCollideNumber(int c)
	{
		return this.collideNumbers.add(c);
	}
	
}
