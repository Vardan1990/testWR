package org.example.webrise.wrtest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetUserDto {

    private String userName;

    private String email;

    private LocalDateTime created;

    private LocalDateTime updated;

}
