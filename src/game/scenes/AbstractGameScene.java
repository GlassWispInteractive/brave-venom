package game.scenes;

import core.masters.SceneMaster;
import game.entity.Entity;
import game.entity.EntityType;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.HashMap;

public abstract class AbstractGameScene extends Scene {
	protected SceneMaster sceneMaster;
	protected Canvas background;
	protected Pane enemyPane;
	protected Pane shotPane;
	protected Pane playerPane;
	protected Canvas topHUD;
	protected Canvas bottomHUD;
	private HashMap<String, GraphicsContext> gcs;

	protected AbstractGameScene(SceneMaster sceneMaster) {
		super(new StackPane(), sceneMaster.windowWidth.get(), sceneMaster.windowHeight.get(), sceneMaster.BACK);

		this.sceneMaster = sceneMaster;
		init_scene();
	}

	private void init_scene() {
		double gameWidth = sceneMaster.gameWidth.get();
		double gameHeight = sceneMaster.gameHeight.get();
		double windowWidth = sceneMaster.windowWidth.get();
		double windowHeight = sceneMaster.windowHeight.get();
		double panelHeight = sceneMaster.panelHeight.get();

		background = new Canvas(windowWidth, windowHeight);
		StackPane entityPanes = new StackPane();
		enemyPane = new Pane();
		playerPane = new Pane();
		shotPane = new Pane();
		forceSize(enemyPane, gameWidth, gameHeight);
		forceSize(playerPane, gameWidth, gameHeight);
		forceSize(shotPane, gameWidth, gameHeight);
		entityPanes.getChildren().addAll(enemyPane, playerPane, shotPane);
		BorderPane foreground = new BorderPane();
		topHUD = new Canvas(windowWidth, panelHeight);
		bottomHUD = new Canvas(windowWidth, panelHeight);
		foreground.setTop(topHUD);
		foreground.setCenter(entityPanes);
		foreground.setBottom(bottomHUD);
		((StackPane) getRoot()).getChildren().addAll(background, foreground);
	}

	private void forceSize(Pane pane, double width, double height) {
		pane.setPrefWidth(width);
		pane.setPrefHeight(height);
		pane.setMaxWidth(width);
		pane.setMaxHeight(height);
		pane.setMinWidth(width);
		pane.setMinHeight(height);
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
		if (pane != null) {
			Canvas canvas = entity.getCanvas();
			pane.getChildren().add(canvas);
			canvas.relocate(entity.getX(), entity.getY());
		}
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
