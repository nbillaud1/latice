package latice.controller;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.PauseTransition;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
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
	private Button idBtnQuit;
	
	@FXML
	private ImageView idPilePlayer1;
	
	@FXML
	private ImageView idPilePlayer2;
	
	@FXML
	private Text idTxtPile;
	
	@FXML
	private Text idNbrTilesPoolP1;
	
	@FXML
	private Text idNbrTilesPoolP2;
	
	@FXML
	private Text idTurnNumber;
	
	 @FXML
	 private Text idErrTile;
	 
	 @FXML
	 private Text idMovesP1;
	 
	 @FXML
	 private Text idMovesP2;
	 
	 @FXML
	 private Text idTxtLatice;
	 
	 @FXML
	 private Text idTxtTrefoil;
	 
	 @FXML
	 private Text idTxtDouble;
	 
	@FXML
	private GridPane idGrid;
	
	@FXML
    private GridPane idInvisibleGrid;
	
	@FXML
	private Button idBtnExtraMove;
	
	private Referer referer = new Referer();
	private Pool pools = new Pool();
	private ArrayList<Tile> poolPlayer1 = pools.tiles().get(0);
	private ArrayList<Tile> poolPlayer2 = pools.tiles().get(1);
	
	private int roundCounter;
	private int canPlay;
	private boolean hasToPlayOnTheMoon = true;
	private boolean isP2 = new Random().nextBoolean();
	private Rack rackPlayer1 = new Rack(poolPlayer1);
	private Rack rackPlayer2 = new Rack(poolPlayer2);
	private String player1Name;
	private String player2Name;
	
	private Player player1 = new Player(poolPlayer1, rackPlayer1, player1Name);
	private Player player2 = new Player(poolPlayer2, rackPlayer2, player2Name);
	
	private Image imageTile1;
	private Image imageTile2;
	private Image imageTile3;
	private Image imageTile4;
	private Image imageTile5;
    
    private ArrayList<Integer> lstPlayer1PlayedTilesIndex = new ArrayList<>();
    private ArrayList<Integer> lstPlayer2PlayedTilesIndex = new ArrayList<>();
    
    public void setPlayer1Name(String name) {
        this.player1Name = name;
    }

    public void setPlayer2Name(String name) {
        this.player2Name = name;
    }
    
    public void emptyLstPlayer1PlayedTilesIndex() {
    	this.lstPlayer1PlayedTilesIndex = new ArrayList<Integer>();
    }
    
    public void emptyLstPlayer2PlayedTilesIndex() {
    	this.lstPlayer2PlayedTilesIndex = new ArrayList<Integer>();
    }
	
	@Override
	public void handle(MouseEvent event) {
		//TODO
    }
    
    @FXML
	public void initialize() {

    	roundCounter = 0;
    	idTurnNumber.setText("Tour 1 :");
    	idErrTile.setVisible(false);
    	
    	if (isP2) {
        	idPilePlayer1.setVisible(false);
        	idMovesP1.setVisible(false);
            createAndSwitchTiles(rackPlayer2);
            idNbrTilesPoolP1.setVisible(false);
            idNbrTilesPoolP2.setVisible(true);
            idNbrTilesPoolP2.setText(String.valueOf(poolPlayer2.size()));
            referer.setPTurnAndAction(player2Name, player2, idTxtPile, idMovesP2);
    	}
    	else {
        	idPilePlayer2.setVisible(false);
        	idMovesP2.setVisible(false);
            createAndSwitchTiles(rackPlayer1);
            idNbrTilesPoolP2.setVisible(false);
            idNbrTilesPoolP1.setVisible(true);
            idNbrTilesPoolP1.setText(String.valueOf(poolPlayer1.size()));
            referer.setPTurnAndAction(player1Name, player1, idTxtPile, idMovesP1);
    	}
        
      //Permet de changer entre le rack p1 et p2
        idBtnPass.setOnAction(e -> { 
        	changeTiles();
        });
        
        idBtnQuit.setOnAction(e -> { 
        	Platform.exit();
        });
        
        //Permet de changer son rack et passer son tour
        idBtnChange.setOnAction(e -> {
        	
        	if (isP2 && player2.move() == 1) {
        		player2.switchRack();
        		rackPlayer2 = player2.rack();
        		changeTiles();
        	}
        	else if (player1.move() == 1){
        		player1.switchRack();
        		rackPlayer1 = player1.rack();
        		changeTiles();
        	}
        	else {
        		referer.displayErrorMessage("Vous ne possédez plus d'actions restantes", idErrTile);
        	}
        	
        });
        
      //Permet d'acheter une action suplémentaire
    	idBtnExtraMove.setOnAction(e -> {
    		if (isP2 && player2.points() >= 2 && player2.move() == 0) {
        		player2.buyExtraMove();
        		referer.setPTurnAndAction(player2Name, player2, idTxtPile, idMovesP2);
        	}
        	else if (!isP2 && player1.points() >= 2 && player1.move() == 0) {
        		player1.buyExtraMove();
        		referer.setPTurnAndAction(player1Name, player1, idTxtPile, idMovesP1);
        	}
        	else {
        		referer.displayErrorMessage("Il faut au moins 2 points pour acheter une action, et une seule action peut être disponible à la fois", idErrTile);
        	}
    	});
       
        idRackInvisibleTile1.setOnDragDetected(event -> {
        	dragP1OrP2Tile(event,imageTile1,idRackInvisibleTile1);
        });
        
        idRackInvisibleTile2.setOnDragDetected(event -> {
        	dragP1OrP2Tile(event,imageTile2,idRackInvisibleTile2);
        });
        
        idRackInvisibleTile3.setOnDragDetected(event -> {
        	dragP1OrP2Tile(event,imageTile3,idRackInvisibleTile3);
        });
        
        idRackInvisibleTile4.setOnDragDetected(event -> {
        	dragP1OrP2Tile(event,imageTile4,idRackInvisibleTile4);
        });
        
        idRackInvisibleTile5.setOnDragDetected(event -> {
        	dragP1OrP2Tile(event,imageTile5,idRackInvisibleTile5);
        });
    }

	private void dragP1OrP2Tile(MouseEvent event, Image imageTile, ImageView idRackInvisibleTile) {
		dragTile(idRackInvisibleTile, imageTile);
		event.consume();
	}

	private void dragTile(ImageView tile, Image imgTile) {
		if (isP2) {
			canPlay = player2.move();
		}
		else {
			canPlay = player1.move();
		}
			hasToPlayOnTheMoon = referer.firstTileOnTheMoon(idInvisibleGrid); //savoir si la grille a été remplie.
			
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
 		        int nbrOfTilesAround = referer.checkAround(idInvisibleGrid, col, row, droppedTile);

 		        if((!hasToPlayOnTheMoon || (col == 4 && row == 4)) && canPlay == 1) {
	 		        if(!gridAlreadyFilled(col, row) &&  (nbrOfTilesAround > 0 || hasToPlayOnTheMoon)) {
	 		        	idErrTile.setVisible(false);
	 		        	idInvisibleGrid.add(droppedTile, col, row);
	 		        	event.setDropCompleted(true);
	 		        	if (isP2) {
	 		        		referer.pointsManagement(nbrOfTilesAround, player2, idTxtLatice, idTxtTrefoil, idTxtDouble, col, row, idTxtPile, idMovesP2, player2Name, isP2);
	 		        	}
	 		        	else {
	 		        		referer.pointsManagement(nbrOfTilesAround, player1, idTxtLatice, idTxtTrefoil, idTxtDouble, col, row, idTxtPile, idMovesP2, player1Name, isP2);    		
	 		        	}
	 		        	
	 		        }
	 		        else {
	 		        	referer.displayErrorMessage("Vous ne pouvez pas poser une tuile ici", idErrTile);
	 		        	event.setDropCompleted(false);
	 		        }

 		        }
 		        else if (canPlay == 0) {
 		        	referer.displayErrorMessage("Vous ne possédez plus d'actions restantes", idErrTile);
 		        	event.setDropCompleted(false);
 		        }
 		        else {
 		        	referer.displayErrorMessage("Vous devez poser la première tuile sur la Lune", idErrTile);
 		        	event.setDropCompleted(false);
 		        }
 		       
 		    }
 		    else {
 		    	event.setDropCompleted(false);	    	
 		    }
 		   
 		    if (event.isDropCompleted()) {
	 		    Object source = event.getGestureSource();
	 		    if (source instanceof ImageView) {										// ce bout de code sert à obtenir l'id
	 		      ((ImageView) source).setImage(new Image("/latice/image/bg_sea.png")); // de la case Invisible, à remplacer son image nulle par le fond
	 		      ((ImageView) source).setOpacity(1);
	 		      ((ImageView) source).setMouseTransparent(true);						// et à désactiver le drag and drop.
	 		    }
	 		//Ensuite on supprime la tuile du rack après l'avoir posée.
		 		if(isP2) {
		 		    for (Tile tileFromRack : rackPlayer2.tiles()) {
		 		    	if (Tile.url(new Image(getClass().getResource(tileFromRack.urlImg()).toExternalForm())).equals(Tile.url(imgTile))) {
		 		    		lstPlayer2PlayedTilesIndex.add(rackPlayer2.tiles().indexOf(tileFromRack)); //utile pour compléter le rack.
		 		    	}
		 		    }
		 		}
		 		else {
		 			for (Tile tileFromRack : rackPlayer1.tiles()) {
		 				if (Tile.url(new Image(getClass().getResource(tileFromRack.urlImg()).toExternalForm())).equals(Tile.url(imgTile))) {
		 		    		lstPlayer1PlayedTilesIndex.add(rackPlayer1.tiles().indexOf(tileFromRack));
		 		    	}
		 		    }
		 		}
 		    }
 		    event.consume();
 		});
	}
	
	//Éteint le jeu
	private void shutTheGame() {
		if (roundCounter == 20) {
			int nbrTilesLeftP1 = poolPlayer1.size();
			int nbrTilesLeftP2 = poolPlayer2.size();
			if (nbrTilesLeftP1 < nbrTilesLeftP2) {
				idTxtPile.setText("C'est " + player1Name + " qui l'emporte");
			}
			else if (nbrTilesLeftP1 > nbrTilesLeftP2) {
				idTxtPile.setText("C'est " + player2Name + " qui l'emporte");
			}
			else {
				idTxtPile.setText("Égalité");
			}
			idTxtPile.setFont(Font.font("Bold",42));
			//Désactive la vue des différentes piles
			idPilePlayer1.setVisible(false);
			idPilePlayer2.setVisible(false);
			//Désactive les boutons
			idBtnChange.setDisable(true);
			idBtnExtraMove.setDisable(true);
			idBtnPass.setDisable(true);
			//Désactive le fait de pouvoir attraper des tuiles
			idRackInvisibleTile1.setMouseTransparent(true);
			idRackInvisibleTile2.setMouseTransparent(true);
			idRackInvisibleTile3.setMouseTransparent(true);
			idRackInvisibleTile4.setMouseTransparent(true);
			idRackInvisibleTile5.setMouseTransparent(true);
			//Désactive les images du rack
			idRackImageTile1.setImage(null);
		    idRackImageTile2.setImage(null);
		    idRackImageTile3.setImage(null);
		    idRackImageTile4.setImage(null);
		    idRackImageTile5.setImage(null);
		    //Cache le nombre de tours, les action restantes
		    idTurnNumber.setVisible(false);
		    idMovesP1.setVisible(false);
		    idMovesP2.setVisible(false);
		    //Cache le nombre te tuiles des racks
		    idNbrTilesPoolP1.setVisible(false);
		    idNbrTilesPoolP2.setVisible(false);
			//Pause de 5 secondes
			PauseTransition pause = new PauseTransition(Duration.seconds(7));
			pause.setOnFinished(event -> Platform.exit());
			pause.play();
		}
	}
	
	
	private void changeTiles() {
		idRackInvisibleTile1.setOpacity(0);
		idRackInvisibleTile2.setOpacity(0);
		idRackInvisibleTile3.setOpacity(0); // faire disparaitre le fond bleu pour pas qu'il n'y ait de cases vides.
		idRackInvisibleTile4.setOpacity(0);
		idRackInvisibleTile5.setOpacity(0);
		
		if (isP2) {
			
			player2.pass();
		    player2.completeRack(lstPlayer2PlayedTilesIndex);
		    emptyLstPlayer1PlayedTilesIndex();
			createAndSwitchTiles(rackPlayer1);
		    
			switchView(isP2);
		    player1.Move(1);
    		referer.setPTurnAndAction(player1Name, player1, idTxtPile, idMovesP1);

            idNbrTilesPoolP1.setText(String.valueOf(poolPlayer1.size()));
		    
		}
		else {
			
			player1.pass();
		    player1.completeRack(lstPlayer1PlayedTilesIndex);
		    emptyLstPlayer2PlayedTilesIndex();
			
			createAndSwitchTiles(rackPlayer2);
		    switchView(isP2);
		    
		    player2.Move(1);
    		referer.setPTurnAndAction(player2Name, player2, idTxtPile, idMovesP2);
            idNbrTilesPoolP2.setText(String.valueOf(poolPlayer2.size()));
		}
		isP2 = !isP2;
		roundCounter ++;
		idTurnNumber.setText("Tour " + Integer.toString(roundCounter/2 + 1) + " :");
		
		idRackInvisibleTile1.setMouseTransparent(false);
		idRackInvisibleTile2.setMouseTransparent(false);
		idRackInvisibleTile3.setMouseTransparent(false); // réactiver le drag and drop au changement de tour.
		idRackInvisibleTile4.setMouseTransparent(false);
		idRackInvisibleTile5.setMouseTransparent(false);
		
		idErrTile.setVisible(false);
		shutTheGame();
	}

	private void createAndSwitchTiles(Rack rack) {
		imageTile1 = new Image(getClass().getResource(rack.tiles().get(0).urlImg()).toExternalForm());
		imageTile2 = new Image(getClass().getResource(rack.tiles().get(1).urlImg()).toExternalForm());
		imageTile3 = new Image(getClass().getResource(rack.tiles().get(2).urlImg()).toExternalForm());
		imageTile4 = new Image(getClass().getResource(rack.tiles().get(3).urlImg()).toExternalForm());
		imageTile5 = new Image(getClass().getResource(rack.tiles().get(4).urlImg()).toExternalForm());

		idRackImageTile1.setImage(imageTile1);
		idRackImageTile2.setImage(imageTile2);
		idRackImageTile3.setImage(imageTile3);
		idRackImageTile4.setImage(imageTile4);
		idRackImageTile5.setImage(imageTile5);
	}

	private void switchView(Boolean isP2) {
		idMovesP2.setVisible(!isP2);
		idMovesP1.setVisible(isP2);
		idNbrTilesPoolP2.setVisible(!isP2);
		idNbrTilesPoolP1.setVisible(isP2);
		idPilePlayer1.setVisible(isP2);
		idPilePlayer2.setVisible(!isP2);
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