import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;


public class Component extends JComponent {
	private Game game;
	private BufferedImage player;
	
	public static final int HEIGHT = 720;
	public static final int WIDTH = 1280;

	public Component(Game game) {
        this.game = game;
        setInput();
        
        try {
            player = ImageIO.read(new File(FileSystems.getDefault().getPath(
                    "data", "stickman.jpg").toUri()));
        } catch (IOException e) {
            System.out.println("Image not found");
        }
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
        //g2.drawImage(player, 200, 500, 120, 250, null);
        for(Node n : this.game.getNodes())
        {
        	n.draw(g2);
        }
    }
    public enum Direction {
        UP, LEFT, RIGHT
    }
    
	private void setInput() {
		// TODO Auto-generated method stub
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
