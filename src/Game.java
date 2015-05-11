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
	
	private double lastUpdate;
	private Map map;
	private Component component;
	private Timer timer;
	
	public Game()
	{
		super("Fysikspel");

	}
	
	public void quit()
	{
		this.dispose();
		System.exit(0);
	}
	
	public void loadMap(Map m)
	{
		this.map = m;
		this.component = new Component(m);
		
		this.setLayout(new BorderLayout());
		this.add(component, BorderLayout.CENTER);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.pack();
		
		super.setVisible(true);
	}
	
	public void runMap()
	{
		if(this.timer != null)
			timer.stop();
		
		lastUpdate = System.currentTimeMillis();
		AbstractAction doOneStep = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double currentTime = System.currentTimeMillis();
				map.update((currentTime - lastUpdate)*0.001);
				lastUpdate = currentTime;
				component.repaint();
			}
		};
		int fps = 1000/60; // 60 fps 
		this.timer = new Timer(fps, doOneStep);
		timer.setCoalesce(true);
		timer.start();
	}
	
	
	
}
