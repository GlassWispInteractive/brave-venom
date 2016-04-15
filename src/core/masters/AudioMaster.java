package core.masters;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioMaster {
	private boolean on = true;

	private MediaPlayer mediaPlayer;

	public AudioMaster() {
		Media sound = new Media(new File("res/audio/lowThreeTone.mp3").toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setAutoPlay(true);
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
	 * @param on the on to set
	 */
	public void setOn(boolean on) {
		this.on = on;
		update();
	}
}
