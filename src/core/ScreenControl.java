package core;

import javafx.animation.FadeTransition;
import javafx.util.Duration;

import java.util.HashMap;

public class ScreenControl implements Visible {
    // singleton object
    private static ScreenControl singleton;

    // game state
    private HashMap<String, Screen> screens;
    private Screen screen;
    private boolean ticking = true;

    private ScreenControl() {
        screens = new HashMap<>();
    }

    /**
     * Returns the ScreenControl getInstance.
     *
     * @return the ScreenControl getInstance
     */
    public static ScreenControl getInstance() {
        if (singleton == null) {
            singleton = new ScreenControl();
        }

        return singleton;
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
     * @param name the name of the screen to be shown
     */
    public void setScreen(String name) {
        // dont try to set new screen
        if (screens.get(name) == null) {
            System.out.println("invalid screen");
            return;
        }

        // fade out animation
        if (screen != null) {
            FadeTransition ft = new FadeTransition(new Duration(500), screen.getScene().getRoot());
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.play();

            ticking = false;

            ft.setOnFinished(e -> {
                fadeIn(name);
            });
        } else {
            fadeIn(name);
        }
    }

    /**
     * Adds a new screen with the given name.
     *
     * @param name   the name of the screen
     * @param screen the screen
     */
    public void addScreen(String name, Screen screen) {
        screens.put(name, screen);
        screen.getScene().getRoot().setOpacity(0);
    }

    /**
     * Removes the screen with the given name.
     *
     * @param name the name of the screen
     */
    public void removeScreen(String name) {
        screens.remove(name);
    }

    /**
     * Fadi in on the screen with the given name.
     *
     * @param name the name of the screen
     */
    private void fadeIn(String name) {
        screen = screens.get(name);
        Context.setScene(screen.getScene());

        screen.getScene().getRoot().setOpacity(1);
        ticking = true;
    }

    /**
     * Sets a new screen with the given name if no screen was assigned to the name, yet.
     *
     * @param name   the name of the screen
     * @param screen the screen
     */
    public void setScreen(String name, Screen screen) {
        // if scene id is already used, dont set screen
        if (screens.get(name) != null) {
            return;
        }

        // add screen first and then set it
        addScreen(name, screen);
        setScreen(name);
    }

    @Override
    public void tick(int ticks) {
        if (ticking && screen != null) {
            screen.tick(ticks);
        }
    }

    /**
     * Renders the current screen.
     */
    @Override
    public void render() {
        if (screen != null) {
            screen.render();
        }
    }
}
