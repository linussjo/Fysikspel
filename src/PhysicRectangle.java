import java.awt.Point;



public class PhysicRectangle extends Rectangle {
	private boolean floorZeroVelocity = false;
	
	private boolean inAir = false;

	boolean didObjectIntersectFloor = false;
	/**
	 * @return the floorZeroVelocity
	 */
	public boolean isFloorZeroVelocity() {
		return floorZeroVelocity;
	}

	/**
	 * @param floorZeroVelocity the floorZeroVelocity to set
	 */
	public void setFloorZeroVelocity(boolean floorZeroVelocity) {
		this.floorZeroVelocity = floorZeroVelocity;
	}

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
		
		this.addCollideNumbers(Collision.SOLIDOBSTACLE);
		this.addCollideNumbers(Collision.BOINKOBSTACLE);
	}

	public boolean collisionCheck(Rectangle r, double updateTime)
	{
		PhysicRectangle pr = this;
		if(pr.intersects(r))
		{
			if(pr.getCollideNumbers().contains(r.getColliderNumber()))
			{
				if(r.getColliderNumber() == Node.Collision.SOLIDOBSTACLE || r.getColliderNumber() == Node.Collision.BOINKOBSTACLE || r.getColliderNumber() == Node.Collision.MOVABLEBOX )
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

							//med boink, annars s�tt velY till 0
							float vy = 0;
							float vx = 0;
							if(r.getColliderNumber() == Node.Collision.BOINKOBSTACLE)
							{
								vy = (float) ((pr.getMass() - r.getMass())/(pr.getMass() + r.getMass()) * pr.getVelocity().getY());
								
								if(r instanceof Obstacle)
									vy *= ((Obstacle)r).getBoinkFactor(); // dont want all the of speed to be left if it is an obstacle
								//vy = (float) ((0.78)*pr.getVelocity().getY()*(-1));
								vx = pr.getVelocity().getX();
								if(vy >= -100)
								{
									vy = 0;
									if(this.floorZeroVelocity)
										vx = 0;
								}
								else
								{
									pr.setInAir(true);
									pr.setDidObjectIntersectFloor(false);
								}

							}
							if(!this.floorZeroVelocity)
								vx = pr.getVelocity().getX();
							else if(pr instanceof MoveableBox)
								vx = pr.getVelocity().getX();
							
							pr.setVelocity(new Velocity(vx, vy));
							pr.setPosition(new Point(pr.getPosition().x, r.getPosition().y - pr.getHeight()));
						}
						else
						{

							//med boink, annars s�tt velY till 0
							float vy = 0;
							if(r.getColliderNumber() == Node.Collision.BOINKOBSTACLE)
							{
								vy = (float) ((pr.getMass() - r.getMass())/(pr.getMass() + r.getMass()) * pr.getVelocity().getY());
								//vy = (int)((0.9)*(pr.getVelocity().getY()*(-1)));
								if(vy <= 60)
								{
									vy = 0;
								}
							}
							else if(pr instanceof MoveableBox)
								vy = pr.getVelocity().getY();

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
								
								vx = (float) ((pr.getMass() - r.getMass())/(pr.getMass() + r.getMass()) * pr.getVelocity().getX());
								
								if(r instanceof Obstacle)
									vx *= ((Obstacle)r).getBoinkFactor(); // dont want all the of speed to be left if it is an obstacle
									
								if(vx >= 60)
								{
									vx = 0;
								}
							}
							else if(pr instanceof MoveableBox)
								vx = pr.getVelocity().getX();

							pr.setVelocity(new Velocity(vx, pr.getVelocity().getY()));
							pr.setPosition(new Point(r.getPosition().x - pr.getWidth(), pr.getPosition().y));
						}
						else if(pr.getVelocity().getX()<0)
						{
							float vx = 0;
							if(r.getColliderNumber() == Node.Collision.BOINKOBSTACLE)
							{
								vx = (float) ((pr.getMass() - r.getMass())/(pr.getMass() + r.getMass()) * pr.getVelocity().getX());
								
								if(r instanceof Obstacle)
									vx *= ((Obstacle)r).getBoinkFactor(); // dont want all the of speed to be left if it is an obstacle
								
								if(vx <= -60)
								{
									vx = 0;
								}
							}
							else if(pr instanceof MoveableBox)
								vx = pr.getVelocity().getX();

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
