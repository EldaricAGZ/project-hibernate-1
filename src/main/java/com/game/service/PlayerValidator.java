package com.game.service;


import com.game.entity.Player;
import jakarta.validation.*;

import java.util.Objects;
import java.util.Set;

public class PlayerValidator {
    public static void validate(Player player) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Player>> validate = validator.validate(player);

        if (!validate.isEmpty()) {
            throw new ConstraintViolationException(validate);
        }
    }

}
