package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Colectible extends FallingObject {
    private HashMap<String, Sprite> colectibleMap;
    private List<String> colectibleTypes;
    private String type;
    //PARTE REMOVIDA DE FALLING OBJECT PARA IMPLEMENTAR TEMPLATE METHOD
    private Sprite sprite;
    private float x, y;
    private float speedY;
    private float highestY; // Nueva variable para rastrear la posición más alta
    private boolean isDetained;
    private boolean remove;
    

    // Constructor
    public Colectible(float initialX) {
        super();
        
        //PARTE REMOVIDA DE FALLING OBJECTthis.x = x;
        this.highestY = 100; // Define la posición inicial en la parte superior
        y = highestY; // Inicializa la posición más alta con la posición inicial
        speedY = 100; // Velocidad de caída inicial
        isDetained = false;
        remove = false;
        //HASTA AQUI
        
        colectibleMap = new HashMap<String, Sprite>();
        colectibleTypes = new ArrayList<String>();

        // Agregar texturas
        colectibleMap.put("Speed", new Sprite(new Texture("Pluma.png"))); // Velocidad a Cheesar
        colectibleMap.put("HP", new Sprite(new Texture("Corazon.png"))); // Más vida a Cheesar (Máx. tres)
        colectibleMap.put("Bonus", new Sprite(new Texture("Estrella.png"))); // Más puntaje/ganancias

        // Agregar a la lista
        colectibleTypes.addAll(colectibleMap.keySet());

        // Asignar un tipo al ingrediente al inicializar
        type = "";

        reset();
    }

    // Implementa resetSprite para obtener un sprite aleatorio del mapa
    private void resetSprite() {
        Random random = new Random();
        String randomType = colectibleTypes.get(random.nextInt(colectibleTypes.size()));
        type = randomType;
        setSprite(colectibleMap.get(type));
    }

    public void reset() {
        resetSprite();
        int minX = 16;
        int maxX = 745;
        int randomX = minX + (int) (Math.random() * ((maxX - minX)));
        this.setX(randomX);
        this.resetHeight();
    }

    public String getType() {
        return this.type;
    }

	@Override
	public void destruir() {
	    // Liberar memoria del HashMap
	    colectibleMap.clear();
	    colectibleMap = null;

	    // Liberar memoria de la List
	    colectibleTypes.clear();
	    colectibleTypes = null;
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
