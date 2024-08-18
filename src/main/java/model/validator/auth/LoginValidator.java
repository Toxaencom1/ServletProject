package model.validator.auth;

import model.validator.ValidateStrategy;

import java.util.ArrayList;
import java.util.List;

public class LoginValidator implements ValidateStrategy {

    @Override
    public List<String> validate(String value, String fieldName) {
        List<String> errors = new ArrayList<>();
        if (value.length() < 3) {
            errors.add(fieldName + " is too short, need greater than 5 characters");
        }
        if (value.contains(" ")) {
            errors.add(fieldName + " should not contain spaces");
        }
        if (value.contains("\\")) {
            errors.add(fieldName + " should not contain escape characters");
        }
        return errors;
    }
}
