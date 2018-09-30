package gameModel;
import java.util.ArrayList;
import java.util.List;

public class AStarNode
{
	
	public Coord coord;
	public AStarNode parent;
	int gCost;  // cost from start to node
	int hCost; // cost to goal from node by default (heuristic)
	
	public AStarNode(Coord coord) {
		this.coord = coord;
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
	public List<AStarNode> getNeighbors(EmptyTile[][] map, MobileEntity hitter) {
		int curX = this.getCoord().getX();
		int curY = this.getCoord().getY();
		List<AStarNode> neighbours = new ArrayList<AStarNode>();
		
		// I RECOGNISE THIS IS DISGUSTING AND SHOULD PROBABLY BE A FOREACH LOOP
		// SHOULD WORK THOUGH
		// Calls getCoord on the map position indicated and creates node with that coord
		//make new nodes for tiles where MOVE is possible
		if(map[curX+1][curY].collide(hitter, false)==Collision.MOVE&& 
				!(map[curX+1][curY].collide(hitter, false)==Collision.NOMOVE)) {
			AStarNode newNeighbour = new AStarNode(map[curX+1][curY].getCoord());
			newNeighbour.setgCost(this.gCost+1);
			neighbours.add(newNeighbour);
		}
		if(map[curX-1][curY].collide(hitter, false)==Collision.MOVE && 
				!(map[curX-1][curY].collide(hitter, false)==Collision.NOMOVE )) {
			AStarNode newNeighbour = new AStarNode(map[curX-1][curY].getCoord());
			newNeighbour.setgCost(this.gCost+1);
			neighbours.add(newNeighbour);
		}
		if(map[curX][curY+1].collide(hitter, false)==Collision.MOVE&& 
				!(map[curX][curY+1].collide(hitter, false)==Collision.NOMOVE)) {
			AStarNode newNeighbour = new AStarNode(map[curX][curY+1].getCoord());
			newNeighbour.setgCost(this.gCost+1);
			neighbours.add(newNeighbour);
		}
		if(map[curX][curY-1].collide(hitter, false)==Collision.MOVE&& 
				!(map[curX][curY-1].collide(hitter, false)==Collision.NOMOVE )) {
			AStarNode newNeighbour = new AStarNode(map[curX][curY-1].getCoord());
			newNeighbour.setgCost(this.gCost+1);
			neighbours.add(newNeighbour);
		}
		return neighbours;
	}

}  