package game.entity;

import core.masters.GameMaster;
import javafx.scene.canvas.Canvas;

public class Shot extends Entity {

	protected Entity origin;

	Shot(GameMaster gm, int x, int y, int dir, String spritefile, Entity origin) {
		super(gm, x, y, dir, spritefile);
		this.origin = origin;
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Canvas canvas) {

	}

}
