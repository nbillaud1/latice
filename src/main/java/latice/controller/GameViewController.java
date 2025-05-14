package latice.controller;

import java.util.ArrayList;
import java.util.Random;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import latice.model.Game;
import latice.model.MainPool;
import latice.model.Pool;
import latice.model.Rack;
import latice.model.Tile;
import latice.model.Player;

public class GameViewController implements EventHandler<MouseEvent>{
	@FXML
	private GridPane idGridTiles;
	
	@FXML
	private HBox idHboxBas;
	
	@FXML
	private ImageView idRackImageTile1;
	
	@FXML
	private ImageView idRackImageTile2;
	
	@FXML
	private ImageView idRackImageTile3;
	
	@FXML
	private ImageView idRackImageTile4;
	
	@FXML
	private ImageView idRackImageTile5;
	
	@FXML
	private ImageView idRackInvisibleTile1;
	
	@FXML
	private ImageView idRackInvisibleTile2;
	
	@FXML
	private ImageView idRackInvisibleTile3;
	
	@FXML
	private ImageView idRackInvisibleTile4;
	
	@FXML
	private ImageView idRackInvisibleTile5;
	
	@FXML
	private Button idBtnPass;
	
	@FXML
	private Button idBtnChange;
	
	@FXML
	private ImageView idPilePlayer1;
	
	@FXML
	private ImageView idPilePlayer2;
	
	@FXML
	private Text idTxtPile;
	
	@FXML
	private Text idNbTour;
	
	 @FXML
	 private Text idErrTile;
	
	@FXML
	private GridPane idGrid;
	
	@FXML
    private GridPane idInvisibleGrid;
	
	@FXML
	private Button idBtnExtraMove;
	
	private Game game = new Game();
	private MainPool mainPool = game.mainPool();

	private Pool pools = new Pool();
	private ArrayList<Tile> poolPlayer1 = pools.tiles().get(0);
	private ArrayList<Tile> poolPlayer2 = pools.tiles().get(1);
	private boolean isJ2 = new Random().nextBoolean();
	private int roundCounter;
	private Rack rackPlayer1 = new Rack(poolPlayer1);
	private Rack rackPlayer2 = new Rack(poolPlayer2);
	private String player1Name;
	private String player2Name;
	
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
	public void initialize() {
    	roundCounter = 0;
    	idNbTour.setText("Tour 1 :");
    	
    	if (isJ2) {
    		this.idTxtPile.setText("Au tour de " + player2Name + " (" + player2.points() + " points)");
        	idPilePlayer1.setVisible(false);
            idRackImageTile1.setImage(imageTile1p2);
            idRackImageTile2.setImage(imageTile2p2);
            idRackImageTile3.setImage(imageTile3p2);
            idRackImageTile4.setImage(imageTile4p2);
            idRackImageTile5.setImage(imageTile5p2);
    	}
    	else {
    		this.idTxtPile.setText("Au tour de " + player1Name + " (" + player1.points() + " points)");
        	idPilePlayer2.setVisible(false);
            idRackImageTile1.setImage(imageTile1p1);
            idRackImageTile2.setImage(imageTile2p1);
            idRackImageTile3.setImage(imageTile3p1);
            idRackImageTile4.setImage(imageTile4p1);
            idRackImageTile5.setImage(imageTile5p1);
    	}
        
      //Permet de changer entre le rack p1 et p2
        idBtnPass.setOnAction(e -> { 
        	changeTiles(imageTile1p1, imageTile2p1, imageTile3p1, imageTile4p1, imageTile5p1, imageTile1p2,
					imageTile2p2, imageTile3p2, imageTile4p2, imageTile5p2);
        });
        
        //Permet de changer son rack et passer son tour
        idBtnChange.setOnAction(e -> {
        	if (isJ2) {
        		player2.switchRack();
        		rackPlayer2 = player2.rack();
        	}
        	else {
        		player1.switchRack();
        		rackPlayer1 = player1.rack();
        	}
        	changeTiles(imageTile1p1, imageTile2p1, imageTile3p1, imageTile4p1, imageTile5p1, imageTile1p2,
					imageTile2p2, imageTile3p2, imageTile4p2, imageTile5p2);
        });
        
      //Permet d'acheter une action suplémentaire
    	idBtnExtraMove.setOnAction(e -> {
    		System.out.println("p2 " + player2.points() + " " + player2.move() );
    		System.out.println("p1 " + player1.points() + " " + player1.move() );
    		if (isJ2 && player2.points() >= 2 && player2.move() == 0) {
        		player2.setPoints(-2);
        		player2.setMove();
        	}
        	else if (!isJ2 && player1.points() >= 2 && player1.move() == 0) {
        		player1.setPoints(-2);
        		player1.setMove();
        	}
    	});
    	
    	idErrTile.setVisible(false);
       
        idRackInvisibleTile1.setOnDragDetected(event -> {
        	if(isJ2) {
			    dragTile(idRackInvisibleTile1, imageTile1p2);
        	}
			else {
				dragTile(idRackInvisibleTile1, imageTile1p1);
			}
			event.consume();
        });
        
        idRackInvisibleTile2.setOnDragDetected(event -> {
        	if(isJ2) {
			    dragTile(idRackInvisibleTile2, imageTile2p2);
        	}
			else {
				dragTile(idRackInvisibleTile2, imageTile2p1);
			}
			event.consume();
        });
        
        idRackInvisibleTile3.setOnDragDetected(event -> {
        	if(isJ2) {
			    dragTile(idRackInvisibleTile3, imageTile3p2);
        	}
			else {
				dragTile(idRackInvisibleTile3, imageTile3p1);
			}
			event.consume();
        });
        
        idRackInvisibleTile4.setOnDragDetected(event -> {
        	if(isJ2) {
			    dragTile(idRackInvisibleTile4, imageTile4p2);
        	}
			else {
				dragTile(idRackInvisibleTile4, imageTile4p1);
			}
			event.consume();
        });
        
        idRackInvisibleTile5.setOnDragDetected(event -> {
        	if(isJ2) {
			    dragTile(idRackInvisibleTile5, imageTile5p2);
        	}
			else {
				dragTile(idRackInvisibleTile5, imageTile5p1);
			}
			event.consume();
        });
    }

