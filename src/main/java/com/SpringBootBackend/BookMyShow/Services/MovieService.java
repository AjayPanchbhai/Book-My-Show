package com.SpringBootBackend.BookMyShow.Services;

import com.SpringBootBackend.BookMyShow.Exceptions.ResourceNotFoundException;
import com.SpringBootBackend.BookMyShow.Models.File;
import com.SpringBootBackend.BookMyShow.Models.Movie;
import com.SpringBootBackend.BookMyShow.Models.User;
import com.SpringBootBackend.BookMyShow.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private FileService fileService;

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie getMovie(Long movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie Not Found with id " + movieId));
    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();

        if(movies.isEmpty())
            throw new ResourceNotFoundException("No movie is Found!");

        return movies;
    }

    public Movie updateMoviePoster(long movieId, MultipartFile poster) throws IOException {
        Movie movie = this.getMovie(movieId);

//        if (!authenticationService.isSignedIn()) {
//            throw new RuntimeException("User is not signed in");
//        }

        if(poster == null)
            throw new IllegalArgumentException("Poster might be Empty!, File is Required!");

        File savedFile = fileService.addFile(poster);
        movie.setPoster(savedFile);

        return movieRepository.save(movie);
    }

    public Movie updateMovie(Movie movie, Long movieId) {
        Movie movie1 = this.getMovie(movieId);

        if(movie.getName() != null) movie1.setName(movie.getName());
        if(movie.getDuration() != null) movie1.setDuration(movie.getDuration());
        if(movie.getGenre() != null) movie1.setGenre(movie.getGenre());
        if(movie.getLanguage() != null) movie1.setLanguage(movie.getLanguage());
        if(movie.getRating() != null) movie1.setRating(movie.getRating());
        if(movie.getReleaseDate() != null) movie1.setReleaseDate(movie.getReleaseDate());

        return movieRepository.save(movie1);
    }

    public Movie deleteMovie(Long movieId) {
        Movie movie = this.getMovie(movieId);

        movieRepository.deleteById(movieId);

        return movie;
    }

}
