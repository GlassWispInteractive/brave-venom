package game.entity;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

public abstract class Entity {
	public static final double SCALE = 0.8;
	
	public double radialSpeed = 0.1;
	public boolean valid = true;
	public double scale;
	protected double x; // 0 - max X
	protected double y; // 0 - max Y
	protected double dirLooking; // 0 - 360
	protected double dirMotion;
	protected double speed; // in pixel per tick
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

	protected void initImage(Image image, double scale) {
		this.image = image;
		this.scale = scale;

		imageWidth = image.getWidth() * scale;
		imageHeight = image.getHeight() * scale;
		canvasSize = Math.max(imageWidth, imageHeight) * 2;
		canvas = new Canvas(canvasSize, canvasSize);
		xOffset = canvas.getWidth() / 2;
		yOffset = canvas.getHeight() / 2;
		x -= xOffset;
		y -= yOffset;
	}

	protected void changeImage(Image image) {
		// This function must not be called with an image of a different resolution!!!
		this.image = image;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public abstract void tick(int ticks);

	public double getXImage() {
		return x;
	}

	public double getXCenter() {
		return x + xOffset;
	}

	public double getYImage() {
		return y;
	}

	public double getYCenter() {
		return y + yOffset;
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

	public Circle collisionCircle() {
		return new Circle(this.getXCenter(), this.getYCenter(), (this.imageWidth + this.imageHeight));
	}

	public abstract void collided(Entity shot);

	protected final void invalidate() {
		valid = false;
		redraw();
	}

	protected abstract void redraw();

	public abstract EntityType getType();
}
