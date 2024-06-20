package com.SpringBootBackend.BookMyShow.Mappers;

import com.SpringBootBackend.BookMyShow.DTO.MovieDTO;
import com.SpringBootBackend.BookMyShow.DTO.ShowDTO;
import com.SpringBootBackend.BookMyShow.DTO.TheaterDTO;
import com.SpringBootBackend.BookMyShow.Models.Show;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ShowMapper {
    public static ShowDTO toShowDTO(Show show) {
        return new ShowDTO(
                show.getShowId(),
                show.getDate(),
                show.getTime(),
                new MovieDTO(
                    show.getMovie().getMovieId(),
                    show.getMovie().getName(),
                    show.getMovie().getDuration(),
                    show.getMovie().getGenre(),
                    show.getMovie().getLanguage(),
                    show.getMovie().getReleaseDate(),
                    show.getMovie().getRating()
                ),
                new TheaterDTO(
                    show.getTheater().getTheaterId(),
                    show.getTheater().getName(),
                    show.getTheater().getAddress()
                )
        );
    }
}
