package core;

import core.audio.AbstractAudioMaster;
import core.graphics.AbstractGraphicsMaster;
import javafx.stage.Stage;

public abstract class Game extends Context {

    private final AbstractGraphicsMaster graphicsMaster;
    private final AbstractAudioMaster audioMaster;

    public Game(String title, AbstractGraphicsMaster graphicsMaster, AbstractAudioMaster audioMaster) {
        super(title);
        this.graphicsMaster = graphicsMaster;
        this.audioMaster = audioMaster;
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

    public AbstractAudioMaster getAudioMaster() {
        return audioMaster;
    }

    public AbstractGraphicsMaster getGraphicsMaster() {
        return graphicsMaster;
    }
}
