package com.game.exception;

import com.game.entity.Player;

public class DeletePlayerNotSuccessfulException extends Exception{
    public DeletePlayerNotSuccessfulException() {
        super("Исключение! Игрока c не удалось удалить!");
    }
}
