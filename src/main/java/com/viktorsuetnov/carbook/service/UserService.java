package com.viktorsuetnov.carbook.service;

import com.viktorsuetnov.carbook.entity.User;
import com.viktorsuetnov.carbook.entity.enums.ERole;
import com.viktorsuetnov.carbook.exceptions.UserExistException;
import com.viktorsuetnov.carbook.payload.request.SignupRequest;
import com.viktorsuetnov.carbook.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.Principal;
import java.util.List;

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

    @Value("${hostname}")
    private String hostname;

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

    public User updateProfile(User userIn, Principal principal) {
        User userFromDB = getUserByPrincipal(principal);
        String newEmail = userIn.getEmail();
        String newPassword = userIn.getPassword();
        String userEmail = userFromDB.getEmail();
        boolean isEmailChange = (newEmail != null && !newEmail.equals(userEmail)) || (userEmail != null && !userEmail.equals(newEmail));
        if (isEmailChange) {
            userFromDB.setEmail(newEmail);
        }
        if (StringUtils.hasText(newPassword)) {
            userFromDB.setPassword(bCryptPasswordEncoder.encode(newPassword));
        }
        return userRepository.save(userFromDB);
    }

    public User getCurrentUser(Principal principal) {
        return getUserByPrincipal(principal);
    }

   public List<User> findAll() {
        return userRepository.findAll();
   }

    public User getUserById(Long userId) {
        return userRepository.findUserById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with " + username + " not found"));
    }
}
