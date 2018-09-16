package GameMain;

import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;


/**
 * Search over a directed graph using A* search by using comparators
 * @author aMAZEd
 * pass in hitter from which to search
 * pass in map to generate search nodes
 *
 */
public class AStarSearch 
{
	
	private Tile[][] map;
	private MobileEntity hitter;
	private Coord goal;
	
	public AStarSearch(Tile[][] map, MobileEntity hitter, Coord goal) {
		this.map = map;
		this.hitter = hitter;
		this.goal = goal;
	}
	
	/**
	 * construct path and return it
	 * @param node is goal node to create path from
	 * @return
	 */
	protected List<AStarNode> makePath(AStarNode node) {
		LinkedList<AStarNode> path = new LinkedList<AStarNode>();
		while(node.getParent() != null) {
			path.addFirst(node);
			node = node.getParent();
		}
		
		return path;
	}
	
	public List<AStarNode> findPath() {
		
		Comparator<AStarNode> nodeComparator = new Comparator<AStarNode>() {
			@Override
			public int compare(AStarNode n1, AStarNode n2) {
				return n1.getfCost() - n2.getfCost();
			}
		};
		
		// lists used in search loop
		PriorityQueue<AStarNode> open = new PriorityQueue<AStarNode>(nodeComparator);
		List<AStarNode> closed = new ArrayList<>();
		
		// init priority queue with origin node
		AStarNode origin = new AStarNode(hitter.getCoord());
		origin.setgCost(0);
		open.add(origin);
		
		AStarNode current = null;
		
		while(!open.isEmpty()) {
			current = open.remove();
			
			//if we have arrived at goal
			if (current.getCoord().getX() == this.goal.getX() &&
					current.getCoord().getY() == this.goal.getY()) {
				return makePath(current);
			}
			
			//if we have not expanded current node
			if (!closed.contains(current)) {			
				closed.add(current);
				List<AStarNode> neighbours = current.getNeighbors(this.map,this.hitter);	
				
				for(AStarNode neighbour: neighbours) {
					//unvisitied nodes only
					if(!closed.contains(neighbour)) {
						int gcost = current.getgCost() + current.compareTo(neighbour); //cost to neighbour from origin
						
						// check if shorter path to neighbour is found
						if ((!open.contains(neighbour) && !closed.contains(neighbour)) || gcost < neighbour.getgCost()) {
							neighbour.setParent(current);
							neighbour.setgCost(gcost);
							neighbour.sethCost(goal);
						}
						
					}
					open.add(neighbour);
					// put neighbour node into open nodes					
					
				}				
			}
		}
		
		// no path exists
		return null;
	}

}