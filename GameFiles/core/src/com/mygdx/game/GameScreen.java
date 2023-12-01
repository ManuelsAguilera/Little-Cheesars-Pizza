package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class GameScreen implements Screen {

    private SpriteBatch batch;
    private GameControler gameControler;
    final GameMenu game;
    private final Music backgroundMusic;
	private Texture backgroundImage;
	private Texture tresvidas;
	private Texture dosvidas;
	private Texture unavidas;
	private Texture[] texturas;//Para cambiar texturas
	private OrthographicCamera camera;
	
	public GameScreen(final GameMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        game.getFont();
        batch = new SpriteBatch();
        
        camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
        
        //Añadir musica
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("novoimpegiato.mp3"));

        // Configura opciones de la música (por ejemplo, repetirla)
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f); // Ajusta el volumen de la música
        backgroundMusic.play(); // Inicia la reproducción de la música
        
        gameControler = GameControler.getInstance();
        backgroundImage = new Texture("Fondo.png");  // Ajusta el nombre del archivo de imagen
        
        texturas = new Texture[3];
        tresvidas = new Texture("vida3de3.png");
        dosvidas = new Texture("vida2de3.png");
        unavidas = new Texture("vida1de3.png");
        texturas[0] = unavidas;
        texturas[1] = dosvidas;
        texturas[2] = tresvidas;
    }
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	public Music getBackgroundMusic() {
        return backgroundMusic;
    }
	
	public void writeInfo() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Montserrat-Bold.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 24; // Tamaño de la fuente
		BitmapFont font = generator.generateFont(parameter);
		generator.dispose();
		font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		//Información del Jugador
        font.draw(batch, "Ganancias: " + gameControler.getScore(), 10, Gdx.graphics.getHeight() - 10);
        font.draw(batch, "Orden: " + gameControler.getOrderConcat(),10 , Gdx.graphics.getHeight()-40);
        int cont = gameControler.getLifes();
        batch.draw(texturas[cont-1], Gdx.graphics.getWidth() - 130, Gdx.graphics.getHeight() - 20, 128, 128);
	}
	
	@Override
	public void render(float delta) {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		gameControler.update(delta);
		if (gameControler.gameOver()) {
	        // La partida ha terminado, cambia a la pantalla de GameOverScreen
	        game.setScreen(new GameOverScreen(game)); // Reemplaza "GameOverScreen" con el nombre real de tu clase de pantalla de Game Over
	        dispose();
	        return;
	    }
        //Aqui se inioian las capas del juego!!
        batch.begin();
        //Fondo
        batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameControler.render(batch);
        writeInfo();
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
	public void resume(){
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		backgroundMusic.stop();
		gameControler.destruir();
		
	}

}
