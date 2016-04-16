package game.scenes;

import core.masters.FontMaster;
import core.masters.GameMaster;
import core.masters.SceneMaster;
import game.entity.Enemy;
import game.entity.Player;
import game.entity.Shot;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class GameScene extends AbstractGameScene {
	private GameMaster gameMaster = sceneMaster.getContext().getGameMaster();

	public GameScene(SceneMaster sceneMaster) {
		super(sceneMaster);
		initScene();

	}

	private void initScene() {
		updateBackground();
		updateForeground();
		updateTopHUD();
	}

	private void updateBackground() {
		Canvas canvas = background;
		GraphicsContext gc = canvas.getGraphicsContext2D();

		gc.setFill(Color.BLACK);
		Image sky = sceneMaster.getImage("sky_blue");
		for (int x = 0; x < sceneMaster.windowWidth.get() / sky.getWidth(); x++) {
			for (int y = 0; y < sceneMaster.windowHeight.get() / sky.getHeight(); y++) {
				gc.drawImage(sky, sky.getWidth() * x, sky.getHeight() * y);
			}
		}
	}

	private void updateForeground() {
		for (Enemy enemy : gameMaster.getEnemies()) {
			enemy.getCanvas().relocate(enemy.getX(), enemy.getY());
		}
		for (Shot shot : gameMaster.getEnemyShots()) {
			shot.getCanvas().relocate(shot.getX(), shot.getY());
		}
		for (Shot shot : gameMaster.getPlayerShots()) {
			shot.getCanvas().relocate(shot.getX(), shot.getY());
		}
		try {
			Player player = gameMaster.player;
			player.getCanvas().relocate(player.getX(), player.getY());
		} catch (NullPointerException ex) {
			assert gameMaster.player == null;
		}

		//		GraphicsContext gcPlayers = playerPane.getGraphicsContext2D();
		//		GraphicsContext gcEnemies = enemyPane.getGraphicsContext2D();
		//		GraphicsContext gcShots = shotPane.getGraphicsContext2D();
		//
		//		// draw enemies
		//		gcPlayers.drawImage(sceneMaster.getImage("playerShip1_blue"), 10, -50);
		//
		//		gcPlayers.drawImage(sceneMaster.getImage("playerShip1_blue"), 10, 10);
		//
		//		gcPlayers.drawImage(sceneMaster.getImage("playerShip1_blue"), 200, 10);
		//		gcPlayers.drawImage(sceneMaster.getImage("playerShip1_damage1"), 200, 10);
		//
		//		gcPlayers.drawImage(sceneMaster.getImage("playerShip1_blue"), 400, 10);
		//		gcPlayers.drawImage(sceneMaster.getImage("playerShip1_damage2"), 400, 10);
		//
		//		gcPlayers.drawImage(sceneMaster.getImage("playerShip1_blue"), 600, 10);
		//		gcPlayers.drawImage(sceneMaster.getImage("playerShip1_damage3"), 600, 10);
	}

	private void updateTopHUD() {
		GraphicsContext gc = topHUD.getGraphicsContext2D();
		gc.clearRect(0, 0, getWidth(), getHeight());

		//		gc.setFill(Color.GRAY);
		//		gc.fillRect(0, 0, getWidth(), getHeight());

		// settings
		gc.setFont(FontMaster.DEFAULT_FONT);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.setFill(sceneMaster.FRONT);

		double bigY = sceneMaster.panelHeight.get() * 0.5;

		gc.fillText("level 1", sceneMaster.gameWidth.get() * 0.2, bigY);
		gc.fillText("score 250", sceneMaster.gameWidth.get() * 0.8, bigY);

		// PROGRESS BAR
		int progress = 50;
		int factor_size = 4;

		// white bar
		Image start = sceneMaster.getImage("barHorizontal_white_left");
		Image mid = sceneMaster.getImage("barHorizontal_white_mid");
		Image end = sceneMaster.getImage("barHorizontal_white_right");

		double x = (sceneMaster.gameWidth.get() - factor_size * 100) * 0.5, y = bigY - start.getHeight() * 0.5;

		gc.drawImage(start, x, y);
		gc.drawImage(mid, x + start.getWidth(), y, factor_size * 100 - start.getWidth() - end.getWidth(),
				mid.getHeight());
		gc.drawImage(end, x + factor_size * 100 - end.getWidth(), y);

		start = sceneMaster.getImage("barHorizontal_blue_left");
		mid = sceneMaster.getImage("barHorizontal_blue_mid");
		end = sceneMaster.getImage("barHorizontal_blue_right");

		gc.drawImage(start, x, y);
		gc.drawImage(mid, x + start.getWidth(), y, factor_size * progress - start.getWidth() - end.getWidth(),
				mid.getHeight());
		gc.drawImage(end, x + factor_size * progress - end.getWidth(), y);

		bottomHUD(bigY, y);
	}

	private void bottomHUD(double bigY, double y) {
		GraphicsContext gc = bottomHUD.getGraphicsContext2D();
		//		bottom.relocate(0, sceneMaster.windowHeight.get() - sceneMaster.panelHeight.get());
		//		bottom.relocate(0, sceneMaster.windowHeight.get());

		gc.clearRect(0, 0, getWidth(), getHeight());

		//		gc.setFill(Color.GRAY);
		//		gc.fillRect(0, 0, getWidth(), getHeight());

		gc.setFont(FontMaster.DEFAULT_FONT);
		gc.setTextAlign(TextAlignment.LEFT);
		gc.setTextBaseline(VPos.CENTER);
		gc.setFill(sceneMaster.FRONT);

		// show lives
		int lifeNum = 123456789;

		Image life = sceneMaster.getImage("playerLife1_green");
		Image numeral_x = sceneMaster.getImage("numeralX");

		gc.drawImage(life, 10, y);

		gc.drawImage(numeral_x, 10 + life.getWidth() + 5, bigY - numeral_x.getHeight() * 0.5);
		char[] cs = Integer.toString(lifeNum).toCharArray();

		double latterY = bigY - sceneMaster.getImage("numeral1").getHeight() * 0.5;
		for (int i = 0; i < cs.length; i++) {
			gc.drawImage(sceneMaster.getImage("numeral" + cs[i]),
					15 + life.getWidth() + (i + 1) * (numeral_x.getWidth() + 4), latterY);
		}

		// desperate bar
		int progress = 50;
		int factor_size = 2;

		// white bar
		Image start = sceneMaster.getImage("barHorizontal_white_left");
		Image mid = sceneMaster.getImage("barHorizontal_white_mid");
		Image end = sceneMaster.getImage("barHorizontal_white_right");

		double hei = start.getHeight() / 2;

		double x = (sceneMaster.gameWidth.get() - factor_size * 100) * 0.8;

		double barY = 50;
		gc.drawImage(start, x, barY, start.getWidth(), hei);
		gc.drawImage(mid, x + start.getWidth(), barY, factor_size * 100 - start.getWidth() - end.getWidth(), hei);
		gc.drawImage(end, x + factor_size * 100 - end.getWidth(), barY, end.getWidth(), hei);

		start = sceneMaster.getImage("barHorizontal_red_left");
		mid = sceneMaster.getImage("barHorizontal_red_mid");
		end = sceneMaster.getImage("barHorizontal_red_right");

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
