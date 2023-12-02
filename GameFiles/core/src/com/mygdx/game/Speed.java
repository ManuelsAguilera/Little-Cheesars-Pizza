package com.mygdx.game;

public class Speed implements PowerUpStrategy {
	private static final float SPEED_MULTIPLIER = 2.0f; // Factor por el cual se multiplicará la velocidad
    private static final float DURATION = 5.0f; // Duración del efecto en segundos

    @Override
    public void applyPowerUp(Player player, Cheesar animation, GameControler gameController) {
        // Duplicar la velocidad del jugador
        player.modificarVeloc(SPEED_MULTIPLIER);
        animation.modificarVeloc(SPEED_MULTIPLIER);
        
        // Iniciar temporizador para revertir el efecto después de un tiempo
        gameController.setStatusChangeTemp(DURATION);
        gameController.setStatusChange("Speed");
    }
}