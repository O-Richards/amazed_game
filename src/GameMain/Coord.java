package GameMain;

public class Coord {
	private Integer x;
	private Integer y;
	
	Coord(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}
	
	public Integer getX() {
		return x;
	}
	
	public void setX(Integer x) {
		this.x = x;
	}
	
	public Integer getY() {
		return y;
	}
	
	public void setY(Integer y) {
		this.y = y;
	}
	
	/**
	 * @precondition dir must be UP, DOWN, LEFT or RIGHT
	 * @param dir The direction to move from the coord
	 * @return A new Coord which as an dir movement from the coord
	 */
	public Coord add(Direction dir) {
		switch(dir) {
		//Note, no need for breaks as we are always returning.
		//Also, note change of direction due to the orientation of the map
		case UP: return new Coord(this.getX() - 1, this.getY());
		case DOWN: return new Coord(this.getX() + 1, this.getY());
		case LEFT: return new Coord(this.getX(), this.getY() - 1);
		case RIGHT: return new Coord(this.getX(), this.getY() + 1);
		}
		System.out.println("Coord.add(): unknown direction");
		return new Coord(0, 0);
	}
	
}
