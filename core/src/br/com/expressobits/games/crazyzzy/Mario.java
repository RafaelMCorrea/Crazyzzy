package br.com.expressobits.games.crazyzzy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public class Mario implements Disposable{
	
	public enum State {VIVO,MORTO};
	
	public State estado;
	
	//frames=5 32x-41y
	public Texture textureMario;
	int tileWidth=32;
	int tileHeight=41;
	int frameCount=0;
	
	float frameInterval = 0;
	static float FRAMETIME = 0.2f;
	
	Rectangle rectangle;
	
	Vector2 velocity = new Vector2();
	float gravity = 0.1f;
	
	
	public Mario() {
		estado = State.MORTO;
		textureMario = new Texture(Gdx.files.internal("mario_sheet.png"));
		rectangle = new Rectangle(200,160,32,41);
	}
	
	public void draw(SpriteBatch batch){
		velocity.y -= gravity;
		rectangle.y += velocity.y;
		if(estado == State.VIVO){
			if(Gdx.input.justTouched()){
				velocity.y= 3f;
				frameCount=0;
			}
		}
		
		
		frameInterval -= Gdx.graphics.getDeltaTime();
		if(frameInterval<0){
			frameInterval = FRAMETIME;
			if(frameCount<4){
				frameCount++;
			}else{
				frameCount=3;
			}
			
		}
		batch.draw(textureMario,rectangle.x,rectangle.y,frameCount*tileWidth,0,tileWidth,tileHeight);
	}

	@Override
	public void dispose() {
		textureMario.dispose();
		
	}

	public void matar() {
		estado = State.MORTO;
		
	}
	
	public void reviver(){
		estado = State.VIVO;
		rectangle.y = Crazyzzy.WIDTH/2;
		velocity.y =0;
	}
}
