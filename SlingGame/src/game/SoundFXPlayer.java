package game;


import java.io.IOException;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundFXPlayer {

	private static Mixer mixer;
	private static Clip bbSound, trSound;
	private URL bbURL, trURL;
	private AudioInputStream audioStream;

	public SoundFXPlayer() {

		mixer = AudioSystem.getMixer(null);

		try {
			bbSound = (Clip) mixer.getLine(new DataLine.Info(Clip.class, null));
			trSound = (Clip) mixer.getLine(new DataLine.Info(Clip.class, null));
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

		try {

			// beanbag sound
			bbURL = AudioPlayer.class.getResource("/beanbag-sound.wav");
			audioStream = AudioSystem.getAudioInputStream(bbURL);
			bbSound.open(audioStream);

			// trustee sound
			trURL = AudioPlayer.class.getResource("/trustee-sound.wav");
			audioStream = AudioSystem.getAudioInputStream(trURL);
			trSound.open(audioStream);

		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void playBbSound() {
		bbSound.setMicrosecondPosition(0);
		bbSound.start();
	}

	public void playTrSound() {
		trSound.setMicrosecondPosition(0);
		trSound.start();

	}

}