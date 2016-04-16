//package game.entity;
//
//import core.masters.GameMaster;
//import javafx.scene.canvas.Canvas;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.image.Image;
//
//public class Enemy extends Entity {
//
//	public Enemy(GameMaster gm, int x, int y, int dir, String spritefile) {
//		super(gm, x, y, dir);
//		Image player = gm.context.getSceneMaster().getImage(spritefile);
//		canvas = new Canvas(player.getWidth(), player.getHeight());
//	}
//
//	private void update() {
//		Image player = gm.context.getSceneMaster().getImage("playerShip1_red");
//		GraphicsContext gc = canvas.getGraphicsContext2D();
//		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//
//		gc.drawImage(player, 0, 0);
//
//	}
//
//	@Override
//	public void tick() {
//
//	}
//
//	@Override
//	public void render(GraphicsContext gc) {
//
//	}
//
//}
package game.entity;

import core.masters.GameMaster;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Enemy extends Entity {
	private int damage = 0;

	public Enemy(GameMaster gm, int x, int y, int dir) {
		super(gm, x, y, dir);

		Image player = gm.context.getSceneMaster().getImage("playerShip1_red");
		canvas = new Canvas(player.getWidth(), player.getHeight());
		update();
	}

	@Override
	public void tick() {
		if (gm.context.eventMaster.isLeft()) {
			x--;
			canvas.relocate(x, y);
		}

		if (gm.context.eventMaster.isRight()) {
			x++;
			canvas.relocate(x, y);
		}

		if (gm.context.eventMaster.isUp()) {
			y--;
			canvas.relocate(x, y);
		}

		if (gm.context.eventMaster.isDown()) {
			y++;
			canvas.relocate(x, y);
		}

		if (gm.context.eventMaster.isSpace()) {
			spawnShot();
		}
	}

	private void update() {
		Image player = gm.context.getSceneMaster().getImage("playerShip1_red");
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		gc.drawImage(player, 0, 0);

		if (damage >= 1 && damage <= 3) {
			canvas.getGraphicsContext2D()
					.drawImage(gm.context.getSceneMaster().getImage("playerShip1_damage" + damage), 0, 0);
		}
	}

	private void spawnShot() {

	}

	@Override
	public void render(GraphicsContext gc) {

	}

}
