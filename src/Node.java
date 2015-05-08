import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;


public abstract class Node {
	
	private boolean shouldDraw = true;
	
	/**
	 * @return the shouldDraw
	 */
	public boolean shouldDraw() {
		return shouldDraw;
	}
	/**
	 * @param shouldDraw the shouldDraw to set
	 */
	public void setShouldDraw(boolean shouldDraw) {
		this.shouldDraw = shouldDraw;
	}
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
		this.oldPosition = new Point(this.position.x, this.position.y);
		this.position = position;
	}
	
	private Point oldPosition;
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
	 * sets the new given velocity to the node
	 * @param Velocity, velocity
	 */
	public void setVelocity(Velocity velocity) {
		this.velocity = velocity;
	}
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
	
	public void applyAccelertaion(Acceleration acc)
	{
		this.acceleration.combine(acc);
	}
	/**
	 * Which collideNumbers that this node will collide with.
	 */
	private Set<Collision> collideNumbers;
	/**
	 * colliderNumber
	 */
	private Collision colliderNumber;
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
	 * @param hasPhysics the hasPhysics to set
	 */
	protected void setHasPhysics(boolean hasPhysics) {
		this.hasPhysics = hasPhysics;
	}
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
		this.oldPosition = pos;
		this.mass = mass;
		this.velocity = new Velocity(0, 0);
		this.acceleration = new Acceleration(0, 0);
		this.collideNumbers = new HashSet<Collision>();
		this.hasPhysics = true;
	}
	/**
	 * Constructor to create a node.
	 * @param Point, pos.
	 */
	public Node(Point pos){
		this.position = pos;
		this.oldPosition = pos;
		this.collideNumbers = new HashSet<Collision>();
		this.hasPhysics = false;
	}
	/**
	 * @return the collideNumbers
	 */
	public Set<Collision> getCollideNumbers() {
		return collideNumbers;
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
	public boolean addCollideNumbers(Collision c)
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
		this.oldPosition = (Point) this.position.clone();
		this.position.translate(dx, dy);
	}
	
	public boolean intersects(Node n)
	{
		if(this instanceof Rectangle && n instanceof Rectangle)
		{
			Rectangle r1 = (Rectangle)this;
			Rectangle r2 = (Rectangle)n;
			
			if((new java.awt.Rectangle(r1.getPosition().x, r1.getPosition().y, r1.getWidth(), r1.getHeight())
			.intersects(r2.getPosition().x, r2.getPosition().y, r2.getWidth(), r2.getHeight())))
			{
				return true;
			}
			else
				return false;
		}
		return false;
	}
	/**
	 * An abstract method to be used for drawing the object to the given Graphics g
	 * @param Graphics, g
	 */
	abstract void draw(Graphics g);
	
	/**
	 * @return the colliderNumber
	 */
	public Collision getColliderNumber() {
		return colliderNumber;
	}
	/**
	 * @param colliderNumber the colliderNumber to set
	 */
	public void setColliderNumber(Collision colliderNumber) {
		this.colliderNumber = colliderNumber;
	}

	/**
	 * @return the oldPosition
	 */
	public Point getOldPosition() {
		return oldPosition;
	}

	public enum Collision{
		PLAYER,
		SOLIDOBSTACLE,
		BOINKOBSTACLE,
		MOVABLEBOX,
		BROWNIE,
		BOW,
		ARROW,
		BUTTON;
	}
	
}
