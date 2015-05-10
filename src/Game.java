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
	static public Physics physics;

	public final static int inventorySpace = 100;
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

		if(player.shotArrow() && !player.hasShotArrow())
		{
			int vx = (movingLeft ? -550 : 550);

			Arrow a = new Arrow(player.getActiveItem());
			this.nodes.add(a);
			a.applyVelocity(new Velocity(vx + player.getVelocity().getX(), -150 + player.getVelocity().getY()));
			
			player.setHasShotArrow(true);
			
			
			AbstractAction doOneStep = new AbstractAction() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				player.setShotArrow(false);
    				player.setHasShotArrow(false);
    			}
    		};
    		
    		javax.swing.Timer timer = new javax.swing.Timer(500, doOneStep);
    		timer.setRepeats(false);
    		timer.start();
    	}
			
		// if the arrow left and arrow right are presses at the same time this wont go through otherwise it will*/
		if ((movingLeft || movingRight) && (!movingLeft || !movingRight)) {
			int vx = (movingLeft ? -500 : 500); 
			int vy = (int) player.getVelocity().getY();
			if(!player.isInAir())
				vy = 0;
			
			player.whichDirectionImage(movingLeft);
			player.setVelocity(new Velocity(vx, vy));
			
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
				
				this.checkCollide(pr, node2, updateTime);
			}
		}
	}
	public void checkCollide(PhysicRectangle pr, Node node2, double updateTime)
	{
		if(pr.isCollidable() && node2.isCollidable())
		{
			if (pr.intersects(node2))
			{
				pr.collide((Rectangle)node2, updateTime);
				((Rectangle)node2).collide(pr, updateTime);
			}
		}
		if(!pr.isTakeCareOfCollision())
			pr.collisionCheck((Rectangle)node2, updateTime); // collision with obstacle
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
