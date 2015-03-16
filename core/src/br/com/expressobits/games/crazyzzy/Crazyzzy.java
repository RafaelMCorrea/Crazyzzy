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
	static AudioGerenciador audioGerenciador;
	SpriteBatch batch;
	Mario mario;
	Background background;
	ArrayList<Cano> canos;
	ArrayList<Fogo> fogos;
	float intervaloCanos = 0f;
	static float TIMEABOVECANOS = 1.8f;
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
		audioGerenciador = new AudioGerenciador();
		batch = new SpriteBatch();
		mario = new Mario();
		background = new Background();
		canos = new ArrayList<Cano>();
		fogos = new ArrayList<Fogo>();
		bf = new BitmapFont();
		prefs = Gdx.app.getPreferences("Crazyzzy");
		if(prefs.getInteger("recorde")>0){
			recorde=prefs.getInteger("recorde");
		}
		
	}
	
	public void inicia(){
		mario.velocity.y = 2;
		velocity.x = 2;
		pontos=0;
		audioGerenciador.musicBg.play();
	}

	public void draw(){
		background.draw(batch);
		mario.draw(batch);
		for (Fogo fogo : fogos) {
			fogo.draw(batch);
		}
		for (Cano cano : canos) {
			cano.draw(batch);
		}
		
	}
	
	public void atualizaJogo(){
		intervaloCanos -=Gdx.graphics.getDeltaTime();
		if(intervaloCanos<0){
			if(pontos>2 && (pontos+2)%6==0){
				criaCano(150,50,2);
			}else if(pontos>2 && (pontos+2)%3==0){
				criaCano(150,50,1);
			}else{
				criaCano(150,50,0);
			}
			
			intervaloCanos = TIMEABOVECANOS;
		}
		for (Fogo fogo : fogos) {
			fogo.update();
			if(mario.rectangle.overlaps(fogo.rectangle)){
				marioColidiu();
			}
		}
		for (Cano cano : canos) {
			
			if(cano instanceof CanoFogo){
				if(((CanoFogo) cano).lastTime<0){
				Fogo fogo = new Fogo(cano.rectangle.x,cano.rectangle.y,new Vector2(-velocity.x,4));
				fogos.add(fogo);
				((CanoFogo) cano).lastTime=CanoFogo.TIMEABOVEFIRES;
				}
			}
			if(mario.rectangle.overlaps(cano.rectangle) ||
					mario.rectangle.overlaps(cano.rectangle2)){
				marioColidiu();
			}
			if(mario.rectangle.overlaps(cano.ri)){
				
					
					if(cano.getPonto()>0){
						pontos++;
						if(recorde!=pontos){
							audioGerenciador.soundPickup.play();
						}else{
							audioGerenciador.soundHighScore.play();
						}
					}
					
				
			}
			if(mario.estado==Mario.State.VIVO){
				cano.update();
			}
		}
	}

	private void marioColidiu() {
		mario.matar();
		audioGerenciador.soundHit.play();
		velocity.x = 0;
		if(pontos>recorde){
			recorde = pontos;
			prefs.putInteger("recorde",recorde);
			prefs.flush();
		}
	}
	
	public void atualizaGameOver(){
		bf.draw(batch,"GAME OVER!",WIDTH/2,HEIGHT/2);
		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			
			limpa();
		}
	}

	private void limpa() {
		for (int i=canos.size()-1;i>=0;i--) {
			canos.get(i).dispose();
			canos.remove(i);
		}
		for (int i=fogos.size()-1;i>=0;i--) {
			fogos.get(i).dispose();
			fogos.remove(i);
		}
		mario.reviver();
		inicia();
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
	
	private void criaCano(int alturaEntre,int mininoAltura,int tipo){
		if(tipo==2){
			CanoFogo cano = new CanoFogo(alturaEntre+100,mininoAltura);
			canos.add(cano);
		}else if(tipo==1){
			CanoAndarilho cano = new CanoAndarilho(alturaEntre,mininoAltura);
			canos.add(cano);
		}else{
			Cano cano = new CanoParado(alturaEntre,mininoAltura);
			canos.add(cano);
		}
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
		audioGerenciador.dispose();
	}
	
}
