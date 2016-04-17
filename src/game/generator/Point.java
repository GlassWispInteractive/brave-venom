package game.generator;

public class Point {
	public int x, y;
	private int value;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		this.value = 0;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value == 2 ? "X" :  " ";
	}
}
