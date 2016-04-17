package game.entity;

import core.Context;
import javafx.scene.canvas.GraphicsContext;

public class Explosion extends Entity {

	int a;
	int b;

	public Explosion(double x, double y) {
		super(x, y, 0, 0);
		a = b = 0;
		initImage(Context.instance.getGraphicsMaster().getImage("explosion-" + a + b), 2.0);
	}

	@Override
	public void tick(int ticks) {
		b += ticks;
		if (b > 7) {
			a++;
			b = 0;
		}
		if (a > 7) {
			invalidate();
		} else {
			changeImage(Context.instance.getGraphicsMaster().getImage("explosion-" + a + b));
		}
		redraw();
	}

	@Override
	protected void redraw() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		if (valid) {
			// TODO: only works for squares, must be adjusted for all rectangles
			double x = (canvasSize - imageWidth) / 2;
			double y = (canvasSize - imageHeight) / 2;
			gc.drawImage(image, x, y, imageWidth, imageHeight);
		}
	}

	@Override
	public void collided(Entity shot) {
		// nothing
	}

	@Override
	public EntityType getType() {
		return EntityType.EXPLOSION;
	}

}
