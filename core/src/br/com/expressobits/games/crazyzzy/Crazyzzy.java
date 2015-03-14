package br.com.expressobits.games.crazyzzy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Crazyzzy extends ApplicationAdapter {
	SpriteBatch batch;
	Mario mario;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		mario = new Mario();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		mario.draw(batch);
		batch.end();
	}
}
