package gameApplication;
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
		this.image = new ImageView(new Image(getClass().getResourceAsStream("/tile.png"),20,20,true,true));			
		pane = new Pane(image);
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

	@Override
	public void update(Observable o, Object arg) {
		//changes the images: 
		//not sure what observable o does and object arg does?? 
			
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

}
