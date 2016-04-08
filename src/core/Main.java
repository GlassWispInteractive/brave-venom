package core;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import screens.EmptyScreen;
import screens.MenuScreen;

public class Main extends Application {
    public static boolean music = false;

    // make the stage acessible
    private static Scene scene;
    private static boolean newStage = false;

    // class members
    private AnimationTimer gameloop;
    private int passedTicks = 0;
    private double lastNanoTime = System.nanoTime();
    private double time = 0;

    public static void main(String[] args) {
        launch(args);
    }

    public static void setScene(Scene scene) {
        Main.newStage = true;
        Main.scene = scene;
    }

    @Override
    public void init() {

    }

    @Override
    public void start(Stage stage) {
        // stage settings
        stage.setTitle(Global.TITLE);
        stage.setResizable(false);
        stage.centerOnScreen();

        // event handling
        stage.setOnCloseRequest(event -> {
            stop();
        });

        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            EventControl.getInstance().addKeyCode(event.getCode());
        });

        stage.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            EventControl.getInstance().removeKeyCode(event.getCode());
        });

        ScreenControl ctrl = ScreenControl.getInstance();
        ctrl.addScreen("menu", MenuScreen.instance());
        ctrl.setScreen("empty", new EmptyScreen());
        // ctrl.addScreen("", );

        gameloop = new AnimationTimer() {

            @Override
            public void handle(long currentNanoTime) {
                // calculate time since last update.
                time += (currentNanoTime - lastNanoTime) / 1000000000.0;
                lastNanoTime = currentNanoTime;
                passedTicks = (int) Math.floor(time * 60.0);
                time -= passedTicks / 60.0;

                // adjust stage if necessary
                if (Main.newStage) {
                    newStage = false;

                    stage.setScene(scene);
                }

                if (EventControl.getInstance().isESC()) {
                    ctrl.setScreen("menu");
                }

                // compute a frame
                ctrl.tick(passedTicks);
                ctrl.render();
            }
        };

        stage.show();
        gameloop.start();
    }

    @Override
    public void stop() {
        gameloop.stop();
        save();
    }

    public void load() {
        // TODO: implement me
    }

    public void save() {
        // TODO: implement me
    }

}
