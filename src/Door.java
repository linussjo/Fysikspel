import java.awt.Point;


public class Door extends Obstacle implements Notifiable{

	public Door(int x, int y, int width, int height, float mass) {
		super(x, y, width, height, mass);
		this.setHasPhysics(true);
		this.addCollideNumbers(Node.Collision.BOINKOBSTACLE);
		this.addCollideNumbers(Node.Collision.SOLIDOBSTACLE);
		this.addCollideNumbers(Node.Collision.PLAYER);
	}

	@Override
	public void collide(Rectangle r, double updateTime) {
		if(r instanceof PhysicRectangle && !(r instanceof Obstacle) && this.ifHasPhysics() && this.isInAir())
		{
			int y1 = this.getOldPosition().y + this.getHeight();
			int y2 = this.getOldPosition().y;
			
			if((this.isInAir() && y1 <= r.getPosition().y || y2 >= r.getPosition().y + r.getHeight()))
			{
				if(this.getVelocity().getY() > 0)
				{
					//this.setVelocity(new Velocity(this.getVelocity().getX(),0));
					this.setPosition(new Point(this.getPosition().x, r.getPosition().y - this.getHeight()));
				}
				else 
				{
					//this.setVelocity(new Velocity(this.getVelocity().getX(),0));
					this.setPosition(new Point(this.getPosition().x, r.getPosition().y + r.getHeight()));
				}
				
				Map.physics.calculateElasticCollision(this, ((PhysicRectangle)r));
			}
		}
		
	}
	
	@Override
	public void notify(Reason r) {
		if(r == Reason.BUTTON)
		{
			//this.setPosition(new Point(this.getPosition().x,  this.getPosition().y + this.getHeight()));
			this.setHasPhysics(true);
			this.setInAir(true);
			this.setVelocity(new Velocity(0, -500));
			this.setAcceleration(new Acceleration(0,-800));
		}
		else if(r == Reason.MAP1HATCH){
			this.setHasPhysics(true);
		}
		
	}

}
