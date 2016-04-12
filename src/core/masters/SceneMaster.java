package core.masters;

import core.Context;
import core.screens.Screen;
import javafx.scene.Scene;

import java.util.HashMap;

public class SceneMaster {
    
    private final Context context;
    
    // game state
    private HashMap<String, Screen> screens = new HashMap<>();
    private Screen screen;
    private boolean ticking = true;
    
    public SceneMaster(Context context) {
        this.context = context;
    }
    
    /**
     * Returns the current screen.
     *
     * @return the current screen
     */
    public Screen getScreen() {
        return screen;
    }
    
    /**
     * Sets the screen with the given name to be shown.
     *
     * @param name
     *            the name of the screen to be shown
     */
    public void showScreen(String name) {
        Scene scene = screens.get(name).getScene();
        context.setScene(scene);
        this.screen = screens.get(name);
        // // dont try to set new screen
        // if (screens.get(name) == null) {
        // System.out.println("invalid screen");
        // return;
        // }
        //
        // // fade out animation
        // if (screen != null) {
        // FadeTransition ft = new FadeTransition(new Duration(500),
        // screen.getScene().getRoot());
        // ft.setFromValue(1);
        // ft.setToValue(0);
        // ft.play();
        //
        // ticking = false;
        //
        // ft.setOnFinished(e -> {
        // fadeIn(name);
        // });
        // } else {
        // fadeIn(name);
        // }
    }
    
    /**
     * Adds a new screen with the given name.
     *
     * @param name
     *            the name of the screen
     * @param screen
     *            the screen
     */
    public void addScreen(String name, Screen screen) {
        screens.put(name, screen);
    }
    
    /**
     * Removes the screen with the given name.
     *
     * @param name
     *            the name of the screen
     */
    public void removeScreen(String name) {
        screens.remove(name);
    }
    
    /**
     * Fade in on the screen with the given name.
     *
     * @param name
     *            the name of the screen
     */
    private void fadeIn(String name) {
        screen = screens.get(name);
        context.setScene(screen.getScene());
        
        screen.getScene().getRoot().setOpacity(1);
        ticking = true;
    }
    
    // /**
    // * Sets a new screen with the given name if no screen was assigned to the
    // name, yet.
    // *
    // * @param name the name of the screen
    // * @param screen the screen
    // */
    // public void setScreen(String name, Screen screen) {
    // // if scene id is already used, dont set screen
    // if (screens.get(name) != null) {
    // return;
    // }
    //
    // // add screen first and then set it
    // addScreen(name, screen);
    // showScreen(name);
    // }
    
    public void tick(int ticks) {
        if (ticking && screen != null) {
            screen.tick(ticks);
        }
    }
    
    /**
     * Renders the current screen.
     */
    public void render() {
        if (screen != null) {
            screen.render();
        }
    }
    
    public Context getContext() {
        return context;
    }
}
