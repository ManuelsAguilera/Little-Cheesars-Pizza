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
    private boolean flipped = false;
    
    public Player(float ypos){
    	x = Gdx.graphics.getWidth()/2 - 11; 
    	y = ypos;
    	speed = defaultSpeed;
    	Texture texture = new Texture(Gdx.files.internal("Hitbox.png"));
    	sprite = new Sprite(texture);
    }
	
    public void update(float delta) {
        // Manejo de entrada para mover al jugador
        if (Gdx.input.isKeyPressed(Keys.A)) {
            x -= speed * delta; // Mover hacia la izquierda
            flipped = true; // Voltear la imagen
        }
        else if (Gdx.input.isKeyPressed(Keys.D)) {
            x += speed * delta; // Mover hacia la derecha
            flipped = false; //Reestablecer la imagen
        }
        
        // Limitar la posición del jugador dentro de la pantalla (ajusta estos valores según tus necesidades)
        // la hitbox amarilla va al mismo tiempo cuando el valor es 20:)
        if (x < 20) {
            x = 20;
        }
        if (x > (Gdx.graphics.getWidth() - sprite.getWidth())-20) {
            x = (Gdx.graphics.getWidth() - sprite.getWidth())-20;
        }
        
        // Actualizar la posición del sprite
        sprite.setPosition(x, y);
    }
    
    public void render(SpriteBatch batch){
    	if (flipped) {
            sprite.setScale(-1, 1);
        } else {
            sprite.setScale(1, 1);
        }
    	sprite.draw(batch);
    }
    
    public Rectangle getBounds()
    {
    	return sprite.getBoundingRectangle();
    }
    
    
    //Encapsular cambio velocidad que funciona ingresando el porcentaje de esta
    public boolean modificarVeloc(float cambio)
    {
    	if (!(0<cambio && cambio<4))
    		return false;
    	
    	this.speed=defaultSpeed*cambio;    	
    	return true;
    }

	@Override
	public void destruir() {
	    // Liberar memoria de la textura
	    if (sprite != null && sprite.getTexture() != null) {
	        sprite.getTexture().dispose();
	    }

	    // Establecer referencias a null
	    sprite = null;
	}
    
}
