import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;


public class Game extends JFrame {
	
	private Set<Node> nodes;
	private double lastUpdate;
	
	boolean movingLeft;
	boolean movingRight;
	boolean movingUp;


	public Game()
	{
		super("Fysikspel");
		this.movingUp = false;
        this.movingLeft = false;
        this.movingRight = false;
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
	    	//System.out.println("bla");
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
