package game.entity;

import core.Context;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public abstract class Entity {
	protected double x; // 0 - max X
	protected double y; // 0 - max Y
	protected double dirLooking; // 0 - 360
	protected double dirMotion;
	protected double speed; // in pixel per tick
	public double radialSpeed = 0.1;
	protected Canvas canvas;
	protected double xOffset;
	protected double yOffset;
	protected double imageWidth;
	protected double imageHeight;
	protected double canvasSize;
	protected Image image;

	public Entity(double x, double y, double dir, double speed) {
		this.x = x;
		this.y = y;
		this.dirLooking = dir;
		this.speed = speed;
	}

	protected void initImage(Image image) {
		this.image = image;
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
		canvasSize = Math.max(imageWidth, imageHeight) * 2;
		canvas = new Canvas(canvasSize, canvasSize);
		xOffset = canvas.getWidth() / 2;
		yOffset = canvas.getHeight() / 2;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public abstract void tick(int ticks);

	public double getX() {
		return x;
	}

	public double getXCenter() {
		return x + xOffset;
	}

	public double getY() {
		return y;
	}

	public double getYCenter() {
		return y + yOffset;
	}

	private boolean tooNear(double dx, double dy) {
		return Double.compare(dx * dx + dy * dy - 5, 0) < 0;
	}

	private double getDir(double dx, double dy) {
		return Math.atan2(dy, dx) * 180 / Math.PI;
	}

	protected void moveInDir(double dir, int ticks) {
		if (!Double.isNaN(dir)) {
			double dx = Math.cos(dir / 180 * Math.PI) * speed * ticks;
			double dy = Math.sin(dir / 180 * Math.PI) * speed * ticks;
			x += Math.cos(dir / 180 * Math.PI) * speed * ticks;
			y += Math.sin(dir / 180 * Math.PI) * speed * ticks;
		}
	}

	protected void moveTowards(double targetX, double targetY, int ticks) {

		double dx = targetX - getXCenter();
		double dy = targetY - getYCenter();
		double dir = getDir(dx, dy);
		moveInDir(dir, ticks);
	}

	protected void turnTowards(double x2, double y2, int ticks) {
		double x1 = getXCenter();
		double y1 = getYCenter();
		double dx = x2 - x1;
		double dy = y2 - y1;
		double dir = getDir(dx, dy);
		turnToDir(dir, ticks);
	}

	protected void turnToDir(double dir, int ticks) {
		double dd = ((dir - this.dirLooking) % 720 + 180) % 360 - 180; // crazy but needed this way
		this.dirLooking += dd * radialSpeed * ticks;
	}

	public void spawnShot() {
		Shot shot = new Shot(getXCenter(), getYCenter(), dirLooking, 10, "laserBlue12", this);
		Context.instance.gameMaster.addShot(shot);
	}
}
