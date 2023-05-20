package sound;

import javafx.application.Platform;
import javafx.scene.media.AudioClip;


/**
 * Sound Manager
 * @author Petitor
 *
 */
public class SoundManager {
	/**
	 * current background music
	 */
	private static AudioClip currentBGM;

	/**
	 * Set Current Background Music With Configured Sound Volume
	 * 
	 * @param url Background Music URL
	 */
	public static void setCurrentBGM(String url) {
		if(currentBGM != null) {
			currentBGM.stop();
		}
		currentBGM = new AudioClip(ClassLoader.getSystemResource(url).toString());
		currentBGM.setCycleCount(AudioClip.INDEFINITE);
		currentBGM.play();
	}

	/**
	 * Set Current Background Music With Specific Volume
	 * 
	 * @param url    Background Music URL
	 * @param volume Sound Volume
	 */
	public static void setCurrentBGM(String url, double volume) {
		if(currentBGM != null) {
			currentBGM.stop();
		}
		currentBGM = new AudioClip(ClassLoader.getSystemResource(url).toString());
		currentBGM.setCycleCount(AudioClip.INDEFINITE);
		currentBGM.setVolume(volume);
		Platform.runLater(
				  () -> {
					  currentBGM.play();
				  }
				);
	}
	
	/**
	 * Play Sound Once With Configured Sound Volume
	 * 
	 * @param url Sound URL
	 */
	public static void playSound(String url) {
		Platform.runLater(
				  () -> {
					  AudioClip audio = new AudioClip(ClassLoader.getSystemResource(url).toString());
						audio.play();
				  }
				);
	}
	
	/**
	 * Play Sound Once With Specific Volume
	 * 
	 * @param url Sound URL
	 * @param volume sound volume
	 */
	public static void playSound(String url, double volume) {
		AudioClip audio = new AudioClip(ClassLoader.getSystemResource(url).toString());
		audio.setVolume(volume);
		audio.play();
	}
}