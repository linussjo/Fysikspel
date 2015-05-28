import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

import javax.imageio.ImageIO;

/**
 * H�ller koll p� vad som h�nder kladdkakan.
 * @author BG5
 *
 */
public class Brownie extends Item {

	public Brownie(String name, int x, int y, int width, int height, float mass) {
		super(name, x, y, width, height, mass);
		
		this.setColliderNumber(Collision.BROWNIE);
		this.addCollideNumbers(Collision.BOINKOBSTACLE);
		this.addCollideNumbers(Collision.SOLIDOBSTACLE);
		
		BufferedImage img = null;
		
		try {
		     img   = ImageIO.read(new File(FileSystems.getDefault().getPath(
	               "data", "Kladdkaka.png").toUri()));
	   } catch (IOException e) {
	       System.out.println("Image not found");
	   }
		this.setImage(img);
	}

	public boolean isKladdkaka = true;

	@Override
	public void collide(Rectangle r, double updateTime) {
		if(r instanceof Player && this.getReason() == Reason.MAP1BDONE)
		{
			this.notifyListerner();
		}
		
	}
}
