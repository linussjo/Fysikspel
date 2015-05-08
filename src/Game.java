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

	private Player player;


	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}
	private Obstacle ob;
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
		Player n = new Player();
		//n.applyVelocity(new Velocity(150, 150));
		this.nodes.add(n);

		this.player = n;

		final int inventorySpace = 100;

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
		obstacle.setColliderNumber(Node.Collision.SOLIDOBSTACLE);
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
		this.nodes.add(kladdkaka);
		kladdkaka.applyVelocity(new Velocity(350, -350));

		Bow bow = new Bow("Andreas båge", 400, Component.HEIGHT - 500-inventorySpace, 30, 30);
		this.nodes.add(bow);
		bow.applyVelocity(new Velocity(250, 250));

		Button butt = new Button(20, 350, 20, 50);
		this.nodes.add(butt);

		this.ob = new Obstacle(Component.WIDTH-160, Component.HEIGHT-20-inventorySpace-250, 50, 250, 1);
		Obstacle ob2 = new Obstacle(Component.WIDTH-160, Component.HEIGHT-20-inventorySpace-300, 140, 50, 1);
		this.nodes.add(ob);
		this.nodes.add(ob2);
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

		if(player.isShotArrow())
		{
			int vx = (movingLeft ? -550 : 550);

			Arrow a = new Arrow(player.getActiveItem());
			this.nodes.add(a);
			a.applyVelocity(new Velocity(vx + player.getVelocity().getX(), -150 + player.getVelocity().getY()));
			player.setShotArrow(false);
		}
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
					if(pr instanceof Arrow)
						pr.setHasPhysics(false);

				}
				else // no collision with obstacle
				{
					if(node1.isCollidable() && node2.isCollidable())
					{
						if (node1.intersects(node2)){
							if (node1.getCollideNumbers().contains(node2.getColliderNumber())){
								/**
								 * Code here to make your own code for what should happen when the node2 and node1 collides
								 * Node1 is a moving object because it is affected by physics
								 * Node2 don't necessary have to be affected by gravity.  
								 */
								if (node2 instanceof Item)
								{
									if(!player.getItemContainer().contains(node2))
									{
										node2.setShouldDraw(false);
										player.addItem((Item)node2);
										node2.setHasPhysics(false);
									}
								}
							}
							else if(node2.getCollideNumbers().contains(node1.getColliderNumber()))
							{
								if(node2 instanceof Button)
								{
									ob.setPosition(new Point(ob.getPosition().x, ob.getPosition().y - ob.getHeight()));
									node2.setCollidable(false);
								}
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
