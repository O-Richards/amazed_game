package MazeGame;

public interface Obstacle {
	//I'm not sure exactly what the functions needed here are
	//I have put returning int here
	//so you could indicate things like 1 == blocked, 2 == pickup
	//But this is definitely a poor design
	public int hit(Entity hitter);
}
