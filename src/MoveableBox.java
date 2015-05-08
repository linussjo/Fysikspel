

public class MoveableBox extends PhysicRectangle {

	public MoveableBox(int x, int y, int width, int height, float mass) {
		super(x, y, width, height, mass);
		
		this.setColliderNumber(Collision.SOLIDOBSTACLE);
		this.addCollideNumbers(Collision.SOLIDOBSTACLE);
		this.addCollideNumbers(Collision.BOINKOBSTACLE);
	}

}
