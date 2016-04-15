package core.masters;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

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
			Files.walk(Paths.get("src/res/graphics")).forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					String[] s = filePath.toString().split(File.separator);
					String name = s[s.length - 1];
					name = name.substring(0, name.lastIndexOf("."));

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
