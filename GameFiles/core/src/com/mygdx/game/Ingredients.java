package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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

    public Ingredients(float initialX) {
        super(initialX);
        
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

    // Implementa reset para cambiar el sprite al azar y el tipo
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
}
