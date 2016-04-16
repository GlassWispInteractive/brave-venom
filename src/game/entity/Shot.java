package game.entity;

import core.Context;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;

public class Shot extends Entity {

	public final Entity origin;

	public Shot(double x, double y, double dir, double speed, String spritefile, Entity origin) {
		super(x, y, dir, speed);
		this.origin = origin;
		initImage(Context.instance.getSceneMaster().getImage("playerShip1_blue"));
	}

	@Override
	public void tick(int ticks) {
		moveInDir(dirLooking, ticks);
		turnToDir(dirLooking, ticks);
		update();

	}

	private void update() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		gc.save();
		Rotate r = new Rotate(dirLooking + 90, xOffset, yOffset);
		gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());

		// TODO: only works for squares, must be adjusted for all rectangles
		double x = (canvasSize - imageWidth) / 2;
		double y = (canvasSize - imageHeight) / 2;

		gc.drawImage(image, x, y);
		gc.restore();
	}

}
