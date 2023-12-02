package com.mygdx.game;

public abstract class FallingObject implements GameObject {
	/*public final void fallingObjectAlgorithm() {
		update(0);
		reset();
		destruir();
        resetHeight();
   	}*/
	
    public abstract void update(float delta);
    
    public abstract void resetSprite();
    
    public abstract void reset();
    
    public abstract void destruir();
    
    public abstract void resetHeight();
    
}