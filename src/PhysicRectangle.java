import java.awt.Point;



public abstract class PhysicRectangle extends Rectangle {
	
	private boolean takeCareOfCollision = false;
	
	private boolean floorZeroVelocity = false;
	
	private boolean inAir = false;

	private boolean didObjectIntersectFloor = false;
	
	private boolean didObjectIntersectWall = false;
	/**
	 * @return the didObjectIntersectWall
	 */
	public boolean isDidObjectIntersectWall() {
		return didObjectIntersectWall;
	}

	/**
	 * @param didObjectIntersectWall the didObjectIntersectWall to set
	 */
	public void setDidObjectIntersectWall(boolean didObjectIntersectWall) {
		this.didObjectIntersectWall = didObjectIntersectWall;
	}

	/**
	 * @return the takeCareOfCollision
	 */
	public boolean isTakeCareOfCollision() {
		return takeCareOfCollision;
	}

	/**
	 * @param takeCareOfCollision the takeCareOfCollision to set
	 */
	public void setTakeCareOfCollision(boolean takeCareOfCollision) {
		this.takeCareOfCollision = takeCareOfCollision;
	}

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
		
	}
	
	public void setVelocity(Velocity v)
	{
		if(v.getX() != 0)
			this.setDidObjectIntersectFloor(false);
		if(v.getY() != 0)
			this.setDidObjectIntersectFloor(false);
		
		super.setVelocity(v);		
	}
	
	public boolean collisionCheck(Rectangle r, double updateTime)
	{
		PhysicRectangle pr = this;
		if(pr.intersects(r))
		{
			if(pr.getCollideNumbers().contains(r.getColliderNumber()))
			{
				if(r.getColliderNumber() == Node.Collision.SOLIDOBSTACLE || r.getColliderNumber() == Node.Collision.BOINKOBSTACLE)
				{    
					if(r.getColliderNumber() == Collision.BOINKOBSTACLE && !(this instanceof Player))
					{
						Sound s = new Sound("sm64_mario_boing.wav", false);
						s.play();
					}
					
					int y1 = pr.getOldPosition().y + pr.getHeight();
					int y2 = pr.getOldPosition().y;
					int y3 = pr.getPosition().y + pr.getHeight();
					int x = pr.getOldPosition().x;
					
					if((pr.isInAir() && y1 <= r.getPosition().y || y2 >= r.getPosition().y + r.getHeight()) || this.didObjectIntersectWall && y1 > r.getPosition().getY())
					{
						if(pr.getVelocity().getY()>=0)
						{
							
							pr.setInAir(false);
							pr.setDidObjectIntersectFloor(true);
							this.didObjectIntersectWall = false;
							

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
						this.didObjectIntersectWall = false;
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
							pr.setPosition(new Point(r.getPosition().x - pr.getWidth() - 1, pr.getPosition().y));
						}
						else  if(pr.getVelocity().getX()<0)
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
							pr.setPosition(new Point(r.getPosition().x + r.getWidth() + 1, pr.getPosition().y));
						}
						this.didObjectIntersectWall = true;
					}
					return true;

				}
				else if(this.ifHasPhysics() && r.ifHasPhysics())
				{
					int x1 = this.getPosition().x + this.getWidth();
					int x2 = this.getPosition().x;
					
					if((x1 >= r.getPosition().x || x2 <= r.getPosition().x + r.getWidth()))
					{
						if(this.getVelocity().getX() > 0)
						{
							this.setPosition(new Point(r.getPosition().x - this.getWidth(), r.getPosition().y));
						}
						else if(this.getVelocity().getX() < 0)
						{
							this.setPosition(new Point(r.getPosition().x + r.getWidth(), r.getPosition().y));
						}
					}
					
					Map.physics.calculateElasticCollision(this, (PhysicRectangle)r);
				}
				return true;

			}
		}
		this.didObjectIntersectWall = false;
		if(!pr.isDidObjectIntersectFloor())
		{
			pr.setInAir(true);
			pr.setDidObjectIntersectFloor(false);
		}
		
		return false;
	}


}
