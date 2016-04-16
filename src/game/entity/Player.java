package game.entity;

import core.Context;
import core.masters.EventMaster;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public class Player extends Entity {
	//	private final double[] dxs = { -Math.sqrt(2), 0, Math.sqrt(2), 1, Math.sqrt(2), 0, -Math.sqrt(2), -1 };
	//	private final double[] dys = { -Math.sqrt(2), -1, -Math.sqrt(2), 0, Math.sqrt(2), 1, Math.sqrt(2), 0 };
	private final double[] dxs = { -1 / Math.sqrt(2), 1 / Math.sqrt(2), -1 / Math.sqrt(2), 1 / Math.sqrt(2), 0, 0, -1,
			1 };
	private final double[] dys = { -1 / Math.sqrt(2), -1 / Math.sqrt(2), 1 / Math.sqrt(2), 1 / Math.sqrt(2), -1, 1, 0,
			0 };
	private int damage = 0;

	public Player(double x, double y, double dir, double speed) {
		super(x, y, dir, speed);
		initImage(Context.instance.getSceneMaster().getImage("playerShip1_blue"));
		update();
	}

	@Override
	public void tick(int ticks) {
		EventMaster eventMaster = Context.instance.eventMaster;
		int dir = eventMaster.getDir();
		if (dir >= 0 && dir < 8) {
			x += dxs[dir] * speed;
			y += dys[dir] * speed;
		}

		//		for (int i = 0; i < ticks; i++) {
		//			if (Context.instance.eventMaster.isLeft()) {
		//				x--;
		//				canvas.relocate(x, y);
		//			}
		//
		//			if (Context.instance.eventMaster.isRight()) {
		//				x++;
		//				canvas.relocate(x, y);
		//			}
		//
		//			if (Context.instance.eventMaster.isUp()) {
		//				y--;
		//				canvas.relocate(x, y);
		//			}
		//
		//			if (Context.instance.eventMaster.isDown()) {
		//				y++;
		//				canvas.relocate(x, y);
		//			}
		//
		//			if (Context.instance.eventMaster.isSpace()) {
		//				spawnShot();
		//			}
		//		}
		turnTowards(Context.instance.gameMaster.mouseX, Context.instance.gameMaster.mouseY);
		update();
	}

	private void update() {
		Image player = Context.instance.getSceneMaster().getImage("playerShip1_blue");
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		gc.save();
		Rotate r = new Rotate(dir + 90, xOffset, yOffset);
		gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());

		// TODO: only works for squares, must be adjusted for all rectangles
		double x = (canvasSize - imageWidth) / 2;
		double y = (canvasSize - imageHeight) / 2;

		gc.drawImage(player, x, y);
		gc.restore();

		if (damage >= 1 && damage <= 3) {
			canvas.getGraphicsContext2D()
					.drawImage(Context.instance.getSceneMaster().getImage("playerShip1_damage" + damage), 0, 0);
		}
	}

	private void spawnShot() {

	}

}
