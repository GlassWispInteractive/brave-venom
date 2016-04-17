package game.scenes;

import java.util.ArrayList;

import core.masters.EventMaster;
import core.masters.GraphicsMaster;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

//import static game.State.*;

public class AbstractMenuScene extends Scene {
	protected GraphicsMaster sceneMaster;

	// instance

	protected ArrayList<String> menuPoints;
	protected BorderPane background;
	protected BorderPane foreground;
	private int cur;

	public AbstractMenuScene(GraphicsMaster sceneMaster) {
		super(new StackPane(), sceneMaster.windowWidth.get(), sceneMaster.windowHeight.get());
		this.sceneMaster = sceneMaster;
		init_scene();
	}

	private void init_scene() {
		background = new BorderPane();
		foreground = new BorderPane();

		((StackPane) getRoot()).getChildren().addAll(background, foreground);
		StackPane.setAlignment(background, Pos.CENTER);
		StackPane.setAlignment(foreground, Pos.CENTER);
	}

	public BorderPane getBackground() {
		return background;
	}

	public BorderPane getForeground() {
		return foreground;
	}

	public void tick(int ticks) {
		EventMaster eventControl = sceneMaster.getContext().getEventMaster();

		// event handling
		if (eventControl.isUp())
			cur = (cur + menuPoints.size() - 1) % menuPoints.size();

		if (eventControl.isDown())
			cur = (cur + 1) % menuPoints.size();

		if (eventControl.isEnter()) {
			switch (menuPoints.get(cur)) {
			case "Classic Mode":
				break;

			case "Credits":
				break;

			case "Help":
				break;

			case "Exit":
				System.exit(0);
				break;
			default:
				break;
			}
		}

		// no double key activation
		eventControl.clear();
	}
}
