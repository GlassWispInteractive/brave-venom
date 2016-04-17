package game.scenes;

import core.Context;
import core.masters.GameMaster;
import core.masters.GraphicsMaster;
import game.entity.Entity;
import game.entity.EntityType;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import java.util.HashMap;

public abstract class AbstractGameScene extends Scene {
	public Label tickLabel, entityLabel;
	public long allticks = 0;
	public Canvas bottomHUD;
	public Pane foreground;
	protected GraphicsMaster sceneMaster;
	protected Canvas background;
	protected Pane enemyPane;
	protected Pane shotPane;
	protected Pane playerPane;
	protected Canvas topHUD;
	protected VBox debugPane;
	private HashMap<String, GraphicsContext> gcs;

	protected AbstractGameScene(GraphicsMaster sceneMaster) {
		super(new StackPane(), sceneMaster.windowWidth.get(), sceneMaster.windowHeight.get(), sceneMaster.BACK);

		this.sceneMaster = sceneMaster;
		initScene();
		initMouseHandler();
	}

	private void initMouseHandler() {
		Pane root = (Pane) getRoot();
		root.setPrefWidth(sceneMaster.windowWidth.get());
		root.setPrefHeight(sceneMaster.windowHeight.get());
		GameMaster gameMaster = Context.instance.getGameMaster();
		double yOffset = sceneMaster.panelHeight.get();
		root.setOnMouseMoved((e) -> {
			gameMaster.mouseX = e.getX();
			gameMaster.mouseY = e.getY() - yOffset;
		});
		root.setOnMouseClicked((e) -> {
			gameMaster.mouseClicked(e.getX(), e.getY());
		});

	}

	private void initScene() {
		double gameWidth = sceneMaster.gameWidth.get();
		double gameHeight = sceneMaster.windowHeight.get();
		double windowWidth = sceneMaster.windowWidth.get();
		double windowHeight = sceneMaster.windowHeight.get();
		double panelHeight = sceneMaster.panelHeight.get();

		background = new Canvas(windowWidth, windowHeight);
		StackPane entityPanes = new StackPane();
		entityPanes.relocate(0, panelHeight);
		shotPane = new Pane();
		enemyPane = new Pane();
		playerPane = new Pane();
		forceSize(enemyPane, gameWidth, gameHeight);
		forceSize(playerPane, gameWidth, gameHeight);
		forceSize(shotPane, gameWidth, gameHeight);
		entityPanes.getChildren().addAll(shotPane, enemyPane, playerPane);

		Pane topHUDPane = new Pane();
		Pane bottomHUDPane = new Pane();
		forceSize(topHUDPane, windowWidth, panelHeight);
		forceSize(bottomHUDPane, windowWidth, panelHeight);
		topHUD = new Canvas(windowWidth, panelHeight);
		bottomHUD = new Canvas(windowWidth, panelHeight);
		topHUDPane.getChildren().addAll(topHUD);
		bottomHUDPane.getChildren().addAll(bottomHUD);
		bottomHUDPane.relocate(0, windowHeight - panelHeight);

		foreground = new Pane();
		forceSize(foreground, windowWidth, windowHeight);
		foreground.getChildren().addAll(entityPanes, topHUDPane, bottomHUDPane);
		((StackPane) getRoot()).getChildren().addAll(background, foreground);

		debugPane = new VBox(10);
		tickLabel = new Label("0 ticks");
		tickLabel.setTextFill(Paint.valueOf("#ff0000"));
		debugPane.getChildren().addAll(tickLabel);
		entityLabel = new Label("0 ticks");
		entityLabel.setTextFill(Paint.valueOf("#ff0000"));
		debugPane.getChildren().addAll(entityLabel);
		((StackPane) getRoot()).getChildren().addAll(debugPane);

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

		// redraw hash maps
		gcs.put(name, layer.getGraphicsContext2D());
	}

	public abstract void prerender();

	public abstract void render();

	public void addEntitiy(EntityType entityType, Entity entity) {
		Pane pane = getPane(entityType);
		if (pane != null) {
			Canvas canvas = entity.getCanvas();
			pane.getChildren().add(canvas);
			canvas.relocate(entity.getXImage(), entity.getYImage());
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
		case EXPLOSION:
			return shotPane;
		}
		return null;
	}
}
