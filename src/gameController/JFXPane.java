package gameController;
import java.util.Observable;
import java.util.Observer;

import gameModel.entity.VisType;
import gameModel.tile.Tile;
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
		this.image = new ImageView(new Image(getClass().getResourceAsStream("/tile.png"),20,20,true,true));			
		pane = new Pane(image);
		pane.setStyle("-fx-border-color: black;-fx-border-width: .5;-fx-border-color:#E8E8E8");

	}
	//Passes back the pane to javafx: 
	public Pane getPane() {
		return pane;
	}
	//Listens for mouse clicks: 
	public void detectMouseClicks() {
		pane.setOnMousePressed(e-> {
			System.out.println("Clicked on "+ row + " "+column);
			System.out.println("SEt to true");
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
		System.out.println("Set to false");
	}
	
	public void updateVisType(VisType img) {
		System.out.println(img.toString());
		/*this.image = new ImageView(new Image(getClass().getResourceAsStream(img.toString()),20,20,true,true));
		this.pane = new Pane(image);
		this.pane.setStyle("-fx-border-color: black;-fx-border-width: .5;-fx-border-color:#E8E8E8");
		*/
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}