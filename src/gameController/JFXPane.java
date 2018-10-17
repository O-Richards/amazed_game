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

	public JFXPane(int row, int column) {
		this.row = row; 
		this.column = column; 
		clicked = new SimpleBooleanProperty(false); 
		//Defaults to a set of tiles: 
		this.image = new ImageView(new Image(getClass().getResourceAsStream("/tile.png"),35,35,true,true));			
		pane = new Pane(this.image);
		System.out.println(pane);
		pane.autosize();
		pane.setStyle("-fx-border-color: black;-fx-border-width: .5;-fx-border-color:#E8E8E8");
	}
	//Passes back the pane: 
	public Pane getPane() {
		return pane;
	}
	//Listens for mouse clicks: 
	public void detectMouseClicks() {
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
	
	public void paneObserve(VisType arg) {
		
	}
	@Override
	public void update(Observable o, Object arg) {
		String img = visTypeToPath((VisType) arg); //cast object argument as what we need
		//removes the current image in the pane:
        pane.getChildren().remove(this.image);
        this.image = new ImageView(new Image(getClass().getResourceAsStream(img),35,35,true,true));
        //Replaces the image with a new image: 
        pane.getChildren().add(this.image);
        System.out.println("we placed an image with directly " + img);
        
	}
	public String visTypeToPath(VisType visType) {
		
		Map<VisType, String> spriteMap = new HashMap<>();
			spriteMap.put(VisType.PLAYER, "/player&tile.png");
			spriteMap.put(VisType.SWITCH,"/switch&tile.png");
			spriteMap.put(VisType.ARROW, "/arrow&tile.png");
			spriteMap.put(VisType.BOULDER, "/boulder&tile.png");
			spriteMap.put(VisType.BOMB, "/unlit&tile.png");
			spriteMap.put(VisType.HOVER_POTION, "/hover&tile.png");
			spriteMap.put(VisType.INVINCIBILITY_POTION, "/invincibility&tile.png");
			spriteMap.put(VisType.TREASURE, "/treasure&tile.png");
			spriteMap.put(VisType.HUNTER, "/hound.png");
			spriteMap.put(VisType.SWORD, "/sword&tile.png");
			spriteMap.put(VisType.EXIT, "/exit.png");
			spriteMap.put(VisType.PIT, "/pit&tile.png");
			spriteMap.put(VisType.WALL, "/wall.png");
			spriteMap.put(VisType.KEY, "/key&tile.png");
			spriteMap.put(VisType.DOOR, "/closed door.png");
			spriteMap.put(VisType.EMPTY_TILE, "/tile.png");
			if(spriteMap.get(visType) == null) {
				System.out.println("unable to place tile!!!");
			}
		return spriteMap.get(visType);

	}
}
