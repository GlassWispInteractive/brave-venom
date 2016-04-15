//package core.masters;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.HashMap;
//import java.util.Map.Entry;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.NamedNodeMap;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
//import com.sun.org.apache.xerces.internal.parsers.DOMParser;
//
//import javafx.scene.image.Image;
//import javafx.scene.image.PixelReader;
//import javafx.scene.image.WritableImage;
//
//public class TileMaster {
//	private static final int MARGIN = 1;
//	private static final int WIDTH = 16;
//	private static final int HEIGHT = 16;
//
//	private HashMap<String, Image> sheets = new HashMap<>();
//	private HashMap<String, Image[][]> tiles = new HashMap<>();
//
//	public TileMaster() {
//		// load all tile sheets from the specific resource folder
//		try {
//			Files.walk(Paths.get("res/tilesheet")).filter(path -> !path.toString().endsWith(".xml"))
//					.forEach(filePath -> {
//						if (Files.isRegularFile(filePath)) {
//							int start = filePath.toString().lastIndexOf(System.getProperty("file.separator")) + 1;
//							int end = filePath.toString().lastIndexOf(".");
//
//							String name = filePath.toString().substring(start, end);
//							File xml = new File(filePath.toString().substring(0, end) + ".xml");
//
//							// save the full image
//							if (xml.exists()) {
//
//								sheets.put(name, new Image(filePath.toUri().toString()));
//							}
//						}
//					});
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		System.exit(0);
//
//		for (Entry<String, Image> e : sheets.entrySet()) {
//			Image sheet = e.getValue();
//			int cols = (int) ((sheet.getWidth() + MARGIN) / (WIDTH + MARGIN));
//
//			tiles.put(e.getKey(), new Image[cols][]);
//			for (int i = 0; i < cols; i++) {
//				int rows = (int) ((sheet.getHeight() + MARGIN) / (HEIGHT + MARGIN));
//				tiles.get(e.getKey())[i] = new Image[rows];
//			}
//		}
//	}
//
//	public Image getTile(String key, int tileX, int tileY) {
//		if (!sheets.containsKey(key)) {
//			throw new AssertionError("image does not exist");
//		}
//
//		// try to get image from cache
//		if (tiles.get(key)[tileX][tileY] != null) {
//			return tiles.get(key)[tileX][tileY];
//		}
//
//		// generate new image from sheet
//		PixelReader reader = sheets.get(key).getPixelReader();
//		WritableImage newImage = new WritableImage(reader, (WIDTH + MARGIN) * tileX, (HEIGHT + MARGIN) * tileY, WIDTH,
//				HEIGHT);
//
//		// put new image in cache
//		tiles.get(key)[tileX][tileY] = newImage;
//
//		return newImage;
//	}
//
//	// ...
//
//	protected Node getNode(String tagName, NodeList nodes) {
//		for (int x = 0; x < nodes.getLength(); x++) {
//			Node node = nodes.item(x);
//			if (node.getNodeName().equalsIgnoreCase(tagName)) {
//				return node;
//			}
//		}
//
//		return null;
//	}
//
//	protected String getNodeValue(Node node) {
//		NodeList childNodes = node.getChildNodes();
//		for (int x = 0; x < childNodes.getLength(); x++) {
//			Node data = childNodes.item(x);
//			if (data.getNodeType() == Node.TEXT_NODE)
//				return data.getNodeValue();
//		}
//		return "";
//	}
//
//	protected String getNodeValue(String tagName, NodeList nodes) {
//		for (int x = 0; x < nodes.getLength(); x++) {
//			Node node = nodes.item(x);
//			if (node.getNodeName().equalsIgnoreCase(tagName)) {
//				NodeList childNodes = node.getChildNodes();
//				for (int y = 0; y < childNodes.getLength(); y++) {
//					Node data = childNodes.item(y);
//					if (data.getNodeType() == Node.TEXT_NODE)
//						return data.getNodeValue();
//				}
//			}
//		}
//		return "";
//	}
//
//	protected String getNodeAttr(String attrName, Node node) {
//		NamedNodeMap attrs = node.getAttributes();
//		for (int y = 0; y < attrs.getLength(); y++) {
//			Node attr = attrs.item(y);
//			if (attr.getNodeName().equalsIgnoreCase(attrName)) {
//				return attr.getNodeValue();
//			}
//		}
//		return "";
//	}
//
//	protected String getNodeAttr(String tagName, String attrName, NodeList nodes) {
//		for (int x = 0; x < nodes.getLength(); x++) {
//			Node node = nodes.item(x);
//			if (node.getNodeName().equalsIgnoreCase(tagName)) {
//				NodeList childNodes = node.getChildNodes();
//				for (int y = 0; y < childNodes.getLength(); y++) {
//					Node data = childNodes.item(y);
//					if (data.getNodeType() == Node.ATTRIBUTE_NODE) {
//						if (data.getNodeName().equalsIgnoreCase(attrName))
//							return data.getNodeValue();
//					}
//				}
//			}
//		}
//
//		return "";
//	}
//}
