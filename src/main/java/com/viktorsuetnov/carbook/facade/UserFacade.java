package com.viktorsuetnov.carbook.facade;

import com.viktorsuetnov.carbook.dto.UserDTO;
import com.viktorsuetnov.carbook.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    public UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }
}
