package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Schema(
        name = "Users",
        description = "Schema to hold Users information"
)
@Getter @Setter
public class UsersDto {

    @NotEmpty(message = "username can not be a null or empty")
    @Schema(
            description = "username of Eazy Bank Users", example = "sanjivani"
    )
    private String username;
    @NotEmpty(message = "password can not be a null or empty")
    @Schema(
            description = "password of Eazy Bank Users", example = "******"
    )
    private String password;
}
