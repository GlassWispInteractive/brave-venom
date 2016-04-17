package game.entity;

import core.Context;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;

public class Player extends Entity {
	private int maxDesperation = 100;
	private int maxDamage = 3;
	private int currentDesperation = 0;
	private int currentDamage = 0;
	private int currentLives = 3;
	private boolean desperate = false;
	private int maxLaser = 100;
	private int maxMissiles = 5;
	private int maxShields = 12;
	private int laser = maxLaser;
	private int missiles;
	private int shields;

	public Player(double x, double y, double dir, double speed) {
		super(x, y, dir, speed);
		initImage(Context.instance.getGraphicsMaster().getImage("playerShip1_green"), 0.8);
		redraw();
	}

	@Override
	public void tick(int ticks) {
		moveInDir(Context.instance.eventMaster.getDir(), ticks);
		turnTowards(Context.instance.gameMaster.mouseX, Context.instance.gameMaster.mouseY, ticks);
		redraw();
	}

	@Override
	protected void redraw() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		if (valid) {
			gc.save();
			Rotate r = new Rotate(dirLooking + 90, xOffset, yOffset);
			gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());

			// TODO: only works for squares, must be adjusted for all rectangles
			double x = (canvasSize - imageWidth) / 2;
			double y = (canvasSize - imageHeight) / 2;

			gc.drawImage(image, x, y, imageWidth, imageHeight);


			if (currentDamage >= 1 && currentDamage <= 3) {
				gc.drawImage(Context.instance.getGraphicsMaster().getImage("playerShip1_damage" + currentDamage), x, y,
						imageWidth, imageHeight);
			}
			gc.restore();
		}
	}

	public void spawnShot() {
		if (laser > 0) {
			double xWeapon = getXCenter() + Math.cos(dirLooking / 180 * Math.PI) * imageWidth / 2.2d;
			double yWeapon = getYCenter() + Math.sin(dirLooking / 180 * Math.PI) * imageHeight / 2.2;
			Shot shot = new Shot(xWeapon, yWeapon, dirLooking, 20, "laserGreen03", this);
			Context.instance.gameMaster.addShot(shot);
			laser--;
		}
	}

	@Override
	public void collided(Entity shot) {
		damage();
	}

	@Override
	public EntityType getType() {
		return EntityType.PLAYER;
	}

	public int getCurrentLives() {
		return currentLives;
	}

	private void damage() {
		currentDamage += 1;
		if (currentDamage > maxDamage) {
			currentDamage = 0;
			kill();
		}
	}

	private void kill() {
		currentLives -= 1;
		if (currentLives <= 0)
			Context.instance.gameMaster.gameOver();
	}

	private void addLive() {
		currentLives += 1;
	}

	public int getCurrentDesperation() {
		return currentDesperation;
	}

	public void addDesperation(int addedDesperation) {
		if (addedDesperation >= 0)
			currentDesperation = Math.min(currentDesperation + addedDesperation, maxDesperation);
		if (currentDesperation == maxDesperation) {
			desperate = true;
		}
	}

	public void reduceDesperation(int removedDesperation) {
		if (removedDesperation >= 0)
			currentDesperation = Math.max(currentDesperation - removedDesperation, 0);
	}

	public void resetCurrentDesperation() {
		currentDesperation = 0;
	}

	public boolean isDesperate() {
		return desperate;
	}

	public int getMaxLaser() {
		return maxLaser;
	}

	public int getMaxMissiles() {
		return maxMissiles;
	}

	public int getLaser() {
		return laser;
	}

	public int getMissiles() {
		return missiles;
	}

	public int getMaxShields() {
		return maxShields;
	}

	public int getShields() {
		return shields;
	}
}
