package core.masters;

import java.util.HashMap;

import core.Context;
import javafx.scene.Scene;

public class SceneMaster {
    
    private final Context context;
    
    // game state
    private HashMap<String, Scene> scenes = new HashMap<>();
    private Scene scene;
    private boolean ticking = true;
    
    public SceneMaster(Context context) {
        this.context = context;
    }
    
    /**
     * Returns the current screen.
     *
     * @return the current screen
     */
    public Scene getScreen() {
        return scene;
    }
    
    /**
     * Sets the screen with the given name to be shown.
     *
     * @param name
     *            the name of the screen to be shown
     */
    public void showScreen(String name) {
        // dont try to set new screen
        if (scenes.get(name) == null) {
            System.err.println("scene name is invalid");
            return;
        }
        
        Scene scene = scenes.get(name);
        context.setScene(scene);
        this.scene = scenes.get(name);
    }
    
    /**
     * Adds a new screen with the given name.
     *
     * @param name
     *            the name of the screen
     * @param screen
     *            the screen
     */
    public void addScreen(String name, Scene screen) {
        scenes.put(name, screen);
    }
    
    /**
     * Removes the screen with the given name.
     *
     * @param name
     *            the name of the screen
     */
    public void removeScreen(String name) {
        scenes.remove(name);
    }
    
    /**
     * Fade in on the screen with the given name.
     *
     * @param name
     *            the name of the screen
     */
    private void fadeIn(String name) {
        scene = scenes.get(name);
        context.setScene(scene);
        
        scene.getRoot().setOpacity(1);
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
        if (ticking && scene != null) {
        }
    }
    
    /**
     * Renders the current screen.
     */
    public void render() {
        if (scene != null) {
        }
    }
    
    public Context getContext() {
        return context;
    }
}
