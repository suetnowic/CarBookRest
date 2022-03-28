package com.viktorsuetnov.carbook.service;

import com.viktorsuetnov.carbook.entity.User;
import com.viktorsuetnov.carbook.entity.enums.ERole;
import com.viktorsuetnov.carbook.exceptions.UserExistException;
import com.viktorsuetnov.carbook.payload.request.SignupRequest;
import com.viktorsuetnov.carbook.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    public User createUser(SignupRequest userIn) {
        User user = new User();
        user.setEmail(userIn.getEmail());
        user.setUsername(userIn.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userIn.getPassword()));
        user.getRoles().add(ERole.ROLE_USER);

        try {
            LOG.info("Saving user with {}", userIn.getEmail());
            return userRepository.save(user);
        } catch (Exception exception) {
            LOG.error("Error during registration {}", exception.getMessage());
            throw new UserExistException("The user with " + userIn.getUsername() + "already exist");
        }
    }
}
