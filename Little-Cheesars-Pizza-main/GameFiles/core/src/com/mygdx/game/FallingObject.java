package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class FallingObject implements GameObject {

	private Sprite sprite;
    private float x, y;
    private float speedY;
    private float highestY; // Nueva variable para rastrear la posición más alta
    private boolean isDetained;
    
    
    public FallingObject(Texture texture, float x) {
        sprite = new Sprite(texture);
        this.x = x;
        this.highestY = 500; // Define la posición inicial en la parte superior
        y = highestY; // Inicializa la posición más alta con la posición inicial
        
        speedY = 500; // Velocidad de caída inicial (ajusta según tus necesidades)
        			
        isDetained=false;
    }
    public FallingObject(float x) {
    	sprite = new Sprite(new Texture("drop.png")); //Default
        this.x = x;
        this.highestY = 500; // Define la posición inicial en la parte superior
        y = highestY; // Inicializa la posición más alta con la posición inicial
        
        speedY = 100; // Velocidad de caída inicial (ajusta según tus necesidades)
        				
    }


    @Override
    public void update(float delta) {
    	
    	//if it does not exist dont do nothing
    	if (this.isDetained)
    		return;
    	
    	//Si llega a y = 0 reiniciar posicion.
    	
    	if (y<=64 )
    		this.reset();
    	
    	
        y -= speedY * delta; // Hacer que el objeto caiga

        // Si el objeto alcanza la parte inferior de la pantalla, reinicia su posición.
        if (y < 0) {
            y = highestY; // Reinicia desde la posición más alta
        } else {
            highestY = Math.max(highestY, y); // Actualiza la posición más alta
        }

        sprite.setPosition(x, y);
    }
    public abstract void reset();
    
    @Override
    public void render(SpriteBatch batch) {
    	if (!this.isDetained)
    		sprite.draw(batch);
    }

    @Override
    public Rectangle getBounds() {
        return sprite.getBoundingRectangle();
    }
    //Eliminarse
    public void remove() {
        x=0;
        this.isDetained = true;
    }
    
    //encapsular velocidad
    public boolean modificarVeloc(float cambio)
    {
    	if (!(0<cambio && cambio<1))
    		return false;
    	
    	this.speedY*=cambio;    	
    	return true;
    }
    
    //encapsular reiniciar altura
    public void resetHeight()
    {
        int minY = 480;
        int maxY = 800;
        int randomY = minY + (int) (Math.random() * ((maxY - minY)));
    	this.y = randomY;
    }
    public void setX(float xValue)
    {
    	this.x = xValue;
    }
    //
    public void setSprite(Sprite change)
    {
    	sprite = change;
    }
    
}