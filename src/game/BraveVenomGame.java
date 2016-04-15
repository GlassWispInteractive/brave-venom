package game;

import core.Context;
import core.masters.AudioMaster;
import core.masters.GraphicsMaster;
import game.scenes.GameScene;
import game.scenes.HelpMenuScene;
import game.scenes.MainMenuScene;
import game.scenes.SettingsMenuScene;
import javafx.stage.Stage;

public class BraveVenomGame extends Context {
	private static final String TITLE = "BraveVenomGame";

	public BraveVenomGame() {
		super(TITLE, new GraphicsMaster(), new AudioMaster());

		// general settings
		graphicsMaster.windowWidth.set(1200);
		graphicsMaster.windowHeight.set(800);
		graphicsMaster.tileSize.set(16);
		graphicsMaster.panelHeight.bind(graphicsMaster.tileSize.multiply(3));
		graphicsMaster.gameHeight.bind(graphicsMaster.windowHeight.subtract(graphicsMaster.panelHeight.multiply(2)));
		graphicsMaster.gameWidth.bind(graphicsMaster.windowWidth);
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
