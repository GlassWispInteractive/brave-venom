package core;

import core.masters.AbstractAudioMaster;
import core.masters.AbstractGraphicsMaster;
import core.masters.EventControl;
import core.screens.ScreenControl;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public abstract class Context extends Application {

    private final AbstractGraphicsMaster graphicsMaster;
    private final AbstractAudioMaster audioMaster;
    protected ScreenControl screenControl;
    protected EventControl eventControl;
    // make the stage acessible
    private Scene scene;
    private boolean newStage = false;
    private int passedTicks = 0;
    private double lastNanoTime = System.nanoTime();
    private double time = 0;
    private ReadOnlyStringWrapper title = new ReadOnlyStringWrapper();
    private Stage stage;
    private AnimationTimer animationTimer;

    public Context(String title, AbstractGraphicsMaster graphicsMaster, AbstractAudioMaster audioMaster) {
        this.title.set(title);
        this.graphicsMaster = graphicsMaster;
        this.audioMaster = audioMaster;
    }

    public void setScene(Scene scene) {
        newStage = true;
        this.scene = scene;
        stage.setScene(scene);
    }

    public void init() {
        screenControl = new ScreenControl(this);
        initAnimationTimer();
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        initStage();
        //        MainMenuScreen s = new MainMenuScreen(null);
        //        stage.setScene(s.getScene());

        stage.show();
        // animationTimer.start();
    }

    @Override
    public void stop() {
        // animationTimer.stop();
    }

    private void initStage() {
        // stage settings
        stage.setTitle(title.get());
        stage.setResizable(false);
        stage.centerOnScreen();

        // event handling
        stage.setOnCloseRequest(event -> {
            stop();
        });

        eventControl = new EventControl(this);

        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            eventControl.addKeyCode(event.getCode());
        });

        stage.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            eventControl.removeKeyCode(event.getCode());
        });

    }

    private void initAnimationTimer() {
        final double fps = 60.0;
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                // calculate time since last update.
                time += (currentNanoTime - lastNanoTime) / 1000000000.0;
                lastNanoTime = currentNanoTime;
                passedTicks = (int) Math.floor(time * fps);
                time -= passedTicks / fps;

                // adjust stage if necessary
                if (Context.this.newStage) {
                    newStage = false;
                    stage.setScene(scene);
                }

                // if (eventControl.isESC()) {
                // screenControl.showScreen("menu");
                // }

                // compute a frame
                screenControl.tick(passedTicks);
                screenControl.render();
            }
        };
    }

    public AnimationTimer getAnimationTimer() {
        return animationTimer;
    }

    public EventControl getEventControl() {
        return eventControl;
    }

    public ScreenControl getScreenControl() {
        return screenControl;
    }

    public String getTitle() {
        return title.get();
    }

    public ReadOnlyStringProperty titleProperty() {
        return title.getReadOnlyProperty();
    }

    public AbstractAudioMaster getAudioMaster() {
        return audioMaster;
    }

    public AbstractGraphicsMaster getGraphicsMaster() {
        return graphicsMaster;
    }
}
