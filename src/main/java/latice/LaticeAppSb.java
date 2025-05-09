package latice;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import latice.controller.GameViewController;

public class LaticeAppSb extends Application{
	
	private Button btnStart;
	private Label lblPlayer1Name;
	private Label lblErrorStart;
	private TextField tfPlayer1Name;
	private Stage primaryStage;
	
	


	@Override
	public void start(Stage primaryStage){
		//Scène principale avant de commencer la partie pour choisir les noms.
		BorderPane root = new BorderPane();
        Stage stage = new Stage();
        this.primaryStage = primaryStage;
        this.btnStart = new Button("Commencer la partie !");
        ButtonListener lstnBtn = new ButtonListener();
		this.btnStart.setOnMouseClicked(lstnBtn);
		
		Label title = new Label("Bienvenue dans notre jeu Latice");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font(null, FontWeight.BOLD, 50));

        Image image = new Image("/latice/image/fond.png");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize
        );
        Background background = new Background(backgroundImage);
        root.setBackground(background);

		this.lblPlayer1Name = new Label("Veuillez entrer le nom du Joueur 1 : ");
		this.lblErrorStart = new Label();
		this.lblErrorStart.setTextFill(Color.RED);
	    this.lblErrorStart.setFont(Font.font(null, FontWeight.BOLD, -1));
		this.tfPlayer1Name = new TextField();
		VBox vBoxName = new VBox();
		vBoxName.setMaxWidth(500);
        vBoxName.setMaxHeight(500);
		vBoxName.setSpacing(10);
		vBoxName.getChildren().addAll(this.lblPlayer1Name,tfPlayer1Name,btnStart);		
		
		root.setTop(title);
		BorderPane.setAlignment(title, javafx.geometry.Pos.CENTER);
		BorderPane.setMargin(title, new Insets(20, 0, 0, 0));
        root.setCenter(vBoxName);
        root.setRight(this.lblErrorStart);
        
        primaryStage.setScene(new Scene(root, 1000,1000));
        primaryStage.setTitle("Choix du nom du joueur1");
        primaryStage.show();
	}

	
	private class ButtonListener implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent event) {
			if(event.getSource() == btnStart) {
				if(tfPlayer1Name.getText().equals("")) {
					lblErrorStart.setText("Vous devez renseigner un nom pour le joueur 1.");
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
		
		GameViewController controller = new GameViewController(); // on créer notre propre controller
	    controller.setPlayer1Name(this.player1Name()); // afin de fournir les noms des joueurs.
	    controller.setPlayer2Name(this.player2Name());
	    loader.setController(controller);
		
		/*Mise en place de la page*/
		StackPane root = new StackPane();
		root.getChildren().add(loader.load());
		Stage stage = new Stage();
		Scene scene = new Scene(root,1000,1000);
		stage.setScene(scene);
		stage.setTitle("Jeu du Latice");
		stage.show();
		primaryStage.close();
    }
	
	public String player1Name() {
		return this.tfPlayer1Name.getText();
	}
	
	public String player2Name() {
		return "b";
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
