import java.util.HashSet;
import java.util.Set;


public class Game {
	
	private Set<Node> nodes;
	private Physics physics;
	
	public Game()
	{
		this.nodes = new HashSet<Node>();
		this.physics = new Physics(9.82);
	}
	
	public boolean addNode(Node n)
	{
		return this.nodes.add(n);
	}
}
