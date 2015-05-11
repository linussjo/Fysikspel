
public class Door extends Obstacle implements Notifiable{

	public Door(int x, int y, int width, int height, float mass) {
		super(x, y, width, height, mass);
		this.setHasPhysics(true);
	}

	@Override
	public void collide(Rectangle r, double updateTime) {
		// TODO Auto-generated method stub
		
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
		
	}

}
