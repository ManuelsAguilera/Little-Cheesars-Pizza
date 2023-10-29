package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ingredients extends FallingObject {
    private List<Sprite> spriteList;
    
    public Ingredients(float initialX) {
        
    	super(initialX);
        spriteList = new ArrayList<Sprite>();
        // Agrega las texturas de ingredientes a la lista
        spriteList.add(new Sprite(new Texture("Salame.png")));
        spriteList.add(new Sprite(new Texture("Pina.png")));
        spriteList.add(new Sprite(new Texture("Champinon.png")));
        spriteList.add(new Sprite(new Texture("Pimenton.png")));
        
        reset();
    }



    // Implementa resetSprite para obtener un sprite aleatorio de la lista
    private Sprite resetSprite() {
    	Random random = new Random();
        int index = random.nextInt(spriteList.size());
        return spriteList.get(index);
    }
    
    // Implementa reset para cambiar el sprite al azar
    public void reset() {
        setSprite(resetSprite());
        int minX = 16;
        int maxX = 463;
        int randomX = minX + (int) (Math.random() * ((maxX - minX)));
        this.setX(randomX);
        this.resetHeight();
    }

}
