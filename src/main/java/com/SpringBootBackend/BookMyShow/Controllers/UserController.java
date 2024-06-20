package com.SpringBootBackend.BookMyShow.Controllers;

import com.SpringBootBackend.BookMyShow.DTO.UserDTO;
import com.SpringBootBackend.BookMyShow.Mappers.UserMapper;
import com.SpringBootBackend.BookMyShow.Models.User;
import com.SpringBootBackend.BookMyShow.Services.AuthService;
import com.SpringBootBackend.BookMyShow.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public ResponseEntity<UserDTO> getUser(@RequestParam("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(
                UserMapper.toUserDTO(userService.getUser(userId))
        );
    }

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.FOUND).body(
                userService.getAllUsers()
                        .stream()
                        .map(UserMapper::toUserDTO)
                        .collect(Collectors.toList())
        );
    }

    @PatchMapping("/profile")
    public ResponseEntity<UserDTO> updateUserProfile(
            @RequestParam("userId") Long userId,
            @RequestBody MultipartFile profile
    ) throws IOException {
        return ResponseEntity.ok().body(
                UserMapper.toUserDTO(userService.updateUserProfile(userId, profile))
        );
    }

    @PatchMapping("")
    public ResponseEntity<UserDTO> updateUser(
            @RequestBody User user,
            @RequestParam("userId") Long userId
    ) {
        return ResponseEntity.ok().body(
                UserMapper.toUserDTO(userService.updateUser(user, userId))
        );
    }

    @DeleteMapping("")
    public ResponseEntity<UserDTO> deleteUser(@RequestParam("userId") Long userId) {
        return ResponseEntity.ok().body(
                UserMapper.toUserDTO(userService.deleteUser(userId))
        );
    }
}
