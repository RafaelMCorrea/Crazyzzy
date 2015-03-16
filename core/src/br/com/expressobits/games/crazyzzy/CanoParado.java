package br.com.expressobits.games.crazyzzy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public class CanoParado extends Cano implements Disposable{
	
	
	
	
	public CanoParado(int alturaEntre, int minimoAltura) {
		super(alturaEntre, minimoAltura);
		textureCano = new Texture(Gdx.files.internal("sprites/canosVerdes.png"));
		// TODO Auto-generated constructor stub
	}

	public void update(){
		super.update();
		
	}
	
	
}
