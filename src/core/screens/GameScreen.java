package core.screens;

public abstract class GameScreen extends Screen {
    protected GameScreen(ScreenControl screenControl) {
        super(screenControl);
    }
    
    public abstract void prerender();
    
    public abstract void render();
}
