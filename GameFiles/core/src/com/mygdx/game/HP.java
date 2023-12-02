package com.mygdx.game;

public class HP implements PowerUpStrategy {
	private static final int HEALTH_INCREASE = 1;

    @Override
    public void applyPowerUp(Player player, Cheesar animation, GameControler gameController) {
        // Aumentar la vida del jugador si es menor a 3
        int currentLifes = gameController.getLifes();
        if (currentLifes < 3) {
            gameController.setLifes(currentLifes + HEALTH_INCREASE);
        }
    }
}