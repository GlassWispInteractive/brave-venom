package game;

import core.Context;
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
		sceneMaster.windowWidth.set(1920);
		sceneMaster.windowHeight.set(1010);
		sceneMaster.tileSize.set(16);
		sceneMaster.panelHeight.bind(sceneMaster.tileSize.multiply(5));
		//		sceneMaster.gameHeight.bind(sceneMaster.windowHeight.subtract(sceneMaster.panelHeight.multiply(2)));
		sceneMaster.gameHeight.bind(sceneMaster.windowHeight);
		sceneMaster.gameWidth.bind(sceneMaster.windowWidth);
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
		sceneMaster.showScene("main_menu");
	}

	private void init_scenes() {
		sceneMaster.addScreen("main_menu", new MainMenuScene(sceneMaster));
		sceneMaster.addScreen("help_menu", new HelpMenuScene(sceneMaster));
		sceneMaster.addScreen("settings_menu", new SettingsMenuScene(sceneMaster));
		sceneMaster.addScreen("game", new GameScene(sceneMaster));
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
