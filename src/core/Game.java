package core;

import core.audio.AudioManager;
import core.graphics.GraphicsManager;
import javafx.stage.Stage;

public abstract class Game extends Context {

    private final GraphicsManager graphicsManager;
    private final AudioManager audioManager;

    public Game(String title, GraphicsManager graphicsManager, AudioManager audioManager) {
        super(title);
        this.graphicsManager = graphicsManager;
        this.audioManager = audioManager;
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

    public AudioManager getAudioManager() {
        return audioManager;
    }

    public GraphicsManager getGraphicsManager() {
        return graphicsManager;
    }
}
