package com.SpringBootBackend.BookMyShow.Mappers;

import com.SpringBootBackend.BookMyShow.DTO.*;
import com.SpringBootBackend.BookMyShow.Models.Ticket;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    @Contract("_ -> new")
    public static @NotNull TicketDTO toTicketDTO(@NotNull Ticket ticket) {
        return new TicketDTO(
                ticket.getTicketId(),
                ticket.getAmount(),
                new ShowDTO(
                    ticket.getShow().getShowId(),
                    ticket.getShow().getDate(),
                    ticket.getShow().getTime(),
                    new MovieDTO(
                        ticket.getShow().getMovie().getMovieId(),
                        ticket.getShow().getMovie().getName(),
                        ticket.getShow().getMovie().getDuration(),
                        ticket.getShow().getMovie().getGenre(),
                        ticket.getShow().getMovie().getLanguage(),
                        ticket.getShow().getMovie().getReleaseDate(),
                        ticket.getShow().getMovie().getRating()
                    ),
                    new TheaterDTO(
                        ticket.getShow().getTheater().getTheaterId(),
                        ticket.getShow().getTheater().getName(),
                        ticket.getShow().getTheater().getAddress()
                    )
                ),
                new UserDTO(
                        ticket.getUser().getUserId(),
                        ticket.getUser().getFirstName(),
                        ticket.getUser().getLastName(),
                        ticket.getUser().getEmail(),
                        ticket.getUser().getMobileNo(),
                        ticket.getUser().getRole()
                ),
                new ShowSeatDTO(
                        ticket.getShowSeat().getShowSeatId(),
                        ticket.getShowSeat().getIsBooked(),
                        ticket.getShowSeat().getIsDisabled(),
                        ticket.getShowSeat().getPrice(),
                        new SeatDTO(
                                ticket.getShowSeat().getSeat().getSeatId(),
                                ticket.getShowSeat().getSeat().getSeatNo(),
                                ticket.getShowSeat().getSeat().getSeatType(),
                                new TheaterDTO(
                                        ticket.getShowSeat().getSeat().getTheater().getTheaterId(),
                                        ticket.getShowSeat().getSeat().getTheater().getName(),
                                        ticket.getShowSeat().getSeat().getTheater().getAddress()
                                )
                        )
                )
        );
    }
}
