package game.entity;

import core.masters.GameMaster;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {

	protected GameMaster gm;
	protected int x; // 0 - max X
	protected int y; // 0 - max Y
	protected int dir; // 0 - 360
	protected Canvas canvas;

	public Entity(GameMaster gm, int x, int y, int dir) {
		this.gm = gm;
		this.x = x;
		this.y = y;
		this.dir = dir;
		//		Image img = gm.context.getSceneMaster().getImage(spritefile);
		//		canvas = new Canvas(img.getWidth(), img.getHeight());
		//		canvas.getGraphicsContext2D().drawImage(img, 0, 0);
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public abstract void tick();

	public abstract void render(GraphicsContext gc);

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
