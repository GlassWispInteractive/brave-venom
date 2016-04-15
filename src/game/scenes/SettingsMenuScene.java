package game.scenes;

import core.masters.SceneMaster;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class SettingsMenuScene extends AbstractMenuScene {

	public SettingsMenuScene(SceneMaster sceneMaster) {
		super(sceneMaster);

		init_scene();
	}

	private void init_scene() {
		BorderPane background = this.getBackground();
		BorderPane foreground = this.getForeground();

		// set background
		ImageView backgroundImageView = new ImageView(sceneMaster.getImage("scorpion"));
		background.setCenter(backgroundImageView);
		background.setOpacity(0.3);

		// set foreground
		VBox vboxMenu = new VBox();
		vboxMenu.setAlignment(Pos.TOP_CENTER);
		foreground.setCenter(vboxMenu);

		BorderPane borderPaneLogo = new BorderPane();
		borderPaneLogo.setPrefHeight(240);

		ImageView imageViewLogo = new ImageView(sceneMaster.getImage("logo"));
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
			sceneMaster.getContext().getScreenMaster().showScene("main_menu", foreground);
		});

		BorderPane borderPaneEmpty = new BorderPane();
		borderPaneEmpty.setPrefHeight(120);

		vboxMenu.getChildren().addAll(borderPaneLogo, vboxButtons, borderPaneEmpty);
	}
}
