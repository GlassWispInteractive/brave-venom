package core.masters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
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

	// internal members
	private HashMap<String, Image> images = new HashMap<>();

	/**
	 * constructor
	 */
	public GraphicsMaster() {
		// load all tile sheets from the specific resource folder
		try {
			Files.walk(Paths.get("res/graphics")).forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					int start = filePath.toString().lastIndexOf(System.getProperty("file.separator")) + 1;
					int end = filePath.toString().lastIndexOf(".");

					String name = filePath.toString().substring(start, end);

					// save the full image
					images.put(name, new Image(filePath.toUri().toString()));
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Image getImage(String key) {
		if (!images.containsKey(key)) {
			throw new AssertionError("image does not exist");
		}

		// put new image in cache
		return images.get(key);
	}
}
