package com.training.users.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;

import java.util.List;
import java.util.Optional;

/**
 * The {@link PasswordConstraintsValidator} class contains the business of password validation.
 *
 * @author mohammed
 * @since 2024.08
 */
public class PasswordConstraintsValidator implements ConstraintValidator<Password, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {

        final PasswordValidator passwordValidator = new PasswordValidator(
            List.of(
                new LengthRule(10, 128),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Special, 1),
                new WhitespaceRule()
            )
        );

        final RuleResult result = passwordValidator.validate(new PasswordData(password));

        if (result.isValid()) {
            return true;
        }

        final Optional<String> message = passwordValidator
                .getMessages(result)
                .stream()
                .findFirst();

        if (message.isEmpty()) {
            return true;
        }

        constraintValidatorContext
                .buildConstraintViolationWithTemplate(message.get())
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;

    }
}