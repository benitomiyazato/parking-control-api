package com.api.parkingcontrol.services;

import com.api.parkingcontrol.models.UserModel;
import com.api.parkingcontrol.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserModel save(UserModel user) {
        return userRepository.save(user);
    }

    public Optional<UserModel> findById(Long userId) {
        return userRepository.findById(userId);
    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }
}
