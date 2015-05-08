import java.awt.Point;
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
	
	private Item activeItem;
	
	public List<Item> getItemContainer() {
		return itemContainer;
	}
	
	public void addItem(Item i) {
		this.itemContainer.add(i);
		if(this.itemContainer.size() == 1)
		{
			this.setActiveItem(0);	
		}
	}
	
	public void setPosition(Point p)
	{
		super.setPosition(p);
		if(this.itemContainer.size() > 0)
		{
			int dir = this.getWidth();
			if(this.movingLeft)
				dir = -this.getWidth() + this.activeItem.getWidth()/2;
			this.activeItem.setPosition(new Point(this.getPosition().x + dir, this.getPosition().y + 15));
		}
	}
	
	public void translatePosition(int dx, int dy)
	{
		super.translatePosition(dx, dy);
		if(this.itemContainer.size() > 0)
		{
			int dir = this.getWidth();
			if(this.movingLeft)
				dir = -this.getWidth() + this.activeItem.getWidth()/2;
			this.activeItem.setPosition(new Point(this.getPosition().x + dir, this.getPosition().y + 15));
		}
	}

	private boolean movingLeft = false;

	/**
	 * @param inAir
	 *            the inAir to set
	 */
	public void setInAir(boolean inAir) {
		super.setInAir(inAir);
		BufferedImage img = null;
		if(inAir && movingLeft){
			try {
			     img  = ImageIO.read(new File(FileSystems.getDefault().getPath(
	                    "data", "8-bit_JumpAndreasLeft.png").toUri()));
	        } catch (IOException e) {
	            System.out.println("Image not found");
	        }
		}
		else if(inAir && !movingLeft)
		{
			try {
			     img  = ImageIO.read(new File(FileSystems.getDefault().getPath(
	                    "data", "8-bit_JumpAndreasRight.png").toUri()));
	        } catch (IOException e) {
	            System.out.println("Image not found");
	        }
		}else{
			try {
			     img  = ImageIO.read(new File(FileSystems.getDefault().getPath(
	                    "data", "8-bit_Andreas.png").toUri()));
	        } catch (IOException e) {
	            System.out.println("Image not found");
	        }
		}
		this.setImage(img);
	}
	public void whichDirectionImage(boolean movingLeft){
		BufferedImage img = null;
		this.movingLeft = movingLeft;
		if(movingLeft){
			try {
			     img  = ImageIO.read(new File(FileSystems.getDefault().getPath(
	                    "data", "8-bit_AndreasLeft.png").toUri()));
			     System.out.println("Back");
	        } catch (IOException e) {
	            System.out.println("Image not found");
	        }
			
		}else{
			try {
			     img  = ImageIO.read(new File(FileSystems.getDefault().getPath(
	                    "data", "8-bit_Andreas.png").toUri()));
	        } catch (IOException e) {
	            System.out.println("Image not found");
	        }
		}
		this.setImage(img);
	}

	/**
	 * @return the activeItem
	 */
	public Item getActiveItem() {
		return activeItem;
	}

	/**
	 * @param activeItem the activeItem to set
	 */
	public void setActiveItem(int i) {
		this.activeItem = this.itemContainer.get(i);
		this.activeItem.setShouldDraw(true);
	}
}
