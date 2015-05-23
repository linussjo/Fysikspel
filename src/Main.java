import java.util.ArrayList;
import java.util.List;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Map mone = new Map1();
		Map moneb = new Map1b();
		Map mtwo = new Map2();
		List<Map> maps = new ArrayList<Map>();
		maps.add(mone);
		maps.add(moneb);
		maps.add(mtwo);
		Sound s1 = new Sound("sm64_mario_thank_you.wav", false);
		s1.play();
		Sound s2 = new Sound("Star_Wars.wav", true);
		s2.setVolume(-12f);
		s2.play();
		for(Map m : maps)
		{
			Game g = new Game();
			g.loadMap(m);
			g.runMap();
			
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