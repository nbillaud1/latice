package latice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class LaticeAppSb extends Application{
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("view/GameView.fxml"));
		
		/*Création de la grille*/
		GridPane grille = new GridPane();
		grille.setHgap(0.2);
        grille.setVgap(0.2);
        grille.setPadding(new Insets(450));
        
  
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
            	Rectangle square = createSquare();
            	if( row == 0 && col == 0) {
            		Image sun = createSun();
            		StackPane cell = new StackPane();
                    cell.getChildren().addAll(square, new ImageView(sun));
            	}
                grille.add(square, col, row);
            }
        }

		
		/* Mise en place de la page */
		
        StackPane root = new StackPane();
        root.getChildren().add(grille);
		Scene scene = new Scene(root,1200,900);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Jeu du Latice");
		primaryStage.show();
	}
	
	private Rectangle createSquare() {
		Rectangle square = new Rectangle(50, 50, Color.WHITE);
        square.setStroke(Color.BLACK);
        return square;
	}
	
	private Image createSun() {
		Image sun = new Image(getClass().getResource("/image/sun.png").toExternalForm());
		return sun;
	}
	

	public static void main(String[] args) {
		Application.launch(args);
	}
}
