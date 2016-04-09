package core;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import screens.EmptyScreen;
import screens.MenuScreen;

public abstract class Context extends Application {

    // make the stage acessible
    private static Scene scene;
    private static boolean newStage = false;

    private AnimationTimer animationTimer;
    private ScreenControl screenControl;
    private int passedTicks = 0;
    private double lastNanoTime = System.nanoTime();
    private double time = 0;

    private ReadOnlyStringWrapper title = new ReadOnlyStringWrapper();
    private Stage stage;

    public Context(String title) {
        this.title.set(title);
    }

    public static void setScene(Scene scene) {
        Context.newStage = true;
        Context.scene = scene;
    }

    public void init() {
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        initStage();
        initScreenControl();
        initAnimationTimes();

        stage.show();
        animationTimer.start();
    }

    @Override
    public void stop() {
        animationTimer.stop();
    }


    private void initStage() {
        // stage settings
        stage.setTitle(Global.TITLE);
        stage.setResizable(false);
        stage.centerOnScreen();

        // event handling
        stage.setOnCloseRequest(event -> {
            stop();
        });

        // TODO: use KeyCodeCombination instead?
        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            EventControl.getInstance().addKeyCode(event.getCode());
        });

        // TODO: use KeyCodeCombination instead?
        stage.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            EventControl.getInstance().removeKeyCode(event.getCode());
        });

    }

    private void initScreenControl() {
        screenControl = ScreenControl.getInstance();
        screenControl.addScreen("menu", MenuScreen.instance());
        screenControl.setScreen("empty", new EmptyScreen());
        // ctrl.addScreen("", );
    }

    private void initAnimationTimes() {
        animationTimer = new AnimationTimer() {

            @Override
            public void handle(long currentNanoTime) {
                // calculate time since last update.
                time += (currentNanoTime - lastNanoTime) / 1000000000.0;
                lastNanoTime = currentNanoTime;
                passedTicks = (int) Math.floor(time * 60.0);
                time -= passedTicks / 60.0;

                // adjust stage if necessary
                if (Context.newStage) {
                    newStage = false;

                    stage.setScene(scene);
                }

                if (EventControl.getInstance().isESC()) {
                    screenControl.setScreen("menu");
                }

                // compute a frame
                screenControl.tick(passedTicks);
                screenControl.render();
            }
        };
    }

    public String getTitle() {
        return title.get();
    }

    public ReadOnlyStringProperty titleProperty() {
        return title.getReadOnlyProperty();
    }
}
