package br.com.expressobits.games.crazyzzy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class TesteSprite {
	
	Vector2 velocity = new Vector2(2,0);
	Texture texture = new Texture(Gdx.files.internal("sprites/yzzy.png"));
	TextureRegion region = new TextureRegion(texture,0,0,32,32);
	TextureRegion region2 = new TextureRegion(texture,32,0,32,32);
	Sprite sprite= new Sprite(region);
	
	int x;
	int y;
	
	public TesteSprite(int x,int y) {
		this.x = x;
		this.y = y;
		sprite.setPosition(x, y);
		sprite.setBounds(x, y, 64, 64);
	}
	
	public void draw(SpriteBatch batch){
		batch.draw(region2, x, y,16,16,128,128,1,1,0);
		//sprite.draw(batch);
	}
	
}
