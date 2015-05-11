
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map tm = new TestMap();
		
		Game g = new Game();
		g.loadMap(tm);
		g.runMap();
	}

}
