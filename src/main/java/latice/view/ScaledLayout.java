package latice.view;

import java.util.function.Consumer;

import javafx.animation.PauseTransition;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

//Remplit toute la fenêtre : le fond prend toute la place, le contenu est agrandi ou réduit
//sans déformation et reste centré. Prévient quand la fenêtre passe en paysage ou en portrait.
public class ScaledLayout extends StackPane {
	private static final double WIDE_RATIO = 1.2;
	private final Region content;
	private final double maxScale;
	private final PauseTransition oceanDelay = new PauseTransition(Duration.millis(150));
	private Consumer<Boolean> onOrientationChange = wide -> { };
	private Boolean wide;
	private OceanBackground ocean;

	public ScaledLayout(Region content, boolean animatedOcean, double maxScale) {
		this.content = content;
		this.maxScale = maxScale;
		getStyleClass().add("scaled-layout");
		content.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		content.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		getChildren().add(content);
		if (animatedOcean) {
			//Le fond est redessiné à la bonne taille, une fois le redimensionnement terminé
			oceanDelay.setOnFinished(e -> rebuildOcean());
			widthProperty().addListener((obs, old, w) -> scheduleOcean());
			heightProperty().addListener((obs, old, h) -> scheduleOcean());
		}
	}

	public void setOnOrientationChange(Consumer<Boolean> onOrientationChange) {
		this.onOrientationChange = onOrientationChange;
		if (wide != null) {
			onOrientationChange.accept(wide);
		}
	}

	private void scheduleOcean() {
		if (ocean == null) {
			rebuildOcean();
		}
		else {
			oceanDelay.playFromStart();
		}
	}

	private void rebuildOcean() {
		if (getWidth() <= 0 || getHeight() <= 0) {
			return;
		}
		if (ocean != null) {
			ocean.stop();
			getChildren().remove(ocean);
		}
		ocean = new OceanBackground(getWidth(), getHeight());
		getChildren().add(0, ocean);
	}

	@Override
	protected void layoutChildren() {
		double w = getWidth();
		double h = getHeight();
		boolean isWide = w > h * WIDE_RATIO;
		if (wide == null || isWide != wide) {
			wide = isWide;
			onOrientationChange.accept(isWide); //peut changer la taille de référence du contenu
		}
		double scale = Math.min(maxScale, Math.min(w / content.prefWidth(-1), h / content.prefHeight(-1)));
		content.setScaleX(scale);
		content.setScaleY(scale);
		super.layoutChildren();
	}
}
