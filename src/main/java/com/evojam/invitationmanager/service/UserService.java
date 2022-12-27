package com.evojam.invitationmanager.service;

import com.evojam.invitationmanager.domain.User;
import com.evojam.invitationmanager.dto.UserDTO;
import com.evojam.invitationmanager.exception.UserAlreadyExistsException;
import com.evojam.invitationmanager.exception.UserNotFoundException;
import com.evojam.invitationmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void createUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new UserAlreadyExistsException("User with email " + userDTO.getEmail() + " already exists!");
        }
        userRepository.save(userDTO.toUser());
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No user by ID: " + userId));
    }
}
