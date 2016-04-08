package core;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Global {
    // game title
    public static final String TITLE         = "brave venom";

    // sizes
    public static final int    WINDOW_WIDTH  = 1200;
    public static final int    WINDOW_HEIGHT = 800;

    private static final int   TILE_SIZE     = 16;
    public static final int    PANEL_HEIGHT  = 3 * TILE_SIZE;
    public static final int    GAME_WIDTH    = WINDOW_WIDTH;
    public static final int    GAME_HEIGHT   = WINDOW_HEIGHT - 2 * PANEL_HEIGHT;

    // fonts
    public static final Font   HUGE_FONT     = Font.font("Helvetica", FontWeight.BOLD, 64);
    public static final Font   BIG_FONT      = Font.font("Helvetica", FontWeight.BOLD, 32);
    public static final Font   DEFAULT_FONT  = Font.font("Helvetica", FontWeight.BOLD, 24);
    public static final Font   SMALL_FONT    = Font.font("Helvetica", FontWeight.NORMAL, 16);

    // colors
    public static final Color  FRONT         = (Color) Paint.valueOf("#000000");
    public static final Color  BACK          = (Color) Paint.valueOf("#CFCFCF");
    public static final Color  GREEN         = (Color) Paint.valueOf("#1B8056");
    public static final Color  DARKGREEN     = (Color) Paint.valueOf("#0F4730");;

    /**
     * never try to instance an object
     */
    private Global() {
        throw new AssertionError();
    }

}
