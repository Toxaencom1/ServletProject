package model.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementDTO {
    private String name;
    private String model;
    private String cost;
    private String url;
}
