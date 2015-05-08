import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;


public class Component extends JComponent {
	private Game game;
	
	public static final int HEIGHT = 720;
	public static final int WIDTH = 1280;
	private Font font;

	public Component(Game game) {
        this.game = game;
        this.font = new Font("Helvetica", Font.PLAIN, 12);
        setInput();
     }
	
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
	
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, WIDTH, HEIGHT);
        /**
         * Draw all the Node from game objects list Nodes
         */
        for(Node n : this.game.getNodes())
        {
        	n.draw(g2);
        }
        int x = 150;
        
        g2.setColor(Color.BLACK);
    	g2.setFont(this.font);
    	g2.drawString("Inventory:", 10, this.HEIGHT - 92);
        
        Player player = ((Player)this.game.getNodes().get(0));
        for(Item i : player.getItemContainer())
        {
        	if(player.getActiveItem() == i)
        	{
        		g2.setColor(Color.YELLOW);
        		g2.fillRect(x - 15, this.HEIGHT - 50 - i.getHeight(), i.getWidth() + 30, i.getHeight() + 30);
        	}
        	g2.drawImage(i.getImage(), x, this.HEIGHT - 35 - i.getHeight(), i.getWidth(), i.getHeight(), null);
        	g2.setColor(Color.BLACK);
        	g2.setFont(this.font);
        	g2.drawString(i.getName(), x - 25, this.HEIGHT - 57 - i.getHeight());
        	x += 150;
        }
    }
    public enum Direction {
        UP, LEFT, RIGHT
    }
    
	private void setInput() {
		
		getInputMap().put(KeyStroke.getKeyStroke("1"), "1");
        getActionMap().put("1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayer().setActiveItem(0);
            }
        });
        
        getInputMap().put(KeyStroke.getKeyStroke("2"), "2");
        getActionMap().put("2", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayer().setActiveItem(1);
            }
        });
        
        getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "SPACE");
        getActionMap().put("SPACE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	if(game.getPlayer().getActiveItem() instanceof Bow && !game.getPlayer().shotArrow())
            	{
            		game.getPlayer().setShotArrow(true);
            	}
            }
            	
        });
        
        
	    getInputMap().put(KeyStroke.getKeyStroke("UP"), "UP");
        getActionMap().put("UP", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.move(Direction.UP, true);
            }
        });
        getInputMap().put(KeyStroke.getKeyStroke("released UP"), "no UP");
        getActionMap().put("no UP", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.move(Direction.UP, false);
            }
        });
        getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "LEFT");
        getActionMap().put("LEFT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.move(Direction.LEFT, true);
            }
        });
        getInputMap().put(KeyStroke.getKeyStroke("released LEFT"), "no LEFT");
        getActionMap().put("no LEFT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.move(Direction.LEFT, false);
            }
        });
        getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "RIGHT");
        getActionMap().put("RIGHT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.move(Direction.RIGHT, true);
            }
        });
        getInputMap().put(KeyStroke.getKeyStroke("released RIGHT"), "no RIGHT");
        getActionMap().put("no RIGHT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.move(Direction.RIGHT, false);
            }
        });
	}
}
