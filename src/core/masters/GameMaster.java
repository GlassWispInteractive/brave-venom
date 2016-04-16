package core.masters;

import core.Context;
import game.entity.Enemy;
import game.entity.EntityType;
import game.entity.Player;
import game.entity.Shot;
import game.scenes.GameScene;
import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.LinkedList;
import java.util.List;

public class GameMaster extends AnimationTimer {

	public final IntegerProperty life = new SimpleIntegerProperty(3);
	public final IntegerProperty round = new SimpleIntegerProperty(100);
	public final IntegerProperty roundTime = new SimpleIntegerProperty(100);
	public final IntegerProperty desperation = new SimpleIntegerProperty(100);
	public final IntegerProperty currentLife = new SimpleIntegerProperty(life.get());
	public final IntegerProperty currentRound = new SimpleIntegerProperty(1);
	public final IntegerProperty currentRoundTime = new SimpleIntegerProperty(roundTime.get());
	public final IntegerProperty currentDesperation = new SimpleIntegerProperty(0);

	public final Context context;
	public final List<Enemy> enemies = new LinkedList<>();
	public final List<Shot> playerShots = new LinkedList<>();
	public final List<Shot> enemyShots = new LinkedList<>();
	public Player player;
	private double lastNanoTime = System.nanoTime();
	private double time = 0;

	public GameMaster(Context context) {
		this.context = context;

	}

	@Override
	public void handle(long currentNanoTime) {
		double fps = 60.0;

		// calculate time since last update.
		time += (currentNanoTime - lastNanoTime) / 1000000000.0;
		lastNanoTime = currentNanoTime;
		int passedTicks = (int) Math.floor(time * fps);
		time -= passedTicks / fps;

		// TODO: add ESC event handler for game scene

		// compute a frame
		SceneMaster sceneMaster = context.getSceneMaster();
		sceneMaster.tick(passedTicks);
		sceneMaster.render();
	}

	@Override
	public void start() {
		super.start();
		player = new Player(this, 0, 0, 0);
		Enemy enemy = new Enemy(this, 100, 100, 0);

		GameScene gamescene = ((GameScene) context.getSceneMaster().getScreen("game"));
		//		gamescene.addEntitiy(EntityType.PLAYER, player);
		// ...

		gamescene.addEntitiy(EntityType.PLAYER, player);
		gamescene.addEntitiy(EntityType.ENEMY, enemy);
	}

	public void pause() {
		stop();
	}

	public void cotinue() {
		start();
	}

	@Override
	public void stop() {
		super.stop();
		// ...
	}

	public void restart() {
		stop();
		start();
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}

	public List<Shot> getEnemyShots() {
		return enemyShots;
	}

	public List<Shot> getPlayerShots() {
		return playerShots;
	}
}
