package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Colectible extends FallingObject {
    private HashMap<String, Sprite> colectibleMap;
    private List<String> colectibleTypes;
    private String type;

    // Constructor
    public Colectible(float initialX) {
        super(initialX);

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

    public ArrayList<String> getColTypes() {
        ArrayList<String> cpy = new ArrayList<String>();
        cpy.addAll(colectibleMap.keySet());
        return cpy;
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
}