	private void dragTile(ImageView tile, Image imgTile) {
        Dragboard dragboard = tile.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putString(imgTile.toString());
        dragboard.setContent(content);
        dragboard.setDragView(imgTile, 50, 50);
 	
 		idInvisibleGrid.setOnDragOver(event -> {
 		    if (event.getGestureSource() != idGrid && event.getDragboard().hasString()) {
 		        event.acceptTransferModes(TransferMode.MOVE);
 		    }
 		    event.consume();
 		});
 	
 		idInvisibleGrid.setOnDragDropped(event -> {
 		    Dragboard db = event.getDragboard();
 		    if (db.hasString()) {
 		        ImageView droppedTile = new ImageView(imgTile);
 		        droppedTile.setFitWidth(80);
 		        droppedTile.setFitHeight(80);
 		    // On veut connaitre les coordonnées de la case où on a drop :
 		        int squareWidth = 80;
 		        int squareHeight = 80;
 		        int col = (int)(event.getX()/ squareWidth); // (int) est fait pour arrondir à un entier.
 		        int row = (int)(event.getY()/ squareHeight);
 		        if(!gridAlreadyFilled(col, row)) {
 		        	idErrTile.setVisible(false);
 		        	idInvisibleGrid.add(droppedTile, col, row);
 		        	event.setDropCompleted(true);
 		        }
 		        else {
 		        	idErrTile.setVisible(true);
 		        	event.setDropCompleted(false);
 		        }
 		    }
 		    else {
 		    	event.setDropCompleted(false);	    	
 		    }
 		   
 		    if (event.isDropCompleted()) {
	 		    Object source = event.getGestureSource();
	 		    if (source instanceof ImageView) {										// ce bout de code sert à obtenir l'id
	 		      ((ImageView) source).setImage(new Image("/latice/image/bg_sea.png"));// de la case Invisible, à remplacer son image nulle par le fond
	 		      ((ImageView) source).setOpacity(1);
	 		      ((ImageView) source).setMouseTransparent(true);						// et de désactiver le drag and drop.
	 		    }
 		    }
 		   
 		    event.consume();
 		});
	}
	
	private void shutTheGame() {
		if (roundCounter == 20) {
			Platform.exit();
		}
	}
 	

