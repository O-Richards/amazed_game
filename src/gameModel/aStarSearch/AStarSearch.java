package gameModel.aStarSearch;

import java.util.PriorityQueue;
import java.util.Set;

import gameModel.Coord;
import gameModel.EntityMover;

import java.util.Comparator;
import java.util.HashSet;
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
	private EntityMover entityMover;
	private boolean flee;
	private Coord start;
	private Coord goal;
	private final int MAX_ITERATION_COUNTER	= 1000;
	
	public AStarSearch(Coord start, Coord goal, boolean flee, EntityMover entityMover) {
		this.start = start;
		this.goal = goal;
		this.flee = flee;
		this.entityMover = entityMover;
	}
	
	/**
	 * construct path and return it
	 * @param node is goal node to create path from
	 * @return
	 */
	protected List<Coord> makePath(AStarNode node) {
		LinkedList<Coord> path = new LinkedList<Coord>();
		while(node.getParent() != null) {
			path.addFirst(node.getCoord());
			node = node.getParent();
		}
		
		return path;
	}
	
	public Coord getNextCoord() {
		List<Coord> path = this.findPath();
		if (path == null || path.size() == 0) return this.start;
		return path.get(0);
	}
	
	public List<Coord> findPath() {
		
		Comparator<AStarNode> nodeComparator = new Comparator<AStarNode>() {
			@Override
			public int compare(AStarNode n1, AStarNode n2) {
				return n1.getfCost() - n2.getfCost();
			}
		};
		
		// lists used in search loop
		PriorityQueue<AStarNode> open = new PriorityQueue<AStarNode>(nodeComparator);
		Set<AStarNode> closed = new HashSet<>();
		
		// init priority queue with origin node
		AStarNode origin = new AStarNode(this.start, flee, this.entityMover);
		origin.setgCost(0);
		open.add(origin);
		
		AStarNode current = null;
		int iterationCounter = 0;
		while(!open.isEmpty() && iterationCounter < MAX_ITERATION_COUNTER) {
			iterationCounter++;
			current = open.remove();
			
			//if we have arrived at goal
			if (current.getCoord().getX() == this.goal.getX() &&
					current.getCoord().getY() == this.goal.getY()) {
				return makePath(current);
			}
			
			//if we have not expanded current node
			if (!closed.contains(current)) {			
				closed.add(current);
				List<AStarNode> neighbours = current.getNeighbors();	
				
				for(AStarNode neighbour: neighbours) {
					//unvisitied nodes only
					if (closed.contains(neighbour)) continue;
					
					int gcost = current.getgCost() + current.compareTo(neighbour); //cost to neighbour from origin
					
					// check if shorter path to neighbour is found
					if ((!open.contains(neighbour) && !closed.contains(neighbour)) || gcost < neighbour.getgCost()) {
						neighbour.setParent(current);
						neighbour.setgCost(gcost);
						neighbour.sethCost(goal);
					}
						
					if (!open.contains(neighbour)) open.add(neighbour);
					// put neighbour node into open nodes					
					
				}				
			}
		}
		
		// no path exists
		return null;
	}

}