/**
 *
 */
package core.masters;

import javafx.scene.text.Font;

import java.io.InputStream;

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
		// font loading does not work in the current version
		// Font.loadFont("../../res/font/kenvector_future.ttf", 30);
		// Font.loadFont("../../res/font/kenvector_future_thin.ttf", 30);
		InputStream inputStream1 = FontMaster.class.getResourceAsStream("../../res/font/kenvector_future.ttf");
		InputStream inputStream2 = FontMaster.class.getResourceAsStream("../../res/font/kenvector_future_thin.ttf");

		System.out.println(inputStream1);
		LARGE_FONT = Font.loadFont(inputStream1, 30);
		NORMAL_FONT = Font.font("Kenvector Future", 20);
		SMALL_FONT = Font.font("Kenvector Future", 16);
		LARGE_FONT_THIN = Font.loadFont(inputStream2, 30);
		NORMAL_FONT_THIN = Font.font("Kenvector Future Thin", 20);
		SMALL_FONT_THIN = Font.font("Kenvector Future Thin", 16);
		DEFAULT_FONT = NORMAL_FONT;
	}
}
