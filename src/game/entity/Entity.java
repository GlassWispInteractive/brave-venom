package game.entity;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public abstract class Entity {

	protected double x; // 0 - max X
	protected double y; // 0 - max Y
	protected double dir; // 0 - 360
	protected double speed; // in pixel per tick
	protected Canvas canvas;
	protected double xOffset;
	protected double yOffset;
	protected double imageWidth;
	protected double imageHeight;
	protected double canvasSize;

	public Entity(double x, double y, double dir, double speed) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.speed = speed;
	}

	protected void initImage(Image image) {
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

	public void turnTowards(double x2, double y2) {
		double x1 = getXCenter();
		double y1 = getYCenter();
		if (x1 == x2 && y1 == y2)
			return;
		double dx = x2 - x1;
		double dy = y2 - y1;
		dir = Math.atan2(dy, dx) * 180 / Math.PI;
		System.out.println(x2 + " " + y2 + " " + dir);
	}
}
