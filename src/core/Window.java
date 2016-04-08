package core;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import screens.MenuScreen;

public class Window extends Application {
    public static boolean  music        = false;

    // make the stage acessible
    private static Scene   scene;
    private static boolean newStage     = false;

    // class members
    private AnimationTimer gameloop;
    private int            passedTicks  = 0;
    private double         lastNanoTime = System.nanoTime();
    private double         time         = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // stage settings
        stage.setTitle(Global.TITLE);
        stage.setResizable(false);
        stage.centerOnScreen();

        // event handling
        stage.setOnCloseRequest(event -> {
            gameloop.stop();
            // save game state here
        });

        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            EventControl.getEvents().addCode(event);
        });

        stage.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            EventControl.getEvents().removeCode(event);
        });

        ScreenControl ctrl = ScreenControl.getCtrl();
        ctrl.setScreen("menu", (MenuScreen.getScreen()));
        // ctrl.addScreen("", );

        gameloop = new AnimationTimer() {

            public void handle(long currentNanoTime) {
                // calculate time since last update.
                time += (currentNanoTime - lastNanoTime) / 1000000000.0;
                lastNanoTime = currentNanoTime;
                passedTicks = (int) Math.floor(time * 60.0);
                time -= passedTicks / 60.0;

                // adjust stage if necessary
                if (Window.newStage) {
                    newStage = false;

                    stage.setScene(scene);
                }

                if (EventControl.getEvents().isESC()) {
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

    public static void setScene(Scene scene) {
        Window.newStage = true;
        Window.scene = scene;
    }
}
