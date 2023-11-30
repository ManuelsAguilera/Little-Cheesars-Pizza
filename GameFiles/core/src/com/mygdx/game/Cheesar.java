package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Cheesar {
    private Animation<TextureRegion> walkAnimation;
    private float stateTime;
    private float x, y;
    private final float defaultSpeed = 200;
    private float speed;
    private boolean isMovingLeft;
    private boolean isMovingRight;
    private boolean checker;
    
    public Cheesar(float initialX, float initialY) {
        // Cargar el atlas desde el archivo CheesarWalk.atlas
        TextureAtlas atlas = new TextureAtlas("CheesarWalk.atlas");

        // Obtener las regiones de textura del atlas para la animación de caminar
        Array<TextureAtlas.AtlasRegion> walkRegions = atlas.findRegions("CheesarWalk");

        // Crear un array de texturas para la animación de caminar
        Array<TextureRegion> walkFrames = new Array<>(walkRegions.size);
        walkFrames.addAll(walkRegions);

        // Ajustar la duración de cada fotograma según tus necesidades
        float frameDuration = 0.1f;
        walkAnimation = new Animation<>(frameDuration, walkFrames);
        stateTime = 0f;

        x = initialX + Gdx.graphics.getWidth()/2 - 32;
        y = initialY;
        speed = defaultSpeed; // Puedes ajustar la velocidad según tus necesidades

    }

    public void update(float delta) {
        // Manejar la entrada del teclado
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x -= speed * delta;
            isMovingLeft = true;
            isMovingRight = false;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x += speed * delta;
            isMovingLeft = false;
            isMovingRight = true;
        }
        else {
        	isMovingLeft = false;
            isMovingRight = false;
        }
        
        if (x < 0) {
            x = 0;
        } else if (x > 800 - 64 ) {
            x = 800 - 64;
        }

        stateTime += Gdx.graphics.getDeltaTime();
    }

    public void render(SpriteBatch batch) {
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        
        Sprite sprite = new Sprite(currentFrame);

        // Voltear el sprite si está moviéndose hacia la izquierda
        if (isMovingLeft) {
            sprite.flip(true, false);
            // Ajustar la posición para que el sprite se voltee sobre su propio eje
            sprite.setPosition(x+(sprite.getWidth() - sprite.getRegionWidth()), y);
            sprite.draw(batch);

            checker=true;
        } else if(isMovingRight) {
            sprite.setPosition(x, y);
            sprite.draw(batch);

            checker=false;
        } else {
	        // No se están presionando A ni D, renderizar la imagen estática
	        sprite.setRegion(new TextureRegion(new Texture("Cheesar.png")));
	        if (checker==true) {//MIRANDO A LA IZQUIERDA
	        	sprite.flip(true, false);
	            // Ajustar la posición para que el sprite se voltee sobre su propio eje
	            sprite.setPosition(x+(sprite.getWidth() - sprite.getRegionWidth()), y);
	            sprite.draw(batch);

	        } else if (checker==false) {//MIRANADO A LA DERECHA
	        	sprite.setPosition(x, y);
	            sprite.draw(batch);
;
	        }
	    }
    }
    
    public boolean modificarVeloc(float cambio){
    	if (!(0<cambio && cambio<4))
    		return false;
    	
    	this.speed=defaultSpeed*cambio;    	
    	return true;
    }
}
