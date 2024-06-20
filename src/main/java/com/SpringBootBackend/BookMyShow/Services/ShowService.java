package com.SpringBootBackend.BookMyShow.Services;

import com.SpringBootBackend.BookMyShow.Enums.SeatType;
import com.SpringBootBackend.BookMyShow.Exceptions.ResourceNotFoundException;
import com.SpringBootBackend.BookMyShow.Models.*;
import com.SpringBootBackend.BookMyShow.Repositories.SeatRepository;
import com.SpringBootBackend.BookMyShow.Repositories.ShowRepository;
import com.SpringBootBackend.BookMyShow.Repositories.ShowSeatRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private TheaterService theaterService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private SeatRepository seatRepository;

//    @Transactional
    public Show addShow(
            @NotNull Show show,
            Long theaterId,
            Long movieId,
            Double classicPrice,
            Double premiumPrice
    ) {
        if (theaterId == null || movieId == null) {
            throw new IllegalArgumentException("Theater ID and Movie ID must not be null");
        }

        Theater theater = theaterService.getTheater(theaterId);
        Movie movie = movieService.getMovie(movieId);

        show.setTheater(theater);
        show.setMovie(movie);

        // Save the Show entity first to get the showId
        show = showRepository.save(show);

        List<ShowSeat> showSeats = new ArrayList<>();
        List<Seat> seats = theater.getSeats();

        for (Seat seat : seats) {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setPrice(seat.getSeatType().equals(SeatType.PREMIUM) ? premiumPrice : classicPrice);
            showSeat.setShow(show);
            showSeat.setSeat(seat);

            showSeats.add(showSeat);
        }

        show.setSeats(showSeats);

        for(ShowSeat showSeat : showSeatRepository.saveAll(showSeats)) {
            showSeat.getSeat().getShowSeats().add(showSeat);
        }

        seatRepository.saveAll(seats);
        return showRepository.save(show);
    }


    public Show getShow(Long showId) {
        return showRepository.findById(showId)
                .orElseThrow(() -> new ResourceNotFoundException("Show Not Found with ID: " + showId));
    }

    public List<Show> getAllShows() {
        List<Show> shows = showRepository.findAll();

        if (shows.isEmpty()) {
            throw new ResourceNotFoundException("No shows found!");
        }

        return shows;
    }
}
