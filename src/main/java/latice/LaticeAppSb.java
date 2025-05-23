package latice;

import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import latice.audio.MusicManager;
import latice.controller.GameViewController;

public class LaticeAppSb extends Application{
	
	private Button btnStart;
	private Label lblPlayer1Name;
	private Label lblPlayer2Name;
	private Label lblErrorStart;
	private TextField tfPlayer1Name;
	private TextField tfPlayer2Name;
	private Stage primaryStage;
	
	


	@Override
	public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        primaryStage.setResizable(false);
        
        //Titre
        Label title = new Label("Bienvenue dans notre jeu Latice");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font(null, FontWeight.BOLD, 50));
        
        //Erreur au start
        this.lblErrorStart = new Label();
		this.lblErrorStart.setTextFill(Color.RED);
	    this.lblErrorStart.setFont(Font.font(null, FontWeight.BOLD, 18));
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
        ButtonListener lstnBtn = new ButtonListener();
		this.btnStart.setOnMouseClicked(lstnBtn);
		
	//Configure l'empilage
		//HBox du milieu
		HBox hBoxMidle = new HBox();
		hBoxMidle.setAlignment(Pos.CENTER);
		hBoxMidle.setSpacing(50);
		hBoxMidle.setPadding(new Insets(20));
		hBoxMidle.getChildren().addAll(vBoxName1,vBoxName2);
		//VBox et HBox du bas
		VBox vBoxBottom = new VBox();
		vBoxBottom.setMaxWidth(500);
        vBoxBottom.setMaxHeight(500);
		vBoxBottom.setSpacing(100);
		vBoxBottom.getChildren().addAll(this.lblErrorStart,btnStart);
		vBoxBottom.setAlignment(Pos.CENTER);
		
		HBox hBoxBottom = new HBox(vBoxBottom);
		hBoxBottom.setAlignment(Pos.CENTER);
		HBox.setMargin(vBoxBottom, new Insets(0, 0, 50, 0));
		//BorderPane
		BorderPane.setAlignment(title, Pos.CENTER);
		BorderPane.setMargin(title, new Insets(20, 0, 0, 0));
		//Root
		BorderPane root = new BorderPane();
		root.setTop(title);
        root.setCenter(hBoxMidle);
        root.setBottom(hBoxBottom);
        
        //Définit le background
      	Image image = new Image("/latice/image/fond.png");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        root.setBackground(background);
        
        //Music du lobby
        MusicManager.play("/latice/sound/Lobby_music.mp3");
        
        primaryStage.setScene(new Scene(root, 1000,1000));
        primaryStage.setTitle("Choix du nom des joueurs");
        primaryStage.show();
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
					try {
						lblErrorStart.setText("");
						startTheGame(primaryStage);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void startTheGame(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/latice/view/GameView.fxml"));
		StackPane root = new StackPane();
		root.getChildren().add(loader.load());
		
		GameViewController controller = loader.getController(); 
	    controller.setPlayer1Name(this.player1Name()); // fournir les noms des joueurs au controller.
	    controller.setPlayer2Name(this.player2Name());
	    controller.initialize();
	    
		Stage stage = new Stage();
		Scene scene = new Scene(root,1000,1000);
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED); // désactive la barre du haut de la fenêtre.
		
		//Définit le background
      	Image image = new Image("/latice/image/fond.png");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        root.setBackground(background);
		stage.setScene(scene);
		stage.setTitle("Jeu du Latice");
		stage.show();
		
		//Music du plateau
		MusicManager.play("/latice/sound/Latice_theme.mp3");
		
		primaryStage.close();
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
