package com.SpringBootBackend.BookMyShow.Services;

import com.SpringBootBackend.BookMyShow.Helpers.PasswordValidator.ValidPassword;
import com.SpringBootBackend.BookMyShow.Models.User;
import com.SpringBootBackend.BookMyShow.Repositories.UserRepository;
import com.SpringBootBackend.BookMyShow.Utilities.JwtRequestFilter;
import com.SpringBootBackend.BookMyShow.Utilities.JwtUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // SIGN UP
    public User signup(@NotNull User user) {
        User user1 = userRepository.findByEmail(user.getEmail()).orElse(null);

        if(user1 != null)
            throw new IllegalArgumentException("User is already exist with email : " + user.getEmail());

        String validationMessage = ValidPassword.isValid(user.getPassword());

        if(!validationMessage.equalsIgnoreCase("valid"))
            throw new IllegalArgumentException(validationMessage);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    // SIGN IN
    public String signin(User user) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),
                    user.getPassword()));
        } catch (Exception e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        return "Auth Token : " + jwt;
    }
}
