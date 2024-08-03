package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Advertisement {
    private Long id;
    private String name;
    private String model;
    private double cost;
    private String url;
}
