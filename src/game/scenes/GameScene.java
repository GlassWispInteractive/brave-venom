package game.scenes;

import core.masters.FontMaster;
import core.masters.GameMaster;
import core.masters.GraphicsMaster;
import core.masters.SceneMaster;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class GameScene extends AbstractGameScene {
	private GraphicsMaster graphicsMaster = sceneMaster.getContext().getGraphicsMaster();
	private GameMaster gameMaster = sceneMaster.getContext().getGameMaster();

	public GameScene(SceneMaster sceneMaster) {
		super(sceneMaster);
		initScene();

	}

	private void initScene() {
		updateBackground();
		updateForeGround();
		topHUD();
	}

	private void updateBackground() {

		Canvas canvas = new Canvas(graphicsMaster.windowWidth.get(), graphicsMaster.windowHeight.get());
		GraphicsContext gc = canvas.getGraphicsContext2D();
		background.getChildren().add(canvas);

		gc.setFill(Color.BLACK);
		Image sky = graphicsMaster.getImage("sky_blue");
		for (int x = 0; x < graphicsMaster.windowWidth.get() / sky.getWidth(); x++) {
			for (int y = 0; y < graphicsMaster.windowHeight.get() / sky.getHeight(); y++) {
				gc.drawImage(sky, sky.getWidth() * x, sky.getHeight() * y);
			}
		}
	}

	private void updateForeGround() {
		Canvas canvas = new Canvas(graphicsMaster.gameWidth.get(), graphicsMaster.gameHeight.get());
		GraphicsContext gc = canvas.getGraphicsContext2D();
		background.getChildren().add(canvas);
		canvas.relocate(0, graphicsMaster.panelHeight.get());

		gc.drawImage(graphicsMaster.getImage("playerShip1_blue"), 10, 10);

		gc.drawImage(graphicsMaster.getImage("playerShip1_blue"), 200, 10);
		gc.drawImage(graphicsMaster.getImage("playerShip1_damage1"), 200, 10);

		gc.drawImage(graphicsMaster.getImage("playerShip1_blue"), 400, 10);
		gc.drawImage(graphicsMaster.getImage("playerShip1_damage2"), 400, 10);

		gc.drawImage(graphicsMaster.getImage("playerShip1_blue"), 600, 10);
		gc.drawImage(graphicsMaster.getImage("playerShip1_damage3"), 600, 10);
	}

	private void topHUD() {
		Canvas top = new Canvas(graphicsMaster.gameWidth.get(), graphicsMaster.panelHeight.get());
		GraphicsContext gc = top.getGraphicsContext2D();
		background.getChildren().add(top);
		gc.clearRect(0, 0, getWidth(), getHeight());

//		gc.setFill(Color.GRAY);
//		gc.fillRect(0, 0, getWidth(), getHeight());

		// settings
		gc.setFont(FontMaster.DEFAULT_FONT);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.setFill(graphicsMaster.FRONT);

		double bigY = graphicsMaster.panelHeight.get() * 0.5;

		gc.fillText("level 1", graphicsMaster.gameWidth.get() * 0.2, bigY);
		gc.fillText("score 250", graphicsMaster.gameWidth.get() * 0.8, bigY);

		// PROGRESS BAR
		int progress = 50;
		int factor_size = 4;

		// white bar
		Image start = graphicsMaster.getImage("barHorizontal_white_left");
		Image mid = graphicsMaster.getImage("barHorizontal_white_mid");
		Image end = graphicsMaster.getImage("barHorizontal_white_right");

		double x = (graphicsMaster.gameWidth.get() - factor_size * 100) * 0.5, y = bigY - start.getHeight() * 0.5;

		gc.drawImage(start, x, y);
		gc.drawImage(mid, x + start.getWidth(), y, factor_size * 100 - start.getWidth() - end.getWidth(),
				mid.getHeight());
		gc.drawImage(end, x + factor_size * 100 - end.getWidth(), y);

		start = graphicsMaster.getImage("barHorizontal_blue_left");
		mid = graphicsMaster.getImage("barHorizontal_blue_mid");
		end = graphicsMaster.getImage("barHorizontal_blue_right");

		gc.drawImage(start, x, y);
		gc.drawImage(mid, x + start.getWidth(), y, factor_size * progress - start.getWidth() - end.getWidth(),
				mid.getHeight());
		gc.drawImage(end, x + factor_size * progress - end.getWidth(), y);

		bottomHUD(bigY, y);
	}

	private void bottomHUD(double bigY, double y) {
		GraphicsContext gc;
		Canvas bottom = new Canvas(graphicsMaster.gameWidth.get(), graphicsMaster.panelHeight.get());
		gc = bottom.getGraphicsContext2D();
		background.getChildren().add(bottom);
		bottom.relocate(0, graphicsMaster.windowHeight.get() - graphicsMaster.panelHeight.get());

		gc.clearRect(0, 0, getWidth(), getHeight());

//		gc.setFill(Color.GRAY);
//		gc.fillRect(0, 0, getWidth(), getHeight());

		gc.setFont(FontMaster.DEFAULT_FONT);
		gc.setTextAlign(TextAlignment.LEFT);
		gc.setTextBaseline(VPos.CENTER);
		gc.setFill(graphicsMaster.FRONT);

		// show lives
		int lifeNum = 123456789;

		Image life = graphicsMaster.getImage("playerLife1_green");
		Image numeral_x = graphicsMaster.getImage("numeralX");

		gc.drawImage(life, 10, y);

		
		gc.drawImage(numeral_x, 10 + life.getWidth() + 5, bigY - numeral_x.getHeight() * 0.5);
		char[] cs = Integer.toString(lifeNum).toCharArray();
		
		double latterY = bigY - graphicsMaster.getImage("numeral1").getHeight() * 0.5;
		for (int i = 0; i < cs.length; i++) {
			gc.drawImage(graphicsMaster.getImage("numeral" + cs[i]),
					15 + life.getWidth() + (i + 1) * (numeral_x.getWidth() + 2), latterY);
		}

		// desperate bar
		int progress = 50;
		int factor_size = 2;

		// white bar
		Image start = graphicsMaster.getImage("barHorizontal_white_left");
		Image mid = graphicsMaster.getImage("barHorizontal_white_mid");
		Image end = graphicsMaster.getImage("barHorizontal_white_right");

		double hei = start.getHeight() / 2;

		double x = (graphicsMaster.gameWidth.get() - factor_size * 100) * 0.8;

		double barY = 50;
		gc.drawImage(start, x, barY, start.getWidth(), hei);
		gc.drawImage(mid, x + start.getWidth(), barY, factor_size * 100 - start.getWidth() - end.getWidth(), hei);
		gc.drawImage(end, x + factor_size * 100 - end.getWidth(), barY, end.getWidth(), hei);

		start = graphicsMaster.getImage("barHorizontal_red_left");
		mid = graphicsMaster.getImage("barHorizontal_red_mid");
		end = graphicsMaster.getImage("barHorizontal_red_right");

		gc.drawImage(start, x, barY, start.getWidth(), hei);
		gc.drawImage(mid, x + start.getWidth(), barY, factor_size * progress - start.getWidth() - end.getWidth(), hei);
		gc.drawImage(end, x + factor_size * progress - end.getWidth(), barY, end.getWidth(), hei);
	}

	@Override
	public void prerender() {

	}

	@Override
	public void render() {

	}
}
