package core;

import java.util.HashMap;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;

public abstract class Screen {
    protected final ScreenControl screenControl;
    
    // class members
    protected StackPane root = new StackPane();
    protected Scene scene = new Scene(root, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT, Global.BACK);
    protected HashMap<String, Node> nodes = new HashMap<>();
    protected HashMap<String, GraphicsContext> gcs = new HashMap<>();
    
    protected Screen(ScreenControl screenControl) {
        this.screenControl = screenControl;
        // create layers and extract their gc
        // layers.put("main", new Canvas(Global.WINDOW_WIDTH,
        // Global.WINDOW_HEIGHT));
        // gcs.put("main", layers.get("main").getGraphicsContext2D());
        // root.getChildren().add(layers.get("main"));
    }
    
    /**
     * Adds a new canvas object to the screen.
     *
     * @param name
     * @param x
     * @param y
     * @param w
     * @param h
     */
    protected void addCanvas(String name, double x, double y, double w, double h) {
        Canvas layer = new Canvas(w, h);
        root.getChildren().add(layer);
        layer.relocate(x, y);
        
        // update hash maps
        nodes.put(name, layer);
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
    
    public abstract void tick(int ticks);
    
    public abstract void render();
    
    public ScreenControl getScreenControl() {
        return screenControl;
    }
}
