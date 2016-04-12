package core.screens;

import javafx.scene.Scene;

public abstract class SceneDecorator extends Scene {
    protected Scene decoratedScreen;
    
    public SceneDecorator(Scene decoratedScene) {
        super(decoratedScene.getRoot());
        this.decoratedScreen = decoratedScene;
    }
}
