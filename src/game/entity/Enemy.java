//package game.entity;
//
//import core.masters.GameMaster;
//import javafx.scene.canvas.Canvas;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.image.Image;
//
//public class Enemy extends Entity {
//
//	public Enemy(GameMaster gm, int x, int y, int dirLooking, String spritefile) {
//		super(gm, x, y, dirLooking);
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

import core.Context;

public abstract class Enemy extends Entity {

	public Enemy(double x, double y, double dir, double speed) {
		super(x, y, dir, speed);
	}

	public void collided(Entity shot) {
		System.out.println("Enemy collided, Spawning Explosion");
		// enemy got shot by player...
		Explosion explosion = new Explosion(this.getXCenter(), this.getYCenter());
		Context.instance.gameMaster.explosions.add(explosion);
		Context.instance.gameMaster.addExplosion(explosion);
	}

}
