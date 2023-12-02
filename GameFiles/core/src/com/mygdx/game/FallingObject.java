package com.mygdx.game;

public abstract class FallingObject implements GameObject {
	
    public abstract void update(float delta);
    
    public abstract void resetSprite();
    
    public abstract void reset();
    
    public abstract void destruir();
    
    public abstract void resetHeight();
    
}