package br.com.expressobits.games.crazyzzy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class IntroScreen implements Screen {

	final Crazyzzy game;
	Texture logo = new Texture(Gdx.files.internal("icone.png"));
	float timeElapsed = 0f;
	
	public IntroScreen(final Crazyzzy game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(timeElapsed,1-timeElapsed,1-timeElapsed,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		game.batch.draw(logo,600/2-144/2,480/2-144/2,144,144);
		game.batch.end();
		
		timeElapsed+=Gdx.graphics.getDeltaTime()/3;
		if(timeElapsed>1f){
			timeElapsed=0f;
			game.setScreen(new GameScreen(game));
			dispose();
		}
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
