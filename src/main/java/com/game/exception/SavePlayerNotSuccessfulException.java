package com.game.exception;

import com.game.entity.Player;

public class SavePlayerNotSuccessfulException extends Exception{
    public SavePlayerNotSuccessfulException(Player player) {
        super("Исключение! Игрока c ID: " + player.getId() + "не удалось сохранить!");
    }
}
