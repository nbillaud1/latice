package latice.controller;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
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
import latice.audio.MusicManager;
import latice.model.GameBoard;
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
	private ImageView idImgSound;
	
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
	
	private GameBoard gameBoard = new GameBoard();
	private Referer referer = new Referer();
	
	private Pool pools = new Pool();
	private ArrayList<Tile> poolPlayer1 = pools.tiles().get(0);
	private ArrayList<Tile> poolPlayer2 = pools.tiles().get(1);
	
	private int roundCounter;
	private int canContinue;
	private boolean hasToPlayOnTheMoon = true;
	private boolean isP2 = new Random().nextBoolean();
	private Rack rackPlayer1 = new Rack(poolPlayer1);
	private Rack rackPlayer2 = new Rack(poolPlayer2);
	private String player1Name;
	private String player2Name;

	private Player player1;
	private Player player2;
	
	private int maxTurnNbr;
	
	private Image imageTile1;
	private Image imageTile2;
	private Image imageTile3;
	private Image imageTile4;
	private Image imageTile5;
	
	private boolean isEndOfTheGame;
    
    private ArrayList<Integer> lstPlayer1PlayedTilesIndex = new ArrayList<>();
    private ArrayList<Integer> lstPlayer2PlayedTilesIndex = new ArrayList<>();
    
    private boolean[] finalRackP1 = {false,false,false,false,false};
    private boolean[] finalRackP2 = {false,false,false,false,false};
    private Boolean musicOn = true;
    
    public void setRoundConter(int value) {
    	maxTurnNbr = value * 2;
    }
    
    public void setPlayer1Name(String name) {
        player1Name = name;
    }

    public void setPlayer2Name(String name) {
        player2Name = name;
    }
    
    public void emptyLstPlayer1PlayedTilesIndex() {
    	lstPlayer1PlayedTilesIndex = new ArrayList<Integer>();
    }
    
    public void emptyLstPlayer2PlayedTilesIndex() {
    	lstPlayer2PlayedTilesIndex = new ArrayList<Integer>();
    }
    
    @FXML
	public void initialize() {
    	ContextMenu contextM = new ContextMenu();
    	MenuItem miHalfStone = new MenuItem("Payer en demi pierres");
        MenuItem miSunStone = new MenuItem("Payer en pierre soleil");
        miHalfStone.setOnAction(e -> buyExtraMoveHalfStone());
        miSunStone.setOnAction(e -> buyExtraMoveSunStone());
        contextM.getItems().addAll(miHalfStone, miSunStone);
    	player1 = new Player(poolPlayer1, rackPlayer1, player1Name);
    	player2 = new Player(poolPlayer2, rackPlayer2, player2Name);
    	idTurnNumber.setText("Tour " + Integer.toString(roundCounter/2 + 1) + "/" + Integer.toString(maxTurnNbr/2) + " :");
    	roundCounter = 0;
    	idErrTile.setVisible(false);
    	isEndOfTheGame = false;
    	
    	
    	if (isP2) {
        	idPilePlayer1.setVisible(false);
        	idMovesP1.setVisible(false);
            createAndSwitchTiles(rackPlayer2);
            idNbrTilesPoolP1.setVisible(false);
            idNbrTilesPoolP2.setVisible(true);
            idNbrTilesPoolP2.setText(String.valueOf(poolPlayer2.size()));
            referer.setPTurnAndAction(player2, idTxtPile, idMovesP2);
    	}
    	else {
        	idPilePlayer2.setVisible(false);
        	idMovesP2.setVisible(false);
            createAndSwitchTiles(rackPlayer1);
            idNbrTilesPoolP2.setVisible(false);
            idNbrTilesPoolP1.setVisible(true);
            idNbrTilesPoolP1.setText(String.valueOf(poolPlayer1.size()));
            referer.setPTurnAndAction(player1, idTxtPile, idMovesP1);
    	}
        
        //Permet de changer son rack et passer son tour
        idBtnChange.setOnAction(e -> {
        	if (isP2 && player2.move() == 1) {
        		player2.switchRack();
        		rackPlayer2 = player2.rack();
        		changeTiles();
        	}
        	else if (!isP2 && player1.move() == 1){
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
    		contextM.show(idBtnExtraMove, Side.BOTTOM, 0, 0);
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
			canContinue = player2.move();
		}
		else {
			canContinue = player1.move();
		}
			hasToPlayOnTheMoon = referer.firstTileNotPutOnTheMoon(gameBoard.board()); //savoir si la grille a été remplie.
			
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
 		        Tile tileToAddOnGameBoard = new Tile(referer.findShape(Tile.url(imgTile)), referer.findColor(Tile.url(imgTile)));
 		        int nbrOfTilesAround = referer.checkAround(gameBoard, col, row, tileToAddOnGameBoard);
 		        if((!hasToPlayOnTheMoon || (col == 4 && row == 4)) && canContinue == 1) {
	 		        if(!referer.gridAlreadyFilled(gameBoard.board(), col, row) &&  (nbrOfTilesAround > 0 || hasToPlayOnTheMoon)) {
	 		        	idErrTile.setVisible(false);
	 		        	if (isP2) {
	 		        		referer.pointsManagement(nbrOfTilesAround, player2, idTxtLatice, idTxtTrefoil, idTxtDouble, col, row, idTxtPile, idMovesP2, player2Name, gameBoard.board());
	 		        	}
	 		        	else {
	 		        		referer.pointsManagement(nbrOfTilesAround, player1, idTxtLatice, idTxtTrefoil, idTxtDouble, col, row, idTxtPile, idMovesP1, player1Name, gameBoard.board());    		
	 		        	}
	 		        	gameBoard.addBoard(row + 1, col + 1, tileToAddOnGameBoard);
	 		        	idInvisibleGrid.add(droppedTile, col, row);

	 		        	MusicManager.DROPSOUND.play();
	 		        	event.setDropCompleted(true);

	 		        }
	 		        else {
	 		        	referer.displayErrorMessage("Vous ne pouvez pas poser une tuile ici", idErrTile);
	 		        	event.setDropCompleted(false);
	 		        }

 		        }
 		        else if (canContinue == 0) {
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

	@FXML
	void quit() {
	    	Platform.exit();
	}
	
	@FXML
	void switchTheSound() {
		if(!musicOn) {
			MusicManager.play("/latice/sound/Latice_theme.wav");
			idImgSound.setImage(new Image("/latice/image/activer_le_son.png"));
		}
		else {
			MusicManager.stop();
			idImgSound.setImage(new Image("/latice/image/couper_le_son.png"));
			
		}
		musicOn = !musicOn;
	}
	
	//Gère le changement entre le tour du P1 et du P2
	@FXML
	private void changeTiles() {
		
		if (isP2) {
			
			player2.pass();
			isItFinalRack(lstPlayer2PlayedTilesIndex, poolPlayer2.size(), finalRackP2);
		    dontShowTilesWhen5TilesLeft(lstPlayer1PlayedTilesIndex, poolPlayer1.size(), finalRackP1);
		    player2.completeRack(lstPlayer2PlayedTilesIndex, poolPlayer2.size());
		    emptyLstPlayer1PlayedTilesIndex();
			createAndSwitchTiles(rackPlayer1);
		    
			switchView(isP2);
		    player1.Move(1);
    		referer.setPTurnAndAction(player1, idTxtPile, idMovesP1);

            idNbrTilesPoolP1.setText(String.valueOf(poolPlayer1.size()));
            if (player2.sunStone() > 3) {
            	player2.threeSunStones();
            }
		    
		}
		else {
			player1.pass();
			isItFinalRack(lstPlayer1PlayedTilesIndex, poolPlayer1.size(), finalRackP1);	
			dontShowTilesWhen5TilesLeft(lstPlayer2PlayedTilesIndex, poolPlayer2.size(), finalRackP2);
			player1.completeRack(lstPlayer1PlayedTilesIndex, poolPlayer1.size());
		    emptyLstPlayer2PlayedTilesIndex();
			
			createAndSwitchTiles(rackPlayer2);
		    switchView(isP2);
		    
		    player2.Move(1);
    		referer.setPTurnAndAction(player2, idTxtPile, idMovesP2);
            idNbrTilesPoolP2.setText(String.valueOf(poolPlayer2.size()));
            if (player1.sunStone() > 3) {
            	player1.threeSunStones();
            }
		}
		isP2 = !isP2;
		roundCounter ++;
		idTurnNumber.setText("Tour " + Integer.toString(roundCounter/2 + 1) + "/" + Integer.toString(maxTurnNbr/2) + " :");
		
		idErrTile.setVisible(false);
		if ((finalRackP1[0] && finalRackP1[1] && finalRackP1[2] && finalRackP1[3] && finalRackP1[4]  )|| (finalRackP2[0] && finalRackP2[1] && finalRackP2[2] && finalRackP2[3] && finalRackP2[4])) {
			isEndOfTheGame = true;
		}
		
		if (roundCounter == maxTurnNbr || isEndOfTheGame) {
			shutTheGame();
		}
	}
	
	//Détermine les cases à ne pas afficher dans le cas où le nombres de tuiles restant dans la pool est inférieur au nombre de tuiles à remettre
	private void isItFinalRack(ArrayList<Integer> lstPlayerPlayedTilesIndex, int poolPlayerSize, boolean[] finalRack) {
		if (!(poolPlayerSize >= 5 || lstPlayerPlayedTilesIndex.size() <= poolPlayerSize)) {
			int nbrOfTilesToHide = lstPlayerPlayedTilesIndex.size() - poolPlayerSize;
			int tilesLeft = nbrOfTilesToHide;
			for (int i = 0; i<nbrOfTilesToHide ; i++) {
				if(tilesLeft > 0) { 
					finalRack[lstPlayerPlayedTilesIndex.get((-i)+1)] = true;
					tilesLeft -= 1;
				}
			}
		}
	}
	
	//Peremt de déterminer quelles tuiles du rack il faut afficher quand il y a moins de 5 tuiles dans la pool
	private void dontShowTilesWhen5TilesLeft(ArrayList<Integer> lstPlayerPlayedTilesIndex, int poolPlayerSize, boolean[] finalRack) { 
		idRackInvisibleTile1.setOpacity(0);
		idRackInvisibleTile2.setOpacity(0);
		idRackInvisibleTile3.setOpacity(0); // faire disparaitre le fond bleu pour pas qu'il n'y ait de cases vides.
		idRackInvisibleTile4.setOpacity(0);
		idRackInvisibleTile5.setOpacity(0);
		
		idRackInvisibleTile1.setMouseTransparent(false);
		idRackInvisibleTile2.setMouseTransparent(false);
		idRackInvisibleTile3.setMouseTransparent(false); // réactiver le drag and drop au changement de tour.
		idRackInvisibleTile4.setMouseTransparent(false);
		idRackInvisibleTile5.setMouseTransparent(false);	
		if (!(poolPlayerSize >= 5 || lstPlayerPlayedTilesIndex.size() <= poolPlayerSize)) {
			if (finalRack[0]) {
				idRackInvisibleTile1.setOpacity(1);
				idRackInvisibleTile1.setMouseTransparent(true);
			}
			if (finalRack[1]) {
				idRackInvisibleTile2.setOpacity(1);
				idRackInvisibleTile2.setMouseTransparent(true);
			}
			if (finalRack[2]) {
				idRackInvisibleTile3.setOpacity(1);
				idRackInvisibleTile3.setMouseTransparent(true);
			}
			if (finalRack[3]) {
				idRackInvisibleTile4.setOpacity(1);
				idRackInvisibleTile4.setMouseTransparent(true);
			}
			if (finalRack[4]) {
				idRackInvisibleTile5.setOpacity(1);
				idRackInvisibleTile5.setMouseTransparent(true);
			}
		}
	}
	
	//Permet de remplacer l'affichage du rack actuel par de nouvelles images
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
	//Permet d'activer / désactiver les différent affichages du P1 et du P2 celon le tour en cour
	private void switchView(Boolean isP2) { 
		idMovesP2.setVisible(!isP2);
		idMovesP1.setVisible(isP2);
		idNbrTilesPoolP2.setVisible(!isP2);
		idNbrTilesPoolP1.setVisible(isP2);
		idPilePlayer1.setVisible(isP2);
		idPilePlayer2.setVisible(!isP2);
	}
	
	//Acheter une extra move via deux halfStones
 	public void buyExtraMoveHalfStone() { 
 		if (isP2 && player2.halfStone() >= 2 && player2.move() == 0) {
     		player2.buyExtraMoveWithHalfStones();
     		referer.setPTurnAndAction(player2, idTxtPile, idMovesP2);
     		idErrTile.setVisible(false);
     	}
     	else if (!isP2 && player1.halfStone() >= 2 && player1.move() == 0) {
     		player1.buyExtraMoveWithHalfStones();
     		referer.setPTurnAndAction(player1, idTxtPile, idMovesP1);
     		idErrTile.setVisible(false);
     	}
     	else {
     		idErrTile.setVisible(true);
		    referer.displayErrorMessage("Il faut au moins 2 demi pierres pour acheter une action, et une seule action peut être disponible à la fois", idErrTile);
     	}
 	}
	
 	//Peremt d'acheter un extra move via une sunStone
 	public void buyExtraMoveSunStone() { 
 		if (isP2 && player2.sunStone() >= 1 && player2.move() == 0) {
     		player2.buyExtraMoveWithSunStones();
     		referer.setPTurnAndAction(player2, idTxtPile, idMovesP2);
     		idErrTile.setVisible(false);
     	}
     	else if (!isP2 && player1.sunStone() >= 1 && player1.move() == 0) {
     		player1.buyExtraMoveWithSunStones();
     		referer.setPTurnAndAction(player1, idTxtPile, idMovesP1);
     		idErrTile.setVisible(false);
     	}
     	else {
     		idErrTile.setVisible(true);
		        referer.displayErrorMessage("Il faut au moins une pierre soleil pour acheter une action, et une seule action peut être disponible à la fois", idErrTile);;
     	}
 	}
	
	//Éteint le jeu
	private void shutTheGame() {
		int nbrTilesLeftP1 = poolPlayer1.size();
		int nbrTilesLeftP2 = poolPlayer2.size();
		if (nbrTilesLeftP1 < nbrTilesLeftP2) {
			idTxtPile.setText("C'est " + player1Name + " qui l'emporte !");
		}
		else if (nbrTilesLeftP1 > nbrTilesLeftP2) {
			idTxtPile.setText("C'est " + player2Name + " qui l'emporte !");
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

	@Override
	public void handle(MouseEvent event) {		
	}
}