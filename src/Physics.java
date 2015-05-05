import java.awt.Point;


public class Physics {
	private double gravitation;
	private double deAcceleration = 100;
	
	public Physics(double gravitation)
	{
		this.gravitation = gravitation;
	}
	
	/**
	 * calculates where the node should be with its own velocity and acceleration. It also applies the given gravitation
	 * @param n, Node. The node you want to calculate where it should be
	 * @param time, Double. The time elapsed
	 */
	public void calculatePosition(Node n, double time)
	{		
		float vy = (float) (n.getVelocity().getY() + this.gravitation*time + n.getAcceleration().getY()*time);
		float vx = (float)(n.getVelocity().getX() + n.getAcceleration().getX()*time);
		
		if(vx != 0)
		{
			if(n.getVelocity().getX() > 0)
			{
				vx -= (float)(this.deAcceleration*time);
				if(vx <= 0)
					vx = 0;
			}
			else if(n.getVelocity().getX() < 0)
			{
				vx += (float)(this.deAcceleration*time);
				if(vx >= 0)
					vx = 0;
			}
		}
		
		n.setVelocity(new Velocity(vx,vy));
		
		n.translatePosition((int)(vx*time), (int)(vy*time));
	}

}
