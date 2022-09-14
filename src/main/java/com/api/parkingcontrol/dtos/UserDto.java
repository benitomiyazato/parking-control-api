package com.api.parkingcontrol.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String matchingPassword;

}
