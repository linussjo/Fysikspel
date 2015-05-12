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
		
		//if(n.isDidObjectIntersectFloor() && !n.isInAir())
			//vy = 0;
		
		n.setVelocity(new Velocity(vx,vy));
		
		int x = (int) Math.round((vx*time));
		int y = (int) Math.round((vy*time));
		
		n.translatePosition(x, y);
	}
	
	
	public float calculateYVelocity(PhysicRectangle n, double time)
	{
		float vy = (float) (n.getVelocity().getY() + this.gravitation*time + n.getAcceleration().getY()*time);
		
		if(!n.isInAir())
			vy = (float) (n.getVelocity().getY());
		
		return vy;
	}
	
	public float calculateXVelocity(PhysicRectangle n, double time)
	{
		float vx = (float)(n.getVelocity().getX() + n.getAcceleration().getX()*time);
		
		if(vx != 0)
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
	
	public void calculateElasticCollision(PhysicRectangle r1, PhysicRectangle r2)
	{
		double m1 = r1.getMass();
		double m2 = r2.getMass();
		float vi1 = r1.getOldVelocity().getX();
		float vi2 = r2.getOldVelocity().getX();
		
		float vfx1 =   (float) ((m1 -m2)*vi1/(m1 + m2) + 2*m2*vi2/(m1 + m2));
		float vfx2 =   (float) ((2*m1*vi1/(m1 + m2)) + (m2 -m1)*vi2/(m1 + m2));
		
		vi1 = r1.getOldVelocity().getY();
		vi2 = r2.getOldVelocity().getY();
		
		float vfy1 =   (float) ((m1 -m2)*vi1/(m1 + m2) + 2*m2*vi2/(m1 + m2));
		float vfy2 =   (float) ((2*m1*vi1/(m1 + m2)) + (m2 -m1)*vi2/(m1 + m2));
		
		r1.setVelocity(new Velocity(vfx1, r1.getVelocity().getY()));
		r2.setVelocity(new Velocity(vfx2, r2.getVelocity().getY()));
	}
	

}
