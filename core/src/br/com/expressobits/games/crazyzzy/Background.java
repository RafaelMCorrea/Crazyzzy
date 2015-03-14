package br.com.expressobits.games.crazyzzy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
	Texture textureBg;
	//512,432 bg 512 432
	
	//Sky #98E0E0
	static float[] azulLimpo= {155f/255f,224f/255f,224f/255f,1f};
	
	static float[] tardeLaranja= {248f/255f,224f/255f,176f/255f,1f};
	int tileWidth = 512;
	int tileHeight = 432;
	int count = 3;
	int andado = 0;
	int bgNumber = 0;
	static float andarTime= 1f;
	
	public Background() {
		textureBg = new Texture(Gdx.files.internal("background"+bgNumber+".png"));
	}
	
	public void draw(SpriteBatch batch){
		andado-=andarTime;
		if(andado<-tileWidth){
			andado=0;
		}
		batch.draw(textureBg,andado,0,0,0,tileWidth,tileHeight);
		batch.draw(textureBg,andado+tileWidth,0,0,0,tileWidth,tileHeight);
		batch.draw(textureBg,andado+(tileWidth*2),0,0,0,tileWidth,tileHeight);
	}
}
