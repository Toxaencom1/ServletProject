package model;

import lombok.*;

@Data
@Builder
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
