package model.validator;

import java.util.List;

public interface ValidateStrategy {
    List<String> validate(String value, String fieldName);
}
