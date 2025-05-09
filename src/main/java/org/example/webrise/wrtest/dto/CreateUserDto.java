package org.example.webrise.wrtest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateUserDto {

    @NotBlank
    private String userName;

    @Email
    @NotBlank
    private String email;

    private LocalDateTime created = LocalDateTime.now();

    private LocalDateTime updated = LocalDateTime.now();


}
