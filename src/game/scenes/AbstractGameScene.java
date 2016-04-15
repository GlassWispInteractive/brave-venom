package game.scenes;

import java.util.HashMap;

import core.masters.SceneMaster;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public abstract class AbstractGameScene extends Scene {
	protected SceneMaster sceneMaster;
	protected BorderPane background;
	protected BorderPane enemyPane;
	protected BorderPane bulletPane;
	protected BorderPane playerPane;
	protected BorderPane topHUD;
	protected BorderPane bottomHUD;
	private HashMap<String, GraphicsContext> gcs;

	protected AbstractGameScene(SceneMaster sceneMaster) {
		super(new StackPane(), sceneMaster.windowWidth.get(), sceneMaster.windowHeight.get(), sceneMaster.BACK);

		this.sceneMaster = sceneMaster;
		init_scene();
	}

	private void init_scene() {
		background = new BorderPane();
		enemyPane = new BorderPane();
		playerPane = new BorderPane();
		bulletPane = new BorderPane();
		topHUD = new BorderPane();
		bottomHUD = new BorderPane();

		((StackPane) getRoot()).getChildren().addAll(background, enemyPane, playerPane, bulletPane, topHUD, bottomHUD);
		StackPane.setAlignment(background, Pos.CENTER);
		StackPane.setAlignment(enemyPane, Pos.CENTER);
		StackPane.setAlignment(playerPane, Pos.CENTER);
		StackPane.setAlignment(bulletPane, Pos.CENTER);
		StackPane.setAlignment(topHUD, Pos.CENTER);
		StackPane.setAlignment(bottomHUD, Pos.CENTER);
	}

	/**
	 * Adds a new canvas object to the scene.
	 *
	 * @param name
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	protected void addCanvas(String name, double x, double y, double w, double h) {
		Canvas layer = new Canvas(w, h);
		((StackPane) getRoot()).getChildren().add(layer);
		layer.relocate(x, y);

		// update hash maps
		gcs.put(name, layer.getGraphicsContext2D());
	}

	public abstract void prerender();

	public abstract void render();
}
