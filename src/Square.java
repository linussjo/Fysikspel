import java.awt.Point;


public class Square extends Node {

	private int width;
	private int height;
	
	public Square(Point pos, int width, int height) {
		super(pos);
		this.height = height;
		this.width = width;
	}

	public Square(int x, int y, int width, int height) {
		super(new Point(x,y));
		this.height = height;
		this.width = width;
	}

}
