package gameController;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import gameModel.entity.VisType;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class JFXPane implements Observer {
	
	private Pane pane;
	private ImageView image; 
	private int row; 
	private int column; 
	private SimpleBooleanProperty clicked; 
	public JFXPane() {
		this.image = new ImageView(new Image(getClass().getResourceAsStream("/inventoryBox.png"),30,30,true,true));	
		pane = new Pane(this.image);
		pane.setMinHeight(25);
		pane.setMinWidth(25);
        image.setFitWidth(pane.getWidth());
        image.setFitHeight(pane.getHeight());

	}

	public JFXPane(int row, int column) {
		this.row = row; 
		this.column = column; 
		clicked = new SimpleBooleanProperty(false); 
		//Defaults to a set of tiles: 
		this.image = new ImageView(new Image(getClass().getResourceAsStream("/tile.png"),30,30,true,true));	
		pane = new Pane(this.image);
		pane.setMinHeight(25);
		pane.setMinWidth(25);
		//pane.setMinWidth(image.getFitWidth());
		//pane.setMinHeight(image.getFitHeight());
        image.setFitWidth(pane.getWidth());
        image.setFitHeight(pane.getHeight());

	}
	//Passes back the pane: 
	public Pane getPane() {
		return pane;
	}
	//Listens for mouse clicks: 
	public void detectMouseClicks() {
		//make the pane have borders: 
		//pane.setStyle("-fx-border-color: black;-fx-border-width: .5;-fx-border-color:#E8E8E8");
		pane.setOnMousePressed(e-> {
			System.out.println("Clicked on "+ row + " "+column);
			clicked.set(true);
        });
		
	}

	public int getRow() {
		return this.row; 
	}
	public int getColumn() {
		return this.column; 
	}
	public BooleanProperty clickedProperty() {
		return clicked; 
	}
	public void resetClicked() {
		clicked.set(false);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		String img = visTypeToPath((VisType) arg); //cast object argument as what we need
		//removes the current image in the pane:
        pane.getChildren().remove(this.image);
        this.image = new ImageView(new Image(getClass().getResourceAsStream(img),30,30,true,true));
        //Replaces the image with a new image: 
        pane.getChildren().add(this.image);  
        image.setFitWidth(pane.getWidth());
        image.setFitHeight(pane.getHeight());

	}


	public String visTypeToPath(VisType visType) {
		Map<VisType, String> spriteMap = new HashMap<>();
			spriteMap.put(VisType.PLAYER, "/player&tile.png");
			spriteMap.put(VisType.SWITCH,"/switch&tile.png");
			spriteMap.put(VisType.ARROW, "/arrow&tile.png");
			spriteMap.put(VisType.BOULDER, "/boulder&tile.png");
			spriteMap.put(VisType.BOMB, "/unlit&tile.png");
			spriteMap.put(VisType.LIT_BOMB, "/explosion&tile.png");
			spriteMap.put(VisType.HOVER_POTION, "/hover&tile.png");
			spriteMap.put(VisType.INVINCIBILITY_POTION, "/invincibility&tile.png");
			spriteMap.put(VisType.TREASURE, "/treasure&tile.png");
			spriteMap.put(VisType.COWARD, "/coward&tile.png");
			spriteMap.put(VisType.STRATEGIST, "/strategist&tile.png");
			spriteMap.put(VisType.HOUND, "/hound&tile.png");
			spriteMap.put(VisType.HUNTER, "/hunter&tile.png");
			spriteMap.put(VisType.SWORD, "/sword&tile.png");
			spriteMap.put(VisType.EXIT, "/exit.png");
			spriteMap.put(VisType.PIT, "/pit&tile.png");
			spriteMap.put(VisType.WALL, "/wall.png");
			spriteMap.put(VisType.KEY, "/key&tile.png");
			spriteMap.put(VisType.DOOR, "/closed door.png");
			spriteMap.put(VisType.OPENED_DOOR, "/opened door.png");
			spriteMap.put(VisType.EMPTY_TILE, "/tile.png");
			spriteMap.put(VisType.INVENTORY, "/inventoryBox.png");
			spriteMap.put(VisType.INVENTORY_ARROW, "/arrow&box.png");
			spriteMap.put(VisType.INVENTORY_BOMB, "/bomb&box.png");
			spriteMap.put(VisType.INVENTORY_TREASURE, "/treasure&box.png");
			spriteMap.put(VisType.INVENTORY_SWORD, "/sword&box.png");
			spriteMap.put(VisType.INVENTORY_KEY, "/key&box.png");
			if(spriteMap.get(visType) == null) {
				System.out.println("unable to place tile!!!");
			}
		return spriteMap.get(visType);
	}

}
