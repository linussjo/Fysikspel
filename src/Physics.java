import java.awt.Point;


public class Physics {
	private double gravitation;
	private double airResistance = 100;
	
	public Physics(double gravitation)
	{
		this.gravitation = gravitation;
	}
	
	/**
	 * calculates where the node should be with its own velocity and acceleration. It also applies the given gravitation
	 * @param n, Node. The node you want to calculate where it should be
	 * @param time, Double. The time elapsed
	 */
	public void calculatePosition(PhysicRectangle n, double time)
	{	
		float vx = this.calculateXVelocity(n, time);
		float vy = this.calculateYVelocity(n, time);
		
		if(n.isDidObjectIntersectFloor() && !n.isInAir())
			vy = 0;
		
		n.setVelocity(new Velocity(vx,vy));
		
		n.translatePosition((int)(vx*time), (int)(vy*time));
	}
	
	
	public float calculateYVelocity(PhysicRectangle n, double time)
	{
		float vy = (float) (n.getVelocity().getY() + this.gravitation*time + n.getAcceleration().getY()*time);
		
		if(n instanceof PhysicRectangle)
			if(!((PhysicRectangle)n).isInAir())
				vy = (float) (n.getVelocity().getY());
		
		return vy;
	}
	
	public float calculateXVelocity(PhysicRectangle n, double time)
	{
		float vx = (float)(n.getVelocity().getX() + n.getAcceleration().getX()*time);
		
		if(vx != 0 && !n.didObjectIntersectFloor)
		{
			if(n.getVelocity().getX() > 0)
			{
				vx -= (float)(this.airResistance*time);
				if(vx <= 0)
					vx = 0;
			}
			else if(n.getVelocity().getX() < 0)
			{
				vx += (float)(this.airResistance*time);
				if(vx >= 0)
					vx = 0;
			}
		}
		
		return vx;
	}
	

}
