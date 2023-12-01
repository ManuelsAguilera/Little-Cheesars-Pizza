package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Ingredients extends FallingObject {
    private Map<String, Sprite> ingredientMap; // Mapa para asociar tipos de ingredientes a sprites
    private List<String> ingredientTypes; // Lista de tipos de ingredientes
    private String type;
    private boolean bonus;
    //PARTE REMOVIDA DE FALLING OBJECT PARA IMPLEMENTAR TEMPLATE METHOD
    private Sprite sprite;
    private float x, y;
    private float speedY;
    private float highestY; // Nueva variable para rastrear la posición más alta
    private boolean isDetained;
    private boolean remove;
    

    public Ingredients(float initialX) {
        super();
        
        //PARTE REMOVIDA DE FALLING OBJECTthis.x = x;
        this.highestY = 100; // Define la posición inicial en la parte superior
        y = highestY; // Inicializa la posición más alta con la posición inicial
        speedY = 100; // Velocidad de caída inicial
        isDetained = false;
        remove = false;
        //HASTA AQUI
        
        
        ingredientMap = new HashMap<String, Sprite>();
        ingredientTypes = new ArrayList<String>();
        
        // Agrega las texturas de ingredientes al mapa con sus respectivos tipos
        ingredientMap.put("Salame", new Sprite(new Texture("Salame.png")));
        ingredientMap.put("Piña", new Sprite(new Texture("Pina.png")));
        ingredientMap.put("Champiñón", new Sprite(new Texture("Champinon.png")));
        ingredientMap.put("Pimentón", new Sprite(new Texture("Pimenton.png")));
        
        ingredientMap.put("BonusPoint", new Sprite(new Texture("money.png")));
        
        // Agrega los tipos de ingredientes a la lista
        ingredientTypes.addAll(ingredientMap.keySet());
        // Asignar un tipo al ingrediente al inicializar
        type = ""; // Puedes ajustar el tipo inicial según el ingrediente actual
        bonus = false;
        reset();
    }

    // Implementa resetSprite para obtener un sprite aleatorio del mapa
    private void resetSprite() {
        if (bonus)
        {
        	type = ingredientTypes.get(4);
        	setSprite(ingredientMap.get(type));
        	this.bonus = false;
        }
        else
        {
        	Random random = new Random();
            String randomType = ingredientTypes.get(random.nextInt(ingredientTypes.size()-1));
            type = randomType;
            setSprite(ingredientMap.get(type));
        }
    }

    //Implementa reset para cambiar el sprite al azar y el tipo
    public void reset(){
        // Asignar un tipo aleatorio al ingrediente
        resetSprite();
        int minX = 16;
        int maxX = 745;
        int randomX = minX + (int) (Math.random() * ((maxX - minX)));
        this.setX(randomX);
        this.resetHeight();
    }
    
    public void bonus() {
    	this.bonus = true;
    }
    // Getter para identificar el tipo de ingrediente.
    public String getType() {
        return this.type;
    }

	@Override
	public void destruir() {
	    // Liberar memoria del HashMap
	    ingredientMap.clear();
	    ingredientMap = null;

	    // Liberar memoria de la List
	    ingredientTypes.clear();
	    ingredientTypes = null;
	}
	
	//Implementar métodos desde falling object
	public boolean getRemove(){
    	return remove;
    }
	public void remove(){
        //this.isDetained = true;
    	this.remove = true;
    }
	public void render(SpriteBatch batch){
    	if (!this.isDetained)
    		sprite.draw(batch);
    }
	public boolean modificarVeloc(float cambio){
    	if (!(0<cambio && cambio<1))
    		return false;
    	
    	this.speedY*=cambio;    	
    	return true;
    }
	public void resetHeight(){
        int minY = 480;
        int maxY = 800;
        int randomY = minY + (int) (Math.random() * ((maxY - minY)));
    	this.y = randomY;
    }
	public void setX(float x){
    	this.x = x;
    }
    public void setY(float y){
    	this.y = y;
    }
    public void setSprite(Sprite change){
    	sprite = change;
    }

    @Override
    public Rectangle getBounds() {
	     return sprite.getBoundingRectangle();
	 }

    public void update(float delta){
    	
    	// Si está detenido, no hace nada
    	if (this.isDetained)
    		return;
    	
    	//Si llega a y = 0 reiniciar posicion.
    	if (y <= 2)
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
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
