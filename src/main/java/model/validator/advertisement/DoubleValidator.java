package model.validator.advertisement;

import model.validator.ValidateStrategy;

import java.util.ArrayList;
import java.util.List;

public class DoubleValidator implements ValidateStrategy {
    @Override
    public List<String> validate(String value, String fieldName) {
        List<String> errors = new ArrayList<>();
        if (value == null || value.isEmpty()) {
            errors.add(fieldName + " is a required field");
        } else if (Double.parseDouble(value) < 0) {
            errors.add(fieldName + " must be a positive number");
        }
        return errors;
    }
}
