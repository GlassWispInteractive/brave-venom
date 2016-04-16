package game.entity;

import core.Context;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;

public class Player extends Entity {
	//	private final double[] dxs = { -Math.sqrt(2), 0, Math.sqrt(2), 1, Math.sqrt(2), 0, -Math.sqrt(2), -1 };
	//	private final double[] dys = { -Math.sqrt(2), -1, -Math.sqrt(2), 0, Math.sqrt(2), 1, Math.sqrt(2), 0 };

	private int damage = 0;

	public Player(double x, double y, double dir, double speed) {
		super(x, y, dir, speed);
		initImage(Context.instance.getSceneMaster().getImage("playerShip1_blue"));
		update();
	}

	@Override
	public void tick(int ticks) {
		moveInDir(Context.instance.eventMaster.getDir(), ticks);
		turnTowards(Context.instance.gameMaster.mouseX, Context.instance.gameMaster.mouseY, ticks);
		update();
	}

	private void update() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		gc.save();
		Rotate r = new Rotate(dir + 90, xOffset, yOffset);
		gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());

		// TODO: only works for squares, must be adjusted for all rectangles
		double x = (canvasSize - imageWidth) / 2;
		double y = (canvasSize - imageHeight) / 2;

		gc.drawImage(image, x, y);
		gc.restore();

		if (damage >= 1 && damage <= 3) {
			canvas.getGraphicsContext2D()
					.drawImage(Context.instance.getSceneMaster().getImage("playerShip1_damage" + damage), 0, 0);
		}
	}

	protected void spawnShot() {

	}

}
