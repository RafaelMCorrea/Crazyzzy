package br.com.expressobits.games.crazyzzy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public class Cano implements Disposable{
	//x0y0 w32 h64
	Texture textureCano;
	Rectangle rectangle = new Rectangle(600,0,32,480);
	
	
	int tileWidth = 32;
	int tileHeight = 480;
	int frame=0;
	
	static Vector2 velocity = new Vector2();
	
	public Cano() {
		textureCano = new Texture(Gdx.files.internal("canos.png"));
		velocity.x = Crazyzzy.velocity.x;
	}

	public void draw(SpriteBatch batch){
		
		batch.draw(textureCano, rectangle.x, rectangle.y,frame*tileWidth,0,tileWidth,tileHeight);
	}
	
	public void update(){
		rectangle.x -=velocity.x;
	}
	
	
	@Override
	public void dispose() {
		textureCano.dispose();
		
	}
}
