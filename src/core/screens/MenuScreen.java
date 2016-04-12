package core.screens;

import core.Context;
import core.masters.EventMaster;
import core.masters.SceneMaster;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

//import static game.State.*;

public class MenuScreen extends Screen {
    // instance
    protected ArrayList<String> menuPoints;
    private int cur;
    private BorderPane background;
    private BorderPane foreground;

    public MenuScreen(SceneMaster screenControl) {
        super(screenControl);

        init_scene();
    }

    private void init_scene() {
        background = new BorderPane();
        foreground = new BorderPane();
        root.getChildren().addAll(background, foreground);
        StackPane.setAlignment(background, Pos.CENTER);
        StackPane.setAlignment(foreground, Pos.CENTER);
    }

    public BorderPane getBackground() {
        return background;
    }

    public BorderPane getForeground() {
        return foreground;
    }

    public final Context getContext() {
        return screenControl.getContext();
    }

    @Override
    public void tick(int ticks) {
        EventMaster eventControl = getContext().getEventControl();

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

    @Override
    public void render() {
        // // start from clean screen
        // final GraphicsContext gc = gcs.get("main");
        // gc.clearRect(0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT);
        //
        // // canvas settings
        // final double w = gc.getCanvas().getWidth();
        //
        // // render logo image
        // gc.drawImage(logo, (w - logo.getWidth()) / 2, 80);
        //
        // // font type
        // gc.setFont(Global.DEFAULT_FONT);
        // gc.setTextAlign(TextAlignment.CENTER);
        // gc.setTextBaseline(VPos.CENTER);
        //
        // gc.setLineWidth(1);
        //
        // for (int i = 0; i < menuPoints.size(); i++) {
        // // render box
        // gc.setFill(Global.FRONT.deriveColor(0, 1, 1, 0.7));
        // gc.fillRoundRect((w - 200) / 2, 200 + 90 * (i + 1), 200, 60, 60,
        // 200);
        //
        // // render text on box
        // gc.setFill(i != cur ? Global.GREEN : Global.GREEN.brighter());
        // gc.fillText(menuPoints.get(i), w / 2, 200 + 90 * (i + 1) + 30);
        // }
    }
}
