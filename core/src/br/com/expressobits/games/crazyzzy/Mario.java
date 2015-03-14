package br.com.expressobits.games.crazyzzy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Mario {
	private Texture textureMario;
	
	public Mario() {
		textureMario = new Texture(Gdx.files.internal("mario_sheet.png"));
	}
	
	public void draw(SpriteBatch batch){
		batch.draw(textureMario,100,200);
	}
}
