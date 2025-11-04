package com.game.exception;

import com.game.entity.Player;

public class UpdatePlayerNotSuccessfulException extends Exception {
    public UpdatePlayerNotSuccessfulException(Player player) {
        super("Исключение! Игрока c ID: " + player.getId() + "не удалось обновить!");
    }
}
