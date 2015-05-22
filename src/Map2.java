import java.awt.Color;
import java.awt.Point;


public class Map2 extends Map implements Notifiable {
	
	public Map2() {
	
	super(new Point(1190, 100));
	
	Map.physics = new Physics(2000);
	
	Obstacle floor = new Obstacle(0, Component.HEIGHT-20-inventorySpace, Component.WIDTH, 20, 1);
	floor.setColor(Color.BLACK);
	floor.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
	
	this.addNode(floor);

	Obstacle leftWall = new Obstacle(0, 0, 20, Component.HEIGHT-inventorySpace, 0.7f);
	leftWall.setColor(Color.BLACK);
	leftWall.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
	this.addNode(leftWall);

	Obstacle ob1 = new Obstacle(1180, 300, 80, 300, 1);
	ob1.setColor(Color.BLUE);
	ob1.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
	this.addNode(ob1);
	
	Obstacle ob2 = new Obstacle(1100, 450, 80, 150, 1);
	ob2.setColor(Color.BLUE);
	ob2.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
	this.addNode(ob2);
	
	Obstacle ob3 = new Obstacle(1020, 550, 80, 50, 1);
	ob3.setColor(Color.BLUE);
	ob3.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
	this.addNode(ob3);
	
	Obstacle bounce = new Obstacle(590, 400, 80, 20, 2);
	bounce.setColor(Color.RED);
	bounce.setColliderNumber(Node.Collision.BOINKOBSTACLE);
	this.addNode(bounce);
	
	Obstacle rightWall = new Obstacle(Component.WIDTH-20, 0, 20, Component.HEIGHT-inventorySpace, 1);
	rightWall.setColor(Color.BLACK);
	rightWall.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
	this.addNode(rightWall);
	
	Obstacle roof = new Obstacle(0, 0, Component.WIDTH, 20, 1);
	roof.setColor(Color.WHITE);
	roof.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
	this.addNode(roof);
	
	
}
	@Override
	public void notify(Reason r) {
		this.setDone(true);
	}
}