package br.com.expressobits.games.crazyzzy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;

public class AudioGerenciador implements Disposable{
	
	Music musicBg;
	Sound soundPontos;
	
	public AudioGerenciador() {
		musicBg = Gdx.audio.newMusic(Gdx.files.internal("Cognitive_Dissonance.ogg"));
	}

	@Override
	public void dispose() {
		musicBg.dispose();
		
	}
	
	public void playMusic(){
		musicBg.setLooping(true);
		//musicBg.play();
	}
	
	
	
}
