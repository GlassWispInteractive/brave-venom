package core.masters;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class GraphicsMaster {
    // static colors
    public static final Color FRONT = (Color) Paint.valueOf("#000000");
    public static final Color BACK = (Color) Paint.valueOf("#CFCFCF");
    public static final Color GREEN = (Color) Paint.valueOf("#1B8056");
    public static final Color DARKGREEN = (Color) Paint.valueOf("#0F4730");
    
    // properties
    protected IntegerProperty windowWidth = new SimpleIntegerProperty();
    protected IntegerProperty windowHeight = new SimpleIntegerProperty();
    
    protected IntegerProperty tileSize = new SimpleIntegerProperty();
    protected IntegerProperty panelHeight = new SimpleIntegerProperty();
    
    protected IntegerProperty gameWidth = new SimpleIntegerProperty();
    protected IntegerProperty gameHeight = new SimpleIntegerProperty();
    
    /**
     * constructor
     */
    public GraphicsMaster() {
    
    }
    
    /**
     * @return the windowWidth
     */
    public IntegerProperty getWindowWidth() {
        return windowWidth;
    }
    
    /**
     * @return the windowHeight
     */
    public IntegerProperty getWindowHeight() {
        return windowHeight;
    }
    
    /**
     * @return the tileSize
     */
    public IntegerProperty getTileSize() {
        return tileSize;
    }
    
    /**
     * @return the panelHeight
     */
    public IntegerProperty getPanelHeight() {
        return panelHeight;
    }
    
    /**
     * @return the gameWidth
     */
    public IntegerProperty getGameWidth() {
        return gameWidth;
    }
    
    /**
     * @return the gameHeight
     */
    public IntegerProperty getGameHeight() {
        return gameHeight;
    }
    
}