	private void changeTiles(Image imageTile1p1, Image imageTile2p1, Image imageTile3p1, Image imageTile4p1,
			Image imageTile5p1, Image imageTile1p2, Image imageTile2p2, Image imageTile3p2, Image imageTile4p2,
			Image imageTile5p2) {
		idRackInvisibleTile1.setOpacity(0);
		idRackInvisibleTile2.setOpacity(0);
		idRackInvisibleTile3.setOpacity(0); // faire disparaitre le fond bleu pour pas qu'il n'y ait pas de cases vides.
		idRackInvisibleTile4.setOpacity(0);
		idRackInvisibleTile5.setOpacity(0);
		
		if (isJ2) {
			imageTile1p1 = new Image(getClass().getResource(rackPlayer1.tiles().get(0).urlImg()).toExternalForm());
		    imageTile2p1 = new Image(getClass().getResource(rackPlayer1.tiles().get(1).urlImg()).toExternalForm());
		    imageTile3p1 = new Image(getClass().getResource(rackPlayer1.tiles().get(2).urlImg()).toExternalForm());
		    imageTile4p1 = new Image(getClass().getResource(rackPlayer1.tiles().get(3).urlImg()).toExternalForm());
		    imageTile5p1 = new Image(getClass().getResource(rackPlayer1.tiles().get(4).urlImg()).toExternalForm());

			idRackImageTile1.setImage(imageTile1p1);
		    idRackImageTile2.setImage(imageTile2p1);
		    idRackImageTile3.setImage(imageTile3p1);
		    idRackImageTile4.setImage(imageTile4p1);
		    idRackImageTile5.setImage(imageTile5p1);
		    
		    player2.pass();
		    idPilePlayer1.setVisible(true);
		    idPilePlayer2.setVisible(false);
		    this.idTxtPile.setText("Au tour de " + player1Name + " (" + player1.points() + " points)");
		    
		    player1.setMove();
		}
		else {
			imageTile1p2 = new Image(getClass().getResource(rackPlayer2.tiles().get(0).urlImg()).toExternalForm());
		    imageTile2p2 = new Image(getClass().getResource(rackPlayer2.tiles().get(1).urlImg()).toExternalForm());
		    imageTile3p2 = new Image(getClass().getResource(rackPlayer2.tiles().get(2).urlImg()).toExternalForm());
		    imageTile4p2 = new Image(getClass().getResource(rackPlayer2.tiles().get(3).urlImg()).toExternalForm());
		    imageTile5p2 = new Image(getClass().getResource(rackPlayer2.tiles().get(4).urlImg()).toExternalForm());
		    
		    idRackInvisibleTile1.setImage(imageTile1p2);
		    idRackInvisibleTile2.setImage(imageTile2p2);
		    idRackInvisibleTile3.setImage(imageTile3p2);
		    idRackInvisibleTile4.setImage(imageTile4p2);
		    idRackInvisibleTile5.setImage(imageTile5p2);
		    
		    player1.pass();
		    idPilePlayer2.setVisible(true);
		    idPilePlayer1.setVisible(false);
		    this.idTxtPile.setText("Au tour de " + player2Name + " (" + player2.points() + " points)");
		    
		    player2.setMove();
		}
		isJ2 = !isJ2;
		roundCounter ++;
		idNbTour.setText("Tour " + Integer.toString(roundCounter/2 + 1) + " :");
		
		idRackInvisibleTile1.setMouseTransparent(false);
		idRackInvisibleTile2.setMouseTransparent(false);
		idRackInvisibleTile3.setMouseTransparent(false); // réactiver le drag and drop au changement de tour.
		idRackInvisibleTile4.setMouseTransparent(false);
		idRackInvisibleTile5.setMouseTransparent(false);
		
		shutTheGame();
	}
	
	private Boolean gridAlreadyFilled(int col, int row) {
		for (Node img : idInvisibleGrid.getChildren()) {
			Integer imgCol = GridPane.getColumnIndex(img);
	        Integer imgRow = GridPane.getRowIndex(img);
	        
	        if ((imgCol != null && imgRow != null) && (imgCol == col && imgRow == row)) {
	        	return true;
	        }
		}
		return false;
	}
	
}