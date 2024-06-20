package com.SpringBootBackend.BookMyShow.DTO;

import com.SpringBootBackend.BookMyShow.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNo;
    private Role role;
}
