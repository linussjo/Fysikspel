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
		this.physics = new Physics(650);
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
        
        Rectangle floor = new Rectangle(0, Component.HEIGHT-20, Component.WIDTH, 20);
        floor.setColor(Color.YELLOW);
        floor.setColliderNumber(Node.Collision.FLOOR);
        this.nodes.add(floor);
        
        Rectangle leftWall = new Rectangle(0, 0, 20, Component.HEIGHT);
        leftWall.setColor(Color.YELLOW);
        leftWall.setColliderNumber(Node.Collision.LEFTWALL);
        this.nodes.add(leftWall);
        
        Rectangle rightWall = new Rectangle(Component.WIDTH-20, 0, 20, Component.HEIGHT);
        rightWall.setColor(Color.YELLOW);
        rightWall.setColliderNumber(Node.Collision.RIGHTWALL);
        this.nodes.add(rightWall);
        
        Rectangle floor1 = new Rectangle(350, 530, 300, 25);
        floor1.setColor(Color.YELLOW);
        floor1.setColliderNumber(Node.Collision.FLOOR);
        this.nodes.add(floor1);
        
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
	            int vx = (movingLeft ? -300 : 300); 
	            player.setVelocity(new Velocity(vx, player.getVelocity().getY()));
	        }
	        if (movingUp) {
	            //dy = -tmpY;
	        	if(!player.isInAir())
	        		player.setVelocity(new Velocity(player.getVelocity().getX(),-400));
	        }
	        // set the new position to the node
	        this.physics.calculatePosition(player, updateTime);
	        
	        boolean didPlayerIntersectFloor = false;
	        
	        for(Node node : this.nodes)
	        {
	        	if(node.getColliderNumber() != Node.Collision.PLAYER)
	        	{
	        		if(player.intersects(node))
	        		{
	        			if(player.getCollideNumbers().contains(node.getColliderNumber()))
	        			{
	        				Rectangle r = (Rectangle)node;
	        				if(node.getColliderNumber() == Node.Collision.FLOOR)
	        				{
		        				player.setInAir(false);
		        				didPlayerIntersectFloor = true;
	        					player.setVelocity(new Velocity(0, 0));
	        					player.setPosition(new Point(player.getPosition().x,r.getPosition().y + (1 - player.getHeight())));
	        				}
	        				else if(node.getColliderNumber() == Node.Collision.LEFTWALL)
	        				{
	        					player.setVelocity(new Velocity(0, player.getVelocity().getY()));
	        					player.setPosition(new Point(r.getPosition().x - 1 + r.getWidth(), player.getPosition().y));
	        				}
	        				else if(node.getColliderNumber() == Node.Collision.RIGHTWALL)
	        				{
	        					player.setVelocity(new Velocity(0, player.getVelocity().getY()));
	        					player.setPosition(new Point(r.getPosition().x + (1 - player.getWidth()), player.getPosition().y));
	        				}
	        				else if(node.getColliderNumber() == Node.Collision.OBSTACLE)
	        				{
	        					int y1 = player.getPosition().y;
	        					int y2 = player.getPosition().y - player.getHeight();
	        					int y3 = r.getPosition().y;
	        					int y4 = r.getPosition().y - r.getHeight();
	        					
	        					int x1 = player.getPosition().x;
	        					int x2 = player.getPosition().x - player.getWidth();
	        					int x3 = r.getPosition().x;
	        					int x4 = r.getPosition().x - r.getWidth();
	        					
	        					/*if(y1 < y4)
	        					{
	        						if(x1 > x3)
	        						{
	        							
	        						}
	        					}
	        					else if(y1 < y4 && x2 > x3)
	        					{
	        						
	        					}
	        					else if(y2 > y3)*/
	        				}
	        			
	        			}
	        		}
	        		else
	        		{
	        			if(!didPlayerIntersectFloor)
	        			{
	        				player.setInAir(true);
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
