package bravevenom;

import core.Game;

public class BraveVenomGame extends Game {
    private static final String TITLE = "BraveVenomGame";

    public BraveVenomGame() {
        super(TITLE, new BVGraphicsManager(), new BVAudioManager());
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void load() {
        // TODO: implement me
    }

    @Override
    protected void save() {
        // TODO: implement me
    }
}
