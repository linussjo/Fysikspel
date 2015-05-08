import java.awt.Point;


public class PhysicRectangle extends Rectangle {
	private boolean inAir = false;

	boolean didObjectIntersectFloor = false;
	/**
	 * @return the didObjectIntersectFloor
	 */
	public boolean isDidObjectIntersectFloor() {
		return didObjectIntersectFloor;
	}

	/**
	 * @param didObjectIntersectFloor the didObjectIntersectFloor to set
	 */
	public void setDidObjectIntersectFloor(boolean didObjectIntersectFloor) {
		this.didObjectIntersectFloor = didObjectIntersectFloor;
	}


	/**
	 * @return the inAir
	 */
	public boolean isInAir() {
		return inAir;
	}

	/**
	 * @param inAir the inAir to set
	 */
	public void setInAir(boolean inAir) {
		this.inAir = inAir;
	}

	public PhysicRectangle(int x, int y, int width, int height, float mass) {
		super(x, y, width, height, mass);
		// TODO Auto-generated constructor stub
	}

	public boolean collisionCheck(Rectangle r, double updateTime)
	{
		PhysicRectangle pr = this;
		if(pr.intersects(r))
		{
			if(pr.getCollideNumbers().contains(r.getColliderNumber()))
			{
				if(r.getColliderNumber() == Node.Collision.SOLIDOBSTACLE || r.getColliderNumber() == Node.Collision.BOINKOBSTACLE )
				{      
					int y1 = pr.getOldPosition().y + pr.getHeight();
					int y2 = pr.getOldPosition().y;
					int x = pr.getOldPosition().x;

					if(pr.isInAir() && y1 <= r.getPosition().y || y2 >= r.getPosition().y + r.getHeight() && !(x  >= r.getPosition().x + r.getWidth()))
					{
						if(pr.getVelocity().getY()>=0)
						{
							
							pr.setInAir(false);
							pr.setDidObjectIntersectFloor(true);

							//med boink, annars sätt velY till 0
							float vy = 0;
							float vx = 0;
							if(r.getColliderNumber() == Node.Collision.BOINKOBSTACLE)
							{
								vy = (float) ((0.78)*pr.getVelocity().getY()*(-1));
								vx = pr.getVelocity().getX();
								if(vy >= -100)
								{
									vy = 0;
									vx = 0;
								}
								else
								{
									pr.setInAir(true);
									pr.setDidObjectIntersectFloor(false);
								}

							}
							
							pr.setVelocity(new Velocity(vx, vy));
							pr.setPosition(new Point(pr.getPosition().x, r.getPosition().y - pr.getHeight()));
						}
						else
						{

							//med boink, annars sätt velY till 0
							float vy = 0;
							if(r.getColliderNumber() == Node.Collision.BOINKOBSTACLE)
							{
								vy = (int)((0.9)*(pr.getVelocity().getY()*(-1)));
								if(vy <= 60)
								{
									vy = 0;
								}
							}
							else
								vy = 0;

							pr.setVelocity(new Velocity(0, vy));
							pr.setPosition(new Point(pr.getPosition().x, r.getPosition().y + r.getHeight()));
						}
					}
					else
					{
						if(pr.getVelocity().getX()>0)
						{
							float vx = 0;
							if(r.getColliderNumber() == Node.Collision.BOINKOBSTACLE)
							{
								vx = (int)((0.8)*(pr.getVelocity().getX()*(-1)));
								if(vx >= 60)
								{
									vx = 0;
								}
							}
							else
								vx = 0;

							pr.setVelocity(new Velocity(vx, pr.getVelocity().getY()));
							pr.setPosition(new Point(r.getPosition().x - pr.getWidth(), pr.getPosition().y));
						}
						else if(pr.getVelocity().getX()<0)
						{
							float vx = 0;
							if(r.getColliderNumber() == Node.Collision.BOINKOBSTACLE)
							{
								vx = (int)((0.8)*(pr.getVelocity().getX()*(-1)));
								if(vx <= -60)
								{
									vx = 0;
								}
							}
							else
								vx = 0;

							pr.setVelocity(new Velocity(vx, pr.getVelocity().getY()));
							pr.setPosition(new Point(r.getPosition().x + r.getWidth(), pr.getPosition().y));
						}
					}
					return true;

				}
			}
		}
		if(!pr.isDidObjectIntersectFloor())
		{
			pr.setInAir(true);
			pr.setDidObjectIntersectFloor(false);
		}
		
		return false;
	}

}
