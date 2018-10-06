package gameModel;
import java.util.ArrayList;
import java.util.List;

public class AStarNode
{
	
	private Coord coord;
	private AStarNode parent;
	private EntityMover entityMover;
	
	int gCost;  // cost from start to node
	int hCost; // cost to goal from node by default (heuristic)
	
	public AStarNode(Coord coord, EntityMover entityMover) {
		this.coord = coord;
		this.entityMover = entityMover;
	}
	
	public int getfCost() {
		return gCost + hCost;
	}
	
	public void setParent(AStarNode node) {
		this.parent = node;		
	}

	public AStarNode getParent() {
		return this.parent;
	}
	public void setgCost(int cost) {
		this.gCost = cost;
	}
	
	public int getgCost() {
		return this.gCost;
	}
	
	public void sethCost(Coord dest) {
		Coord curCoord = this.getCoord();
		int diffX = dest.getX() - curCoord.getX();
		int diffY = dest.getY() - curCoord.getY();
		
		this.hCost = (Math.abs(diffX) + Math.abs(diffY));
	}
	
	public int gethCost() {
		return this.hCost;
	}
	
	public Coord getCoord() {
		return this.coord;
	}
	
	public Coord getCoord(Direction dir) {
		return this.getCoord().add(dir);
	}
	
	/**
	 * get abs value of cost to next
	 * @param next
	 * @return
	 */
	public int compareTo(AStarNode next) {
		    return Math.abs(this.getgCost() - next.getgCost());
	}
 
	@Override
	public String toString() {
		return this.coord.toString();
	}
	/**
	Gets the neighbours by checking adjacent squares in the map and creating new nodes
	for tiles that are adjacent and legal to traverse. No need to make directed graph before
	making AStarSearch.
	*/
	public List<AStarNode> getNeighbors() {
		List<AStarNode> neighbours = new ArrayList<AStarNode>();
		
		// Calls getCoord on the map position indicated and creates node with that coord
		//make new nodes for tiles where MOVE is possible
		//TODO: There is a possible bug here -> dir includes CENTRE
		for (Direction dir : Direction.values()) {
			Coord curCoord = this.getCoord(dir);
			if (this.entityMover.traversable(curCoord)) {
				AStarNode newNeighbour = new AStarNode(curCoord, this.entityMover);
				newNeighbour.setgCost(this.gCost+1);
				neighbours.add(newNeighbour);
			}
		}
		return neighbours;
	}

}  