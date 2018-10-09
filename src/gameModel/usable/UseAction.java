package gameModel.usable;

public enum UseAction {
	ARROW, SWORD, BOMB, NONE, TREASURE, INVINCIBILITY, HOVER, KEY;

	public static String toString(UseAction action) {
		switch(action) {
		case ARROW: return "Arrow";
		case SWORD: return "Sword";
		case BOMB: return "Bomb";
		case TREASURE: return "Treasure";
		case INVINCIBILITY: return "Invincibility";
		case HOVER: return "Hover";
		case KEY: return "Key";
		default: return "Unknown";
		}
	}
}
