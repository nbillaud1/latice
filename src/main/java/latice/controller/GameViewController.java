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
	
	private Player player1 = new Player(poolPlayer1, rackPlayer1, player1Name);
	private Player player2 = new Player(poolPlayer2, rackPlayer2, player2Name);
	
	private Image imageTile1p1 = new Image(getClass().getResource(rackPlayer1.tiles().get(0).urlImg()).toExternalForm());
    private Image imageTile2p1 = new Image(getClass().getResource(rackPlayer1.tiles().get(1).urlImg()).toExternalForm());
    private Image imageTile3p1 = new Image(getClass().getResource(rackPlayer1.tiles().get(2).urlImg()).toExternalForm());
    private Image imageTile4p1 = new Image(getClass().getResource(rackPlayer1.tiles().get(3).urlImg()).toExternalForm());
    private Image imageTile5p1 = new Image(getClass().getResource(rackPlayer1.tiles().get(4).urlImg()).toExternalForm());

    private Image imageTile1p2 = new Image(getClass().getResource(rackPlayer2.tiles().get(0).urlImg()).toExternalForm());
    private Image imageTile2p2 = new Image(getClass().getResource(rackPlayer2.tiles().get(1).urlImg()).toExternalForm());
    private Image imageTile3p2 = new Image(getClass().getResource(rackPlayer2.tiles().get(2).urlImg()).toExternalForm());
    private Image imageTile4p2 = new Image(getClass().getResource(rackPlayer2.tiles().get(3).urlImg()).toExternalForm());
    private Image imageTile5p2 = new Image(getClass().getResource(rackPlayer2.tiles().get(4).urlImg()).toExternalForm());
    
    @FXML
    void initialize() { 	

        idRackPlayerTile1.setImage(imageTile1p1);
        idRackPlayerTile2.setImage(imageTile2p1);
        idRackPlayerTile3.setImage(imageTile3p1);
        idRackPlayerTile4.setImage(imageTile4p1);
        idRackPlayerTile5.setImage(imageTile5p1);
        
      //Permet de changer entre le rack p1 et p2
        idBtnPasser.setOnAction(e -> { 
        	changeTiles(imageTile1p1, imageTile2p1, imageTile3p1, imageTile4p1, imageTile5p1, imageTile1p2,
					imageTile2p2, imageTile3p2, imageTile4p2, imageTile5p2);
        });
        
        //Permet de changer son rack et passer son tour
        idBtnChanger.setOnAction(e -> {
        	if (isJ2) {
        		player2.switchRack();
        		rackPlayer2 = player2.Rack();
        		System.out.println(rackPlayer2.tiles().get(0).afficher());
        	}
        	else {
        		player1.switchRack();
        		rackPlayer1 = player1.Rack();
        	}
        	changeTiles(imageTile1p1, imageTile2p1, imageTile3p1, imageTile4p1, imageTile5p1, imageTile1p2,
					imageTile2p2, imageTile3p2, imageTile4p2, imageTile5p2);
        });
        
        this.idTxtPile1.setText(idTxtPile1.getText() + player1Name);
        this.idTxtPile2.setText(idTxtPile2.getText() + player2Name);
    }

	private void changeTiles(Image imageTile1p1, Image imageTile2p1, Image imageTile3p1, Image imageTile4p1,
			Image imageTile5p1, Image imageTile1p2, Image imageTile2p2, Image imageTile3p2, Image imageTile4p2,
			Image imageTile5p2) {
		if (isJ2) {
			imageTile1p1 = new Image(getClass().getResource(rackPlayer1.tiles().get(0).urlImg()).toExternalForm());
		    imageTile2p1 = new Image(getClass().getResource(rackPlayer1.tiles().get(1).urlImg()).toExternalForm());
		    imageTile3p1 = new Image(getClass().getResource(rackPlayer1.tiles().get(2).urlImg()).toExternalForm());
		    imageTile4p1 = new Image(getClass().getResource(rackPlayer1.tiles().get(3).urlImg()).toExternalForm());
		    imageTile5p1 = new Image(getClass().getResource(rackPlayer1.tiles().get(4).urlImg()).toExternalForm());

			idRackPlayerTile1.setImage(imageTile1p1);
		    idRackPlayerTile2.setImage(imageTile2p1);
		    idRackPlayerTile3.setImage(imageTile3p1);
		    idRackPlayerTile4.setImage(imageTile4p1);
		    idRackPlayerTile5.setImage(imageTile5p1);
		    player2.pass();
		}
		else {
			imageTile1p2 = new Image(getClass().getResource(rackPlayer2.tiles().get(0).urlImg()).toExternalForm());
		    imageTile2p2 = new Image(getClass().getResource(rackPlayer2.tiles().get(1).urlImg()).toExternalForm());
		    imageTile3p2 = new Image(getClass().getResource(rackPlayer2.tiles().get(2).urlImg()).toExternalForm());
		    imageTile4p2 = new Image(getClass().getResource(rackPlayer2.tiles().get(3).urlImg()).toExternalForm());
		    imageTile5p2 = new Image(getClass().getResource(rackPlayer2.tiles().get(4).urlImg()).toExternalForm());
		    
		    idRackPlayerTile1.setImage(imageTile1p2);
		    idRackPlayerTile2.setImage(imageTile2p2);
		    idRackPlayerTile3.setImage(imageTile3p2);
		    idRackPlayerTile4.setImage(imageTile4p2);
		    idRackPlayerTile5.setImage(imageTile5p2);
		    player1.pass();
		}
		isJ2 = !isJ2;
	}
}