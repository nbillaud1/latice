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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
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
		/*Scène principale avant de commencer la partie pour choisir les noms.*/
		BorderPane root = new BorderPane();
        Stage stage = new Stage();
        this.primaryStage = primaryStage;
        this.btnStart = new Button("Commencer la partie !");
        ButtonListener lstnBtn = new ButtonListener();
		this.btnStart.setOnMouseClicked(lstnBtn);

		this.lblPlayer1Name = new Label("Nom du Joueur 1 : ");
		this.lblPlayer2Name = new Label("Nom du Joueur 2 : ");
		this.lblErrorStart = new Label();
		this.lblErrorStart.setTextFill(Color.RED);
	    this.lblErrorStart.setFont(Font.font(null, FontWeight.BOLD, -1));
		this.tfPlayer1Name = new TextField();
		this.tfPlayer2Name = new TextField();
		GridPane gridNames = new GridPane();
		gridNames.add(this.lblPlayer1Name, 0, 0);
		gridNames.add(this.lblPlayer2Name, 0, 1);
		gridNames.add(this.tfPlayer1Name, 1, 0);
		gridNames.add(this.tfPlayer2Name, 1, 1);
		gridNames.add(this.btnStart, 1, 2);
		gridNames.setHgap(15);
		gridNames.setVgap(15);
		
        root.setCenter(gridNames);
        root.setRight(this.lblErrorStart);
        
        primaryStage.setScene(new Scene(root, 800, 300));
        primaryStage.setTitle("Choix des noms");
        primaryStage.show();
	}

	
	private class ButtonListener implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent event) {
			if(event.getSource() == btnStart) {
				if(tfPlayer1Name.getText().equals("")) {
					lblErrorStart.setText("Vous devez renseigner un nom pour le joueur 1.");
				}
				else if(tfPlayer2Name.getText().equals("")){
					lblErrorStart.setText("Vous devez renseigner un nom pour le joueur 2.");
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
		return this.tfPlayer2Name.getText();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
