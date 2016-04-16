package core.masters;

import core.Context;
import game.entity.*;
import game.scenes.GameScene;
import javafx.animation.AnimationTimer;

import java.util.LinkedList;
import java.util.List;

public class GameMaster extends AnimationTimer {

	public final Context context;
	public final List<Enemy> enemies = new LinkedList<>();
	public final List<Shot> playerShots = new LinkedList<>();
	public final List<Shot> enemyShots = new LinkedList<>();

	public final int maxLife = 3;
	public final int maxRound = 100;
	public final int maxRoundTime = 100;
	public final int maxDesperation = 100;
	public Player player;
	public double mouseX;
	public double mouseY;
	private int currentLife = maxLife;
	private int currentRound = 1;
	private int currentRoundTime = maxRoundTime;
	private int currentDesperation = 0;
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

		tick(passedTicks);
		render();
	}

	private void tick(int ticks) {
		SceneMaster sceneMaster = context.getSceneMaster();
		sceneMaster.tick(ticks);
		for (Enemy enemy : enemies)
			enemy.tick(ticks);
		for (Shot shot : playerShots)
			shot.tick(ticks);
		for (Shot shot : enemyShots)
			shot.tick(ticks);
		player.tick(ticks);

		// debug info
		GameScene gameScene = ((GameScene) sceneMaster.getContext().getSceneMaster().getScene("game"));
		gameScene.allticks += ticks;
		gameScene.tickLabel.setText(gameScene.allticks + " ticks");
	}

	private void render() {

		SceneMaster sceneMaster = context.getSceneMaster();
		sceneMaster.render();
		GameScene gameScene = ((GameScene) sceneMaster.getContext().getSceneMaster().getScene("game"));
		gameScene.update();
	}

	@Override
	public void start() {
		super.start();
		player = new Player(200, 200, 0, 10);
		Enemy enemy1 = new ActiveEnemy(100, 100, 0, 0);
		Enemy enemy2 = new PassivEnemy(100, 100, 0, 0);

		GameScene gamescene = ((GameScene) context.getSceneMaster().getScene("game"));
		//		gamescene.addEntitiy(EntityType.PLAYER, player);
		// ...

		gamescene.addEntitiy(EntityType.PLAYER, player);
		gamescene.addEntitiy(EntityType.ENEMY, enemy1);
		gamescene.addEntitiy(EntityType.ENEMY, enemy2);
		enemies.add(enemy1);
		enemies.add(enemy2);
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

	public int getCurrentLife() {
		return currentLife;
	}

	public void damage(int damage) {
		if (damage >= 0)
			this.currentLife = Math.max(currentLife - damage, 0);
	}

	public void heal(int health) {
		if (health >= 0)
			this.currentLife = Math.min(currentLife + health, maxLife);
	}

	public int getCurrentRound() {
		return currentRound;
	}

	public void setCurrentRound(int currentRound) {
		this.currentRound = currentRound;
	}

	public int getCurrentRoundTime() {
		return currentRoundTime;
	}

	public void startRoundTime() {
		this.currentRoundTime = 0;
	}

	public int getCurrentDesperation() {
		return currentDesperation;
	}

	public void addDesperation(int addedDesperation) {
		if (addedDesperation >= 0)
			currentDesperation = Math.min(currentDesperation + addedDesperation, maxDesperation);
		if (currentDesperation == maxDesperation) {
			// TODO: start furious round
		}
	}

	public void resetCurrentDesperation() {
		currentDesperation = 0;
	}
}
