package game.screens;

import core.Global;
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
        BorderPane logoPane = new BorderPane();
        root.getChildren().addAll(background, foreground);
        
        // set logo
        logoPane.setCenter(new ImageView(new Image("/res/graphics/logo.png")));
        root.getChildren().add(logoPane);
        
        logoPane.setPrefWidth(Global.WINDOW_WIDTH);
        logoPane.setPrefHeight(Global.WINDOW_HEIGHT);
        logoPane.relocate((Global.WINDOW_WIDTH - logoPane.getWidth()) / 2, 0);
        
        // set background
        ImageView backgroundImageView = new ImageView(new Image("/res/graphics/scorpion.png"));
        background.setCenter(backgroundImageView);
        background.setOpacity(0.5);
        
        // buttons settings
        VBox vboxButtons = new VBox(20);
        foreground.setCenter(vboxButtons);
        vboxButtons.setMaxWidth(200);
        vboxButtons.setPrefWidth(200);
        vboxButtons.setAlignment(Pos.CENTER);
        
        // set buttons
        for (String menuPoint : menuPoints) {
            Button button = new Button(menuPoint);
            button.maxWidthProperty().bindBidirectional(vboxButtons.maxWidthProperty());
            button.prefWidthProperty().bindBidirectional(vboxButtons.prefWidthProperty());
            button.setPrefHeight(40);
            vboxButtons.getChildren().add(button);
        }
        
        StackPane.setAlignment(logoPane, Pos.TOP_CENTER);
        StackPane.setAlignment(background, Pos.CENTER);
        StackPane.setAlignment(foreground, Pos.CENTER);
        
    }
    
}
