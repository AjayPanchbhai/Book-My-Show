package com.SpringBootBackend.BookMyShow.Mappers;

import com.SpringBootBackend.BookMyShow.DTO.SeatDTO;
import com.SpringBootBackend.BookMyShow.DTO.TheaterDTO;
import com.SpringBootBackend.BookMyShow.Models.Seat;
import org.springframework.stereotype.Component;

@Component
public class SeatMapper {
    public static SeatDTO toSeatDTO(Seat seat) {
        return new SeatDTO(
                seat.getSeatId(),
                seat.getSeatNo(),
                seat.getSeatType(),
                new TheaterDTO(
                        seat.getTheater().getTheaterId(),
                        seat.getTheater().getName(),
                        seat.getTheater().getAddress()
                )
        );
    }
}
