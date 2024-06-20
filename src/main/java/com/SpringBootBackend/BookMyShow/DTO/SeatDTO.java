package com.SpringBootBackend.BookMyShow.DTO;

import com.SpringBootBackend.BookMyShow.Enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SeatDTO {
    private Long seatId;
    private String seatNo;
    private SeatType seatType;
    private TheaterDTO theater;
}
