package latice.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

//Fond animé commun aux écrans : ciel, soleil, vagues, bulles et plage
public class OceanBackground extends Pane {
	private static final double WAVELENGTH = 200;
	private final Random random = new Random();
	private final List<Animation> animations = new ArrayList<>();

	public OceanBackground(double width, double height) {
		setPrefSize(width, height);
		setMouseTransparent(true);
		double horizon = height * 0.22;
		double shore = height * 0.86;

		//Ciel puis mer, du lagon clair vers le bleu profond
		Rectangle water = new Rectangle(width, height);
		water.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
				new Stop(0, Color.web("#ffe7b8")),
				new Stop(0.16, Color.web("#8fdcef")),
				new Stop(0.22, Color.web("#3fc1d0")),
				new Stop(0.55, Color.web("#1385a8")),
				new Stop(1, Color.web("#0b3954"))));
		getChildren().add(water);

		//Soleil qui rayonne doucement au-dessus de l'horizon
		Circle glow = new Circle(width * 0.82, horizon * 0.55, 110);
		glow.setFill(new RadialGradient(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
				new Stop(0, Color.web("#fff3c4", 0.9)), new Stop(1, Color.web("#ffc94a", 0))));
		Circle sun = new Circle(width * 0.82, horizon * 0.55, 44, Color.web("#ffc94a"));
		sun.setStroke(Color.web("#ffe08a"));
		sun.setStrokeWidth(6);
		getChildren().addAll(glow, sun);
		ScaleTransition sunPulse = new ScaleTransition(Duration.seconds(3), glow);
		sunPulse.setToX(1.15);
		sunPulse.setToY(1.15);
		sunPulse.setAutoReverse(true);
		sunPulse.setCycleCount(Animation.INDEFINITE);
		start(sunPulse);

		//Rayons de lumière sous l'eau
		for (int i = 0; i < 4; i++) {
			Path ray = new Path(new MoveTo(width * (0.15 + i * 0.22), horizon),
					new LineTo(width * (0.22 + i * 0.22), horizon),
					new LineTo(width * (0.05 + i * 0.25), shore),
					new LineTo(width * (-0.05 + i * 0.25), shore), new ClosePath());
			ray.setFill(Color.web("#ffffff", 0.06));
			ray.setStroke(null);
			ray.setEffect(new GaussianBlur(18));
			getChildren().add(ray);
			FadeTransition shimmer = new FadeTransition(Duration.seconds(4 + i), ray);
			shimmer.setFromValue(0.4);
			shimmer.setToValue(1);
			shimmer.setAutoReverse(true);
			shimmer.setCycleCount(Animation.INDEFINITE);
			start(shimmer);
		}

		//Trois couches de vagues à l'horizon, chacune à sa vitesse
		addWave(width, horizon - 4, 10, Color.web("#bff3f7", 0.55), 9);
		addWave(width, horizon + 6, 12, Color.web("#5fd3dc", 0.55), 6);
		addWave(width, horizon + 18, 9, Color.web("#2fb1c6", 0.6), 4);

		//Bulles qui remontent
		for (int i = 0; i < 22; i++) {
			addBubble(width, horizon + 40, shore);
		}

		//Plage de sable en bas avec une ligne d'écume
		addShore(width, height, shore);
	}

	private void start(Animation animation) {
		animations.add(animation);
		animation.play();
	}

	//Arrête les animations quand le fond est remplacé (sinon elles tournent pour rien)
	public void stop() {
		animations.forEach(Animation::stop);
	}

	private void addWave(double width, double y, double amplitude, Color color, double seconds) {
		Path wave = new Path(new MoveTo(0, y));
		for (double x = 0; x < width + WAVELENGTH * 2; x += WAVELENGTH) {
			wave.getElements().add(new QuadCurveTo(x + WAVELENGTH / 4, y - amplitude, x + WAVELENGTH / 2, y));
			wave.getElements().add(new QuadCurveTo(x + WAVELENGTH * 3 / 4, y + amplitude, x + WAVELENGTH, y));
		}
		wave.getElements().addAll(new LineTo(width + WAVELENGTH * 2, y + 60), new LineTo(0, y + 60), new ClosePath());
		wave.setFill(color);
		wave.setStroke(null);
		getChildren().add(wave);
		//Décalage d'une longueur d'onde exactement : la boucle ne se voit pas
		TranslateTransition move = new TranslateTransition(Duration.seconds(seconds), wave);
		move.setFromX(0);
		move.setToX(-WAVELENGTH);
		move.setInterpolator(Interpolator.LINEAR);
		move.setCycleCount(Animation.INDEFINITE);
		start(move);
	}

	private void addBubble(double width, double top, double bottom) {
		double radius = 3 + random.nextDouble() * 9;
		Circle bubble = new Circle(radius, Color.web("#ffffff", 0.12));
		bubble.setStroke(Color.web("#e6fbff", 0.7));
		bubble.setStrokeWidth(1.5);
		bubble.setCenterX(random.nextDouble() * width);
		bubble.setCenterY(bottom);
		getChildren().add(bubble);

		Duration duration = Duration.seconds(7 + random.nextDouble() * 9);
		TranslateTransition rise = new TranslateTransition(duration, bubble);
		rise.setFromY(0);
		rise.setToY(top - bottom);
		rise.setInterpolator(Interpolator.EASE_IN);
		TranslateTransition sway = new TranslateTransition(Duration.seconds(1.5 + random.nextDouble() * 2), bubble);
		sway.setByX(10 + random.nextDouble() * 15);
		sway.setAutoReverse(true);
		sway.setCycleCount(Animation.INDEFINITE);
		FadeTransition fade = new FadeTransition(duration, bubble);
		fade.setFromValue(1);
		fade.setToValue(0);
		ParallelTransition life = new ParallelTransition(rise, fade);
		life.setCycleCount(Animation.INDEFINITE);
		//Démarre à un moment aléatoire pour que les bulles ne partent pas toutes ensemble
		animations.add(life);
		life.playFrom(duration.multiply(random.nextDouble()));
		start(sway);
	}

	private void addShore(double width, double height, double shore) {
		Path sand = new Path(new MoveTo(0, shore + 10));
		sand.getElements().addAll(
				new QuadCurveTo(width * 0.25, shore - 18, width * 0.5, shore + 4),
				new QuadCurveTo(width * 0.75, shore + 24, width, shore - 6),
				new LineTo(width, height), new LineTo(0, height), new ClosePath());
		sand.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
				new Stop(0, Color.web("#f6e3b4")), new Stop(1, Color.web("#dcb877"))));
		sand.setStroke(Color.web("#ffffff", 0.85));
		sand.setStrokeWidth(5);
		getChildren().add(sand);
		//Petits grains de sable
		for (int i = 0; i < 70; i++) {
			Circle grain = new Circle(random.nextDouble() * width, shore + 30 + random.nextDouble() * (height - shore - 30),
					1 + random.nextDouble() * 1.5, Color.web("#b88a4a", 0.35));
			getChildren().add(grain);
		}
	}
}
