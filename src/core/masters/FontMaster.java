/**
 * 
 */
package core.masters;

import java.io.InputStream;

import javafx.scene.text.Font;

public class FontMaster {
    public static final Font LARGE_FONT;
    public static final Font NORMAL_FONT;
    public static final Font SMALL_FONT;
    public static final Font LARGE_FONT_THIN;
    public static final Font NORMAL_FONT_THIN;
    public static final Font SMALL_FONT_THIN;
    public static final Font DEFAULT_FONT;
    
    // initialization of the static attributes
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
}
