package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerAnimation {

    private Animation<AtlasRegion> animation;
    private float stateTime;

    public PlayerAnimation() {
        // Cargar el atlas y configurar la animación
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("CheesarWalk.atlas"));
        animation = new Animation<>(1 / 12f, atlas.findRegions("CheesarWalk"), Animation.PlayMode.LOOP);
        stateTime = 0f;
    }

    public void update(float delta) {
        // Actualizar el tiempo del estado para avanzar en la animación
        stateTime += delta;
    }

    public TextureRegion getCurrentFrame() {
        // Obtener el frame actual de la animación
        return animation.getKeyFrame(stateTime);
    }
}
