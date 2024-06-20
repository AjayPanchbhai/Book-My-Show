package com.SpringBootBackend.BookMyShow.Mappers;

import com.SpringBootBackend.BookMyShow.DTO.UserDTO;
import com.SpringBootBackend.BookMyShow.Models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static UserDTO toUserDTO(User user) {
        return new UserDTO (
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getMobileNo(),
                user.getRole()
        );
    }
}
