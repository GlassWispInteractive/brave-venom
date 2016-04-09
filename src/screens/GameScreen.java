package screens;

import core.Screen;
import core.ScreenControl;

public abstract class GameScreen extends Screen {
    protected GameScreen(ScreenControl screenControl) {
        super(screenControl);
    }

    public abstract void prerender();

    public abstract void render();
}
