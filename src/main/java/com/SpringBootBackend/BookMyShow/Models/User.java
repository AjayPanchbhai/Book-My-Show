package com.SpringBootBackend.BookMyShow.Models;

import com.SpringBootBackend.BookMyShow.Enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First Name is Required!")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "Email is Required!")
    @Email(message = "Email format is Invalid!")
    private String email;

    @Column(name = "mobile")
    private String mobileNo;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password is Required!")
    private String password;

    @OneToOne
    @JoinColumn(name = "profile")
    private File profile;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Ticket> tickets;

    public User() {
        this.role = Role.USER;
    }
}
