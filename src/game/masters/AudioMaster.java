package game.masters;

import core.masters.AbstractAudioMaster;
import javafx.scene.media.MediaPlayer;

public class AudioMaster extends AbstractAudioMaster {
    private boolean on = false;
    
    private MediaPlayer mediaPlayer;
    
    public AudioMaster() {
        // Media sound = new Media(new
        // File("src/res/audio/go.ogg").toURI().toString());
        // mediaPlayer = new MediaPlayer(sound);
    }
    
    private void update() {
        if (on) {
            mediaPlayer.setAutoPlay(true);
        }
    }
    
    /**
     * @return the on
     */
    public boolean isOn() {
        return on;
    }
    
    /**
     * @param on
     *            the on to set
     */
    public void setOn(boolean on) {
        this.on = on;
        update();
    }
}
