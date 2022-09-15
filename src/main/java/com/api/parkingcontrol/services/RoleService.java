package com.api.parkingcontrol.services;

import com.api.parkingcontrol.enums.RoleName;
import com.api.parkingcontrol.models.RoleModel;
import com.api.parkingcontrol.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public RoleModel save(RoleModel roleModel) {
        return roleRepository.save(roleModel);
    }

    public Optional<RoleModel> findByRoleName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
