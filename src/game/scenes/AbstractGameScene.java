package game.scenes;

import core.masters.SceneMaster;
import game.entity.Entity;
import game.entity.EntityType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.HashMap;

public abstract class AbstractGameScene extends Scene {
	protected SceneMaster sceneMaster;
	protected BorderPane background;
	protected Pane enemyPane;
	protected Pane shotPane;
	protected Pane playerPane;
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
		enemyPane = new Pane();
		playerPane = new Pane();
		shotPane = new Pane();
		topHUD = new BorderPane();
		bottomHUD = new BorderPane();

		((StackPane) getRoot()).getChildren().addAll(background, enemyPane, playerPane, shotPane, topHUD, bottomHUD);
		StackPane.setAlignment(background, Pos.CENTER);
		StackPane.setAlignment(enemyPane, Pos.CENTER);
		StackPane.setAlignment(playerPane, Pos.CENTER);
		StackPane.setAlignment(shotPane, Pos.CENTER);
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

	public void addEntitiy(EntityType entityType, Entity entity) {
		Pane pane = getPane(entityType);
		if (pane != null)
			pane.getChildren().add(entity.getCanvas());
	}

	public void removeEntitiy(EntityType entityType, Entity entity) {
		Pane pane = getPane(entityType);
		if (pane != null)
			pane.getChildren().remove(entity.getCanvas());
	}

	private Pane getPane(EntityType entityType) {
		switch (entityType) {
		case PLAYER:
			return playerPane;
		case ENEMY:
			return enemyPane;
		case SHOT:
			return shotPane;
		}
		return null;
	}
}
