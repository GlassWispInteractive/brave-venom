package game.entity;

import core.masters.GameMaster;
import javafx.scene.canvas.Canvas;

public class Player extends Entity {

	public Player(GameMaster gm, int x, int y, int dir, String spritefile) {
		super(gm, x, y, dir, spritefile);
	}

	@Override
	public void tick() {
		if (gm.context.eventMaster.isLeft())
			x--;
		if (gm.context.eventMaster.isRight())
			x++;
		if (gm.context.eventMaster.isUp())
			y--;
		if (gm.context.eventMaster.isDown())
			y++;
		if (gm.context.eventMaster.isSpace())
			spawnShot();
	}

	private void spawnShot() {
		
	}

	@Override
	public void render(Canvas canvas) {

	}

}
