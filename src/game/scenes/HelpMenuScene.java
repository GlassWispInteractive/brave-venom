package game.scenes;

import core.masters.FontMaster;
import core.masters.SceneMaster;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class HelpMenuScene extends AbstractMenuScene {
	private final static String HELP_TEXT = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy "
			+ "eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et "
			+ "accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem "
			+ "ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod "
			+ "tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et "
			+ "justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor "
			+ "sit amet.";

	public HelpMenuScene(SceneMaster sceneMaster) {
		super(sceneMaster);

		initScene();
	}

	private void initScene() {
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

		BorderPane borderPaneEmpty = new BorderPane();
		borderPaneEmpty.setPrefHeight(120);
		foreground.setBottom(borderPaneEmpty);

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

		Label labelDescription = new Label(HELP_TEXT);
		labelDescription.setWrapText(true);
		labelDescription.setBackground(new Background(new BackgroundFill(new Color(1, 1, 1, 0.8), null, null)));
		labelDescription.setFont(FontMaster.SMALL_FONT);

		vboxButtons.getChildren().add(labelDescription);

		vboxMenu.getChildren().addAll(borderPaneLogo, vboxButtons, borderPaneEmpty);

	}
}
