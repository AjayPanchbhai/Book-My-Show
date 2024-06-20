package com.SpringBootBackend.BookMyShow.DTO;
import java.time.LocalDate;
import java.time.LocalTime;

import com.SpringBootBackend.BookMyShow.Enums.Genre;
import com.SpringBootBackend.BookMyShow.Enums.Language;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieDTO {
    private Long movieId;
    private String name;
    private LocalTime duration;
    private Genre genre;
    private Language language;
    private LocalDate releaseDate;
    private Double rating;
}
