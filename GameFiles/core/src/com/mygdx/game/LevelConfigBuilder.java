package com.mygdx.game;

public class LevelConfigBuilder {
    private LevelConfig levelConfig;

    public LevelConfigBuilder() {
        levelConfig = new LevelConfig();
    }

    public LevelConfigBuilder setFallSpeed(float fallSpeed) {
        levelConfig.setFallSpeed(fallSpeed);
        return this;
    }

    public LevelConfigBuilder setInitialLives(int initialLives) {
        levelConfig.setInitialLives(initialLives);
        return this;
    }

    public LevelConfigBuilder setBackgroundImage(String backgroundImage) {
        levelConfig.setBackgroundImage(backgroundImage);
        return this;
    }

    public LevelConfigBuilder setBackgroundMusic(String backgroundMusic) {
        levelConfig.setBackgroundMusic(backgroundMusic);
        return this;
    }

    public LevelConfig build() {
        return this.levelConfig;
    }
}
