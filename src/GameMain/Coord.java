package GameMain;

public class Coord {	
	private Integer x;
	private Integer y;
	//Coordinates of the current position: 
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
			case UP: return new Coord(this.getX() + 1, this.getY());
			case DOWN: return new Coord(this.getX() - 1, this.getY());
			case LEFT: return new Coord(this.getX(), this.getY() - 1);
			case RIGHT: return new Coord(this.getX(), this.getY() + 1);
			case CENTRE: return new Coord(this.getX(), this.getY());
		}
		System.out.println("Coord.add(): unknown direction");
		return new Coord(0, 0);
	}
	
	/**
	 * Get the x direction from this to c2
	 * @param c2 The point to compare to
	 * @return LEFT if c2 is to the left of this, RIGHT if c2 is to the right of this. Centre else.
	 */
	public Direction minusX(Coord c2) {
		Integer difference = this.getX() - c2.getX();
		if (difference.equals(0)) {
			return Direction.CENTRE;
		} else if (difference < 0) {
			return Direction.UP;
		} else {
			return Direction.DOWN;
		}
	}
	
	/**
	 * Get the y direction from this to c2
	 * @param c2 The point to compare to
	 * @return UP if c2 is to the up of this, DOWN if c2 is to the down of this. Centre else.
	 */
	public Direction minusY(Coord c2) {
		Integer difference = this.getY() - c2.getY();
		if (difference.equals(0)) {
			return Direction.CENTRE;
		} else if (difference < 0) {
			return Direction.RIGHT;
		} else {
			return Direction.LEFT;
		}
	}

	@Override
	public String toString() {
		return "Coord [x=" + x + ", y=" + y + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof Coord) {
			Coord objCoord = (Coord)obj;
			if (objCoord.getX().equals(this.getX()) && objCoord.getY().equals(this.getY())) {
				return true;
			}
		}
		return false;	
	}
	
	
}
