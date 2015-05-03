import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.*;


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
	
	private boolean movingLeft;
	private boolean movingRight;
	private boolean movingUp;


	public Game()
	{
		super("Fysikspel");
		this.nodes = new ArrayList<Node>();
		this.movingUp = false;
        this.movingLeft = false;
        this.movingRight = false;
        
        /**
         * creating a node for as an example.
         * this are the types im putting in to "new Rectangle(Position x, Position y, Width, Height, Mass)" in the code
         */
        Node n = new Rectangle(500,400,50,50,50);
        n.applyVelocity(new Velocity(150, 150));
        this.nodes.add(n);
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
                update(currentTime - lastUpdate);
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
		 	Node n = this.nodes.get(0);
	        double dx = 0;
	        double dy = 0;
	        // Calculate where the node should in x and y axis on the next step.
	        double tmpX = n.getVelocity().getX() * updateTime * 0.001;
	        double tmpY = n.getVelocity().getY() * updateTime * 0.001;
	        // if the arrow left and arrow right are presses at the same time this wont go through otherwise it will
	        if ((movingLeft || movingRight) && (!movingLeft || !movingRight)) {
	        	// if moving left -tmpX otherwise tmpX
	            dx = (movingLeft ? -tmpX : tmpX); 
	        }
	        if (movingUp) {
	            dy = -tmpY;
	        }
	        // set the new position to the node
	        n.translatePosition((int) dx, (int) dy);
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
