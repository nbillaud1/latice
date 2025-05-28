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
import javafx.scene.layout.BorderPane;
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
        rulesWindow.setTitle("Règles du jeu");
        Label rules = new Label("Voici les règles du jeu :\n\n"
                + "Le Latice est un jeu de stratégie où vous devez associer des cases de la même couleur ou \n"
                + "de la même forme.\r\n"
                + "\r\n"
                + "Victoire:\r\n"
                + "Le gagnant est celui qui arrive à ce débarrasser de toutes ses tuiles, ou dans le cas de notre \n"
                + "application, celui qui a le moins de tuiles à la fin du nombre de tour impartie (10 minimum).\r\n"
                + "\r\n"
                + "Déroulement:\r\n"
                + "La première tuile doit être placée sur la lune au centre.\r\n"
                + "Par la suite, on ne peux poser une tuile seulement à côté d'une autre. Mais, Il faut qu'elle soit \n"
                + "de la même forme ou couleur. Attention, les diagonales ne comptent pas. \r\n"
                + "Chaque tour, chaques joueurs se voit accordé une action.\r\n"
                + "Si vous ne pouvez jouer où souhaitez juste changer votre rack, vous pouvez utilisez une action. \r\n"
                + "Attention! Cette action remplace l'intégralité de votre rack et passe votre tour.\r\n"
                + "Si vous ne pouvez jouer et ou n'avez pas la possibilité de changer votre rack, vous pouvez \n"
                + "aussi passer votre tour.\r\n"
                + "A la fin de votre tour vous piochez le nombre de tuiles manquantes de votre rack pour avoir \n"
                + "lors de votre prochain tour 5 tuiles dedans (sauf cas où la pioche est vide).\r\n"
                + "En jouant, vous pouvez obtenir différentes pierres qui vous permettrons de les échanger contre \n"
                + "des actions supplémentaires.\r\n"
                + "Une action suplémentaire peut être acquise en échangeant une pierre soleil, ou bien deux demi \n"
                + "pierres avec l'arbitre (l'ordinateur).\r\n"
                + "\r\n"
                + "Pierres:\r\n"
                + "Pour obtenir une pierre, il faut que tu pause ta tuile de manière à ce qu'elle aie au minimum \n"
                + "deux tuiles adjacentes:\r\n"
                + "	- Deux tuiles te procure une demi pierre et forment un Double.\r\n"
                + "	- Trois tuiles te procurent une pierre soleil et forment un Trefoil.\r\n"
                + "	- Quatre tuiles te procurent deux pierres soleil et forment un Latice.\r\n"
                + "Tu peux aussi obtenir une pierre soleil en pausant ta tuile sur un soleil. La limite de demi pierres \n"
                + "est infini, mais si tu finis ton tour avec plus de 3 pierres soleil l'excédent sera retiré.\r\n"
                + "\r\n"
                + ""
                );
        rules.setFont(new Font("Arial", 15));
        
        Button btnClose = new Button("Fermer");
        btnClose.setOnAction(e -> rulesWindow.close());
        HBox hb = new HBox();
        hb.getChildren().add(btnClose);
        hb.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(rules, hb);

        Scene scene = new Scene(layout, 640, 670);
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
		return tfPlayer1Name.getText();
	}
	
	public String player2Name() {
		return tfPlayer2Name.getText();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
