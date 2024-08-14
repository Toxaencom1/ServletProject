package model;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Advertisement {
    private Long id;
    private UUID userId;
    private String name;
    private String model;
    private double cost;
    private String url;
}
