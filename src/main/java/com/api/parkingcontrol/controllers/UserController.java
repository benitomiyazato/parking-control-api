package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dtos.RoleDto;
import com.api.parkingcontrol.dtos.UserDto;
import com.api.parkingcontrol.enums.RoleName;
import com.api.parkingcontrol.models.RoleModel;
import com.api.parkingcontrol.models.UserModel;
import com.api.parkingcontrol.services.RoleService;
import com.api.parkingcontrol.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto) {

        if (!userDto.getPassword().equals(userDto.getMatchingPassword())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Passwords don't match!");
        }

        UserModel user = new UserModel();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        List<RoleModel> userDtoRoles = userDto.getRoles();
        for (RoleModel role : userDtoRoles) {
            Optional<RoleModel> roleOptional = roleService.findByRoleName(role.getRoleName());
            if (roleOptional.isEmpty())
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Role " + role.getRoleName() + " not found");

            role.setRoleId(roleOptional.get().getRoleId());
        }
        user.setRoles(userDtoRoles);

        return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/role/{userId}")
    public ResponseEntity<Object> addUserRole(@PathVariable Long userId, @RequestBody RoleDto roleDto) {
        // trying to get User from database
        Optional<UserModel> userOptional = userService.findById(userId);
        if (userOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID: " + userId + " was not found");
        UserModel user = userOptional.get();

        // trying to get Role from database by Request Body's roleName
        Optional<RoleModel> roleOptional;
        try {
            roleOptional = roleService.findByRoleName(RoleName.valueOf(roleDto.getRoleName()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Role not found");
        }
        RoleModel role = roleOptional.get();

        // checking if user already has this specific role
        if(user.getRoles().contains(role))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already has " + role.getRoleName() + " role.");

        user.getRoles().add(role);
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
    }
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserModel>> findAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }
}
