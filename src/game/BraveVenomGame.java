package game;

import core.Context;
import game.generator.WorldBuilder;
import game.scenes.GameScene;
import game.scenes.HelpMenuScene;
import game.scenes.MainMenuScene;
import game.scenes.SettingsMenuScene;
import javafx.stage.Stage;

public class BraveVenomGame extends Context {
	private static final String TITLE = "BraveVenomGame";

	public BraveVenomGame() {
		super(TITLE);

		// general settings
		graphicsMaster.windowWidth.set(1200);
		graphicsMaster.windowHeight.set(800);
		graphicsMaster.tileSize.set(16);
		graphicsMaster.panelHeight.bind(graphicsMaster.tileSize.multiply(5));
		// sceneMaster.gameHeight.bind(sceneMaster.windowHeight.subtract(sceneMaster.panelHeight.multiply(2)));
		graphicsMaster.gameHeight.bind(graphicsMaster.windowHeight);
		graphicsMaster.gameWidth.bind(graphicsMaster.windowWidth);

		WorldBuilder world = new WorldBuilder();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() {
		super.init();
		init_scenes();
	}

	@Override
	public void start(Stage stage) {
		super.start(stage);
		graphicsMaster.showScene("main_menu");
	}

	private void init_scenes() {
		graphicsMaster.addScreen("main_menu", new MainMenuScene(graphicsMaster));
		graphicsMaster.addScreen("help_menu", new HelpMenuScene(graphicsMaster));
		graphicsMaster.addScreen("settings_menu", new SettingsMenuScene(graphicsMaster));
		graphicsMaster.addScreen("game", new GameScene(graphicsMaster));
	}

	@Override
	protected void load() {
		// TODO: implement me
	}

	@Override
	protected void save() {
		// TODO: implement me
	}
}
