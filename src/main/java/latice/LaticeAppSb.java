package latice;

import java.io.File;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import latice.audio.MusicManager;
import latice.controller.GameViewController;

public class LaticeAppSb extends Application{
	
	private Button btnStart;
	private Button btnBackground;
	private ImageView btnQuit;
	private Label lblPlayer1Name;
	private Label lblPlayer2Name;
	private Label lblErrorStart;
	private Label lblBackgPath;
	private TextField tfPlayer1Name;
	private TextField tfPlayer2Name;
	private Stage primaryStage;
	private String imgBackground = "fond.png";
	private int turnNumber;
	
	


	@Override
	public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/latice/image/icon.png")));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        
        //Titre + btnQuit
        Label title = new Label("Bienvenue dans notre jeu Latice");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font(null, FontWeight.BOLD, 50));
        ButtonListener lstnBtn = new ButtonListener();
        btnQuit = new ImageView(new Image(getClass().getResourceAsStream("/latice/image/quitter.png")));
		btnQuit.setOnMouseClicked(lstnBtn);
		btnQuit.setCursor(Cursor.HAND);
		HBox hbTop = new HBox();
		hbTop.getChildren().addAll(title, btnQuit);
		hbTop.setSpacing(50);
        
        //Erreur au start
        this.lblErrorStart = new Label();
		this.lblErrorStart.setTextFill(Color.RED);
	    this.lblErrorStart.setFont(Font.font(null, FontWeight.BOLD, 18));
	    DropShadow outline = new DropShadow();
        outline.setOffsetX(0);
        outline.setOffsetY(0);
        outline.setColor(Color.WHITE);
        outline.setRadius(2);
        outline.setSpread(1);
        lblErrorStart.setEffect(outline);
	    lblErrorStart.setMaxWidth(Double.MAX_VALUE);

		//Vbox j1
		this.lblPlayer1Name = new Label("Veuillez entrer le nom du Joueur 1 : ");
		lblPlayer1Name.setTextFill(Color.WHITE);
        lblPlayer1Name.setFont(Font.font(null, FontWeight.BOLD, 20));
		this.tfPlayer1Name = new TextField();
		VBox vBoxName1 = new VBox();
		vBoxName1.setMaxWidth(400);
        vBoxName1.setMaxHeight(400);
		vBoxName1.setSpacing(10);
		vBoxName1.getChildren().addAll(this.lblPlayer1Name,tfPlayer1Name);
		vBoxName1.setAlignment(Pos.CENTER);
		
		//Vbox j2
		this.lblPlayer2Name = new Label("Veuillez entrer le nom du Joueur 2 : ");
		lblPlayer2Name.setTextFill(Color.WHITE);
        lblPlayer2Name.setFont(Font.font(null, FontWeight.BOLD, 20));
		this.tfPlayer2Name = new TextField();
		VBox vBoxName2 = new VBox();
		vBoxName2.setMaxWidth(400);
        vBoxName2.setMaxHeight(400);
		vBoxName2.setSpacing(10);
		vBoxName2.getChildren().addAll(this.lblPlayer2Name,tfPlayer2Name);
		vBoxName2.setAlignment(Pos.CENTER);
		vBoxName2.setSpacing(10);

		
		//Bouton pour commencer
		this.btnStart = new Button("Commencer la partie !");
		this.btnStart.setOnMouseClicked(lstnBtn);
		
		//VBox background chooser
	    this.lblBackgPath = new Label("Fond d'écran de la partie : " + imgBackground);
	    lblBackgPath.setTextFill(Color.WHITE);
	    lblBackgPath.setFont(Font.font(null, FontWeight.BOLD, 15));
		this.btnBackground = new Button("Changer le fond");
		this.btnBackground.setOnMouseClicked(lstnBtn);
		VBox vbBg = new VBox();
		vbBg.getChildren().addAll(btnBackground, lblBackgPath);
		vbBg.setAlignment(Pos.CENTER);
		vbBg.setSpacing(10);
		
		//HBox des deux noms
		HBox hBoxNames = new HBox();
		hBoxNames.setAlignment(Pos.CENTER);
		hBoxNames.setSpacing(50);
		hBoxNames.setPadding(new Insets(20));
		hBoxNames.getChildren().addAll(vBoxName1,vBoxName2);
		
		//Bouton pour afficher les règles
		Button btnRules = new Button("Règles");
		btnRules.setOnAction(e -> showRules());
		
		//Choix du nombre de tours
		ComboBox<Integer> cbTurnNbr = new ComboBox<>();
        cbTurnNbr.getItems().addAll(10,12,14,16,18,20);
        cbTurnNbr.setValue(10);
        turnNumber = cbTurnNbr.getValue();
        cbTurnNbr.setOnAction(e -> {
        	turnNumber = cbTurnNbr.getValue();
        });
        
        //Label nombre de tours
        Label lbTurnNbr = new Label("Nombre de tours:");
        lbTurnNbr.setTextFill(Color.WHITE);
        lbTurnNbr.setFont(Font.font(null, FontWeight.BOLD, 15));
        
        //HBox du nombre de tour
        HBox hbTurnNbr = new HBox(10);
        hbTurnNbr.setAlignment(Pos.CENTER);
        hbTurnNbr.getChildren().addAll(lbTurnNbr,cbTurnNbr);
        
        
        //HBox dans la VBox du milieu qui contient les règles et le nombre de tours
        HBox hbMidle = new HBox(500);
        hbMidle.setAlignment(Pos.CENTER);
        hbMidle.getChildren().addAll(hbTurnNbr,btnRules);
		
		//VBox du millieu
		VBox vbMiddle = new VBox();
		vbMiddle.setAlignment(Pos.CENTER);
		vbMiddle.getChildren().addAll(hBoxNames, vbBg, hbMidle);
		vbMiddle.setSpacing(40);
		vbMiddle.setPadding(new Insets(250, 0, 0, 0));
		
		//VBox et HBox du bas
		VBox vBoxBottom = new VBox();
		vBoxBottom.setMaxWidth(500);
        vBoxBottom.setMaxHeight(500);
		vBoxBottom.setSpacing(100);
		vBoxBottom.getChildren().addAll(this.lblErrorStart, btnStart);
		vBoxBottom.setAlignment(Pos.CENTER);
		
		HBox hBoxBottom = new HBox(vBoxBottom);
		hBoxBottom.setAlignment(Pos.CENTER);
		HBox.setMargin(vBoxBottom, new Insets(0, 0, 50, 0));
		//BorderPane
		BorderPane.setAlignment(hbTop, Pos.CENTER);
		BorderPane.setMargin(hbTop, new Insets(40, 0, 0, 90));
		//Root
		BorderPane root = new BorderPane();
		root.setTop(hbTop);
        root.setCenter(vbMiddle);
        root.setBottom(hBoxBottom);
        
        //Définit le background
      	Image image = new Image("/latice/image/" + imgBackground);
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        root.setBackground(background);
        
        //Music du lobby
        MusicManager.play("/latice/sound/Lobby_music.wav");
        
        primaryStage.setScene(new Scene(root, 1000,1000));
        primaryStage.setTitle("Choix du nom des joueurs");
        primaryStage.show();
	}

	private void showRules() {
        Stage rulesWindow = new Stage();
        rulesWindow.initModality(Modality.APPLICATION_MODAL); // Bloque la fenêtre principale
        rulesWindow.setResizable(false);
        rulesWindow.getIcons().add(new Image(getClass().getResourceAsStream("/latice/image/icon.png")));
        rulesWindow.initStyle(StageStyle.UNDECORATED);
        rulesWindow.setTitle("Règles du jeu");
        Label tabulationTitle = new Label("					");
        Label rulesTitle = new Label("Voici les règles du jeu :\n\n");
        Label rules = new Label(
        		"\r\n"
                + "Le Latice est un jeu de stratégie où vous devez associer des cases de la même \n"
                + "couleur ou de la même forme.\r\n"
                + "Distribution :\r\n"
                + "Au début de la partie, chaque joueur possède une pioche contenant 31 tuiles\r\n"
                + "plus une main de 5 tuiles.\r\n"
                + "Il existe seulement deux exemplaires de chaque tuiles.\r\n"
                + "\r\n"
                + "Victoire :\r\n"
                + "Le gagnant est celui qui arrive à se débarrasser de toutes ses tuiles, ou dans le cas \n"
                + "de notre application, celui qui a le moins de tuiles à la fin du nombre de\r\n"
                + "tours impartis (10 minimum). \r\n"
                + "\r\n"
                + "Déroulement:\r\n"
                + "La première tuile doit être placée sur la lune au centre.\r\n"
                + "Par la suite, on ne peut poser une tuile que si elle possède un côté\r\n"
                + "adjacent à une tuile déjà posée.\r\n"
                + "Il faut qu'elle soit de la même forme ou couleur (les diagonales ne comptent pas). \r\n"
                + "A chaque tour, chaque joueur se voit accorder une action :\r\n"
                + " - Poser une tuile\r\n"
                + " - Changer l'intégralité de sa main (cette option passe le tour du joueur)\r\n"
                + " - Passer son tour\r\n"
                + "S'il pose une tuile, il peut alors acheter une action supplémentaire à l'aide \r\n"
                + "de ses pierres.\r\n"
                + "Les pierres (pierre soleil ou demi pierre) sont obtenues comme suit : \r\n"
                + "	- Poser sa tuile de manière à ce qu'elle ait deux tuiles adjacentes\r\n"
                + "   procure une demi pierre et forme un Double.\r\n"
                + "	- Poser sa tuile de manière à ce qu'elle ait trois tuiles adjacentes\r\n"
                + "   procure une pierre soleil et forme un Trefoil.\r\n"
                + "	- Poser sa tuile de manière à ce qu'elle ait quatre tuiles adjacentes\r\n"
                + "   procure deux pierres soleil et forme un Latice.\r\n"
                + " - Poser sa tuile sur un soleil permet d'obtenir une pierre soleil. \r\n"
                + "Une action supplémentaire coûte une pierre ou deux demi pierres.\r\n"
                + "Il n'y a pas de limite de demi pierres, mais si le joueur finit son tour avec plus \r\n"
                + "de 3 pierres soleil l'excédent sera retiré. \r\n"
                + "\r\n");
        rules.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        rulesTitle.setFont(Font.font("Calibri", FontWeight.BOLD, 25));
        rulesTitle.setUnderline(true);
        tabulationTitle.setFont(Font.font("Calibri", FontWeight.BOLD, 25));
        
        
        ImageView btnClose = new ImageView(new Image(getClass().getResourceAsStream("/latice/image/quitter.png")));
        btnClose.setFitHeight(40);
        btnClose.setFitWidth(40);
        btnClose.setOnMouseClicked(e -> rulesWindow.close());
        btnClose.setCursor(Cursor.HAND);
        HBox hb = new HBox();
        hb.getChildren().add(btnClose);
        hb.setAlignment(Pos.CENTER);

        VBox content = new VBox(10);
        HBox title = new HBox();
        title.getChildren().addAll(tabulationTitle, rulesTitle);
        content.getChildren().addAll(title, rules, hb);
        content.setPadding(new Insets(25, 15, 10, 20));
        Image image = new Image("/latice/image/fond_regles.png");
        BackgroundSize backgroundSize = new BackgroundSize(712, 1200, false, false, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        content.setBackground(background);
        
        //Permet d'autoriser le scroll
        ScrollPane scrollPane = new ScrollPane(content);
        BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, new CornerRadii(0), new BorderWidths(3));
        scrollPane.setBorder(new Border(borderStroke));
        
        Scene scene = new Scene(scrollPane, 740, 670);
        rulesWindow.setScene(scene);
        rulesWindow.showAndWait(); // Affiche la fenêtre et attend sa fermeture
    }

	private class ButtonListener implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent event) {
			if(event.getSource() == btnStart) {
				if(tfPlayer1Name.getText().equals("")) {
					lblErrorStart.setText("Vous devez renseigner un nom pour le joueur 1.");
				}
				else if(tfPlayer2Name.getText().equals("")) {
					lblErrorStart.setText("Vous devez renseigner un nom pour le joueur 2.");
				}
				else if(tfPlayer2Name.getText().equals(tfPlayer1Name.getText())) {
					lblErrorStart.setText("Les noms doivent être différents");
				}
				else if(tfPlayer2Name.getLength() > 10 || tfPlayer1Name.getLength() > 10) {
					lblErrorStart.setText("10 caractères maximum par noms");
				}
				else {
					lblErrorStart.setText("");
					try {
						startTheGame(primaryStage);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			else if(event.getSource() == btnBackground) {
				FileChooser fileChooser = new FileChooser();
			      fileChooser.setTitle("Choisir une image provenant de : latice/image/ ");
			      File selectedFile = fileChooser.showOpenDialog(primaryStage);
			      if (selectedFile != null) {
			        imgBackground = selectedFile.getName();
			        lblBackgPath.setText("Fond d'écran de la partie : " + imgBackground);
			      }
			}
			else if(event.getSource() == btnQuit) {
				Platform.exit();
			}
		}
	}
	
	public void startTheGame(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/latice/view/GameView.fxml"));
		StackPane root = new StackPane();
		root.getChildren().add(loader.load());
		
		GameViewController controller = loader.getController(); 
		controller.setroundConter(turnNumber);
	    controller.setPlayer1Name(this.player1Name()); // on fournit les noms des joueurs au controller.
	    controller.setPlayer2Name(this.player2Name());
	    controller.initialize();
	    
		Stage stage = new Stage();
		Scene scene = new Scene(root,1000,1000);
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED); // désactive la barre du haut de la fenêtre.
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/latice/image/icon.png")));
		
		//Définit le background
		try {
	      	Image image = new Image("/latice/image/" + imgBackground);
	        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
	        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
	        Background background = new Background(backgroundImage);
	        root.setBackground(background);
	        stage.setScene(scene);
			stage.setTitle("Jeu du Latice");
			stage.show();
			
			//Music du plateau
			MusicManager.play("/latice/sound/Latice_theme.wav");
			
			primaryStage.close();
		}
		catch(IllegalArgumentException e) {
			lblErrorStart.setText("L'image selectionnée n'est pas valide.");
		}
			
    }
	
	public int turnNumber() {
		return turnNumber;
	}
	
	public String player1Name() {
		return this.tfPlayer1Name.getText();
	}
	
	public String player2Name() {
		return this.tfPlayer2Name.getText();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
