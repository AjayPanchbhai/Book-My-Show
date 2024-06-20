package com.SpringBootBackend.BookMyShow.Services;

import com.SpringBootBackend.BookMyShow.Exceptions.ResourceNotFoundException;
import com.SpringBootBackend.BookMyShow.Models.Show;
import com.SpringBootBackend.BookMyShow.Models.ShowSeat;
import com.SpringBootBackend.BookMyShow.Models.Ticket;
import com.SpringBootBackend.BookMyShow.Models.User;
import com.SpringBootBackend.BookMyShow.Repositories.ShowSeatRepository;
import com.SpringBootBackend.BookMyShow.Repositories.TicketRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ShowService showService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private ShowSeatService showSeatService;

    public Ticket bookMovieSeatTicket(
            @NotNull Ticket ticket,
            Long showId,
            Long userId,
            Long showSeatId
    ) {
        Show show = showService.getShow(showId);
        User user = userService.getUser(userId);
        ShowSeat showSeat = showSeatService.getShowSeat(showSeatId);

        if(showSeat.getIsDisabled())
            throw new IllegalArgumentException("Seat is Disabled");

        if (showSeat.getIsBooked()) {
            throw new IllegalArgumentException("Seat is Already Booked");
        }

        showSeat.setIsBooked(true);
        ticket.setShowSeat(showSeat);
        ticket.setAmount(showSeat.getPrice());
        ticket.setShow(show);
        ticket.setUser(user);

        showSeatRepository.save(showSeat);
        return ticketRepository.save(ticket);
    }

    public Ticket getTicket(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket Not Found with ID : " + ticketId));
    }

    public List<Ticket> getTicketByUser(Long userId) {
        List<Ticket> tickets = ticketRepository.findByUserId(userId);

        if (tickets.isEmpty())
            throw new ResourceNotFoundException("NoFound for User ID : " + userId);

        return tickets;
    }

    public Ticket cancelTicket(Long ticketId) {
        Ticket ticket = this.getTicket(ticketId);
        ShowSeat showSeat = ticket.getShowSeat();

        showSeat.setIsBooked(false);
        showSeatRepository.save(showSeat);

        ticketRepository.delete(ticket);

        return ticket;
    }
}
