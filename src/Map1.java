import java.awt.Color;
import java.awt.Point;



public class Map1 extends Map implements Notifiable {
	
	public Map1()
	{
		super(new Point(600, 300));
		
		Map.physics = new Physics(2000);
		
		Obstacle floor = new Obstacle(0, Component.HEIGHT-20-inventorySpace, Component.WIDTH, 20, 1);
		floor.setColor(Color.BLACK);
		floor.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		
		this.addNode(floor);

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
		
		Door hatch = new Door(500, 400, 200, 10, 0);
		this.addNode(hatch);
		hatch.setHasPhysics(false);
		
		Obstacle boxRoof = new Obstacle(500, 200, 200, 10, 1);
		boxRoof.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		this.addNode(boxRoof);
		
		Obstacle boxRWall = new Obstacle(500, 200, 10, 200, 0.8f);
		//boxRWall.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		this.addNode(boxRWall);
		
		Obstacle boxLWall = new Obstacle(700, 200, 10, 200, 0.8f);
		this.addNode(boxLWall);
		
		
		
		Button hatchButton = new Button(510, 300, 10, 20);
		this.addNode(hatchButton);
		hatchButton.setReason(Reason.MAP1HATCH);
		hatchButton.registerListerner(hatch);
		hatchButton.setColor(Color.red);
		
		Obstacle ob1 = new Obstacle(750, 500, 100, 20, 0);
		ob1.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		this.addNode(ob1);
		
		Obstacle ob2 = new Obstacle(850, 350, 100, 20, 0);
		ob2.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		this.addNode(ob2);
		

		Bow bow = new Bow("Andreas b�ge", 600, 169, 30, 30);
		this.addNode(bow);
		
		Door ob3 = new Door(100, 450, 50, 150, 0);
		ob3.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		this.addNode(ob3);
		
		Obstacle ob4 = new Obstacle(20, 450, 80, 50, 0);
		ob4.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		this.addNode(ob4);
		
		Button arrowButton = new Button(20, 250, 10, 20);
		arrowButton.setColor(Color.red);
		arrowButton.setReason(Reason.MAP1ARROW);
		arrowButton.registerListerner(ob3);
		this.addNode(arrowButton);
		
		Button doneButton = new Button(0, 520, 25, 80);
		doneButton.setColor(Color.white);
		doneButton.setReason(Reason.MAP1DONE);
		doneButton.registerListerner(this);
		this.addNode(doneButton);
		
		
	}

	@Override
	public void notify(Reason r) {
		if(r == Reason.MAP1DONE)
			this.setDone(true);
		
	}

}