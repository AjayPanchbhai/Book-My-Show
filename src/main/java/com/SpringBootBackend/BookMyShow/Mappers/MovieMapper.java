package com.SpringBootBackend.BookMyShow.Mappers;

import com.SpringBootBackend.BookMyShow.DTO.MovieDTO;
import com.SpringBootBackend.BookMyShow.Models.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    public static MovieDTO toMovieDTO(Movie movie) {
        return new MovieDTO(
                movie.getMovieId(),
                movie.getName(),
                movie.getDuration(),
                movie.getGenre(),
                movie.getLanguage(),
                movie.getReleaseDate(),
                movie.getRating()
        );
    }
}
