package com.mygdx.game;

public class LevelDirector {
    public LevelConfig constructDefaultLevel() {
        return new LevelConfigBuilder()
                .setFallSpeed(100.0f)
                .setInitialLives(3)
                .setBackgroundImage("Fondo.png")
                .setBackgroundMusic("novoimpegiato.mp3")
                .build();
    }
}