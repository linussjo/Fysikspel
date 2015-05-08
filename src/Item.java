import java.awt.Point;


public abstract class Item extends PhysicRectangle {


	public Item(String name, int x, int y, int width, int height, float mass) {
		super(x, y, width, height, mass);
		this.name = name;
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
	
	
	
	

}
