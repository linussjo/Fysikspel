import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

import javax.imageio.ImageIO;



public class Arrow extends PhysicRectangle {
	private double rotate;
	public Arrow(Item bow) {
		super(bow.getPosition().x , bow.getPosition().y + bow.getWidth() / 2, 20, 7, 0.1f);
		this.setColliderNumber(Collision.ARROW);
		this.addCollideNumbers(Collision.SOLIDOBSTACLE);
		this.addCollideNumbers(Collision.BOINKOBSTACLE);
		try {
		     this.setImage(ImageIO.read(new File(FileSystems.getDefault().getPath(
	               "data", "Arrow_Up_Left.png").toUri())));
	   } catch (IOException e) {
	       System.out.println("Image not found");
	   }
		
	}
	
	public void draw(Graphics g)
	{
		if(this.shouldDraw())
		{
			if(this.getImage() != null)
			{
				g.setColor(this.getColor());
				//g.drawImage(this.getImage(), this.getPosition().x, this.getPosition().y, this.getWidth(), this.getHeight(), null);
				// create the transform, note that the transformations happen
	              // in reversed order (so check them backwards)
	            AffineTransform at = new AffineTransform();
	
	            // 4. translate it to the center of the component
	            at.translate(this.getPosition().x, this.getPosition().y);
	
	            // 3. do the actual rotation
	            if(this.getVelocity().getX() != 0 && this.getVelocity().getY() != 0)
	            {
	            	if(this.getVelocity().getX() >= 0)
	            		this.rotate = Math.PI*3/4;
	            	else
	            		this.rotate = Math.PI*(-1)/4;
	            	
		            if(this.getVelocity().getX() != 0)
		            	this.rotate += Math.atan(this.getVelocity().getY()/this.getVelocity().getX());
		            else 
		            	this.rotate = -Math.PI;
	            }
	            at.rotate(rotate);
	            
	            //System.out.println(this.getWidth() + " : " + this.getHeight());  + Math.abs((Math.sin(Math.PI - this.rotate)*21)  + Math.abs(Math.cos(Math.PI - this.rotate)*21)
	            // 2. just a scale because this image is big
	            at.scale(3, 2.5);
	
	            // 1. translate the object so that you rotate it around the 
	            //    center (easier :))
	            at.translate(-getImage().getWidth()/2, -getImage().getHeight()/2);
	
	              // draw the image
	            Graphics2D g2d = (Graphics2D) g;
	            g2d.drawImage(this.getImage(), at, null);
	            g2d.rotate(0);
			}
		}
	}

}
