package com.np.suprimpoudel.mapper;

import com.np.suprimpoudel.dto.UserDTO;
import com.np.suprimpoudel.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toUser(String username, String password) {
        var user = new User();
        user.setUsername(username);
        user.setPassword(password);

        return user;
    }

    public UserDTO toUserDTO(User user) {
        var userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());

        return userDTO;
    }
}
