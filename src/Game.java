import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;


public class Game extends JFrame {
	/**
	 * A List to hold all the nodes that will be placed out on the screen
	 */
	private List<Node> nodes;
	/**
	 * Gets the node list
	 * @return Set, nodes
	 */
	public List<Node> getNodes() {
		return nodes;
	}

	private double lastUpdate;
	private Physics physics;

	private boolean movingLeft;
	private boolean movingRight;
	private boolean movingUp;


	public Game()
	{
		super("Fysikspel");
		this.nodes = new ArrayList<Node>();
		this.physics = new Physics(1000);
		this.movingUp = false;
		this.movingLeft = false;
		this.movingRight = false;

		/**
		 * creating a node for as an example.
		 * this are the types im putting in to "new Rectangle(Position x, Position y, Width, Height, Mass)" in the code
		 */
		Node n = new Player();
		//n.applyVelocity(new Velocity(150, 150));
		this.nodes.add(n);
		
		int inventorySpace = 100;

		Rectangle floor = new Rectangle(0, Component.HEIGHT-20-inventorySpace, Component.WIDTH, 20);
		floor.setColor(Color.YELLOW);
		floor.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		this.nodes.add(floor);
		
		Rectangle floor2 = new Rectangle(200, 500-inventorySpace, 330, 1);
		floor2.setColor(Color.BLACK);
		floor2.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
		this.nodes.add(floor2);
		
		Obstacle obstacle = new Obstacle(600, 500-inventorySpace, 300, 55, 1);
		obstacle.setColor(Color.YELLOW);
		obstacle.setColliderNumber(Node.Collision.BOINKOBSTACLE);
		this.nodes.add(obstacle);

		Rectangle leftWall = new Rectangle(0, 0, 20, Component.HEIGHT-inventorySpace);
		leftWall.setColor(Color.YELLOW);
		leftWall.setColliderNumber(Node.Collision.BOINKOBSTACLE);
		this.nodes.add(leftWall);

		Rectangle rightWall = new Rectangle(Component.WIDTH-20, 0, 20, Component.HEIGHT-inventorySpace);
		rightWall.setColor(Color.YELLOW);
		rightWall.setColliderNumber(Node.Collision.BOINKOBSTACLE);
		this.nodes.add(rightWall);

		Brownie kladdkaka = new Brownie("Andreas kladdkaka", 400, Component.HEIGHT - 500-inventorySpace, 30, 30, 1);
		kladdkaka.setColliderNumber(Node.Collision.BROWNIE);
		this.nodes.add(kladdkaka);
		kladdkaka.applyVelocity(new Velocity(350, -350));
	}

	public void run(){
		Component component = new Component(this);
		lastUpdate = System.currentTimeMillis();

		this.setLayout(new BorderLayout());
		this.add(component, BorderLayout.CENTER);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.pack();

		AbstractAction doOneStep = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double currentTime = System.currentTimeMillis();
				update((currentTime - lastUpdate)*0.001);
				lastUpdate = currentTime;
				component.repaint();
			}
		};
		int fps = 1000/60; // 60 fps 
		Timer timer = new Timer(fps, doOneStep);
		timer.setCoalesce(true);
		timer.start();


		super.setVisible(true);
	}

	public void update(double updateTime) {
		// get the first node, as we expect that node to be the player at the moment
		Player player = (Player) this.nodes.get(0);
		// if the arrow left and arrow right are presses at the same time this wont go through otherwise it will*/
		if ((movingLeft || movingRight) && (!movingLeft || !movingRight)) {
			int vx = (movingLeft ? -500 : 500); 
			
			player.whichDirectionImage(movingLeft);
			player.setVelocity(new Velocity(vx, player.getVelocity().getY()));
			player.setDidObjectIntersectFloor(false);
		}
		if (movingUp) {
			//dy = -tmpY;
			if(!player.isInAir())
			{
				player.setVelocity(new Velocity(player.getVelocity().getX(),-800));
				player.setDidObjectIntersectFloor(false);
			}
		}
		
		for(Node node1 : this.nodes)
		{
			if(!node1.ifHasPhysics())
				continue;

			PhysicRectangle pr = (PhysicRectangle)node1;
			
			this.physics.calculatePosition(pr, updateTime);
			
			for(Node node2 : this.nodes)
			{
				if(node1 == node2)
					continue;
				
				
				if(pr.collisionCheck((Rectangle)node2, updateTime)) // collision with obstacle
				{
					
				}
				else // no collision with obstacle
				{
					if (node1.intersects(node2)){
						if (node1.getCollideNumbers().contains(node2.getColliderNumber())){
							if (node2 instanceof Brownie){
								if(!player.getItemContainer().contains(node2))
								{
									player.addItem((Brownie)node2);
									node2.setHasPhysics(false);
								}
								//node2.setPosition(new Point(100, Component.HEIGHT-60));
								//System.out.println(((Brownie) node2).getName());
								
							}
						}
					}
										
				}
				
			}
		}
	}
	/**
	 * Adds a node to the node list
	 * @param Node, n
	 */
	public void addNode(Node n)
	{
		this.nodes.add(n);
	}

	public void move(Component.Direction dir, boolean val) {
		switch (dir){
		case UP:
			movingUp = val;
			break;
		case LEFT:
			movingLeft = val;
			break;
		case RIGHT:
			movingRight = val;
			break;
		}
	}
}
