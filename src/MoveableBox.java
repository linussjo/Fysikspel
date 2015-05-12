import java.awt.Point;



public class MoveableBox extends PhysicRectangle {

	public MoveableBox(int x, int y, int width, int height, float mass) {
		super(x, y, width, height, mass);
		this.setFloorZeroVelocity(false);
		
		this.setColliderNumber(Node.Collision.MOVABLEBOX);
		this.addCollideNumbers(Collision.SOLIDOBSTACLE);
		this.addCollideNumbers(Collision.BOINKOBSTACLE);
	}

	@Override
	public void collide(Rectangle r, double updateTime)
	{
		
	}

}
