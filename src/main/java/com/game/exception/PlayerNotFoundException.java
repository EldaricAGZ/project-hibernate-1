package com.game.exception;

public class PlayerNotFoundException extends Exception{
    public PlayerNotFoundException() {
        super("Игрока по введенным данным не существует");
    }
}
