package com.SpringBootBackend.BookMyShow.Services;

import com.SpringBootBackend.BookMyShow.Exceptions.ResourceNotFoundException;
import com.SpringBootBackend.BookMyShow.Helpers.PasswordValidator.ValidPassword;
import com.SpringBootBackend.BookMyShow.Models.File;
import com.SpringBootBackend.BookMyShow.Models.User;
import com.SpringBootBackend.BookMyShow.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FileService fileService;

    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with ID : " + userId));
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        if(users.isEmpty()) {
            throw new ResourceNotFoundException("No user Found!");
        }

        return users;
    }

    // update user profile
    public User updateUserProfile(long userId, MultipartFile profile) throws IOException {
        User user = this.getUser(userId);

//        if (!authenticationService.isSignedIn()) {
//            throw new RuntimeException("User is not signed in");
//        }

        if(profile == null)
            throw new IllegalArgumentException("Profile might be Empty!, File is Required!");

        File savedFile = fileService.addFile(profile);
        user.setProfile(savedFile);

        return userRepository.save(user);
    }

    public User updateUser(User user, Long userId) {
        User user1 = this.getUser(userId);

        if(user.getFirstName() != null) user1.setFirstName(user.getFirstName());
        if(user.getLastName() != null) user1.setLastName(user.getLastName());
        if(user.getEmail() != null) user1.setEmail(user.getEmail());

        if(user.getPassword() != null) {
            String validationMessage = ValidPassword.isValid(user.getPassword());

            if(!validationMessage.equalsIgnoreCase("valid"))
                throw new IllegalArgumentException(validationMessage);

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user1.setPassword(user.getPassword());
        }

        if(user.getMobileNo()!= null) user1.setMobileNo(user.getMobileNo());
        if(user.getRole() != null) user1.setRole(user.getRole());

        return userRepository.save(user1);
    }

    public User deleteUser(Long userId) {
        User user = this.getUser(userId);

        userRepository.deleteById(userId);

        return user;
    }
}
