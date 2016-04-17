package game.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import core.Context;
import core.masters.GraphicsMaster;

public class WorldBuilder {
	protected final int ROOM_LIMIT = 300;
	protected final Point[] NEIGHS_ALL = new Point[] { new Point(1, 0), new Point(-1, 0), new Point(0, 1),
			new Point(0, -1) };

	protected Random rnd = new Random();
	protected Point[][] world;
	protected int n, m;

	protected ArrayList<Point> fields;
	protected GraphicsMaster graphics = Context.instance.getGraphicsMaster();

	private final int field_width = 150;
	private final int field_height = 150;

	public WorldBuilder() {
		n = 3 * graphics.gameWidth.get() / field_width;
		m = 50 * graphics.gameHeight.get() / field_height;

		// make odd sizes
		n = n - (n + 1) % 2;
		m = m - (m + 1) % 2;

		world = new Point[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				world[i][j] = new Point(i, j);
			}
		}

		// for debug
		rnd.setSeed(42);

		// init
		initFields();

		genFloors();
		
//		System.out.println(world[1][1]);
//		System.out.println(world[n-2][m-2]);
		clearDeadends();
		// genMeteorits(0.1);

		for (int y = 0; y < m; y++) {
			for (int x = 0; x < n; x++) {
				System.out.print(world[x][y] + " ");
			}
			System.out.println();
		}

		System.out.println("works");
		System.exit(0);
	}

	private void initFields() {

		// create new array
		fields = new ArrayList<>();

		for (int i = 0; i < n; i += 1) {
			for (int j = 0; j < m; j += 1) {
				// queue neighbours when one corner is not a room
				fields.add(new Point(i, j));
			}
		}

		// choose connector in a random order
		Collections.shuffle(fields, rnd);
	}

	private void genFloors() {
		// init asteroids
		for (int i = 0; i < n; i += 1) {
			for (int j = 0; j < m; j += 1) {
				world[i][j].setValue(2);
			}
		}

		DisjointSet<Point> cc = new DisjointSet<>();

		ArrayList<Point[]> q = new ArrayList<>();

		for (int x = 1; x < n; x += 2) {
			for (int y = 1; y < m; y += 2) {
				// fill in fixed cells on odd / odd coordinates
				world[x][y].setValue(0);

				cc.makeSet(world[x][y]);

				// queue neighbours
				if (x + 2 < n)
					q.add(new Point[] { world[x][y], world[x + 2][y] });
				if (y + 2 < m)
					q.add(new Point[] { world[x][y], world[x][y + 2] });
			}
		}

		// choose connector in a random order
		Collections.shuffle(q);

		for (Point[] e : q) {
			// rename array

			if (cc.findSet(world[e[0].x][e[0].y]) == null) {
				continue;
			}

			if (cc.findSet(world[e[1].x][e[1].y]) == null) {
				continue;
			}

			// check if two cells are already connected
			if (cc.findSet(world[e[0].x][e[0].y]) == cc.findSet(world[e[1].x][e[1].y])) {
				continue;
			}

			// merge two components by adding a connector
			cc.union(world[e[0].x][e[0].y], world[e[1].x][e[1].y]);
			world[(e[0].x + e[1].x) / 2][(e[0].y + e[1].y) / 2].setValue(0);
		}
	}

	private void clearDeadends() {

		int count;
		boolean repeat = true, deadend[][];

		while (repeat) {
			// fresh inits for single execution of elemination
			deadend = new boolean[n][m];
			repeat = false;

			// dead end iff 3 neighbours are walls
			for (int x = 0; x < n; x++) {
				for (int y = 0; y < m; y++) {
					// no space
					if (world[x][y].getValue() == 2) {
						continue;
					}

					// ignore starters
					if ((x == 1 && y == 1) || (x == n - 2 && y == m - 2)) {
						continue;
					}
					
					count = 0;
					for (int j = 0; j < 4; j++) {
						int x_2 = x + NEIGHS_ALL[j].x, y_2 = y + NEIGHS_ALL[j].y;

						if (x_2 < 0 || x_2 >= n || y_2 < 0 || y_2 >= m) {
							continue;
						}

						if (world[x_2][y_2].getValue() == 2)
							count++;
					}

					if (count >= 3) {
						deadend[x][y] = true;
						repeat = true;
					}
				}
			}

			// remove dead ends
			for (int x = 0; x < n; x++) {
				for (int y = 0; y < m; y++) {
					if (deadend[x][y])
						world[x][y].setValue(2);
				}
			}
		}
	}

	private WorldBuilder genEntity(double amout, Creatable creator) {
		// room counter
		Point pos;
		double amountCounter = 0;

		// add one entity at a floor cell when the quotient adding up exceeds 1
		for (int i = 0; i < fields.size(); i++) {
			amountCounter += amout;

			// break condition
			if (amountCounter < 1) {
				continue;
			}

			// loop is true at least once
			while (amountCounter >= 1) {
				// update j
				amountCounter -= 1;

				// put up monster at a random floor cells
				boolean checked = true;
				while (checked) {
					pos = fields.get(0);

					for (int j = 0; j < NEIGHS_ALL.length; j++) {
						int x = pos.x + NEIGHS_ALL[j].x, y = pos.y + NEIGHS_ALL[j].y;

						if (x < 0 || x >= n || y < 0 || y >= m) {
							continue;
						}

						if (world[x][y].getValue() != 0) {
							i++;
							checked = true;
							continue;
						}
					}

					creator.generate(pos.x, pos.y);
					fields.remove(0);
					checked = false;
				}
			}
		}

		return this;
	}

	public WorldBuilder genMeteorits(double amout) {

		genEntity(amout, (x, y) -> {
			world[x][y].setValue(1);
		});

		return this;
	}
}