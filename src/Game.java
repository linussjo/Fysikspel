import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.*;


public class Game extends JFrame {
	
	private List<Node> nodes;
	/**
	 * Gets the node list
	 * @return Set, nodes
	 */
	public List<Node> getNodes() {
		return nodes;
	}
	private double lastUpdate;
	
	boolean movingLeft;
	boolean movingRight;
	boolean movingUp;


	public Game()
	{
		super("Fysikspel");
		this.nodes = new ArrayList<Node>();
		this.movingUp = false;
        this.movingLeft = false;
        this.movingRight = false;
        
        /**
         * creating a node for as an example.
         */
        Node n = new Rectangle(30,30,50,50,50);
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

        Timer timer = new Timer(17, doOneStep);
        timer.setCoalesce(true);
        timer.start();


        super.setVisible(true);
	}
	
	 public void update(double updateTime) {
		 	Node n = this.nodes.get(0);
	        double dx = 0;
	        double dy = 0;
	        double tmpX = n.getVelocity().getX() * updateTime * 0.001;
	        double tmpY = n.getVelocity().getY() * updateTime * 0.001;
	        if ((movingLeft || movingRight) && (!movingLeft || !movingRight)) {
	            dx = (movingLeft ? -tmpX : tmpX);
	        }
	        if (movingUp) {
	            dy = (movingUp ? -tmpY : tmpY);
	        }
	        n.getPosition().translate((int) dx, (int) dy);
	    }
	
	public boolean addNode(Node n)
	{
		return this.nodes.add(n);
	}
	
    public void move(Component.Direction dir, boolean val) {
        switch (dir){
            case UP:
                movingUp = val;
                System.out.println("Up");
                break;
            case LEFT:
                movingLeft = val;
                System.out.println("Left");
                break;
            case RIGHT:
                movingRight = val;
                System.out.println("Right");
                break;
        }
    }
}
