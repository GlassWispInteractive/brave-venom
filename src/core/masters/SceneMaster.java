package core.masters;

import java.util.HashMap;

import core.Context;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.util.Duration;

public class SceneMaster {

	private final Context context;

	// game state
	private HashMap<String, Scene> scenes = new HashMap<>();
	private Scene scene;
	private boolean ticking = true;

	public SceneMaster(Context context) {
		this.context = context;
	}

	/**
	 * Returns the current screen.
	 *
	 * @return the current screen
	 */
	public Scene getScreen() {
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
}
