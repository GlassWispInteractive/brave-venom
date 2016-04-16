package game.entity;

import javafx.scene.canvas.GraphicsContext;
import core.Context;

public class Explosion extends Entity {

	int a;
	int b;

	public Explosion(double x, double y) {
		super(x, y, 0, 0);
		a = b = 0;
		initImage(Context.instance.getSceneMaster().getImage("explosion-"+a+b));
	}

	@Override
	public void tick(int ticks) {
		b++;
		if (b>7) {
			a++;
			b=0;
		}
		if (a>7) {
//			Context.instance.getGameMaster().explosions.remove(this);
			return;
		}
//		System.err.println("Changging Image to "+"explosion-"+a+b);
		changeImage(Context.instance.getSceneMaster().getImage("explosion-"+a+b));
		update();
	}

	private void update() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		// TODO: only works for squares, must be adjusted for all rectangles
		double x = 0;//(canvasSize - imageWidth) / 2;
		double y = 0;//(canvasSize - imageHeight) / 2;
		gc.drawImage(image, x, y);
	}

	@Override
	public void collided(Entity shot) {
		// nothing
	}

}
