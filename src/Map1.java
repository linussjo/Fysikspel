import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

import javax.imageio.ImageIO;



public class Map1 extends Map implements Notifiable {
	
	private BufferedImage dirtImage = null;
	private BufferedImage woodImage = null;
	
	public Map1()
	{
		super(new Point(600, 300));
		
		Map.physics = new Physics(2000);
		
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
		
		
		Obstacle floor = new Obstacle(0, Component.HEIGHT-20-inventorySpace, Component.WIDTH, 20, 1);
		floor.setColor(Color.BLACK);
		floor.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		
		this.addNode(floor);
		
		Obstacle ob4 = new Obstacle(20, 450, 80, 50, 0);
		ob4.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		ob4.setImage(dirtImage);
		this.addNode(ob4);
		
		Obstacle ob3 = new Obstacle(750, 300, 100, 20, 0);
		ob3.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		ob3.setImage(dirtImage);
		this.addNode(ob3);

		Obstacle leftWall = new Obstacle(0, 0, 20, Component.HEIGHT-inventorySpace, 0.7f);
		leftWall.setColor(Color.BLACK);
		leftWall.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		this.addNode(leftWall);

		Obstacle rightWall = new Obstacle(Component.WIDTH-20, 0, 20, Component.HEIGHT-inventorySpace, 1);
		rightWall.setColor(Color.BLACK);
		rightWall.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		this.addNode(rightWall);
		
		Obstacle roof = new Obstacle(0, 0, Component.WIDTH, 20, 1);
		roof.setColor(Color.WHITE);
		roof.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		this.addNode(roof);
		
		Door hatch = new Door(490, 400, 230, 20, 0);
		this.addNode(hatch);
		hatch.setImage(woodImage);
		hatch.setHasPhysics(false);
		
		Obstacle boxRoof = new Obstacle(490, 180, 230, 20, 1);
		boxRoof.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		boxRoof.setImage(woodImage);
		this.addNode(boxRoof);
		
		Obstacle boxRWall = new Obstacle(490, 200, 20, 200, 0.8f);
		//boxRWall.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		boxRWall.setImage(woodImage);
		this.addNode(boxRWall);
		
		Obstacle boxLWall = new Obstacle(700, 200, 20, 200, 0.8f);
		boxLWall.setImage(woodImage);
		this.addNode(boxLWall);
		
		
		
		Button hatchButton = new Button(510, 300, 10, 20);
		this.addNode(hatchButton);
		hatchButton.setReason(Reason.MAP1HATCH);
		hatchButton.registerListerner(hatch);
		hatchButton.setColor(Color.red);
		
		Obstacle ob1 = new Obstacle(750, 500, 100, 20, 0);
		ob1.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		ob1.setImage(dirtImage);
		this.addNode(ob1);
		
		Obstacle ob2 = new Obstacle(950, 350, 100, 20, 0);
		ob2.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		ob2.setImage(dirtImage);
		this.addNode(ob2);
		

		Bow bow = new Bow("Andreas båge", 600, 120, 30, 30);
		this.addNode(bow);
		
		Door door = new Door(100, 450, 50, 150, 0);
		door.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		this.addNode(door);
		
		
		Button arrowButton = new Button(20, 200, 10, 20);
		arrowButton.setColor(Color.red);
		arrowButton.setReason(Reason.MAP1ARROW);
		arrowButton.registerListerner(door);
		this.addNode(arrowButton);
		
		Button doneButton = new Button(0, 520, 25, 80);
		doneButton.setColor(Color.white);
		doneButton.setReason(Reason.MAP1DONE);
		doneButton.registerListerner(this);
		this.addNode(doneButton);
		
		
	}

	@Override
	public void startUp() {
		// TODO Auto-generated method stub
		
	}

}
