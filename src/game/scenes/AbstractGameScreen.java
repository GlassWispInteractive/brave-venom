package game.scenes;

import java.util.HashMap;

import core.Context;
import core.masters.GraphicsMaster;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;

public abstract class AbstractGameScreen extends Scene {
	protected Context context;

	private HashMap<String, GraphicsContext> gcs;

	/**
	 * constructor
	 *
	 * @param context
	 */
	protected AbstractGameScreen(Context context) {
		super(new Group(), context.getGraphicsMaster().windowWidth.get(),
				context.getGraphicsMaster().windowHeight.get(), GraphicsMaster.BACK);

		this.context = context;
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
