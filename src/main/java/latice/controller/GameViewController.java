package latice.controller;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import latice.model.Game;
import latice.model.MainPool;
import latice.model.Pool;
import latice.model.Rack;
import latice.model.Tile;
import latice.model.Player;

public class GameViewController implements EventHandler<MouseEvent>{
	
	@FXML
	private ImageView idRackPlayerTile1;
	
	@FXML
	private ImageView idRackPlayerTile2;
	
	@FXML
	private ImageView idRackPlayerTile3;
	
	@FXML
	private ImageView idRackPlayerTile4;
	
	@FXML
	private ImageView idRackPlayerTile5;
	
	@FXML
	private Button idBtnPasser;
	
	@FXML
	private Button idBtnChanger;
	
	@FXML
	private Text idTxtPile1;
	
	@FXML
	private Text idTxtPile2;
	
	private Game game = new Game();
	private MainPool mainPool = game.mainPool();

	private Pool pools = new Pool();
	private ArrayList<Tile> poolPlayer1 = pools.tiles().get(0);
	private ArrayList<Tile> poolPlayer2 = pools.tiles().get(1);
	private boolean isJ2 = true;
	private Rack rackPlayer1 = new Rack(poolPlayer1);
	private Rack rackPlayer2 = new Rack(poolPlayer2);
	private String player1Name;
	private String player2Name;
	
	public void setPlayer1Name(String name) {
        this.player1Name = name;
    }

    public void setPlayer2Name(String name) {
        this.player2Name = name;
    }
	
	@Override
	public void handle(MouseEvent event) {
		//TODO
    }

    @FXML
    void initialize() { 	
    	Player player1 = new Player(poolPlayer1, rackPlayer1, player1Name);
    	Player player2 = new Player(poolPlayer2, rackPlayer2, player2Name);
    	
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
    	
        idRackPlayerTile1.setImage(imageTile1j1);
        idRackPlayerTile2.setImage(imageTile2j1);
        idRackPlayerTile3.setImage(imageTile3j1);
        idRackPlayerTile4.setImage(imageTile4j1);
        idRackPlayerTile5.setImage(imageTile5j1);
        
        idBtnPasser.setOnAction(e -> { //Permet de changer entre le raack j1 et j2
        	if (isJ2) {
        		idRackPlayerTile1.setImage(imageTile1j2);
	            idRackPlayerTile2.setImage(imageTile2j2);
	            idRackPlayerTile3.setImage(imageTile3j2);
	            idRackPlayerTile4.setImage(imageTile4j2);
	            idRackPlayerTile5.setImage(imageTile5j2);
        	}
        	else {
                idRackPlayerTile1.setImage(imageTile1j1);
                idRackPlayerTile2.setImage(imageTile2j1);
                idRackPlayerTile3.setImage(imageTile3j1);
                idRackPlayerTile4.setImage(imageTile4j1);
                idRackPlayerTile5.setImage(imageTile5j1);
        	}
        	isJ2 = !isJ2;
        });
        
        idBtnChanger.setOnAction(e -> {
        	if (isJ2) {
        		rackPlayer1 = new Rack(poolPlayer1);
        	}
        	else {
        		rackPlayer2 = new Rack(poolPlayer2);
        	}
        });
        
        this.idTxtPile1.setText(idTxtPile1.getText() + player1Name);
        this.idTxtPile2.setText(idTxtPile2.getText() + player2Name);
    }
}