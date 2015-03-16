package br.com.expressobits.games.crazyzzy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;

public class AudioGerenciador implements Disposable{
	
	public static String PATH = "audio/";
	public static String MUSIC1 = PATH+"528702_WIP-some-techno.mp3";
	public static String MUSIC2 = PATH+"GAME_MUSIC.wav";
	public static String SOUNDPICKUP = PATH+"coin2.wav";
	public static String SOUNDHIGHSCORE = PATH+"Hi-Score.ogg";
	public static String SOUNDLEVANTA = PATH+"levanta.wav";
	public static String SOUNDCAI = PATH+"cai.wav";
	public static String SOUNDHIT = PATH+"qubodupPunch04.ogg";
	
	
	public Music musicBg;
	Sound soundPickup;
	Sound soundLevanta;
	Sound soundCai;
	Sound soundHighScore;
	Sound soundHit;
	
	public AudioGerenciador() {
		musicBg = Gdx.audio.newMusic(Gdx.files.internal(MUSIC1));
		soundPickup = Gdx.audio.newSound(Gdx.files.internal(SOUNDPICKUP));
		soundHighScore = Gdx.audio.newSound(Gdx.files.internal(SOUNDHIGHSCORE));
		soundLevanta = Gdx.audio.newSound(Gdx.files.internal(SOUNDLEVANTA));
		soundCai = Gdx.audio.newSound(Gdx.files.internal(SOUNDCAI));
		soundHit = Gdx.audio.newSound(Gdx.files.internal(SOUNDHIT));
		musicBg.setLooping(true);
	}

	@Override
	public void dispose() {
		musicBg.dispose();
		soundPickup.dispose();
	}
	
	
	
}
