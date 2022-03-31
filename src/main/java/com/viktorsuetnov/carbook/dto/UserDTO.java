package com.viktorsuetnov.carbook.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserDTO {

    private Long id;
    private String username;
    private String email;

}
