package br.com.expressobits.games.crazyzzy;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Crazyzzy extends ApplicationAdapter {
	SpriteBatch batch;
	Mario mario;
	Background background;
	ArrayList<Cano> canos;
	ArrayList<Rectangle> rPontos;
	float intervaloCanos = 0f;
	static float TIMEABOVECANOS = 4f;
	static int WIDTH = 600;
	static int HEIGHT = 480;
	int pontos;
	int recorde;
	BitmapFont bf;
	Music musicBg;
	Preferences prefs;
	
	static public Vector2 velocity = new Vector2(2,0);
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		mario = new Mario();
		background = new Background();
		canos = new ArrayList<Cano>();
		rPontos = new ArrayList<Rectangle>();
		bf = new BitmapFont();
		musicBg = Gdx.audio.newMusic(Gdx.files.internal("Cognitive_Dissonance.ogg"));
		musicBg.setLooping(true);
		musicBg.play();
		prefs = Gdx.app.getPreferences("Crazyzzy");
		if(prefs.getInteger("recorde")>0){
			recorde=prefs.getInteger("recorde");
		}
		
	}

	
	public void draw(){
		background.draw(batch);
		mario.draw(batch);
		for (Cano cano : canos) {
			cano.draw(batch);
		}
	}
	
	public void atualizaJogo(){
		intervaloCanos -=Gdx.graphics.getDeltaTime();
		if(intervaloCanos<0){
			criaCanos();
			intervaloCanos = TIMEABOVECANOS;
		}
		for (Cano cano : canos) {
			if(mario.rectangle.overlaps(cano.rectangle)){
				mario.matar();
				velocity.x = 0;
				if(pontos>recorde){
					recorde = pontos;
					prefs.putInteger("recorde",recorde);
					prefs.flush();
				}
			}
			if(mario.estado==Mario.State.VIVO){
				cano.update();
			}
		}
		for (int i = rPontos.size()-1; i>=0; i--) {
			rPontos.get(i).x-=velocity.x;
			
			if(rPontos.get(i).overlaps(mario.rectangle)){
				pontos++;
				rPontos.remove(i);
			}
		}
	}
	
	public void atualizaGameOver(){
		bf.draw(batch,"GAME OVER!",WIDTH/2,HEIGHT/2);
		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			
			limpa();
		}
	}


	private void limpa() {
		for (int i=canos.size();i<0;i--) {
			canos.get(i).dispose();
			canos.remove(i);
		}
		for (int i=rPontos.size();i<0;i--) {
			rPontos.remove(i);
		}
		pontos=0;
		mario.reviver();
	}
	
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(248f/255f,224f/255f,176f/255f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		if(mario.estado == Mario.State.VIVO){
			atualizaJogo();
			draw();
		}else{
			draw();
			atualizaGameOver();
		}
		
		
		bf.setColor(Color.BLACK);
		bf.draw(batch,"Pontos "+pontos,10,HEIGHT-20);
		bf.draw(batch,"Recorde "+recorde,100,HEIGHT-20);
		
		batch.end();
	}
	
	private void criaCanos(){
		Cano c1 = new Cano();
		c1.frame=0;
		canos.add(c1);
		Cano c2 = new Cano();
		c2.frame=1;
		canos.add(c2);
		
		Rectangle ri = new Rectangle(c1.rectangle.x,MathUtils.random(380),32,100);
		c2.rectangle.y = +ri.y+ri.height;
		c1.rectangle.y = ri.y-c2.rectangle.height;
		rPontos.add(ri);
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		for (Cano cano : canos) {
			cano.dispose();
		}
		mario.dispose();
		background.dispose();
	}
}
