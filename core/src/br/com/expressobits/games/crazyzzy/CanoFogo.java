package br.com.expressobits.games.crazyzzy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.sun.javafx.geom.Vec2d;

public class CanoFogo extends Cano{
	
	
	float lastTime= Gdx.graphics.getDeltaTime();
	static float TIMEABOVEFIRES = 1f; 
	private boolean flagUpDownFires=false;
	
	
	public CanoFogo(int alturaEntre, int minimoAltura) {
		super(alturaEntre, minimoAltura);
		tipo = 2;
	}

	@Override
	public void update() {
		super.update();
		lastTime -=Gdx.graphics.getDeltaTime();
		
	}

}
