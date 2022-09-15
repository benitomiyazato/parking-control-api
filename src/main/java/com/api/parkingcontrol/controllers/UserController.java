package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dtos.UserDto;
import com.api.parkingcontrol.models.RoleModel;
import com.api.parkingcontrol.models.UserModel;
import com.api.parkingcontrol.repositories.RoleRepository;
import com.api.parkingcontrol.services.RoleService;
import com.api.parkingcontrol.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto){

        if(!userDto.getPassword().equals(userDto.getMatchingPassword())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Passwords don't match!");
        }

        UserModel user = new UserModel();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        List<RoleModel> userDtoRoles = userDto.getRoles();
        for (RoleModel role: userDtoRoles) {
            Optional<RoleModel> roleOptional = roleService.findByRoleName(role.getRoleName());
            if(roleOptional.isEmpty())
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Role " + role.getRoleName() + " not found");

            role.setRoleId(roleOptional.get().getRoleId());
        }
        user.setRoles(userDtoRoles);
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<UserModel> addUserRole(@PathVariable Long userId, @RequestBody RoleModel role){
//
//    }
}
