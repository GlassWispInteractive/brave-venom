package core;

public interface Visible {
	/**
	 * tick method for computing the logic
	 * 
	 * @param ticks
	 */
	public void tick(int ticks);

	/**
	 * render method for visualization
	 */
	public void render();
}
