package game.scenes;

import core.Context;
import core.masters.FontMaster;
import core.screens.MenuScreen;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class HelpMenuScreen extends MenuScreen {
    private final static String HELP_TEXT = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy "
            + "eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et "
            + "accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem "
            + "ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod "
            + "tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et "
            + "justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor "
            + "sit amet.";
            
    public HelpMenuScreen(Context context) {
        super(context);
        
        this.context = context;
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
        
        Label labelDescription = new Label(HELP_TEXT);
        labelDescription.setWrapText(true);
        labelDescription.setBackground(new Background(new BackgroundFill(new Color(1, 1, 1, 0.8), null, null)));
        labelDescription.setFont(FontMaster.SMALL_FONT);
        
        vboxMenu.getChildren().add(labelDescription);
        
    }
}
