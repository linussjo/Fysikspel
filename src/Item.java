import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * Vad de olika "lösa" föremålen ska ha för egenskaper. T.ex. Bilder
 * 
 * @author BG5
 *
 */
public abstract class Item extends PhysicRectangle {

	private boolean left;

	/**
	 * @param imgUse
	 *            the imgUse to set
	 */
	protected void setImgUse(BufferedImage imgUse) {
		super.setImage(imgUse);
	}

	private BufferedImage leftImg;

	/**
	 * @param leftImg
	 *            the leftImg to set
	 */
	protected void setLeftImg(BufferedImage leftImg) {
		this.leftImg = leftImg;
	}

	/**
	 * @param rightImg
	 *            the rightImg to set
	 */
	protected void setRightImg(BufferedImage rightImg) {
		this.rightImg = rightImg;
	}

	private BufferedImage rightImg;

	public Item(String name, int x, int y, int width, int height, float mass) {
		super(x, y, width, height, mass);
		this.name = name;
		super.setFloorZeroVelocity(true);
	}

	/**
	 * Name(String) of the item
	 */
	private String name;

	public String getName() {
		return name;
	}

	public void setHasPhysics(boolean hasPhysics) {
		super.setHasPhysics(hasPhysics);
	}

	public void changeDirection(boolean left) {
		this.left = left;
		if (this.leftImg != null && this.rightImg != null) {
			if (left)
				super.setImage(this.leftImg);
			else
				super.setImage(this.rightImg);
		}
	}

}
