package game.entity;

public class Shot extends Entity {

	protected Entity origin;

	public Shot(double x, double y, double dir, double speed, String spritefile, Entity origin) {
		super(x, y, dir, speed);
		this.origin = origin;
	}

	@Override
	public void tick(int ticks) {

	}

}
