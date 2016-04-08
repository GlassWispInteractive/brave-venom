package screens;

import core.EventControl;
import core.Global;
import core.Screen;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.Arrays;

//import static game.State.*;

public class MenuScreen extends Screen {
    // instance
    private static MenuScreen instance;

    private Image logo;

    private ArrayList<String> list;
    private int cur;

    private MenuScreen() {
        super();

        // init
        setMenuPoints(new String[] { "Start", "Wildcard", "Help", "Credits", "Exit" });

        // load logo
        logo = new Image("/res/graphics/logo.png");
    }

    public static MenuScreen instance() {
        if (instance == null) {
            instance = new MenuScreen();
        }

        return instance;
    }

    @Override
    public void tick(int ticks) {
        EventControl e = EventControl.getInstance();

        // event handling
        if (e.isUp())
            cur = (cur + list.size() - 1) % list.size();

        if (e.isDown())
            cur = (cur + 1) % list.size();

        if (e.isEnter()) {
            switch (list.get(cur)) {
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
        EventControl.getInstance().clear();
    }

    @Override
    public void render() {
        // start from clean screen
        final GraphicsContext gc = gcs.get("main");
        gc.clearRect(0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT);

        // canvas settings
        final double w = gc.getCanvas().getWidth();

        // render logo image
        gc.drawImage(logo, (w - logo.getWidth()) / 2, 80);

        // font type
        gc.setFont(Global.DEFAULT_FONT);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);

        gc.setLineWidth(1);

        for (int i = 0; i < list.size(); i++) {
            // render box
            gc.setFill(Global.FRONT.deriveColor(0, 1, 1, 0.7));
            gc.fillRoundRect((w - 200) / 2, 200 + 90 * (i + 1), 200, 60, 60, 200);

            // render text on box
            gc.setFill(i != cur ? Global.GREEN : Global.GREEN.brighter());
            gc.fillText(list.get(i), w / 2, 200 + 90 * (i + 1) + 30);
        }

    }

    /**
     * function to load a list of menu points
     *
     * @param strings
     */
    private void setMenuPoints(String[] strings) {
        list = new ArrayList<String>(Arrays.asList(strings));
        cur = 0;
    }
}
