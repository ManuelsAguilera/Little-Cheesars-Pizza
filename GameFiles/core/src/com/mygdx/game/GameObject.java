package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public interface GameObject{
	
    void update(float delta);
    void render(SpriteBatch batch);
    Rectangle getBounds();
    void destruir();
}