package latice.controller;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import latice.model.Game;
import latice.model.MainPool;
import latice.model.Pool;
import latice.model.Rack;
import latice.model.Tile;

public class GameViewController implements EventHandler<MouseEvent>{
	
	@FXML
	private ImageView idRackPlayer1Tile1;
	
	@FXML
	private ImageView idRackPlayer1Tile2;
	
	@FXML
	private ImageView idRackPlayer1Tile3;
	
	@FXML
	private ImageView idRackPlayer1Tile4;
	
	@FXML
	private ImageView idRackPlayer1Tile5;
	
	@FXML
	private Button idBtnPasser;
	
	@Override
	public void handle(MouseEvent event) {
		//TODO
    }
	
	private boolean isJ2 = true;

    @FXML
    void initialize() {
    	Game game = new Game();
    	MainPool mainPool = game.mainPool();
    	Pool pools = new Pool();
    	ArrayList<Tile> poolPlayer1 = pools.tiles().get(0);
    	ArrayList<Tile> poolPlayer2 = pools.tiles().get(1);
    	Rack rackPlayer1 = new Rack(poolPlayer1);
    	Rack rackPlayer2 = new Rack(poolPlayer2);
    	//Player player1 = new Player(poolPlayer1, rackPlayer1);
    	//Player player2 = new Player(poolPlayer2, rackPlayer2);
    	
    	
    	
    	Image imageTile1j1 = new Image(getClass().getResource(rackPlayer1.tiles().get(0).urlImg()).toExternalForm());
        Image imageTile2j1 = new Image(getClass().getResource(rackPlayer1.tiles().get(1).urlImg()).toExternalForm());
        Image imageTile3j1 = new Image(getClass().getResource(rackPlayer1.tiles().get(2).urlImg()).toExternalForm());
        Image imageTile4j1 = new Image(getClass().getResource(rackPlayer1.tiles().get(3).urlImg()).toExternalForm());
        Image imageTile5j1 = new Image(getClass().getResource(rackPlayer1.tiles().get(4).urlImg()).toExternalForm());

        Image imageTile1j2 = new Image(getClass().getResource(rackPlayer2.tiles().get(0).urlImg()).toExternalForm());
        Image imageTile2j2 = new Image(getClass().getResource(rackPlayer2.tiles().get(1).urlImg()).toExternalForm());
        Image imageTile3j2 = new Image(getClass().getResource(rackPlayer2.tiles().get(2).urlImg()).toExternalForm());
        Image imageTile4j2 = new Image(getClass().getResource(rackPlayer2.tiles().get(3).urlImg()).toExternalForm());
        Image imageTile5j2 = new Image(getClass().getResource(rackPlayer2.tiles().get(4).urlImg()).toExternalForm());
    	
        idRackPlayer1Tile1.setImage(imageTile1j1);
        idRackPlayer1Tile2.setImage(imageTile2j1);
        idRackPlayer1Tile3.setImage(imageTile3j1);
        idRackPlayer1Tile4.setImage(imageTile4j1);
        idRackPlayer1Tile5.setImage(imageTile5j1);
        
        idBtnPasser.setOnAction(e -> {
        	if (isJ2) {
        		idRackPlayer1Tile1.setImage(imageTile1j2);
	            idRackPlayer1Tile2.setImage(imageTile2j2);
	            idRackPlayer1Tile3.setImage(imageTile3j2);
	            idRackPlayer1Tile4.setImage(imageTile4j2);
	            idRackPlayer1Tile5.setImage(imageTile5j2);
        	}
        	else {
                idRackPlayer1Tile1.setImage(imageTile1j1);
                idRackPlayer1Tile2.setImage(imageTile2j1);
                idRackPlayer1Tile3.setImage(imageTile3j1);
                idRackPlayer1Tile4.setImage(imageTile4j1);
                idRackPlayer1Tile5.setImage(imageTile5j1);
        	}
        	isJ2 = !isJ2;
        });
    }	
}