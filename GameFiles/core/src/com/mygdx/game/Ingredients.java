package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ingredients extends FallingObject {
    private List<Sprite> spriteList;
    private String type;

    public Ingredients(float initialX) {
        super(initialX);
        spriteList = new ArrayList<Sprite>();
        
        // Agregar las texturas de ingredientes a la lista con su respectivo tipo
        spriteList.add(new Sprite(new Texture("Salame.png")));
        spriteList.add(new Sprite(new Texture("Pina.png")));
        spriteList.add(new Sprite(new Texture("Champinon.png")));
        spriteList.add(new Sprite(new Texture("Pimenton.png")));
        
        // Asignar un tipo al ingrediente al inicializar
        type = ""; // Puedes ajustar el tipo inicial según el ingrediente actual

        reset();
    }

    // Implementa resetSprite para obtener un sprite aleatorio de la lista
    private Sprite resetSprite() {
        Random random = new Random();
        int index = random.nextInt(spriteList.size());
        return spriteList.get(index);
    }

    // Implementa reset para cambiar el sprite al azar y el tipo
    public void reset() {
        setSprite(resetSprite());

        // Asignar un tipo aleatorio al ingrediente
        int typeIndex = new Random().nextInt(spriteList.size());
        type = getIngredientType(typeIndex);

        int minX = 16;
        int maxX = 463;
        int randomX = minX + (int) (Math.random() * ((maxX - minX)));
        this.setX(randomX);
        this.resetHeight();
    }

    // Método para obtener el tipo de ingrediente basado en el índice
    private String getIngredientType(int index) {
        // Asegúrate de que este método devuelva el tipo correcto según el índice
        switch (index) {
            case 0:
                return "Salame";
            case 1:
                return "Pina";
            case 2:
                return "Champinon";
            case 3:
                return "Pimenton";
            default:
                return "Desconocido";
        }
    }
    
    //getter para identificar.
    public String getType()
    {
    	return this.type;
    }
}
