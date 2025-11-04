package com.game.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Set;

public class HandlerException {
    private static final Logger logger = LoggerFactory.getLogger(HandlerException.class);
    public static void handleConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        constraintViolations.stream().map(ConstraintViolation::getMessage).forEach(logger::warn);
    }
    public static void handlePlayerNotFoundException(PlayerNotFoundException e) {
        logger.warn(e.getMessage());
    }
    public static void handleUpdatePlayerNotSuccessfulException(UpdatePlayerNotSuccessfulException e) {
        logger.error(e.getMessage());
    }

    public static void handleSimpleException(Exception e) {
        logger.error("Что то пошло не так! Exception message: {}", e.getMessage());
    }

    public static void handleSavePlayerNotSuccessfulException(SavePlayerNotSuccessfulException e) {
        logger.error(e.getMessage());
    }

    public static void handleDeletePlayerNotSuccessfulException(DeletePlayerNotSuccessfulException e) {
        logger.error(e.getMessage());
    }
}
