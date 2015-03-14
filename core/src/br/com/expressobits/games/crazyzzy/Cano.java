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
	Rectangle rectangle = new Rectangle(640,0,64,64);
	
	int frame =0;
	
	static Vector2 velocity = new Vector2();
	
	public Cano() {
		textureCano = new Texture(Gdx.files.internal("canos.png"));
		velocity.x = 2;
	}

	public void draw(SpriteBatch batch){
		rectangle.x -=velocity.x;
		batch.draw(textureCano, rectangle.x, rectangle.y,frame*32,0,32,32);
		if(frame==1){
			for(float i = rectangle.y+32;i<Crazyzzy.HEIGHT;i+=32){
				batch.draw(textureCano, rectangle.x, i,0,32,32,32);
			}
		}else{
			for(float i = rectangle.y-32;i>-32;i-=32){
				batch.draw(textureCano, rectangle.x, i,0,32,32,32);
			}
		}
	}
	
	@Override
	public void dispose() {
		textureCano.dispose();
		
	}
}
