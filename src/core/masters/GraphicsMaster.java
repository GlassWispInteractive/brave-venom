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
    public final IntegerProperty windowWidth = new SimpleIntegerProperty();
    public final IntegerProperty windowHeight = new SimpleIntegerProperty();
    
    public final IntegerProperty tileSize = new SimpleIntegerProperty();
    public final IntegerProperty panelHeight = new SimpleIntegerProperty();
    
    public final IntegerProperty gameWidth = new SimpleIntegerProperty();
    public final IntegerProperty gameHeight = new SimpleIntegerProperty();
    
    /**
     * constructor
     */
    public GraphicsMaster() {
    
    }
}
