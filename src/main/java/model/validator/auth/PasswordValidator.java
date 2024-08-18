package model.validator.auth;

import model.validator.ValidateStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PasswordValidator implements ValidateStrategy {
    private static final String LOWERCASE_REGEX = ".*[a-z].*";
    private static final String UPPERCASE_REGEX = ".*[A-Z].*";
    private static final String DIGIT_REGEX = ".*\\d.*";
    private static final String SPECIAL_CHARACTERS_REGEX = ".*[!@#$%^&*()_+\\-=\\[\\]{}|;':\",.<>/?].*";
    private static final int MIN_LENGTH = 6;

    @Override
    public List<String> validate(String value, String fieldName) {

        List<String> errors = new ArrayList<>();
        if (value.length() < MIN_LENGTH) {
            errors.add(fieldName + " length must be at least " + MIN_LENGTH + " characters");
        }
        if (!Pattern.matches(LOWERCASE_REGEX, value)) {
            errors.add(fieldName + " not contains lower case characters");
        }
        if (!Pattern.matches(UPPERCASE_REGEX, value)) {
            errors.add(fieldName + " not contains upper case characters");
        }
        if (!Pattern.matches(DIGIT_REGEX, value)) {
            errors.add(fieldName + " not contains digit characters");
        }
        if (!Pattern.matches(SPECIAL_CHARACTERS_REGEX, value)) {
            errors.add(fieldName + " not contains special characters");
        }
        return errors;
    }
}
