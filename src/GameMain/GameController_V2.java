package GameMain;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import java.io.IOException;

public class GameController extends Application{
	private boolean keyPressed; 
	private boolean action; 
	private static char charPressed = '\0'; 
    
	public static void main(String[] args) throws IOException, InterruptedException {
        GameSystem gs = new GameSystem();


        //Setup template maze
        gs.placeEntity(new BoulderMobileEntity(null), new Coord(2, 2));
        System.out.println("Use W A S D keys to move me around");  
        Thread frame = new Thread(new MultiThreading()); 
        frame.start();

        while(true) {
            //char c = (char)System.in.read();<- read waits for an enter
            //if (c == 0) break;
        	if(charPressed == 'E') {
        		break;
        	}
            //Reset movement to nothing
            if(charPressed != '\0') {
                gs.movePlayer(gs.charToDirection(charPressed));
                System.out.println(gs.levelString());
                System.out.flush();
                charPressed = '\0';
                gs.tick();
           
            }
         
            Thread.sleep(500);

        }
        System.out.println("EXIT");
    }
    //Implements a window:  
	@Override
	public void start(Stage temp) throws Exception {
		// TODO Auto-generated method stub
        Group temporary = new Group();
		Scene scene = new Scene(temporary, 200, 200,Color.WHITE);
		//Handling keys: 
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
    			keyPressed = true;
    			KeyCode pressedKeyNumber = event.getCode();
    			if(pressedKeyNumber == KeyCode.ESCAPE) {
    				charPressed = 'E';
    			}
    			//Gets the direction(movement): 
    			if (pressedKeyNumber == KeyCode.W) {
					charPressed = 'w';
				}else if(pressedKeyNumber == KeyCode.A) {
					charPressed = 'a';
				}else if(pressedKeyNumber == KeyCode.S) {
					charPressed = 's';
				}else if(pressedKeyNumber == KeyCode.D) {
					charPressed = 'd';
				}
    			
    			//direction to shoot/hit: 
    			if (pressedKeyNumber == KeyCode.UP) {
					charPressed = '^';
				}else if(pressedKeyNumber == KeyCode.DOWN) {
					charPressed = 'v';
				}else if(pressedKeyNumber == KeyCode.LEFT) {
					charPressed = '<';
				}else if(pressedKeyNumber == KeyCode.RIGHT) {
					charPressed = '>';
				}
    			//A key is already pressed down: 
    			if(keyPressed == true) {
    				//F to use sword & shift to shoot
    				if(pressedKeyNumber == KeyCode.F || pressedKeyNumber == KeyCode.SHIFT) {
    					if (charPressed == '^') {
    						action = true; 
    					}else if(charPressed == 'v') {
    						action = true; 
    					}else if(charPressed == '<') {
    						action = true; 
    					}else if(charPressed == '>') {
    						action = true; 
    					}
    				}
    			
    			}
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            	keyPressed = false; 
            }
        });
        temp.setScene(scene);
        temp.requestFocus();
		temp.show();
	}

	
}
//Temp implementation on a different thread:  
//Would be removed when we finally implement it: 
class MultiThreading implements Runnable 
{ 
    public void run() 
    { 
        try
        { 
            //Launches a window: 
           Application.launch(GameController.class);
        } 
        catch (Exception e) 
        { 
            // Throwing an exception 
            System.out.println ("Error occured"); 
        } 
    } 
} 
