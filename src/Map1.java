import java.awt.Color;



public class Map1 extends Map {
	
	public Map1()
	{
		super();

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
		

		Brownie kladdkaka = new Brownie("Andreas kladdkaka", 400, Component.HEIGHT - 400-inventorySpace, 30, 30, 1);
		this.addNode(kladdkaka);
		//kladdkaka.applyVelocity(new Velocity(0, -250));
		
		Obstacle boxRoof = new Obstacle(500, 200, 200, 10, 1);
		this.addNode(boxRoof);
		
		Obstacle boxRWall = new Obstacle(500, 200, 10, 200, 1);
		this.addNode(boxRWall);
		
		Obstacle boxLWall = new Obstacle(700, 200, 10, 200, 1);
		this.addNode(boxLWall);
		
		Door hatch = new Door(500, 400, 200, 10, 1);
		this.addNode(hatch);
		hatch.setHasPhysics(false);
		

//		Bow bow = new Bow("Andreas båge", 400, Component.HEIGHT - 500-inventorySpace, 30, 30);
//		this.addNode(bow);
//		bow.applyVelocity(new Velocity(250, 250));
	}

}
