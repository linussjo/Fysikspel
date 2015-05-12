import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Player extends PhysicRectangle {
	
	private boolean didPressSpace;

	/**
	 * @return the didPressSpace
	 */
	public boolean didPressSpace() {
		return didPressSpace;
	}

	/**
	 * @param didPressSpace the didPressSpace to set
	 */
	public void setDidPressSpace(boolean didPressSpace) {
		this.didPressSpace = didPressSpace;
	}

	/**
	 * @return the hasShotArrow
	 */
	public boolean hasShotArrow() {
		return hasShotArrow;
	}

	/**
	 * @param hasShotArrow the hasShotArrow to set
	 */
	public void setHasShotArrow(boolean hasShotArrow) {
		this.hasShotArrow = hasShotArrow;
	}

	Player(Point p) {
		super(p.x, p.y, 50, 50, 50);
		this.setColliderNumber(Collision.PLAYER);
		this.addCollideNumbers(Collision.MOVABLEBOX);
		this.addCollideNumbers(Collision.SOLIDOBSTACLE);
		this.addCollideNumbers(Collision.BOINKOBSTACLE);
		this.setColliderNumber(Collision.PLAYER);
		this.addCollideNumbers(Collision.BROWNIE);
		this.addCollideNumbers(Collision.BOW);
		this.addCollideNumbers(Collision.BUTTON);
		super.setFloorZeroVelocity(true);
	}
	
	private List<Item> itemContainer = new ArrayList<Item>();
	
	private Item activeItem;
	
	private boolean shotArrow;
	
	private boolean hasShotArrow;
	
	/**
	 * @return the shotArrow
	 */
	public boolean shotArrow() {
		return shotArrow;
	}

	/**
	 * @param shotArrow the shotArrow to set
	 */
	public void setShotArrow(boolean shotArrow) {
		this.shotArrow = shotArrow;
	}

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
		this.fixItemsPosition();
	}
	
	public void translatePosition(int dx, int dy)
	{
		super.translatePosition(dx, dy);
		this.fixItemsPosition();
	}
	
	private void fixItemsPosition()
	{
		if(this.itemContainer.size() > 0)
		{
			int dir = this.getWidth() - 10;
			if(this.movingLeft)
				dir = -this.getWidth() + this.activeItem.getWidth()/2 + 10;
			this.activeItem.setPosition(new Point(this.getPosition().x + dir, this.getPosition().y + 10));
		}
	}

	private boolean movingLeft = false;
	private boolean movingRight = false;

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
			this.setImage(img);
		}
		else if(inAir && movingRight)
		{
			try {
			     img  = ImageIO.read(new File(FileSystems.getDefault().getPath(
	                    "data", "8-bit_JumpAndreasRight.png").toUri()));
	        } catch (IOException e) {
	            System.out.println("Image not found");
	        }
			this.setImage(img);
		}
		else{
			whichDirectionImage(movingLeft, movingRight);
		}
	}
	public void whichDirectionImage(boolean movingLeft, boolean movingRight){
		BufferedImage img = null;
		this.movingLeft = movingLeft;
		this.movingRight = movingRight;
		if(movingLeft){
			try {
			     img  = ImageIO.read(new File(FileSystems.getDefault().getPath(
	                    "data", "8-bit_AndreasLeft.png").toUri()));
	        } catch (IOException e) {
	            System.out.println("Image not found");
	        }
			this.setImage(img);
		}else if(movingRight){
			try {
			     img  = ImageIO.read(new File(FileSystems.getDefault().getPath(
	                    "data", "8-bit_Andreas.png").toUri()));
	        } catch (IOException e) {
	            System.out.println("Image not found");
	        }
			this.setImage(img);
		}else{
			img = this.getImage();
			this.setImage(img);
		}
		if(this.itemContainer.size() > 0)
			this.activeItem.changeDirection(movingLeft);
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
		if(this.itemContainer.size() < i + 1)
			return;
		
		if(this.itemContainer.size() > 1)
			this.activeItem.setShouldDraw(false);
		
		this.activeItem = this.itemContainer.get(i);
		this.activeItem.setShouldDraw(true);
		this.activeItem.changeDirection(this.movingLeft);
	}

	@Override
	public void collide(Rectangle r, double updateTime) {
		if(r instanceof Item)
		{
			if(!this.getItemContainer().contains(r))
			{
				r.setShouldDraw(false);
				this.addItem((Item)r);
				r.setHasPhysics(false);
			}
		}
		else if(r.getColliderNumber() == Node.Collision.BUTTON)
		{
			r.setCollidable(false);
		}
		
	}
}
