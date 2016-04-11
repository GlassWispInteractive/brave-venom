package core.masters;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class AbstractGraphicsMaster {

    protected IntegerProperty windowWdith = new SimpleIntegerProperty();
    protected IntegerProperty windowHeight = new SimpleIntegerProperty();

    protected IntegerProperty tileSize = new SimpleIntegerProperty();
    protected IntegerProperty panelHeight = new SimpleIntegerProperty();

    protected IntegerProperty gameWidth = new SimpleIntegerProperty();
    protected IntegerProperty gameHeight = new SimpleIntegerProperty();

    public int getWindowWdith() {
        return windowWdith.get();
    }

    public void setWindowWdith(int windowWdith) {
        this.windowWdith.set(windowWdith);
    }

    public IntegerProperty windowWdithProperty() {
        return windowWdith;
    }

    public int getWindowHeight() {
        return windowHeight.get();
    }

    public void setWindowHeight(int windowHeight) {
        this.windowHeight.set(windowHeight);
    }

    public IntegerProperty windowHeightProperty() {
        return windowHeight;
    }

    public int getTileSize() {
        return tileSize.get();
    }

    public void setTileSize(int tileSize) {
        this.tileSize.set(tileSize);
    }

    public IntegerProperty tileSizeProperty() {
        return tileSize;
    }

    public int getPanelHeight() {
        return panelHeight.get();
    }

    public void setPanelHeight(int panelHeight) {
        this.panelHeight.set(panelHeight);
    }

    public IntegerProperty panelHeightProperty() {
        return panelHeight;
    }

    public int getGameWidth() {
        return gameWidth.get();
    }

    public void setGameWidth(int gameWidth) {
        this.gameWidth.set(gameWidth);
    }

    public IntegerProperty gameWidthProperty() {
        return gameWidth;
    }

    public int getGameHeight() {
        return gameHeight.get();
    }

    public void setGameHeight(int gameHeight) {
        this.gameHeight.set(gameHeight);
    }

    public IntegerProperty gameHeightProperty() {
        return gameHeight;
    }
}
