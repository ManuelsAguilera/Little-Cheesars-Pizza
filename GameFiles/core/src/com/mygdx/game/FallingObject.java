package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class FallingObject implements GameObject {
	public final void fallingObjectAlgorithm() {
		update();
		reset();
        remove();
        resetHeight();
    }
	
	//PASO 1: Actualizar
    public abstract void update();
    
    //PASO 2: Resetear
    public abstract void reset();

    //Paso 3: Eliminar IMPORTANTE
    public abstract void remove();

    //PASO 4: ReSpawn IMPORTANTE
    public abstract void resetHeight();
}