package com.SpringBootBackend.BookMyShow.DTO;

import com.SpringBootBackend.BookMyShow.Models.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShowSeatDTO {
    private Long showSeatId;
    private Boolean isBooked;
    private Boolean isDisabled;
    private Double price;
    private SeatDTO seat;
}
