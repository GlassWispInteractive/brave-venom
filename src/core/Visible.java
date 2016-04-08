package core;

public interface Visible {
    /**
     * tick method for computing the logic
     *
     * @param ticks number of ticks
     */
    public void tick(int ticks);

    /**
     * Renders for visualization.
     */
    public void render();
}
