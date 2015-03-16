package br.com.expressobits.games.crazyzzy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public class CanoAndarilho extends Cano implements Disposable{
	
	Vector2 velocity = new Vector2(2,3);
	static int RANDOMSEED = 200;
	static int UPRANDOM=MathUtils.random(RANDOMSEED);
	static int DOWNRANDOM=Crazyzzy.HEIGHT-MathUtils.random(RANDOMSEED);
	int lastUpRandom=UPRANDOM;
	int lastDownRandom=DOWNRANDOM;
	
	
	public CanoAndarilho(int alturaEntre,int minimoAltura) {
		super(alturaEntre, minimoAltura);
		textureCano = new Texture(Gdx.files.internal("sprites/canosAmarelos.png"));
	}
	
	public void update(){
		super.update();
		
		
		ri.y +=velocity.y;
		if(ri.y<lastUpRandom){
			velocity.y=2;
			lastUpRandom = MathUtils.random(RANDOMSEED);
		}
		if(ri.y+ri.height>lastDownRandom){
			velocity.y=-2;
			lastDownRandom = Crazyzzy.HEIGHT-MathUtils.random(RANDOMSEED);
		}
		
		rectangle2.y = +ri.y+ri.height;
		rectangle.y = ri.y-rectangle2.height;
	}
	
	
}
