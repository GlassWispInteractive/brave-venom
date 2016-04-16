package game.entity;

import javafx.scene.canvas.Canvas;

public abstract class Entity {

	protected double x; // 0 - max X
	protected double y; // 0 - max Y
	protected double dir; // 0 - 360
	protected double speed; // in pixel per tick
	protected Canvas canvas;

	public Entity(double x, double y, double dir, double speed) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.speed = speed;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public abstract void tick(int ticks);

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
