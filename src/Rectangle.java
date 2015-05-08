import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

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
	 * @param width the width to set
	 */
	protected void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @param height the height to set
	 */
	protected void setHeight(int height) {
		this.height = height;
	}
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
	 * The color of the rectangle
	 */
	private Color color = Color.black;
	
	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * @param color, the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	/**
	 * An image to draw if not to use a color
	 */
	private BufferedImage image;
	
	/**
	 * @param image the image to set
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage getImage()
	{
		return this.image;
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
	/**
	 * This method draws the rectangle into the given Grahpics object
	 * @param Graphics, g - the graphic to draw this rectangle
	 */
	public void draw(Graphics g)
	{
		if(this.shouldDraw())
		{
			if(this.image != null)
			{
				g.setColor(this.color);
				g.drawImage(this.image, this.getPosition().x, this.getPosition().y, this.width, this.height, null);
			}
			else
			{
				g.setColor(this.color);
				g.fillRect(this.getPosition().x, this.getPosition().y, this.width, this.height);
			}
		}
	}

}
