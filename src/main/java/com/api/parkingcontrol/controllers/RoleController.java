package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dtos.RoleDto;
import com.api.parkingcontrol.enums.RoleName;
import com.api.parkingcontrol.models.RoleModel;
import com.api.parkingcontrol.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // TODO:
    // Duplicate Roles in DB

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Object> saveRole(@RequestBody @Valid RoleDto roleDto) {
        RoleModel roleModel = new RoleModel();
        try {
            roleModel.setRoleName(RoleName.valueOf(roleDto.getRoleName()));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body("There's no such RoleName enumerated");
        }
        return ResponseEntity.status(HttpStatus.OK).body(roleService.save(roleModel));
    }
}
