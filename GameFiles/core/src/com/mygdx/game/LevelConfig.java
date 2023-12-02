package com.mygdx.game;

public class LevelConfig {
    private float fallSpeed;
    private int initialLives;
    private String backgroundImage;
    private String backgroundMusic;

    public void setFallSpeed(float fallSpeed) {
        this.fallSpeed = fallSpeed;
    }

    public void setInitialLives(int initialLives) {
        this.initialLives = initialLives;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public void setBackgroundMusic(String backgroundMusic) {
        this.backgroundMusic = backgroundMusic;
    }
    
    public float getFallSpeed() {
        return this.fallSpeed;
    }
    
    public float getInitialLives() {
        return this.initialLives;
    }
    
    public String getBackgroundImage() {
        return this.backgroundImage;
    }
    
    public String getBackgroundMusic() {
        return this.backgroundMusic;
    }
}
