package model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AccountIdentifierDTO {
   private UUID id;
   private String login;
}
