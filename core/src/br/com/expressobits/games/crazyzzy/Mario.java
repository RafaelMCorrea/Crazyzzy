package br.com.expressobits.games.crazyzzy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Mario {
	private Texture textureMario;
	
	public Mario() {
		textureMario = new Texture(Gdx.files.internal("mario_sheet.png"));
	}
}
