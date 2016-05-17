package game;

import java.io.IOException;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {

	private static Mixer mixer;
	private static Clip bgMusic;
	private URL bgURL;
	private AudioInputStream audioStream;

	public AudioPlayer() {

		mixer = AudioSystem.getMixer(null);

		try {
			bgMusic = (Clip) mixer.getLine(new DataLine.Info(Clip.class, null));
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

		try {
			// background music
			bgURL = AudioPlayer.class.getResource("/theme_music_16bit.wav");
			audioStream = AudioSystem.getAudioInputStream(bgURL);
			

		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void playBgMusic() {
		try {
			bgMusic.open(audioStream);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bgMusic.loop(Clip.LOOP_CONTINUOUSLY);
		FloatControl gainControl = (FloatControl) bgMusic.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-25.0f);
	}
}