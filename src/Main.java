
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map tm = new TestMap();
		Map mone = new Map1();
		
		Game g = new Game();
		g.loadMap(mone);
		g.runMap();
	}
}