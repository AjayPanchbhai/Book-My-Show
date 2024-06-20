package com.SpringBootBackend.BookMyShow.Controllers;

import com.SpringBootBackend.BookMyShow.DTO.UserDTO;
import com.SpringBootBackend.BookMyShow.Mappers.UserMapper;
import com.SpringBootBackend.BookMyShow.Models.User;
import com.SpringBootBackend.BookMyShow.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auths")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@RequestBody User user) {
        return ResponseEntity.ok().body(
                UserMapper.toUserDTO(authService.signup(user))
        );
    }

    @PostMapping("/signin")
    public String signin(@RequestBody User user) throws Exception {
        return authService.signin(user);
    }
}
