import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

import javax.imageio.ImageIO;


public class Map1b extends Map{

	private BufferedImage dirtImage = null;
	private BufferedImage woodImage = null;
	
	public Map1b() {
		super(new Point(1200,550));
		
		// Screen boundries start
		Obstacle floor = new Obstacle(0, Component.HEIGHT-20-inventorySpace, Component.WIDTH, 20, 1);
		floor.setColor(Color.BLACK);
		floor.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		
		Obstacle leftWall = new Obstacle(0, 0, 20, Component.HEIGHT-inventorySpace, 0.7f);
		leftWall.setColor(Color.BLACK);
		leftWall.setColliderNumber(Node.Collision.SOLIDOBSTACLE);

		Obstacle rightWall = new Obstacle(Component.WIDTH-20, 0, 20, Component.HEIGHT-inventorySpace, 1);
		rightWall.setColor(Color.BLACK);
		rightWall.setColliderNumber(Node.Collision.SOLIDOBSTACLE);

		// Screen boundries end
		try {
		     BufferedImage img  = ImageIO.read(new File(FileSystems.getDefault().getPath(
	               "data", "Platform.png").toUri()));
		     this.dirtImage = img;
		} catch (IOException e) {
	       System.out.println("Image not found");
	   }
		try {
		     BufferedImage img  = ImageIO.read(new File(FileSystems.getDefault().getPath(
	               "data", "woodplatform.png").toUri()));
		     this.woodImage = img;
		} catch (IOException e) {
	       System.out.println("Image not found");
	   }
		
		// Player setup
		Bow b = new Bow("Andreas Båge",-111,-111,35,35);
		this.addNode(b);
		this.getPlayer().pickUpItem(b);
		
		// Brownie door setup
		Obstacle doorRoof = new Obstacle(20, 200, 80, 50, 0);
		doorRoof.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		doorRoof.setImage(woodImage);
		
		Obstacle doorFloor = new Obstacle(20, 350, 130, 50, 0);
		doorFloor.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		doorFloor.setImage(woodImage);

		Door door = new Door(100, 200, 50, 150, 0);
		door.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		
		Brownie br = new Brownie("Andreas Kladdkaka", 35 , 310, 30,30, 10f);
		this.addNode(br);
		br.registerListerner(this);
		br.setReason(Reason.MAP1BDONE);
		
		
		// arrow shoot setup
		Obstacle arrowRWall = new Obstacle(1245, 50, 25, 450, 0.9f);
		arrowRWall.setImage(woodImage);
		Obstacle arrowLWall = new Obstacle(1045, 150, 25, 350, 0.9f);
		arrowLWall.setImage(woodImage);
		Obstacle arrowFloor = new Obstacle(1045, 500, 225, 25, 0.9f);
		arrowFloor.setImage(woodImage);
		
		Obstacle playerPlatform1 = new Obstacle(650, 450, 150, 25, 0);
		playerPlatform1.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		playerPlatform1.setImage(dirtImage);
		
		Obstacle playerPlatform2 = new Obstacle(550, 350, 150, 25, 0);
		playerPlatform2.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		playerPlatform2.setImage(dirtImage);
		
		Obstacle playerPlatform3 = new Obstacle(300, 400, 150, 25, 0);
		playerPlatform3.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		playerPlatform3.setImage(dirtImage);
		
		Button arrowButton = new Button(1070, 450, 8, 25);
		arrowButton.setColor(Color.RED);
		arrowButton.setReason(Reason.MAP1BARROW);
		arrowButton.registerListerner(door);
		this.addNode(arrowButton);
		
		
		//Floors
		this.addNode(doorFloor);
		this.addNode(floor);
		this.addNode(doorRoof);
		this.addNode(arrowFloor);
		this.addNode(playerPlatform1);
		this.addNode(playerPlatform2);
		this.addNode(playerPlatform3);
		// Walls
		this.addNode(arrowRWall);
		this.addNode(arrowLWall);
		this.addNode(door);
		this.addNode(rightWall);
		this.addNode(leftWall);
	}

	@Override
	public void startUp() {
		// TODO Auto-generated method stub
		
	}

}
