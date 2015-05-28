import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;

/**
 * Talar om vad som h�nder n�r en knapp aktiveras.
 * @author BG5
 *
 */
public class Button extends Rectangle {

	public Button(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.setColliderNumber(Collision.BUTTON);
	}
	
	/**
	 * Aktiverar actionen n�r knappen aktiveras.
	 */
	@Override
	public void collide(Rectangle r, double updateTime) {
		if(r instanceof Player || r instanceof Arrow)
		{
			this.notifyListerner();
			setCollidable(false);
			AbstractAction doOneStep = new AbstractAction() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				setCollidable(true);
    			}
    		};
    		
    		javax.swing.Timer timer = new javax.swing.Timer(1000, doOneStep);
    		timer.setRepeats(false);
    		timer.start();
		}
	}

}
