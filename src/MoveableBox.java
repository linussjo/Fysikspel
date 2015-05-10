

public class MoveableBox extends PhysicRectangle {

	public MoveableBox(int x, int y, int width, int height, float mass) {
		super(x, y, width, height, mass);
		this.setFloorZeroVelocity(true);
		
		this.setColliderNumber(Node.Collision.MOVABLEBOX);
		this.addCollideNumbers(Collision.SOLIDOBSTACLE);
		this.addCollideNumbers(Collision.BOINKOBSTACLE);
	}

	@Override
	public void collide(Rectangle r, double updateTime)
	{
		
		if(r instanceof PhysicRectangle)
		{
			if(!(r instanceof Obstacle))
			{
				this.collisionCheck(r, updateTime);
				this.setVelocity(this.getOldVelocity());
				Game.physics.calculateElasticCollision(this, (PhysicRectangle)r);
			}
			else
				this.collisionCheck(r, updateTime);
		}
	}

}
