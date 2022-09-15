package com.api.parkingcontrol.dtos;


import com.api.parkingcontrol.models.RoleModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @NotNull
    private List<RoleModel> roles;
}
