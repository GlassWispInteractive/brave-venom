package game.scenes;

import core.Context;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class SettingsMenuScreen extends AbstractMenuScreen {

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
        background.setOpacity(0.3);

        // set foreground
        VBox vboxMenu = new VBox();
        vboxMenu.setAlignment(Pos.TOP_CENTER);
        foreground.setCenter(vboxMenu);

        BorderPane borderPaneLogo = new BorderPane();
        borderPaneLogo.setPrefHeight(240);

        ImageView imageViewLogo = new ImageView(new Image("/res/graphics/logo.png"));
        borderPaneLogo.setCenter(imageViewLogo);
        BorderPane.setAlignment(imageViewLogo, Pos.CENTER);

        VBox vboxButtons = new VBox(40);
        vboxButtons.setMaxWidth(400);
        vboxButtons.setPrefWidth(400);
        vboxButtons.setFillWidth(true);
        vboxButtons.setAlignment(Pos.CENTER);

        TilePane tilePaneButtons = new TilePane(20, 20);
        tilePaneButtons.setPrefColumns(1);
        tilePaneButtons.setMaxWidth(200);
        tilePaneButtons.setPrefTileWidth(200);
        tilePaneButtons.setPrefTileHeight(40);
        tilePaneButtons.setAlignment(Pos.CENTER);
        vboxButtons.getChildren().add(tilePaneButtons);

        Button buttonBack = new Button("Back");
        tilePaneButtons.getChildren().addAll(buttonBack);

        buttonBack.setPrefWidth(Double.MAX_VALUE);
        buttonBack.setPrefHeight(Double.MAX_VALUE);

        buttonBack.setOnAction((e) -> {
            context.getScreenControl().showScreen("main_menu", foreground);
        });

        BorderPane borderPaneEmpty = new BorderPane();
        borderPaneEmpty.setPrefHeight(120);

        vboxMenu.getChildren().addAll(borderPaneLogo, vboxButtons, borderPaneEmpty);
    }
}
