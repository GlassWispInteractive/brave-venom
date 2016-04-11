package game.masters;

import core.Game;
import game.screens.HelpMenuScreen;
import game.screens.MainMenuScreen;
import game.screens.SettingsMenuScreen;
import javafx.stage.Stage;

public class BraveVenomGame extends Game {
    private static final String TITLE = "BraveVenomGame";

    public BraveVenomGame() {
        super(TITLE, new GraphicsMaster(), new AudioMaster());
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
        screenControl.showScreen("main_menu");
    }

    private void init_scenes() {
        screenControl.addScreen("main_menu", new MainMenuScreen(screenControl));
        screenControl.addScreen("help_menu", new HelpMenuScreen(screenControl));
        screenControl.addScreen("settings_menu", new SettingsMenuScreen(screenControl));
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
