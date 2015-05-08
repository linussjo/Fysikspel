import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Player extends PhysicRectangle {


	Player() {
		super(100, 500, 50, 50, 50);
		this.getCollideNumber(Collision.FLOOR);
		this.getCollideNumber(Collision.LEFTWALL);
		this.getCollideNumber(Collision.RIGHTWALL);
		this.getCollideNumber(Collision.SOLIDOBSTACLE);
		this.getCollideNumber(Collision.BOINKOBSTACLE);
		this.setColliderNumber(Collision.PLAYER);
		this.getCollideNumber(Collision.BROWNIE);
	}
	
	private List<Item> itemContainer = new ArrayList<Item>();
	
	
	public List<Item> getItemContainer() {
		return itemContainer;
	}
	
	public void addItem(Item i) {
		this.itemContainer.add(i);
	}


	/**
	 * @param inAir
	 *            the inAir to set
	 */
	public void setInAir(boolean inAir) {
		super.setInAir(inAir);
		BufferedImage img = null;
		if(inAir)
		{
			try {
			     img  = ImageIO.read(new File(FileSystems.getDefault().getPath(
	                    "data", "8-bit_JumpAndreasRight.png").toUri()));
	        } catch (IOException e) {
	            System.out.println("Image not found");
	        }
		}
		else
		{
			try {
			     img  = ImageIO.read(new File(FileSystems.getDefault().getPath(
	                    "data", "8-bit_Andreas.png").toUri()));
	        } catch (IOException e) {
	            System.out.println("Image not found");
	        }
		}
		this.setImage(img);
	}
}
