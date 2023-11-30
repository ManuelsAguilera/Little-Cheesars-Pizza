package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {

    private SpriteBatch batch;
    private GameControler gameControler;
    final GameMenu game;
    private final Music backgroundMusic;
	private BitmapFont font;
	private Texture backgroundImage;
	private Texture lifesCheesar;
	
	public GameScreen(final GameMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        batch = new SpriteBatch();
        
        //Añadir musica
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("novoimpegiato.mp3"));

        // Configura opciones de la música (por ejemplo, repetirla)
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f); // Ajusta el volumen de la música
        backgroundMusic.play(); // Inicia la reproducción de la música
        
        gameControler = GameControler.getInstance();
        backgroundImage = new Texture("Fondo.png");  // Ajusta el nombre del archivo de imagen
        lifesCheesar = new Texture("Corazon.png"); 
    }
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		gameControler.update(delta);
		if (gameControler.gameOver()) {
	        // La partida ha terminado, cambia a la pantalla de GameOverScreen
	        game.setScreen(new GameOverScreen(game)); // Reemplaza "GameOverScreen" con el nombre real de tu clase de pantalla de Game Over
	        dispose();
	    }     
        //Aqui se inioian las capas del juego!!
        batch.begin();
        batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //PONE EL FONDO
        gameControler.render(batch);
        //Información del Jugador
        font.getData().setScale(1.5f); 
        font.draw(batch, "Score: " + gameControler.getScore(), 10, Gdx.graphics.getHeight() - 10);
        font.draw(batch, "Lives: " + gameControler.getLifes(), Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 10);
        font.getData().setScale(1f); 
        font.draw(batch, "Orden de pizza: " + gameControler.getOrderConcat(),10 , Gdx.graphics.getHeight()-40);
        batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
	    batch.dispose();
	    font.dispose();
	    backgroundMusic.stop(); // Detén la música
	    backgroundMusic.dispose(); // Libera los recursos de la música
	}

}
