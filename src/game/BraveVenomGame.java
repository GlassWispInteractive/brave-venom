package game;

import core.Game;
import core.masters.AudioMaster;
import core.masters.GraphicsMaster;
import game.scenes.HelpMenuScreen;
import game.scenes.MainMenuScreen;
import game.scenes.SettingsMenuScreen;
import javafx.stage.Stage;

public class BraveVenomGame extends Game {
    private static final String TITLE = "BraveVenomGame";
    
    public BraveVenomGame() {
        super(TITLE, new GraphicsMaster(), new AudioMaster());
        
        // general settings
        graphicsMaster.getWindowWidth().set(1200);
        graphicsMaster.getWindowHeight().set(800);
        graphicsMaster.getTileSize().set(16);
        graphicsMaster.getPanelHeight().bind(graphicsMaster.getTileSize().multiply(3));
        graphicsMaster.getGameHeight()
                .bind(graphicsMaster.getWindowHeight().subtract(graphicsMaster.getPanelHeight().multiply(2)));
        graphicsMaster.getGameWidth().bind(graphicsMaster.getWindowWidth());
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
        sceneMaster.showScreen("main_menu");
    }
    
    private void init_scenes() {
        sceneMaster.addScreen("main_menu", new MainMenuScreen(this));
        sceneMaster.addScreen("help_menu", new HelpMenuScreen(this));
        sceneMaster.addScreen("settings_menu", new SettingsMenuScreen(this));
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
