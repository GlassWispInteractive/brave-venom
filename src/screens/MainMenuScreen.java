package screens;

import core.ScreenControl;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainMenuScreen extends MenuScreen {
    public MainMenuScreen(ScreenControl screenControl) {
        super(screenControl);
        init_scene();
    }
    
    private void init_scene() {
        BorderPane background = new BorderPane();
        BorderPane foreground = new BorderPane();
        root.getChildren().addAll(background, foreground);
        
        ImageView backgroundImageView = new ImageView(new Image("/res/graphics/scorpion.png"));
        background.setCenter(backgroundImageView);
        background.setOpacity(0.5);
        
        VBox vboxButtons = new VBox(20);
        foreground.setCenter(vboxButtons);
        vboxButtons.setMaxWidth(200);
        vboxButtons.setPrefWidth(200);
        vboxButtons.setAlignment(Pos.CENTER);
        
        for (String menuPoint : menuPoints) {
            Button button = new Button(menuPoint);
            button.maxWidthProperty().bindBidirectional(vboxButtons.maxWidthProperty());
            button.prefWidthProperty().bindBidirectional(vboxButtons.prefWidthProperty());
            button.setPrefHeight(40);
            vboxButtons.getChildren().add(button);
        }
        
        StackPane.setAlignment(background, Pos.CENTER);
        StackPane.setAlignment(foreground, Pos.CENTER);
        
    }
    
}
