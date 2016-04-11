package game.screens;

import java.util.ArrayList;
import java.util.Arrays;

import core.Context;
import core.EventControl;
import core.Screen;
import core.ScreenControl;

//import static game.State.*;

public class MenuScreen extends Screen {
    // instance
    protected ArrayList<String> menuPoints;
    private int cur;
    
    public MenuScreen(ScreenControl screenControl) {
        super(screenControl);
        
        // init
        setMenuPoints(new String[] { "Start", "Wildcard", "Help", "Credits", "Exit" });
        //
        // load logo
    }
    
    public final Context getContext() {
        return screenControl.getContext();
    }
    
    @Override
    public void tick(int ticks) {
        EventControl eventControl = getContext().getEventControl();
        
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
    
    /**
     * function to load a menuPoints of menu points
     *
     * @param strings
     */
    private void setMenuPoints(String[] strings) {
        menuPoints = new ArrayList<String>(Arrays.asList(strings));
        cur = 0;
    }
}
