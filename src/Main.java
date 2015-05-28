import java.util.ArrayList;
import java.util.List;

/**
 * Skapar en ny instans av spelet och talar om vilka banor som ska vara med.
 * Lägger till bakgrundsmusiken i spelet.
 * @author BG5
 *
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Map mone = new Map1();
		Map moneb = new Map1b();
		Map mtwo = new Map2();
		Map mfin = new MapFinish();
		List<Map> maps = new ArrayList<Map>();
		maps.add(mone);
		maps.add(moneb);
		maps.add(mtwo);
		maps.add(mfin);
		Sound s2 = new Sound("Star_Wars.wav", true);
		s2.setVolume(-7f);
		s2.play();
		for(Map m : maps)
		{
			Game g = new Game();
			g.loadMap(m);
			g.runMap();
			m.startUp();
			while(!m.isDone())
			{
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Sound s3 = new Sound("smb_stage_clear.wav", false);
			s3.play();
			g.quit();
			
		}
	}
	
	public static void doNothing()
	{
		
	}
}