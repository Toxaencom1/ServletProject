package model.validator.advertisement;

import model.validator.ValidateStrategy;

import java.util.ArrayList;
import java.util.List;

public class StringValidator implements ValidateStrategy {

    @Override
    public List<String> validate(String value, String fieldName) {
        List<String> errors = new ArrayList<>();
        if (value == null || value.isEmpty()) {
            errors.add(fieldName + " cannot be empty");
        } else if (value.length() > 32) {
            errors.add(fieldName + " is too long, must be less than or equal to 32");
        }
        return errors;
    }
}
