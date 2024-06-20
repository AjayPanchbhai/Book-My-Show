package com.SpringBootBackend.BookMyShow.Controllers;

import com.SpringBootBackend.BookMyShow.DTO.TicketDTO;
import com.SpringBootBackend.BookMyShow.Mappers.TicketMapper;
import com.SpringBootBackend.BookMyShow.Models.Ticket;
import com.SpringBootBackend.BookMyShow.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("")
    public ResponseEntity<TicketDTO> bookMovieTicket(
            @RequestBody Ticket ticket,
            @RequestParam("showId")  Long showId,
            @RequestParam("userId") Long userId,
            @RequestParam("showSeatId") Long showSeatId
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                TicketMapper.toTicketDTO(ticketService.bookMovieSeatTicket(
                        ticket,
                        showId,
                        userId,
                        showSeatId
                ))
        );
    }

    @GetMapping("/get")
    public ResponseEntity<TicketDTO> getTicket(@RequestParam("ticketId") Long ticketId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(
                TicketMapper.toTicketDTO(ticketService.getTicket(ticketId))
        );
    }

    @GetMapping("/get/user")
    public ResponseEntity<List<TicketDTO>> getTicketByUser(@RequestParam("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(
                ticketService.getTicketByUser(userId)
                        .stream()
                        .map(TicketMapper::toTicketDTO)
                        .collect(Collectors.toList())
        );
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<TicketDTO> cancelTicket(
            @RequestParam("ticketId") Long ticketId
    ) {
        return ResponseEntity.ok().body(
                TicketMapper.toTicketDTO(ticketService.cancelTicket(ticketId))
        );
    }
}
