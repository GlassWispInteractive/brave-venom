package core;

import core.masters.AbstractAudioMaster;
import core.masters.AbstractGraphicsMaster;
import javafx.stage.Stage;

public abstract class Game extends Context {

    
    public Game(String title, AbstractGraphicsMaster graphicsMaster, AbstractAudioMaster audioMaster) {
        super(title, graphicsMaster, audioMaster);
    }
    
    @Override
    public void init() {
        super.init();
    }
    
    @Override
    public void start(Stage stage) {
        super.start(stage);
    }
    
    @Override
    public void stop() {
        super.stop();
        save();
    }
    
    protected abstract void load();
    
    protected abstract void save();
}
