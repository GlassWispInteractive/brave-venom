package game.scenes;

import java.util.ArrayList;

import core.Context;
import core.masters.EventMaster;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

//import static game.State.*;

public class AbstractMenuScreen extends Scene {
    protected Context context;
    
    // instance
    
    protected ArrayList<String> menuPoints;
    private int cur;
    private BorderPane background;
    private BorderPane foreground;
    
    public AbstractMenuScreen(Context context) {
        super(new StackPane(), context.getGraphicsMaster().getWindowWidth().get(),
                context.getGraphicsMaster().getWindowHeight().get());
                
        this.context = context;
        init_scene();
    }
    
    private void init_scene() {
        background = new BorderPane();
        foreground = new BorderPane();
        // getRoot().getChildren().addAll(background, foreground);
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
        return context;
    }
    
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
}
