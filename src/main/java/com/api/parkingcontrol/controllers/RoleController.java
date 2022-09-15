package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dtos.RoleDto;
import com.api.parkingcontrol.enums.RoleName;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    final List<String> roleNames;

    public RoleController() {
        roleNames = RoleName.enumRoleNames();
    }

    @PostMapping
    public ResponseEntity<Object> saveRole(@RequestBody @Valid RoleDto roleDto){
        String dtoRoleName = roleDto.getRoleName();
        if(!roleNames.contains(dtoRoleName))


    }
}
