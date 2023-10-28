package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {

    private SpriteBatch batch;
    private Player player; // Agrega una referencia al objeto Player
    final GameMenu game;
	private BitmapFont font;

    public GameScreen(final GameMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        batch = new SpriteBatch();
        player = new Player(); // Crea una instancia de Player en el constructor
    }
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		player.update(delta);

        // Limpia la pantalla y dibuja al jugador
        ScreenUtils.clear(0, 0, 0.2f, 1);
        batch.begin();
        player.render(batch);
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
		// TODO Auto-generated method stub
		
	}

}
