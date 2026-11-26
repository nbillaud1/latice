package latice.view;

import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

//Écran de fin : pluie de tuiles, soleil rayonnant et carte du vainqueur
public class VictoryOverlay extends StackPane {
	private static final String[] SHAPES = {"bird", "dolphin", "feather", "flower", "gecko", "turtle"};
	private static final String[] COLORS = {"g", "m", "n", "r", "t", "y"};
	private final Random random = new Random();

	public VictoryOverlay(double width, double height, String title, String winner, String detail, Runnable onReplay, Runnable onQuit) {
		getStyleClass().add("victory-overlay");
		setPrefSize(width, height);

		//Soleil qui tourne derrière la carte
		Group sunburst = new Group();
		for (int i = 0; i < 18; i++) {
			Polygon ray = new Polygon(0, 0, -28, -520, 28, -520);
			ray.setFill(Color.web("#ffc94a", 0.22));
			ray.getTransforms().add(new Rotate(i * 20, 0, 0));
			sunburst.getChildren().add(ray);
		}
		sunburst.setMouseTransparent(true);
		RotateTransition spin = new RotateTransition(Duration.seconds(40), sunburst);
		spin.setByAngle(360);
		spin.setInterpolator(Interpolator.LINEAR);
		spin.setCycleCount(Animation.INDEFINITE);
		spin.play();

		//Pluie de tuiles du jeu
		Pane confetti = new Pane();
		confetti.setMouseTransparent(true);
		for (int i = 0; i < 45; i++) {
			confetti.getChildren().add(fallingTile(width, height));
		}

		//Carte du vainqueur
		Label lblTitle = new Label(title);
		lblTitle.getStyleClass().add("victory-title");
		Label lblWinner = new Label(winner);
		lblWinner.getStyleClass().add("victory-winner");
		Label lblDetail = new Label(detail);
		lblDetail.getStyleClass().add("victory-detail");
		lblDetail.setWrapText(true);

		Button btnReplay = new Button("Nouvelle partie");
		btnReplay.getStyleClass().add("cta");
		btnReplay.setOnAction(e -> onReplay.run());
		Button btnQuit = new Button("Quitter");
		btnQuit.getStyleClass().add("secondary");
		btnQuit.setOnAction(e -> onQuit.run());
		HBox buttons = new HBox(18, btnReplay, btnQuit);
		buttons.setAlignment(Pos.CENTER);

		VBox card = new VBox(14, lblTitle, lblWinner, lblDetail, buttons);
		card.getStyleClass().addAll("panel", "victory-card");
		card.setAlignment(Pos.CENTER);
		card.setMaxSize(560, Region.USE_PREF_SIZE);

		getChildren().addAll(sunburst, confetti, card);

		//Apparition : fondu du voile puis la carte rebondit
		setOpacity(0);
		FadeTransition fadeIn = new FadeTransition(Duration.millis(450), this);
		fadeIn.setToValue(1);
		card.setScaleX(0.2);
		card.setScaleY(0.2);
		ScaleTransition grow = new ScaleTransition(Duration.millis(380), card);
		grow.setToX(1.08);
		grow.setToY(1.08);
		grow.setInterpolator(Interpolator.EASE_OUT);
		ScaleTransition settle = new ScaleTransition(Duration.millis(220), card);
		settle.setToX(1);
		settle.setToY(1);
		new SequentialTransition(fadeIn, grow, settle).play();

		//Le titre se balance comme une bouée
		RotateTransition bob = new RotateTransition(Duration.seconds(1.4), lblTitle);
		bob.setFromAngle(-3);
		bob.setToAngle(3);
		bob.setAutoReverse(true);
		bob.setCycleCount(Animation.INDEFINITE);
		bob.setInterpolator(Interpolator.EASE_BOTH);
		bob.play();
	}

	private ImageView fallingTile(double width, double height) {
		String url = "/latice/image/" + SHAPES[random.nextInt(SHAPES.length)] + "_" + COLORS[random.nextInt(COLORS.length)] + ".png";
		ImageView tile = new ImageView(new Image(url, 48, 48, true, true));
		double size = 26 + random.nextDouble() * 22;
		tile.setFitWidth(size);
		tile.setFitHeight(size);
		tile.setLayoutX(random.nextDouble() * (width - size));
		tile.setLayoutY(-size);

		Duration duration = Duration.seconds(3.5 + random.nextDouble() * 4);
		TranslateTransition fall = new TranslateTransition(duration, tile);
		fall.setFromY(0);
		fall.setToY(height + size * 2);
		fall.setInterpolator(Interpolator.LINEAR);
		RotateTransition turn = new RotateTransition(duration, tile);
		turn.setByAngle((random.nextBoolean() ? 1 : -1) * (180 + random.nextDouble() * 360));
		ParallelTransition life = new ParallelTransition(fall, turn);
		life.setCycleCount(Animation.INDEFINITE);
		life.playFrom(duration.multiply(random.nextDouble()));
		return tile;
	}
}
