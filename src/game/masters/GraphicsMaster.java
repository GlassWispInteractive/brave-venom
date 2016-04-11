package game.masters;

import core.masters.AbstractGraphicsMaster;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.io.InputStream;

public class GraphicsMaster extends AbstractGraphicsMaster {

    // colors
    // TODO: move colors
    public static final Color FRONT = (Color) Paint.valueOf("#000000");
    public static final Color BACK = (Color) Paint.valueOf("#CFCFCF");
    public static final Color GREEN = (Color) Paint.valueOf("#1B8056");
    public static final Color DARKGREEN = (Color) Paint.valueOf("#0F4730");

    public static final Font LARGE_FONT;
    public static final Font NORMAL_FONT;
    public static final Font SMALL_FONT;
    public static final Font LARGE_FONT_THIN;
    public static final Font NORMAL_FONT_THIN;
    public static final Font SMALL_FONT_THIN;
    public static final Font DEFAULT_FONT;

    // fonts
    //    public static final Font HUGE_FONT = Font.font("Helvetica", FontWeight.BOLD, 64);
    //    public static final Font BIG_FONT = Font.font("Helvetica", FontWeight.BOLD, 32);
    //    public static final Font DEFAULT_FONT = Font.font("Helvetica", FontWeight.BOLD, 24);
    //    public static final Font SMALL_FONT = Font.font("Helvetica", FontWeight.NORMAL, 16);

    // Note: Fonts have to be loaded exactly once by Font.font() and further be referenced by Font.font().
    static {
        InputStream inputStream1 = GraphicsMaster.class.getResourceAsStream("../../res/font/kenvector_future.ttf");
        InputStream inputStream2 = GraphicsMaster.class.getResourceAsStream("../../res/font/kenvector_future_thin.ttf");
        LARGE_FONT = Font.loadFont(inputStream1, 30);
        NORMAL_FONT = Font.font("Kenvector Future", 20);
        SMALL_FONT = Font.font("Kenvector Future", 16);
        LARGE_FONT_THIN = Font.loadFont(inputStream2, 30);
        NORMAL_FONT_THIN = Font.font("Kenvector Future Thin", 20);
        SMALL_FONT_THIN = Font.font("Kenvector Future Thin", 16);
        DEFAULT_FONT = NORMAL_FONT;
    }

    public GraphicsMaster() {
        windowWidth.setValue(1200);
        windowHeight.setValue(800);

        tileSize.setValue(16);
        panelHeight.bind(tileSize.multiply(3));

        gameWidth.bind(panelHeight);
        gameHeight.bind(windowHeight.subtract(panelHeight.multiply(2)));

    }
}
