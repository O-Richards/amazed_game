package GameMain;

public interface Collidable {
	//return true if the movement is blocked, false else.
	public Collision collide(MobileEntity hitter);
}
