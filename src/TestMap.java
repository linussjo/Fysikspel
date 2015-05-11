import java.awt.Color;


public class TestMap extends Map {
	
	public TestMap()
	{
		super();
		Door ob1 = new Door(Component.WIDTH-160, Component.HEIGHT-20-inventorySpace-260, 50, 249, 0);
		Obstacle ob2 = new Obstacle(Component.WIDTH-100, Component.HEIGHT-20-inventorySpace-300, 90, 50, 1);
		ob1.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		ob1.setVelocity(new Velocity(0,-150));
		
		this.addNode(ob2);

		Obstacle floor = new Obstacle(0, Component.HEIGHT-20-inventorySpace, Component.WIDTH, 20, 1);
		floor.setColor(Color.YELLOW);
		floor.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		
		this.addNode(floor);
		
		this.addNode(ob1);


		Obstacle obstacle = new Obstacle(600, 500-inventorySpace, 300, 55, 1);
		obstacle.setColor(Color.YELLOW);
		obstacle.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		this.addNode(obstacle);

		Obstacle leftWall = new Obstacle(0, 0, 20, Component.HEIGHT-inventorySpace, 0.7f);
		leftWall.setColor(Color.YELLOW);
		leftWall.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		this.addNode(leftWall);

		Obstacle rightWall = new Obstacle(Component.WIDTH-20, 0, 20, Component.HEIGHT-inventorySpace, 1);
		rightWall.setColor(Color.YELLOW);
		rightWall.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		this.addNode(rightWall);
		
		Obstacle roof = new Obstacle(0, 0, Component.WIDTH, 20, 1);
		roof.setColor(Color.YELLOW);
		roof.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		this.addNode(roof);
		

		Brownie kladdkaka = new Brownie("Andreas kladdkaka", 400, Component.HEIGHT - 400-inventorySpace, 30, 30, 1);
		this.addNode(kladdkaka);
		kladdkaka.applyVelocity(new Velocity(0, -250));

		Bow bow = new Bow("Andreas båge", 400, Component.HEIGHT - 500-inventorySpace, 30, 30);
		this.addNode(bow);
		bow.applyVelocity(new Velocity(250, 250));

		Button butt = new Button(20, 350, 20, 50);
		this.addNode(butt);
		butt.registerListerner(ob1);
	}

}
