package com.SpringBootBackend.BookMyShow.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketDTO {
    private Long ticketId;
    private Double amount;
    private ShowDTO show;
    private UserDTO user;
    private ShowSeatDTO showSeat;
}
