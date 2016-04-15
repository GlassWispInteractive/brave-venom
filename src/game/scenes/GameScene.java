package game.scenes;

import core.masters.FontMaster;
import core.masters.GameMaster;
import core.masters.GraphicsMaster;
import core.masters.SceneMaster;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameScene extends AbstractGameScene {

	public GameScene(SceneMaster sceneMaster) {
		super(sceneMaster);
		initScene();
	}

	private void initScene() {
		updateBackground();
		updateForeGround();
		updateGui();
	}

	private void updateBackground() {

	}

	private void updateForeGround() {
	}

	private void updateGui() {
		GraphicsMaster graphicsMaster = sceneMaster.getContext().getGraphicsMaster();
		GameMaster gameMaster = sceneMaster.getContext().getGameMaster();

		Canvas canvas = new Canvas(graphicsMaster.gameWidth.get(), graphicsMaster.gameHeight.get());
		GraphicsContext gc = canvas.getGraphicsContext2D();
		background.getChildren().add(canvas);

		// general settings
		double barOuterWidth = graphicsMaster.gameWidth.get() / 3;
		double barOuterHeight = 16;
		double barOffset = 2;
		double barInnerWidth = barOuterWidth - 2 * barOffset;
		double barInnerHeight = barOuterHeight - 2 * barOffset;
		double barOuterArc = barOuterHeight * 0.75;
		double barInnerArc = barInnerHeight * 0.75;

		double barDistance = barOuterHeight;

		Color colorRounTimeBarOuter = Color.GREEN;
		Color colorRounTimeBarInner = Color.LIGHTGREEN;
		Color colorDesperateBarOuter = Color.DARKBLUE;
		Color colorDesperateBarInner = Color.LIGHTBLUE;

//		double fontSizeLarge = FontMaster.LARGE_FONT.getSize();
//		double fontSizeNormal = FontMaster.NORMAL_FONT.getSize();

		double lifeIconWidth = 48;
		double lifeIconHeight = 48;

		double xo, xi, yo, yi, f, wo, wi, ho, hi;
		Color co, ci;

		// TODO: round time bar
		// round time bar
		xo = graphicsMaster.gameWidth.get() / 3;
		xi = xo + barOffset;
		yo = barDistance;
		yi = yo + barOffset;
		f = gameMaster.currentRoundTime.doubleValue() / gameMaster.roundTime.doubleValue();
		wo = barOuterWidth * f;
		wi = wo - 2 * barOffset;
		ho = barOuterHeight;
		hi = ho - 2 * barOffset;
		co = colorRounTimeBarOuter;
		ci = colorRounTimeBarInner;

		gc.setFill(co);
		gc.fillRoundRect(xo, yo, wo, ho, barOuterArc, barOuterArc);

		gc.setFill(ci);
		gc.fillRoundRect(xi, yi, wi, hi, barInnerArc, barInnerArc);

		// TODO: desperation bar
		xo = graphicsMaster.gameWidth.get() / 3;
		xi = xo + barOffset;
		yo = barDistance + barOuterHeight + barDistance;
		yi = yo + barOffset;
		f = gameMaster.currentRoundTime.doubleValue() / gameMaster.roundTime.doubleValue();
		wo = barOuterWidth * f;
		wi = wo - 2 * barOffset;
		ho = barOuterHeight;
		hi = ho - 2 * barOffset;
		co = colorRounTimeBarOuter;
		ci = colorRounTimeBarInner;

		gc.setFill(co);
		gc.fillRoundRect(xo, yo, wo, ho, barOuterArc, barOuterArc);

		gc.setFill(ci);
		gc.fillRoundRect(xi, yi, wi, hi, barInnerArc, barInnerArc);

		// TODO: level label
		// TODO: time label
		// TODO: life number

	}

	@Override
	public void prerender() {

	}

	@Override
	public void render() {

	}
}
