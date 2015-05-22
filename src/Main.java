import java.util.ArrayList;
import java.util.List;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map tm = new TestMap();
		Map mone = new Map1();
		Map mtwo = new Map2();
		List<Map> maps = new ArrayList<Map>();
		maps.add(mtwo);
		maps.add(mone);
		maps.add(tm);
		
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
			g.quit();
		}
	}
	
	public static void doNothing()
	{
		
	}
}