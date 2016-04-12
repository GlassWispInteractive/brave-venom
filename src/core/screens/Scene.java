//package core.screens;
//
//import core.masters.GraphicsMaster;
//import core.masters.SceneMaster;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.canvas.Canvas;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.layout.StackPane;
//
//import java.util.HashMap;
//
//public abstract class Scene {
//    protected final SceneMaster screenControl;
//    
//    // class members
//    protected StackPane root = new StackPane();
//    protected Scene scene;
//    protected HashMap<String, Node> nodes = new HashMap<>();
//    protected HashMap<String, GraphicsContext> gcs = new HashMap<>();
//    
//    protected Scene(SceneMaster screenControl) {
//        this.screenControl = screenControl;
//        
//        
//        // create layers and extract their gc
//        // layers.put("main", new Canvas(Global.WINDOW_WIDTH,
//        // Global.WINDOW_HEIGHT));
//        // gcs.put("main", layers.get("main").getGraphicsContext2D());
//        // root.getChildren().add(layers.get("main"));
//    }
//    
//   
//    
//    /**
//     * Returns the scene.
//     *
//     * @return the scene
//     */
//    public Scene getScene() {
//        return scene;
//    }
//    
//    public abstract void tick(int ticks);
//    
//    public abstract void render();
//    
//    public final SceneMaster getScreenControl() {
//        return screenControl;
//    }
//    
//    public final GraphicsMaster getGraphicsMaster() {
//        return screenControl.getContext().getGraphicsMaster();
//    }
//}
