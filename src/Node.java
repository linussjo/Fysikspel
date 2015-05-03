import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;


public abstract class Node {
	
	/**
	 * The nodes position in x and y
	 */
	private Point position;
	/**
	 * Gets the position of the node.
	 * @return position, Returns the position of the node.
	 */
	public Point getPosition() {
		return position;
	}
	/**
	 * Sets the new position of the node and the old position to oldPosition
	 * @param position, Type of Point.
	 */
	public void setPosition(Point position) {
		this.oldPosition = this.position;
		this.position = position;
	}
	/**
	 * The nodes old position in x and y
	 */
	private Point oldPosition;
	/**
	 * Returns the old position of the node.
	 * @return oldPosition, Returns the old position of the node.
	 */
	public Point getOldPosition() {
		return oldPosition;
	}

	/**
	 * The mass of the node
	 */
	private double mass;
	/**
	 * Returns the mass of the node
	 * @return mass, Returns the mass of the node.
	 */
	public double getMass() {
		return mass;
	}
	/**
	 * The velocity of the node
	 */
	private Velocity velocity;

	/**
	 * Returns the velocity of the node.
	 * @return velocity, Return the velocity of the node
	 */
	public Velocity getVelocity() {
		return velocity;
	}
	/**
	 * The acceleration of the node.
	 */
	private Acceleration acceleration;
	/**
	 * Returns the acceleration of the node.
	 * @return accereleration, Returns the acceleration of the node.
	 */
	public Acceleration getAcceleration() {
		return acceleration;
	}
	/**
	 * Sets the acceleration of the node.
	 * @param acceleration, type of Accelereration.
	 */
	public void setAcceleration(Acceleration acceleration) {
		this.acceleration = acceleration;
	}
	/**
	 * Which collideNumbers that this node will collide with.
	 */
	private Set<Integer> collideNumbers;
	/**
	 * colliderNumber
	 */
	private int colliderNumber;
	/**
	 * If the node is collidable with.
	 */
	private boolean isCollidable = true;
	/**
	 * Return isCollidable.
	 * @return boolean, isCollidable.
	 */
	public boolean isCollidable() {
		return isCollidable;
	}
	/**
	 * Set if isCollidable is true or false.
	 * @param isCollidable, boolean.
	 */
	public void setCollidable(boolean isCollidable) {
		this.isCollidable = isCollidable;
	}
	/**
	 * If a node is affected by physics.
	 */
	private boolean hasPhysics;
	/**
	 * Returns hasPhysics is true or false.
	 * @return boolean, hasPhysics.
	 */
	public boolean ifHasPhysics() {
		return hasPhysics;
	}
	/**
	 * Constructor to create a node.
	 * @param Point, pos.
	 * @param float, mass.
	 */
	public Node(Point pos, float mass)
	{
		this.position = pos;
		this.mass = mass;
		this.velocity = new Velocity(0, 0);
		this.acceleration = new Acceleration(0, 0);
		this.collideNumbers = new HashSet<Integer>();
		this.hasPhysics = true;
	}
	/**
	 * Constructor to create a node.
	 * @param Point, pos.
	 */
	public Node(Point pos){
		this.position = pos;
		this.hasPhysics = false;
	}
	/**
	 * Combine the new velocity with the old one.
	 * @param Velocity, v.
	 */
	public void applyVelocity(Velocity v){
		this.velocity = Velocity.combineVelocities(this.velocity, v);
	}
	/**
	 * Sets velocity to 0.
	 */
	public void stopMovement(){
		this.velocity = new Velocity(0, 0);
	}
	/**
	 * Adds collideNumber too set Collidable.
	 * @param int, c.
	 * @return boolean, Returns true if the collideNumber didn't exist, else false.
	 */
	public boolean addCollideNumber(int c)
	{
		return this.collideNumbers.add(c);
	}
	/**
	 * translate the given distance to move in x and y axis from current position
	 * @param Int, dx the distance to move in x axis
	 * @param Int, dy the distance to move in y axis
	 */
	public void translatePosition(int dx, int dy)
	{
		this.oldPosition = this.position;
		this.position.translate(dx, dy);
	}
	/**
	 * An abstract method to be used for drawing the object to the given Graphics g
	 * @param Graphics, g
	 */
	abstract void draw(Graphics g);
	
}
