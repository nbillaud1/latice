package latice.controller;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
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
	
	private Player player1 = new Player(poolPlayer1, rackPlayer1, player1Name);
	private Player player2 = new Player(poolPlayer2, rackPlayer2, player2Name);
	
	private Image imageTile1p1 = new Image((rackPlayer1.tiles().get(0).urlImg()));
    private Image imageTile2p1 = new Image(getClass().getResource(rackPlayer1.tiles().get(1).urlImg()).toExternalForm());
    private Image imageTile3p1 = new Image(getClass().getResource(rackPlayer1.tiles().get(2).urlImg()).toExternalForm());
    private Image imageTile4p1 = new Image(getClass().getResource(rackPlayer1.tiles().get(3).urlImg()).toExternalForm());
    private Image imageTile5p1 = new Image(getClass().getResource(rackPlayer1.tiles().get(4).urlImg()).toExternalForm());

    private Image imageTile1p2 = new Image(getClass().getResource(rackPlayer2.tiles().get(0).urlImg()).toExternalForm());
    private Image imageTile2p2 = new Image(getClass().getResource(rackPlayer2.tiles().get(1).urlImg()).toExternalForm());
    private Image imageTile3p2 = new Image(getClass().getResource(rackPlayer2.tiles().get(2).urlImg()).toExternalForm());
    private Image imageTile4p2 = new Image(getClass().getResource(rackPlayer2.tiles().get(3).urlImg()).toExternalForm());
    private Image imageTile5p2 = new Image(getClass().getResource(rackPlayer2.tiles().get(4).urlImg()).toExternalForm());
    
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
	
	@FXML
	void quit() {
	    	Platform.exit();
	}
	
	@FXML
	private void changeTiles() {
		idRackInvisibleTile1.setOpacity(0);
		idRackInvisibleTile2.setOpacity(0);
		idRackInvisibleTile3.setOpacity(0); // faire disparaitre le fond bleu pour pas qu'il n'y ait de cases vides.
		idRackInvisibleTile4.setOpacity(0);
		idRackInvisibleTile5.setOpacity(0);
		
		if (isP2) {
			
			player2.pass();
			idMovesP2.setVisible(false);
			idMovesP1.setVisible(true);
		    player2.completeRack(lstPlayer2PlayedTilesIndex);
		    emptyLstPlayer1PlayedTilesIndex();
			
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
		    
		    idPilePlayer1.setVisible(true);
		    idPilePlayer2.setVisible(false);
		    player1.Move(1);
		    idTxtPile.setText("Au tour de " + player1Name + " (" + player1.points() + " points)");
		    idMovesP1.setText("Actions restantes : " + player1.move());
		    idNbrTilesPoolP2.setVisible(false);
		    idNbrTilesPoolP1.setVisible(true);
            idNbrTilesPoolP1.setText(String.valueOf(poolPlayer1.size()));
		    
		}
		else {
			
			player1.pass();
			idMovesP1.setVisible(false);
			idMovesP2.setVisible(true);
		    player1.completeRack(lstPlayer1PlayedTilesIndex);
		    emptyLstPlayer2PlayedTilesIndex();
			
			imageTile1p2 = new Image(getClass().getResource(rackPlayer2.tiles().get(0).urlImg()).toExternalForm());
		    imageTile2p2 = new Image(getClass().getResource(rackPlayer2.tiles().get(1).urlImg()).toExternalForm());
		    imageTile3p2 = new Image(getClass().getResource(rackPlayer2.tiles().get(2).urlImg()).toExternalForm());
		    imageTile4p2 = new Image(getClass().getResource(rackPlayer2.tiles().get(3).urlImg()).toExternalForm());
		    imageTile5p2 = new Image(getClass().getResource(rackPlayer2.tiles().get(4).urlImg()).toExternalForm());
		    
		    idRackImageTile1.setImage(imageTile1p2);
		    idRackImageTile2.setImage(imageTile2p2);
		    idRackImageTile3.setImage(imageTile3p2);
		    idRackImageTile4.setImage(imageTile4p2);
		    idRackImageTile5.setImage(imageTile5p2);
		    
		    idPilePlayer2.setVisible(true);
		    idPilePlayer1.setVisible(false);
		    player2.Move(1);
		    idTxtPile.setText("Au tour de " + player2Name + " (" + player2.points() + " points)");
		    idMovesP2.setText("Actions restantes : " + player2.move());
		    idNbrTilesPoolP1.setVisible(false);
		    idNbrTilesPoolP2.setVisible(true);
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
	
	@FXML
 	public void buyExtraMove() {
 		if (isP2 && player2.points() >= 2 && player2.move() == 0) {
     		player2.buyExtraMove();
     		idTxtPile.setText("Au tour de " + player2Name + " (" + player2.points() + " points)");
	        	idMovesP2.setText("Actions restantes : " + player2.move());
     	}
     	else if (!isP2 && player1.points() >= 2 && player1.move() == 0) {
     		player1.buyExtraMove();
     		idTxtPile.setText("Au tour de " + player1Name + " (" + player1.points() + " points)");
	        	idMovesP1.setText("Actions restantes : " + player1.move());
     	}
     	else {
     		idErrTile.setVisible(true);
		        idErrTile.setText("Il faut au moins 2 points pour acheter une action, et une seule action peut être disponible à la fois");
     	}
 	}
    
    @FXML
	public void initialize() {

    	roundCounter = 0;
    	idTurnNumber.setText("Tour 1 :");
    	idErrTile.setVisible(false);
    	
    	if (isP2) {
    		this.idTxtPile.setText("Au tour de " + player2Name + " (" + player2.points() + " points)");
        	idPilePlayer1.setVisible(false);
        	idMovesP1.setVisible(false);
        	idMovesP2.setText("Actions restantes : " + player2.move());
            idRackImageTile1.setImage(imageTile1p2);
            idRackImageTile2.setImage(imageTile2p2);
            idRackImageTile3.setImage(imageTile3p2);
            idRackImageTile4.setImage(imageTile4p2);
            idRackImageTile5.setImage(imageTile5p2);
            idNbrTilesPoolP1.setVisible(false);
            idNbrTilesPoolP2.setVisible(true);
            idNbrTilesPoolP2.setText(String.valueOf(poolPlayer2.size()));
    	}
    	else {
    		this.idTxtPile.setText("Au tour de " + player1Name + " (" + player1.points() + " points)");
        	idPilePlayer2.setVisible(false);
        	idMovesP2.setVisible(false);
        	idMovesP1.setText("Actions restantes : " + player1.move());
            idRackImageTile1.setImage(imageTile1p1);
            idRackImageTile2.setImage(imageTile2p1);
            idRackImageTile3.setImage(imageTile3p1);
            idRackImageTile4.setImage(imageTile4p1);
            idRackImageTile5.setImage(imageTile5p1);
            idNbrTilesPoolP2.setVisible(false);
            idNbrTilesPoolP1.setVisible(true);
            idNbrTilesPoolP1.setText(String.valueOf(poolPlayer1.size()));
    	}
        
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
        		idErrTile.setVisible(true);
		        idErrTile.setText("Vous ne possédez plus d'actions restantes");
        	}
        });
            
        idRackInvisibleTile1.setOnDragDetected(event -> {
        	if(isP2) {
			    dragTile(idRackInvisibleTile1, imageTile1p2);
        	}
			else {
				dragTile(idRackInvisibleTile1, imageTile1p1);
			}
			event.consume();
        });
        
        idRackInvisibleTile2.setOnDragDetected(event -> {
        	if(isP2) {
			    dragTile(idRackInvisibleTile2, imageTile2p2);
        	}
			else {
				dragTile(idRackInvisibleTile2, imageTile2p1);
			}
			event.consume();
        });
        
        idRackInvisibleTile3.setOnDragDetected(event -> {
        	if(isP2) {
			    dragTile(idRackInvisibleTile3, imageTile3p2);
        	}
			else {
				dragTile(idRackInvisibleTile3, imageTile3p1);
			}
			event.consume();
        });
        
        idRackInvisibleTile4.setOnDragDetected(event -> {
        	if(isP2) {
			    dragTile(idRackInvisibleTile4, imageTile4p2);
        	}
			else {
				dragTile(idRackInvisibleTile4, imageTile4p1);
			}
			event.consume();
        });
        
        idRackInvisibleTile5.setOnDragDetected(event -> {
        	if(isP2) {
			    dragTile(idRackInvisibleTile5, imageTile5p2);
        	}
			else {
				dragTile(idRackInvisibleTile5, imageTile5p1);
			}
			event.consume();
        });
    }

	private void dragTile(ImageView tile, Image imgTile) {
		AnimationTimer laticeAnimation = animateText(idTxtLatice);
    	AnimationTimer trefoilAnimation = animateText(idTxtTrefoil);
        AnimationTimer doubleAnimation = animateText(idTxtDouble);
        
		if (isP2) {
			canContinue = player2.move();
		}
		else {
			canContinue = player1.move();
		}
		hasToPlayOnTheMoon = referer.firstTileNotPuttedOnTheMoon(gameBoard.board()); //savoir si la grille a été remplie.
		
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
 		        
 		        Tile tileToAddOnGameBoard = new Tile(referer.findShape(Tile.url(imgTile)), referer.findColor(Tile.url(imgTile))); // On récupère la Tile à poser.
 		        
 		        int nbrOfTilesAround = referer.checkAround(gameBoard, col, row, tileToAddOnGameBoard);
 		        
 		        if((!hasToPlayOnTheMoon || (col == 4 && row == 4)) && canContinue == 1) {
	 		        if(!referer.gridAlreadyFilled(gameBoard.board(), col, row) &&  (nbrOfTilesAround > 0 || hasToPlayOnTheMoon)) {
	 		        	idErrTile.setVisible(false);
	 		        	gameBoard.addBoard(row, col, tileToAddOnGameBoard); // normalement ça doit ajouter un tuile à la liste en console.
	 	 		        gameBoard.printGameBoard(); // test.
	 		        	idInvisibleGrid.add(droppedTile, col, row);
	 		        	event.setDropCompleted(true);
	 		        	if (isP2) {
	 		        		if (nbrOfTilesAround == 2) {
		 		        		player2.addPoints(1);
		 		        		doubleAnimation.start();
		 		        	}
	 		        		else if (nbrOfTilesAround == 3) {
	 		        			player2.addPoints(2);
	 		        			trefoilAnimation.start();
	 		        		}
	 		        		else if (nbrOfTilesAround == 4) {
	 		        			player2.addPoints(4);
	 		        			laticeAnimation.start();
	 		        		}
	 		        		player2.Move(0);
	 		        		if (referer.isSunTile(col, row)) {
								player2.addPoints(2);
							}
	 		        		idTxtPile.setText("Au tour de " + player2Name + " (" + player2.points() + " points)");
	 		        		idMovesP2.setText("Actions restantes : " + player2.move());
	 		        	}
	 		        	else {
	 		        		if (nbrOfTilesAround == 2) {
		 		        		player1.addPoints(1);
		 		        		doubleAnimation.start();
		 		        	}
	 		        		else if (nbrOfTilesAround == 3) {
	 		        			player1.addPoints(2);
	 		        			trefoilAnimation.start();
	 		        		}
	 		        		else if (nbrOfTilesAround == 4) {
	 		        			player1.addPoints(4);
	 		        			laticeAnimation.start();
	 		        		}
	 		        		player1.Move(0);
	 		        		if (referer.isSunTile(col, row)) {
								player1.addPoints(2);
							}
	 		        		idTxtPile.setText("Au tour de " + player1Name + " (" + player1.points() + " points)");
	 		        		idMovesP1.setText("Actions restantes : " + player1.move());
	 		        		
	 		        	}
	 		        	
	 		        }
	 		        else {
	 		        	idErrTile.setVisible(true);
	 		        	idErrTile.setText("Vous ne pouvez pas poser une tuile ici");
	 		        	event.setDropCompleted(false);
	 		        }
	
 		        }
 		        else if (canContinue == 0) {
 		        	idErrTile.setVisible(true);
 		        	idErrTile.setText("Vous ne possédez plus d'actions restantes");
 		        	event.setDropCompleted(false);
 		        }
 		        else {
 		        	idErrTile.setVisible(true);
 		        	idErrTile.setText("Vous devez poser la première tuile sur la Lune");
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
	
	private AnimationTimer animateText (Text text) {
        AnimationTimer anim = new AnimationTimer(){
            @Override
            public void handle(long now) {
                if (text.getFont().getSize() < 96) {
                	text.setOpacity(1);
                    text.setFont(new Font(text.getFont().getName(), text.getFont().getSize()+1));
                }
                else if(text.getOpacity() > 0){
                	text.setOpacity(text.getOpacity() - 0.01);
                }
                else{
                	text.setFont(new Font(text.getFont().getName(), 1));
                    stop();
                }
            }
        };
		return anim;
    }

	@Override
	public void handle(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}
}