package gameModel;

public class EntityPlacementException extends Exception {
	//Forced to add this to make the java gods happy
	private static final long serialVersionUID = 8634948987228608288L;

	public EntityPlacementException(String msg) {
		super(msg);
	}
}