package com.SpringBootBackend.BookMyShow.Controllers;

import com.SpringBootBackend.BookMyShow.DTO.MovieDTO;
import com.SpringBootBackend.BookMyShow.Mappers.MovieMapper;
import com.SpringBootBackend.BookMyShow.Models.Movie;
import com.SpringBootBackend.BookMyShow.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("")
    public ResponseEntity<MovieDTO> addMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok().body(
                MovieMapper.toMovieDTO(movieService.addMovie(movie))
        );
    }

    @GetMapping("/get")
    public ResponseEntity<MovieDTO> getMovie(@RequestParam("movieId") Long movieId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(
                MovieMapper.toMovieDTO(movieService.getMovie(movieId))
        );
    }

    @GetMapping("")
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        return ResponseEntity.status(HttpStatus.FOUND).body(
                movieService.getAllMovies()
                        .stream()
                        .map(MovieMapper::toMovieDTO)
                        .collect(Collectors.toList())
        );
    }

    @PatchMapping("/poster")
    public ResponseEntity<MovieDTO> updateMoviePoster(
            @RequestParam("movieId") Long movieId,
            @RequestBody MultipartFile poster
    ) throws IOException {
        return ResponseEntity.ok().body(
                MovieMapper.toMovieDTO(movieService.updateMoviePoster(movieId, poster))
        );
    }

    @PatchMapping("")
    public ResponseEntity<MovieDTO> updateMovie(
            @RequestBody Movie movie,
            @RequestParam("movieId") Long movieId
    ) {
        return ResponseEntity.ok().body(
                MovieMapper.toMovieDTO(movieService.updateMovie(movie, movieId))
        );
    }

    @DeleteMapping("")
    public ResponseEntity<MovieDTO> deleteMovie(@RequestParam("movieId") Long movieId) {
        return ResponseEntity.ok().body(
                MovieMapper.toMovieDTO(movieService.deleteMovie(movieId))
        );
    }
}
