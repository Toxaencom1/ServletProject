package model.validator.advertisement;

import model.validator.ValidateStrategy;

import java.util.ArrayList;
import java.util.List;

public class UrlValidator implements ValidateStrategy {
    @Override
    public List<String> validate(String value, String fieldName) {
        List<String> errors = new ArrayList<>();
        if (value == null || value.isEmpty()) {
            errors.add(fieldName + " cannot be empty");
        }
        return errors;
    }
}
