package br.com.expressobits.games.crazyzzy;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Crazyzzy extends ApplicationAdapter {
	SpriteBatch batch;
	Mario mario;
	Background background;
	ArrayList<Cano> canos;
	ArrayList<Rectangle> rPontos;
	float intervaloCanos = 0f;
	static float TIMEABOVECANOS = 2f;
	static int WIDTH = 600;
	static int HEIGHT = 480;
	int pontos;
	BitmapFont bf;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		mario = new Mario();
		background = new Background();
		canos = new ArrayList<Cano>();
		rPontos = new ArrayList<Rectangle>();
		bf = new BitmapFont();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(248f/255f,224f/255f,176f/255f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		background.draw(batch);
		mario.draw(batch);
		
		intervaloCanos -=Gdx.graphics.getDeltaTime();
		if(intervaloCanos<0){
			criaCanos();
			intervaloCanos = TIMEABOVECANOS;
		}
		
		for (Cano cano : canos) {
			cano.draw(batch);
		}
		for (int i = rPontos.size()-1; i>=0; i--) {
			rPontos.get(i).x-=Cano.velocity.x;
			if(rPontos.get(i).overlaps(mario.rectangle)){
				pontos++;
				rPontos.remove(i);
			}
		}
		
		bf.draw(batch,"Pontos "+pontos,10,HEIGHT-20);
		batch.end();
	}
	
	private void criaCanos(){
		Cano c1 = new Cano();
		c1.frame=0;
		c1.rectangle.y=10;
		canos.add(c1);
		Cano c2 = new Cano();
		c2.frame=1;
		c2.rectangle.y=100;
		canos.add(c2);
		
		Rectangle ri = new Rectangle(c1.rectangle.x+45,MathUtils.random(480),32,100);
		c1.rectangle.y = ri.y-ri.height;
		c2.rectangle.y = ri.y+c2.rectangle.height;
		rPontos.add(ri);
	}
}
