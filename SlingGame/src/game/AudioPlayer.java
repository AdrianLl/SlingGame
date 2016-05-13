package game;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {

	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();

	public static void load() {

		try {

			soundMap.put("bbSound", new Sound("audio/beanbag-sound.wav"));
			soundMap.put("trSound", new Sound("audio/trustee-sound.wav"));
			musicMap.put("bgMusic", new Music("audio/background-sound.wav"));

		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	public static Music getMusic(String key) {
		return musicMap.get(key);
	}

	public static Sound getSound(String key) {
		return soundMap.get(key);
	}
}
