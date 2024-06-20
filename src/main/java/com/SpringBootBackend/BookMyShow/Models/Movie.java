package com.SpringBootBackend.BookMyShow.Models;

import com.SpringBootBackend.BookMyShow.Enums.Genre;
import com.SpringBootBackend.BookMyShow.Enums.Language;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id", nullable = false)
    private Long movieId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToOne
    @JoinColumn(name = "poster")
    private File poster;

    @Column(name = "duration")
    private LocalTime duration;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "genre")
    private Genre genre;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "language")
    private Language language;

    @Column(name = "release_data")
    private LocalDate releaseDate;

    @Column(name = "rating")
    private Double rating;

    @OneToMany(mappedBy = "movie")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Show> shows;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
