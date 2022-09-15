package com.api.parkingcontrol.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleDto {
    @NotBlank
    private String roleName;
}
