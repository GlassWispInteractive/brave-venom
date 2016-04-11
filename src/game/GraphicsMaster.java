package game;

import core.masters.AbstractGraphicsMaster;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GraphicsMaster extends AbstractGraphicsMaster {
    // fonts
    public static final Font HUGE_FONT = Font.font("Helvetica", FontWeight.BOLD, 64);
    public static final Font BIG_FONT = Font.font("Helvetica", FontWeight.BOLD, 32);
    public static final Font DEFAULT_FONT = Font.font("Helvetica", FontWeight.BOLD, 24);
    public static final Font SMALL_FONT = Font.font("Helvetica", FontWeight.NORMAL, 16);
    
    // colors
    public static final Color FRONT = (Color) Paint.valueOf("#000000");
    public static final Color BACK = (Color) Paint.valueOf("#CFCFCF");
    public static final Color GREEN = (Color) Paint.valueOf("#1B8056");
    public static final Color DARKGREEN = (Color) Paint.valueOf("#0F4730");
    
    public GraphicsMaster() {
        windowWdith.setValue(1200);
        windowHeight.setValue(800);
        
        tileSize.setValue(16);
        panelHeight.bind(tileSize.multiply(3));
        
        gameWidth.bind(panelHeight);
        gameHeight.bind(windowHeight.subtract(panelHeight.multiply(2)));
    }
}
