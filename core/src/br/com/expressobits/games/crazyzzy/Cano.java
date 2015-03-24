package br.com.expressobits.games.crazyzzy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public abstract class Cano implements Disposable{
	

	Vector2 velocity = new Vector2(2,0);
	Texture textureCano;
	TextureRegion[] region = new TextureRegion[6];
	Rectangle rectangle;
	Rectangle rectangle2;
	Rectangle ri;
	int ponto = 1;
	int tileWidth = 32;
	int tileHeight = 480;
	int tipo=0;
	
	public Cano(int alturaEntre,int minimoAltura) {
		
		textureCano = new Texture(Gdx.files.internal("sprites/canos.png"));
		for (int i = 0; i < region.length; i++) {
			region[i] = new TextureRegion(textureCano,i*32,0,32,480);
		}
		
		rectangle = new Rectangle(800,0,64,480);
		rectangle2 = new Rectangle(800,0,64,480);
		int i=0;
		try{
			i = MathUtils.random(GameScreen.HEIGHT-(2*minimoAltura)-alturaEntre);
			
		}catch(java.lang.IllegalArgumentException ex){
			System.out.println("Random igual á = "+i+"\n"+ex.getMessage());
		}
		ri = new Rectangle(rectangle.x+45
				,i
				,32
				,alturaEntre);
		rectangle = new Rectangle(800,0,64,960);
		rectangle2 = new Rectangle(800,0,64,960);
		
		
		rectangle2.y = +ri.y+ri.height;
		rectangle.y = ri.y-rectangle2.height;
		velocity.x = GameScreen.velocity.x;
	}
	
	public void update(){
		ri.x -=velocity.x;
		rectangle.x =ri.x-45;
		rectangle2.x =ri.x-45;
	}
	public void draw(SpriteBatch batch){
		batch.draw(region[0+(tipo*2)], rectangle.x, rectangle.y ,16,16,rectangle.width,rectangle.height,1,1,0);
		batch.draw(region[1+(tipo*2)], rectangle2.x, rectangle2.y,16,16,rectangle.width,rectangle.height,1,1,0);
	}
	
	@Override
	public void dispose() {
		textureCano.dispose();
	}
	
	public int getPonto() {
		int result = ponto;
		ponto =0;
		return result;
	}
}
