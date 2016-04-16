package game.entity;

import core.Context;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends Entity {
	private int damage = 0;

	public Player(double x, double y, double dir, double speed) {
		super(x, y, dir, speed);

		Image player = Context.instance.getSceneMaster().getImage("playerShip1_blue");
		canvas = new Canvas(player.getWidth(), player.getHeight());
		update();
	}

	@Override
	public void tick(int ticks) {
		for (int i = 0; i < ticks; i++) {
			if (Context.instance.eventMaster.isLeft()) {
				x--;
				canvas.relocate(x, y);
			}

			if (Context.instance.eventMaster.isRight()) {
				x++;
				canvas.relocate(x, y);
			}

			if (Context.instance.eventMaster.isUp()) {
				y--;
				canvas.relocate(x, y);
			}

			if (Context.instance.eventMaster.isDown()) {
				y++;
				canvas.relocate(x, y);
			}

			if (Context.instance.eventMaster.isSpace()) {
				spawnShot();
			}
		}
		update();
	}

	private void update() {
		Image player = Context.instance.getSceneMaster().getImage("playerShip1_blue");
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		gc.drawImage(player, 0, 0);

		if (damage >= 1 && damage <= 3) {
			canvas.getGraphicsContext2D()
					.drawImage(Context.instance.getSceneMaster().getImage("playerShip1_damage" + damage), 0, 0);
		}
	}

	private void spawnShot() {

	}

}
