package core;

import core.masters.AudioMaster;
import core.masters.EventMaster;
import core.masters.GraphicsMaster;
import core.masters.SceneMaster;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public abstract class Context extends Application {
	// instances from the master classes
	protected final GraphicsMaster graphicsMaster;
	protected final AudioMaster audioMaster;
	protected final SceneMaster sceneMaster;
	protected final EventMaster eventMaster;
//	protected final TileMaster tileMaster;

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
//		this.tileMaster = new TileMaster();
	}

	public void setScene(Scene scene) {
		newStage = true;
		this.scene = scene;
		stage.setScene(scene);
	}

	@Override
	public void init() {
		initAnimationTimer();
	}

	@Override
	public void start(Stage stage) {
		this.stage = stage;
		initStage();
		// MainMenuScreen s = new MainMenuScreen(null);
		// stage.setScene(s.getScene());

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
		stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			eventMaster.addKeyCode(event.getCode());
		});

		stage.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
			eventMaster.removeKeyCode(event.getCode());
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
				sceneMaster.tick(passedTicks);
				sceneMaster.render();
			}
		};
	}

	public AnimationTimer getAnimationTimer() {
		return animationTimer;
	}

	public EventMaster getEventControl() {
		return eventMaster;
	}

	public SceneMaster getScreenControl() {
		return sceneMaster;
	}

	public String getTitle() {
		return title.get();
	}

	public ReadOnlyStringProperty titleProperty() {
		return title.getReadOnlyProperty();
	}

	public AudioMaster getAudioMaster() {
		return audioMaster;
	}

	public GraphicsMaster getGraphicsMaster() {
		return graphicsMaster;
	}
}
