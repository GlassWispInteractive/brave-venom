package game.scenes;

import core.Context;
import core.screens.MenuScreen;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class SettingsMenuScreen extends MenuScreen {
    
    public SettingsMenuScreen(Context context) {
        super(context);
        init_scene();
    }
    
    private void init_scene() {
        getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        BorderPane background = this.getBackground();
        BorderPane foreground = this.getForeground();
        
        // set background
        ImageView backgroundImageView = new ImageView(new Image("/res/graphics/scorpion.png"));
        background.setCenter(backgroundImageView);
        background.setOpacity(0.5);
        
        // set foreground
        
        VBox vboxMenu = new VBox(40);
        vboxMenu.setMaxWidth(400);
        vboxMenu.setPrefWidth(400);
        vboxMenu.setFillWidth(true);
        vboxMenu.setAlignment(Pos.CENTER);
        foreground.setCenter(vboxMenu);
        
        ImageView imageViewLogo = new ImageView(new Image("/res/graphics/logo.png"));
        vboxMenu.getChildren().add(imageViewLogo);
        
        TilePane tilePaneButtons = new TilePane(20, 20);
        tilePaneButtons.setPrefColumns(1);
        tilePaneButtons.setMaxWidth(200);
        tilePaneButtons.setPrefTileWidth(200);
        tilePaneButtons.setPrefTileHeight(40);
        tilePaneButtons.setAlignment(Pos.CENTER);
        vboxMenu.getChildren().add(tilePaneButtons);
        
        Button buttonBack = new Button("Back");
        tilePaneButtons.getChildren().addAll(buttonBack);
        
        buttonBack.setPrefWidth(Double.MAX_VALUE);
        buttonBack.setPrefHeight(Double.MAX_VALUE);
        
        buttonBack.setOnAction((e) -> {
            context.getScreenControl().showScreen("main_menu");
        });
        
    }
}
