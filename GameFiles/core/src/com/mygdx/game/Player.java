package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Player implements GameObject{
	private Sprite sprite;
    private float x, y; // Posición del jugador
    private final float defaultSpeed = 200;
    private float speed; // Velocidad de movimiento
    
    public Player(float ypos)
    {
    	x=0; 	y=ypos;
    	speed = defaultSpeed;
    	
    	Texture texture = new Texture(Gdx.files.internal("Personaje.png"));
    	
    	sprite = new Sprite(texture);
    	
    	
    }
	

    public void update(float delta) {
    	
    	
    	  	
    	
        // Manejo de entrada para mover al jugador
        if (Gdx.input.isKeyPressed(Keys.A)) {
            x -= speed * delta; // Mover hacia la izquierda
        }
        if (Gdx.input.isKeyPressed(Keys.D)) {
            x += speed * delta; // Mover hacia la derecha
        }
        
        // Limitar la posición del jugador dentro de la pantalla (ajusta estos valores según tus necesidades)
        if (x < 0) {
            x = 0;
        }
        if (x > Gdx.graphics.getWidth() - sprite.getWidth()) {
            x = Gdx.graphics.getWidth() - sprite.getWidth();
        }
        
        // Actualizar la posición del sprite
        sprite.setPosition(x, y);
    }
    
    public void render(SpriteBatch batch)
    {
    	sprite.draw(batch);
    }
    
    public Rectangle getBounds()
    {
    	return sprite.getBoundingRectangle();
    }
    
    //Encapsular cambio velocidad
    public boolean modificarVeloc(float cambio)
    {
    	if (!(0<cambio && cambio<1))
    		return false;
    	
    	this.speed*=cambio;    	
    	return true;
    }
    
}
