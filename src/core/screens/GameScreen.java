package core.screens;

import core.masters.SceneMaster;

public abstract class GameScreen extends Screen {
    protected GameScreen(SceneMaster screenControl) {
        super(screenControl);
    }
    
    public abstract void prerender();
    
    public abstract void render();
}
