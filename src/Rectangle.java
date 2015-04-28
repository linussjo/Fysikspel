import java.awt.Point;

/**
 * Creates a rectangle that extends from Node.
 * @author BG5
 *
 */
public class Rectangle extends Node {
	/**
	 * The width of the rectangle.
	 */
	private int width;
	/**
	 * Returns the width of the rectangle.
	 * @return int, width.
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * The height of the rectangle.
	 */
	private int height;
	/**
	 * Returns the height of the rectangle.
	 * @return int, height.
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * Contructor creates a new rectangle that's affected by physics.
	 * @param Point, pos - Position of the rectangle.
	 * @param int, width - Width of the rectangle.
	 * @param int, height - Height of the rectangle. 
	 * @param float, mass - Mass of the rectangle.
	 */
	public Rectangle(Point pos, int width, int height, float mass) {
		super(pos, mass);
		this.height = height;
		this.width = width;
	}
	/**
	 * Contructor creates a new rectangle that's affected by physics.
	 * @param int, x - Position in x axis. 
	 * @param int, y - Position in y axis.
	 * @param int, width - Width of the rectangle.
	 * @param int, height - Height of the rectangle. 
	 * @param float, mass - Mass of the rectangle.
	 */
	public Rectangle(int x, int y, int width, int height, float mass) {
		super(new Point(x,y), mass);
		this.height = height;
		this.width = width;
	}
	/**
	 * Contructor creates a new rectangle that's NOT affected by physics.
	 * @param Point, pos - Position of the rectangle.
	 * @param int, width - Width of the rectangle.
	 * @param int, height - Height of the rectangle. 
	 */
	public Rectangle(Point pos, int width, int height){
		super(pos);
		this.height = height;
		this.width = width;
	}
	/**
	 * Contructor creates a new rectangle that's NOT affected by physics.
	 * @param int, x - Position in x axis. 
	 * @param int, y - Position in y axis.
	 * @param int, width - Width of the rectangle.
	 * @param int, height - Height of the rectangle. 
	 */
	public Rectangle(int x, int y, int width, int height){
		super(new Point(x,y));
		this.height = height;
		this.width = width;
	}

}
