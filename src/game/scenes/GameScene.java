package game.scenes;

import core.masters.FontMaster;
import core.masters.GameMaster;
import core.masters.SceneMaster;
import game.entity.Enemy;
import game.entity.Explosion;
import game.entity.Player;
import game.entity.Shot;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class GameScene extends AbstractGameScene {
	private GameMaster gameMaster = sceneMaster.getContext().getGameMaster();

	public GameScene(SceneMaster sceneMaster) {
		super(sceneMaster);
		update();
	}

	public final void update() {
		updateBackground();
		updateForeground();
		updateTopHUD();
		updateBottomHUD();
	}

	private void updateBackground() {
		GraphicsContext gc = background.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		Image sky = sceneMaster.getImage("sky_blue");
		for (int x = 0; x < sceneMaster.windowWidth.get() / sky.getWidth(); x++) {
			for (int y = 0; y < sceneMaster.windowHeight.get() / sky.getHeight(); y++) {
				gc.drawImage(sky, sky.getWidth() * x, sky.getHeight() * y);
			}
		}
	}

	private void updateForeground() {
		for (Enemy enemy : gameMaster.enemies) {
			enemy.getCanvas().relocate(enemy.getXImage(), enemy.getYImage());
		}
		for (Shot shot : gameMaster.enemyShots) {
			shot.getCanvas().relocate(shot.getXImage(), shot.getYImage());
		}
		for (Shot shot : gameMaster.playerShots) {
			shot.getCanvas().relocate(shot.getXImage(), shot.getYImage());
		}
		for (Explosion explosion : gameMaster.explosions) {
			explosion.getCanvas().relocate(explosion.getXImage(), explosion.getYImage());
		}
		try {
			Player player = gameMaster.player;
			player.getCanvas().relocate(player.getXImage(), player.getYImage());
		} catch (NullPointerException ex) {
			assert gameMaster.player == null;
		}
	}

	private void updateTopHUD() {
		GraphicsContext gc = topHUD.getGraphicsContext2D();
		gc.clearRect(0, 0, getWidth(), getHeight());

		double widthBar = 200;
		double widthLongBar = 500;
		double heightBar = 26;
		double widthGlass = widthBar + 32;
		double widthLongGlass = widthLongBar + 32;
		double heightGlass = 68;
		double x;
		double yOffset = 12;
		double yText = (sceneMaster.panelHeight.get() - heightBar) / 2 + 2;
		double yTextBottom = (sceneMaster.panelHeight.get() - heightBar) / 2 + 34;
		double yBar = (sceneMaster.panelHeight.get() - heightBar) / 2 + yOffset;
		double yGlass = (sceneMaster.panelHeight.get() - heightGlass) / 2;

		// settings
		gc.setTextAlign(TextAlignment.LEFT);
		gc.setFill(sceneMaster.FRONT);

		// glass panes
		x = sceneMaster.windowWidth.get() * 1 / 8 - widthGlass / 2;
		new BorderImages("glassPanel", widthGlass, heightGlass).draw(gc, x, yGlass);

		x = sceneMaster.windowWidth.get() * 4 / 8 - widthLongGlass / 2;
		new BorderImages("glassPanel", widthLongGlass, heightGlass).draw(gc, x, yGlass);

		x = sceneMaster.windowWidth.get() * 7 / 8 - widthGlass / 2;
		new BorderImages("glassPanel", widthGlass, heightGlass).draw(gc, x, yGlass);

		// level
		int level = gameMaster.currentLevel;
		x = sceneMaster.windowWidth.get() * 1 / 8 - widthBar / 2;
		gc.setFont(FontMaster.DEFAULT_FONT);
		gc.fillText("Level", x, yText);
		gc.setFont(FontMaster.LARGE_FONT);
		gc.fillText("" + level, x, yTextBottom);

		// time bar
		int max = gameMaster.maxRoundTime;
		int curr = gameMaster.currentRoundTime;
		x = sceneMaster.windowWidth.get() * 4 / 8 - widthLongBar / 2;
		gc.setFont(FontMaster.DEFAULT_FONT);
		gc.fillText("Round Time", x, yText);

		new BarImages("barHorizontal_white", widthLongBar).draw(gc, x, yBar);
		if (gameMaster.player != null) {
			String sprite = gameMaster.player.isDesperate() ? "barHorizontal_blue" : "barHorizontal_yellow";
			new BarImages(sprite, widthLongBar * curr / max).draw(gc, x, yBar);
		}

		// score
		x = sceneMaster.windowWidth.get() * 7 / 8 - widthBar / 2;
		gc.setFont(FontMaster.DEFAULT_FONT);
		gc.fillText("Score", x, yText);
		gc.setFont(FontMaster.LARGE_FONT);
		gc.fillText("2500", x, yTextBottom);

	}

	private void updateBottomHUD() {
		GraphicsContext gc = bottomHUD.getGraphicsContext2D();
		gc.clearRect(0, 0, getWidth(), getHeight());

		double widthBar = 200;
		double heightBar = 26;
		double widthGlass = widthBar + 32;
		double heightGlass = 68;
		double x;
		double yOffset = 12;
		double yText = (sceneMaster.panelHeight.get() - heightBar) / 2 + 2;
		double yBar = (sceneMaster.panelHeight.get() - heightBar) / 2 + yOffset;
		double yLetter = (sceneMaster.panelHeight.get() - sceneMaster.getImage("numeral1").getHeight()) / 2 + yOffset;
		double yGlass = (sceneMaster.panelHeight.get() - heightGlass) / 2;
		double gapSmall = 2;
		double gapNormal = 5;
		double gapLarge = 8;
		int max;
		int curr;

		// glass panes
		for (int i = 0; i < 4; i++) {
			x = sceneMaster.windowWidth.get() * (2 * i + 1) / 8 - widthGlass / 2;
			new BorderImages("glassPanel", widthGlass, heightGlass).draw(gc, x, yGlass);
		}

		// lives
		gc.setFont(FontMaster.DEFAULT_FONT);
		gc.setTextAlign(TextAlignment.LEFT);
		gc.setFill(sceneMaster.FRONT);

		Image imageLife = sceneMaster.getImage("playerLife1_green");
		Image imageNumeralX = sceneMaster.getImage("numeralX");
		//		Image[] imagesDigits = new Image[10];
		//		for (int i = 0; i < 10; i++) {
		//			imagesDigits[i] = sceneMaster.getImage("numeral" + i);
		//		}

		x = sceneMaster.windowWidth.get() * 1 / 8 - widthBar / 2;
		gc.fillText("Lives", x, yText);

		gc.drawImage(imageLife, x, yBar);
		x += imageLife.getWidth() + gapLarge;

		gc.drawImage(imageNumeralX, x, yLetter);
		x += imageNumeralX.getWidth() + gapLarge;

		int life = gameMaster.player == null ? 0 : gameMaster.player.getCurrentLives();
		char[] cs = Integer.toString(life).toCharArray();
		for (char c : cs) {
			Image imageDigit = sceneMaster.getImage("numeral" + c);
			gc.drawImage(imageDigit, x, yLetter);
			x += imageDigit.getWidth() + gapSmall;
		}

		// shields
		max = gameMaster.player == null ? 1 : gameMaster.player.getMaxShields();
		curr = gameMaster.player == null ? 0 : gameMaster.player.getShields();
		x = sceneMaster.windowWidth.get() * 3 / 8 - widthBar / 2;
		gc.fillText("Shield", x, yText);

		new StepImages("barHorizontal_white", max, gapNormal).draw(gc, x, yBar);
		if (gameMaster.player != null) {
			String sprite = gameMaster.player.isDesperate() ? "barHorizontal_blue" : "barHorizontal_yellow";
			new StepImages(sprite, curr, gapNormal).draw(gc, x, yBar);
		}

		// laser bar
		max = gameMaster.player == null ? 1 : gameMaster.player.getMaxLaser();
		curr = gameMaster.player == null ? 0 : gameMaster.player.getLaser();
		x = sceneMaster.windowWidth.get() * 5 / 8 - widthBar / 2;
		gc.fillText("Laser", x, yText);

		new BarImages("barHorizontal_white", widthBar).draw(gc, x, yBar);
		if (gameMaster.player != null) {
			String sprite = gameMaster.player.isDesperate() ? "barHorizontal_blue" : "barHorizontal_green";
			new BarImages(sprite, widthBar * curr / max).draw(gc, x, yBar);
		}

		// missiles bar
		max = gameMaster.player == null ? 0 : gameMaster.player.getMaxMissiles();
		curr = gameMaster.player == null ? 0 : gameMaster.player.getMissiles();
		x = sceneMaster.windowWidth.get() * 7 / 8 - widthBar / 2;
		gc.fillText("Missiles", x, yText);

		new BarImages("barHorizontal_white", widthBar).draw(gc, x, yBar);
		if (gameMaster.player != null) {
			String sprite = gameMaster.player.isDesperate() ? "barHorizontal_blue" : "barHorizontal_red";
			new BarImages(sprite, 100).draw(gc, x, yBar);
		}
	}

	@Override
	public void prerender() {

	}

	@Override
	public void render() {

	}

	class BorderImages {

		private final Image imageTopLeft;
		private final Image imageTopRight;
		private final Image imageBottomLeft;
		private final Image imageBottomRight;
		private final Image imageTop;
		private final Image imageBottom;
		private final Image imageLeft;
		private final Image imageRight;
		private final Image imageCenter;

		double left, top, right, bottom, centerV, centerH;

		public BorderImages(String name, double width, double height) {
			imageTopLeft = sceneMaster.getImage(name + "_corner_top_left");
			imageTopRight = sceneMaster.getImage(name + "_corner_top_right");
			imageBottomLeft = sceneMaster.getImage(name + "_corner_bottom_left");
			imageBottomRight = sceneMaster.getImage(name + "_corner_bottom_right");
			imageTop = sceneMaster.getImage(name + "_edge_top");
			imageBottom = sceneMaster.getImage(name + "_edge_bottom");
			imageLeft = sceneMaster.getImage(name + "_edge_left");
			imageRight = sceneMaster.getImage(name + "_edge_right");
			imageCenter = sceneMaster.getImage(name + "_center");

			left = imageLeft.getWidth();
			right = imageRight.getWidth();
			top = imageTop.getHeight();
			bottom = imageBottom.getHeight();
			centerV = height - top - bottom;
			centerH = width - left - right;
		}

		public void draw(GraphicsContext gc, double xBase, double yBase) {
			gc.drawImage(imageTopLeft, xBase, yBase);
			gc.drawImage(imageTopRight, left + centerH + xBase, yBase);
			gc.drawImage(imageBottomLeft, xBase, top + centerV + yBase);
			gc.drawImage(imageBottomRight, left + centerH + xBase, top + centerV + yBase);
			gc.drawImage(imageTop, left + xBase, yBase, centerH, top);
			gc.drawImage(imageBottom, left + xBase, top + centerV + yBase, centerH, bottom);
			gc.drawImage(imageLeft, xBase, top + yBase, left, centerV);
			gc.drawImage(imageRight, left + centerH + xBase, top + yBase, right, centerV);
			gc.drawImage(imageCenter, left + xBase, top + yBase, centerH, centerV);
		}
	}

	class BarImages {
		private final Image imageLeft;
		private final Image imageRight;
		private final Image imageCenter;

		double left, right, centerH, height;

		public BarImages(String name, double width) {
			imageLeft = sceneMaster.getImage(name + "_left");
			imageCenter = sceneMaster.getImage(name + "_mid");
			imageRight = sceneMaster.getImage(name + "_right");

			left = imageLeft.getWidth();
			right = imageRight.getWidth();
			centerH = width - left - right;
			height = imageLeft.getHeight();
		}

		public void draw(GraphicsContext gc, double xBase, double yBase) {
			gc.drawImage(imageLeft, xBase, yBase, left, height);
			gc.drawImage(imageRight, left + centerH + xBase, yBase, right, height);
			gc.drawImage(imageCenter, left + xBase, yBase, centerH, height);
		}
	}

	class StepImages {
		private final Image imageLeft;
		private final Image imageRight;
		private final int nrSteps;

		double left, right, height, gap;

		public StepImages(String name, int nrSteps, double gap) {
			imageLeft = sceneMaster.getImage(name + "_left");
			imageRight = sceneMaster.getImage(name + "_right");

			this.nrSteps = nrSteps;
			this.gap = gap;
			left = imageLeft.getWidth();
			right = imageRight.getWidth();
			height = imageLeft.getHeight();
		}

		public void draw(GraphicsContext gc, double xBase, double yBase) {
			for (int i = 0; i < nrSteps; i++) {
				gc.drawImage(imageLeft, xBase + (left + right + gap) * i, yBase, left, height);
				gc.drawImage(imageRight, xBase + (left + right + gap) * i + right, yBase, right, height);
			}
		}
	}
}
