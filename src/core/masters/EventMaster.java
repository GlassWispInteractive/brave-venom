package core.masters;

import core.Context;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;

public class EventMaster {
	private final Context context;

	private List<KeyCode> input = new ArrayList<>();
	// private ArrayList<String> delete = new ArrayList<String>();

	public EventMaster(Context context) {
		this.context = context;
	}

	/**
	 * Adds a KeyCode as a current active input.
	 *
	 * @param keyCode the KeyCode
	 */
	public void addKeyCode(KeyCode keyCode) {
		if (!input.contains(keyCode))
			input.add(keyCode);
	}

	/**
	 * Removes a KeyCode from the current active inputs.
	 *
	 * @param keyCode the KeyCode
	 */
	public void removeKeyCode(KeyCode keyCode) {
		input.remove(keyCode);
	}

	/**
	 * clears the queue
	 * <p>
	 * use this if you want to get rid off the activly pressed buttons
	 */
	public void clear() {
		input.clear();
	}

	public boolean isLeft() {
		return input.contains(KeyCode.LEFT) || input.contains(KeyCode.A);
	}

	public boolean isRight() {
		return input.contains(KeyCode.RIGHT) || input.contains(KeyCode.D);
	}

	public boolean isUp() {
		return input.contains(KeyCode.UP) || input.contains(KeyCode.W);
	}

	public boolean isDown() {
		return input.contains(KeyCode.DOWN) || input.contains(KeyCode.S);
	}

	public boolean isUpLeft() {
		return isUp() && isLeft();
	}

	public boolean isUpRight() {
		return isUp() && isRight();
	}

	public boolean isDownLeft() {
		return isDown() && isLeft();
	}

	public boolean isDownRight() {
		return isDown() && isRight();
	}

	public double getDir() {
		if (isUpLeft())
			return 5 * 45;
		if (isUpRight())
			return 7 * 45;
		if (isDownLeft())
			return 3 * 45;
		if (isDownRight())
			return 1 * 45;
		if (isUp())
			return 6 * 45;
		if (isDown())
			return 2 * 45;
		if (isLeft())
			return 4 * 45;
		if (isRight())
			return 0 * 45;
		return Double.NaN;
	}

	public boolean isC() {
		return input.contains(KeyCode.C);
	}

	public boolean isQ() {
		return input.contains(KeyCode.Q);
	}

	public boolean isSpace() {
		return input.contains(KeyCode.SPACE);
	}

	public boolean isEnter() {
		return input.contains(KeyCode.ENTER);
	}

	public boolean isESC() {
		return input.contains(KeyCode.ESCAPE);
	}

	public boolean isOne() {
		return input.contains(KeyCode.DIGIT1) || input.contains(KeyCode.NUMPAD1);
	}

	public boolean isTwo() {
		return input.contains(KeyCode.DIGIT2) || input.contains(KeyCode.NUMPAD2);
	}

	public boolean isThree() {
		return input.contains(KeyCode.DIGIT3) || input.contains(KeyCode.NUMPAD3);
	}

	public boolean isFour() {
		return input.contains(KeyCode.DIGIT4) || input.contains(KeyCode.NUMPAD4);
	}

	public boolean isFive() {
		return input.contains(KeyCode.DIGIT5) || input.contains(KeyCode.NUMPAD5);
	}

	public Context getContext() {
		return context;
	}
}
