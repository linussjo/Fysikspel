import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

import javax.imageio.ImageIO;

/**
 * Håller koll på vilket håll bågen ska hållas ut.
 * @author BG5
 *
 */
public class Bow extends Item {

	public Bow(String name, int x, int y, int width, int height) {
		super(name, x, y, width, height, 1);
		this.setColliderNumber(Node.Collision.BOW);
		this.addCollideNumbers(Collision.BOINKOBSTACLE);
		this.addCollideNumbers(Collision.SOLIDOBSTACLE);
		
		BufferedImage imgL = null;
		BufferedImage imgR = null;
		
		try {
		     imgL   = ImageIO.read(new File(FileSystems.getDefault().getPath(
	               "data", "BowLeft.png").toUri()));
		     imgR   = ImageIO.read(new File(FileSystems.getDefault().getPath(
		               "data", "BowRight.png").toUri()));
	   } catch (IOException e) {
	       System.out.println("Image not found");
	   }
		this.setLeftImg(imgL);
		this.setRightImg(imgR);
		this.setImgUse(imgL);
	}

	@Override
	public void collide(Rectangle r, double updateTime) {
		// TODO Auto-generated method stub
		
	}

}
