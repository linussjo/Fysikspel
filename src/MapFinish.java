import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

import javax.imageio.ImageIO;

public class MapFinish extends Map {

	public MapFinish() {

		super(new Point(700, 550));
		Map.physics = new Physics(2000);
		
		// Player setup
		Bow b = new Bow("Andreas Båge", -111, -111, 35, 35);
		this.addNode(b);
		this.getPlayer().pickUpItem(b);
		
		Obstacle background = new Obstacle(300, 0, 750, 500, 1);
		try {
			BufferedImage img = ImageIO.read(new File(FileSystems.getDefault()
					.getPath("data", "WinScreen.png").toUri()));
			background.setImage(img);
		} catch (IOException e) {
			System.out.println("Image not found");
		}
		background.setCollidable(false);
		this.addNode(background);

		Obstacle floor = new Obstacle(0,
				Component.HEIGHT - 20 - inventorySpace, Component.WIDTH, 20, 1);
		floor.setColor(Color.BLACK);
		floor.setColliderNumber(Node.Collision.SOLIDOBSTACLE);

		this.addNode(floor);

		Obstacle leftWall = new Obstacle(0, 0, 20, Component.HEIGHT
				- inventorySpace, 0.7f);
		leftWall.setColor(Color.BLACK);
		leftWall.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		this.addNode(leftWall);

		Obstacle rightWall = new Obstacle(Component.WIDTH - 20, 0, 20,
				Component.HEIGHT - inventorySpace, 1);
		rightWall.setColor(Color.BLACK);
		rightWall.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		this.addNode(rightWall);

		MoveableBox movebox = new MoveableBox(400, 500, 30, 30, 1);
		movebox.setColor(Color.BLUE);
		movebox.setColliderNumber(Node.Collision.MOVABLEBOX);
		this.addNode(movebox);
	}

	@Override
	public void startUp() {
		// TODO Auto-generated method stub
		Sound s1 = new Sound("sm64_mario_thank_you.wav", false);
		s1.play();
	}

}
