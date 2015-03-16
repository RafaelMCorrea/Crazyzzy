package br.com.expressobits.games.crazyzzy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public class Fogo implements Disposable{
	Texture textureFogo;
	Rectangle rectangle;
	Vector2 velocity;
	int frameCount=0;
	
	float frameInterval = 0;
	static float FRAMETIME = 0.2f;
	
	public Fogo(float x,float y,Vector2 velocity) {
		textureFogo = new Texture(Gdx.files.internal("sprites/flyenemies.png"));
		rectangle = new Rectangle(x,y,16,16);
		this.velocity = velocity;
	}
	
	public void update(){
		rectangle.x += velocity.x;
		rectangle.y += velocity.y;
		frameInterval -= Gdx.graphics.getDeltaTime();
		if(frameInterval<0){
			frameInterval = FRAMETIME;
			if(frameCount<1){
				frameCount++;
			}else{
				frameCount=0;
			}
			
		}
	}
	
	public void draw(SpriteBatch batch){
		batch.draw(textureFogo,rectangle.x,rectangle.y,frameCount*16,0,16,16);
	}

	@Override
	public void dispose() {
		textureFogo.dispose();
		
	}
}
