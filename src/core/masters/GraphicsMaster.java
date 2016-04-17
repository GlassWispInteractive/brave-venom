package core.masters;

import core.Context;
import javafx.animation.FadeTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class GraphicsMaster {
	public final Color FRONT = (Color) Paint.valueOf("#CFCFCF");
	public final Color BACK = (Color) Paint.valueOf("#000000");
	public final Color GREEN = (Color) Paint.valueOf("#1B8056");
	public final Color DARKGREEN = (Color) Paint.valueOf("#0F4730");

	// properties
	public final IntegerProperty windowWidth = new SimpleIntegerProperty();
	public final IntegerProperty windowHeight = new SimpleIntegerProperty();

	public final IntegerProperty tileSize = new SimpleIntegerProperty();
	public final IntegerProperty panelHeight = new SimpleIntegerProperty();

	public final IntegerProperty gameWidth = new SimpleIntegerProperty();
	public final IntegerProperty gameHeight = new SimpleIntegerProperty();
	private final Context context;
	// internal members
	private HashMap<String, Image> images = new HashMap<>();
	// game state
	private HashMap<String, Scene> scenes = new HashMap<>();
	private Scene scene;
	private boolean ticking = true;

	public GraphicsMaster(Context context) {
		this.context = context;
		try {
			Files.walk(Paths.get("res/graphics")).forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					int start = filePath.toString().lastIndexOf(System.getProperty("file.separator")) + 1;
					int end = filePath.toString().lastIndexOf(".");

					String name = filePath.toString().substring(start, end);

					// save the full image
					images.put(name, new Image(filePath.toUri().toString()));
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Image getImage(String key) {
		if (!images.containsKey(key)) {
			throw new AssertionError("image does not exist: " + key);
		}

		// put new image in cache
		return images.get(key);
	}

	/**
	 * Returns the current screen.
	 *
	 * @return the current screen
	 */
	public Scene getScene() {
		return scene;
	}

	/**
	 * Sets the screen with the given name to be shown. The scene root will be faded.
	 *
	 * @param name the name of the screen to be shown
	 */
	public void showScene(String name) {
		assert scenes.containsKey(name) : "scene name is invalid";

		showScene(name, scenes.get(name).getRoot());
	}

	/**
	 * Sets the screen with the given name to be shown. The given node will be faded.
	 *
	 * @param name     the name of the screen to be shown
	 * @param nodeFade the node which will be faded
	 */
	public void showScene(String name, Node nodeFade) {
		assert scenes.containsKey(name) : "scene name is invalid";

		// update the showing scene
		boolean firstStart = (scene == null);
		scene = scenes.get(name);
		context.setScene(scene);

		if (!firstStart) {
			// show a fade in animation
			FadeTransition ft = new FadeTransition(Duration.millis(800));
			ft.setNode(nodeFade);
			ft.setFromValue(0);
			ft.setToValue(1);
			ft.play();
		}
	}

	/**
	 * Adds a new screen with the given name.
	 *
	 * @param name   the name of the screen
	 * @param screen the screen
	 */
	public void addScreen(String name, Scene screen) {
		scenes.put(name, screen);
	}

	/**
	 * Removes the screen with the given name.
	 *
	 * @param name the name of the screen
	 */
	public void removeScreen(String name) {
		scenes.remove(name);
	}

	public void tick(int ticks) {
		if (ticking && scene != null) {
		}
	}

	/**
	 * Renders the current screen.
	 */
	public void render() {
		if (scene != null) {
		}
	}

	public Context getContext() {
		return context;
	}

	public Scene getScene(String key) {
		return scenes.get(key);
	}
}
