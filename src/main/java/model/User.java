package model;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private UUID id;
    private String login;
    private String password;
}
