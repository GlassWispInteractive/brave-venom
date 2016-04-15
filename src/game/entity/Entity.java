package game.entity;

import core.masters.GameMaster;
import javafx.scene.canvas.Canvas;

public abstract class Entity {
	protected Canvas canvas;

	protected GameMaster gm;
	protected int x; // 0 - max X
	protected int y; // 0 - max Y
	protected int dir; // 0 - 360
	protected Canvas sprite;

	public Entity(GameMaster gm, int x, int y, int dir) {
		this.gm = gm;
		this.x = x;
		this.y = y;
		this.dir = dir;
//		Image img = gm.context.getSceneMaster().getImage(spritefile);
//		this.sprite = new Canvas(img.getWidth(), img.getHeight());
//		this.sprite.getGraphicsContext2D().drawImage(img, 0, 0);
	}

	public abstract void tick();
	public abstract void render(Canvas targetCanvas);

}
