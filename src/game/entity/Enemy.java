package game.entity;

import core.masters.GameMaster;
import javafx.scene.canvas.Canvas;

public class Enemy extends Entity {

	public Enemy(GameMaster gm, int x, int y, int dir, String spritefile) {
		super(gm, x, y, dir, spritefile);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Canvas canvas) {

	}

}
