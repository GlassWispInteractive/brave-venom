package core;

import core.masters.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

public abstract class Context extends Application {
	// instances from the master classes
	public final GraphicsMaster graphicsMaster;
	public final AudioMaster audioMaster;
	public final SceneMaster sceneMaster;
	public final EventMaster eventMaster;
	public final GameMaster gameMaster;

	// make the stage acessible
	private Scene scene;
	private boolean newStage = false;
	private int passedTicks = 0;
	private double lastNanoTime = System.nanoTime();
	private double time = 0;
	private ReadOnlyStringWrapper title = new ReadOnlyStringWrapper();
	private Stage stage;
	private AnimationTimer animationTimer;

	public Context(String title, GraphicsMaster graphicsMaster, AudioMaster audioMaster) {
		this.title.set(title);

		this.graphicsMaster = graphicsMaster;
		this.audioMaster = audioMaster;
		this.sceneMaster = new SceneMaster(this);
		this.eventMaster = new EventMaster(this);
		this.gameMaster = new GameMaster(this);
	}

	public void setScene(Scene scene) {
		newStage = true;
		this.scene = scene;
		stage.setScene(scene);
	}

	public void init() {
	}

	@Override
	public void start(Stage stage) {
		this.stage = stage;
		initStage();
		stage.show();
	}

	@Override
	public void stop() {
		gameMaster.stop();
		save();
	}

	private void initStage() {
		// stage settings
		stage.setTitle(title.get());
		stage.setResizable(false);
		stage.centerOnScreen();

		// event handling
		stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			eventMaster.addKeyCode(event.getCode());
		});

		stage.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
			eventMaster.removeKeyCode(event.getCode());
		});

		System.out.println(Screen.getScreens().size());
		if (Screen.getScreens().size() > 1) {

			Rectangle2D bounds = Screen.getScreens().get(1).getBounds();
			stage.setX(bounds.getMinX());
			stage.setY(bounds.getMinY());
		}
	}

	protected abstract void load();

	protected abstract void save();

	public String getTitle() {
		return title.get();
	}

	public ReadOnlyStringProperty titleProperty() {
		return title.getReadOnlyProperty();
	}

	public AnimationTimer getAnimationTimer() {
		return animationTimer;
	}

	public EventMaster getEventMaster() {
		return eventMaster;
	}

	public SceneMaster getScreenMaster() {
		return sceneMaster;
	}

	public AudioMaster getAudioMaster() {
		return audioMaster;
	}

	public GraphicsMaster getGraphicsMaster() {
		return graphicsMaster;
	}

	public SceneMaster getSceneMaster() {
		return sceneMaster;
	}

	public GameMaster getGameMaster() {
		return gameMaster;
	}
}
