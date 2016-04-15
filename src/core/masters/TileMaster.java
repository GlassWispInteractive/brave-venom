package core.masters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class TileMaster {
	private static final int MARGIN = 1;
	private static final int WIDTH = 16;
	private static final int HEIGHT = 16;

	private HashMap<String, Image> sheets = new HashMap<>();
	private HashMap<String, Image[][]> tiles = new HashMap<>();

	public TileMaster() {
		// load all tile sheets from the specific resource folder
		try {
			Files.walk(Paths.get("res/tilesheet")).forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					int start = filePath.toString().lastIndexOf(System.getProperty("file.separator")) + 1;
					int end = filePath.toString().lastIndexOf(".");
					
					String name = filePath.toString().substring(start, end);

					// save the full image
					sheets.put(name, new Image(filePath.toUri().toString()));
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Entry<String, Image> e : sheets.entrySet()) {
			Image sheet = e.getValue();
			int cols = (int) ((sheet.getWidth() + MARGIN) / (WIDTH + MARGIN));

			tiles.put(e.getKey(), new Image[cols][]);
			for (int i = 0; i < cols; i++) {
				int rows = (int) ((sheet.getHeight() + MARGIN) / (HEIGHT + MARGIN));
				tiles.get(e.getKey())[i] = new Image[rows];
			}
		}
	}

	public Image getTile(String key, int tileX, int tileY) {
		if (!sheets.containsKey(key)) {
			throw new AssertionError("image does not exist");
		}

		// try to get image from cache
		if (tiles.get(key)[tileX][tileY] != null) {
			return tiles.get(key)[tileX][tileY];
		}

		// generate new image from sheet
		PixelReader reader = sheets.get(key).getPixelReader();
		WritableImage newImage = new WritableImage(reader, (WIDTH + MARGIN) * tileX, (HEIGHT + MARGIN) * tileY, WIDTH,
				HEIGHT);

		// put new image in cache
		tiles.get(key)[tileX][tileY] = newImage;

		return newImage;
	}
}
