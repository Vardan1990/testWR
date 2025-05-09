package org.example.webrise.wrtest.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UpdateUserDto {

    private String userName;

    @Email
    private String email;

}
