package game.entity;

import core.masters.GameMaster;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public abstract class Entity {

	GameMaster gm;
	int x; // 0 - max X
	int y; // 0 - max Y
	int dir; // 0 - 360
	Image sprite;

	Entity(GameMaster gm, int x, int y, int dir, String spritefile) {
		this.gm = gm;
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.sprite = gm.context.getGraphicsMaster().getImage(spritefile);
	}

	public abstract void tick();
	public abstract void render(Canvas canvas);

}
