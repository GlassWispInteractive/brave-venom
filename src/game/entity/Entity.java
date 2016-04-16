package game.entity;

import core.Context;
import core.masters.EventMaster;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public abstract class Entity {
	private final static double[] dxs = { -1 / Math.sqrt(2), 1 / Math.sqrt(2), -1 / Math.sqrt(2), 1 / Math.sqrt(2), 0,
			0, -1, 1 };
	private final static double[] dys = { -1 / Math.sqrt(2), -1 / Math.sqrt(2), 1 / Math.sqrt(2), 1 / Math.sqrt(2), -1,
			1, 0, 0 };

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
	protected Image image;

	public Entity(double x, double y, double dir, double speed) {
		this.x = x;
		this.y = y;
		this.dir = dir;
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

	public void turnTowards(double x2, double y2) {
		double x1 = getXCenter();
		double y1 = getYCenter();
		if (x1 == x2 && y1 == y2)
			return;
		double dx = x2 - x1;
		double dy = y2 - y1;
		dir = Math.atan2(dy, dx) * 180 / Math.PI;
	}

	public void moveInDir(int dir) {
		EventMaster eventMaster = Context.instance.eventMaster;
		if (dir >= 0 && dir < 8) {
			x += dxs[dir] * speed;
			y += dys[dir] * speed;
		}
	}

	public void moveTowards(int dir) {
		EventMaster eventMaster = Context.instance.eventMaster;
		if (dir >= 0 && dir < 8) {
			x += dxs[dir] * speed;
			y += dys[dir] * speed;
		}
	}
}
