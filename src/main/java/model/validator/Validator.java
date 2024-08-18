package model.validator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Validator {
    private ValidateStrategy validateStrategy;

    public List<String> validate(String input, String fieldName) {
        return validateStrategy.validate(input, fieldName);
    }
}
