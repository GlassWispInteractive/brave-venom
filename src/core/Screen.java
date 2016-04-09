package core;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.HashMap;

public abstract class Screen implements Visible {
    protected final ScreenControl screenControl;

    // class members
    protected Group group = new Group();
    protected Scene scene = new Scene(group, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT, Global.BACK);
    protected HashMap<String, Canvas> layers = new HashMap<>();
    protected HashMap<String, GraphicsContext> gcs = new HashMap<>();

    protected Screen(ScreenControl screenControl) {
        this.screenControl = screenControl;
        // create layers and extract their gc
        layers.put("main", new Canvas(Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT));
        gcs.put("main", layers.get("main").getGraphicsContext2D());
        group.getChildren().add(layers.get("main"));
    }

    /**
     * Adds a new layer to the screen.
     *
     * @param name
     * @param x
     * @param y
     * @param w
     * @param h
     */
    protected void addLayer(String name, double x, double y, double w, double h) {
        Canvas layer = new Canvas(w, h);
        group.getChildren().add(layer);
        layer.relocate(x, y);

        // update hash maps
        layers.put(name, layer);
        gcs.put(name, layer.getGraphicsContext2D());
    }

    /**
     * Returns the scene.
     *
     * @return the scene
     */
    public Scene getScene() {
        return scene;
    }

    @Override
    public abstract void tick(int ticks);

    @Override
    public abstract void render();

    public ScreenControl getScreenControl() {
        return screenControl;
    }
}
