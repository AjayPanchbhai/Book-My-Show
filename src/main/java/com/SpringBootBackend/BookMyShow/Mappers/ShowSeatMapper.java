package com.SpringBootBackend.BookMyShow.Mappers;

import com.SpringBootBackend.BookMyShow.DTO.SeatDTO;
import com.SpringBootBackend.BookMyShow.DTO.ShowSeatDTO;
import com.SpringBootBackend.BookMyShow.DTO.TheaterDTO;
import com.SpringBootBackend.BookMyShow.Models.Seat;
import com.SpringBootBackend.BookMyShow.Models.ShowSeat;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class ShowSeatMapper {
    @Contract("_ -> new")
    public static @NotNull ShowSeatDTO toShowSeatDTO(@NotNull ShowSeat showSeat) {
        return new ShowSeatDTO(
                showSeat.getShowSeatId(),
                showSeat.getIsBooked(),
                showSeat.getIsDisabled(),
                showSeat.getPrice(),
                new SeatDTO(
                        showSeat.getSeat().getSeatId(),
                        showSeat.getSeat().getSeatNo(),
                        showSeat.getSeat().getSeatType(),
                        new TheaterDTO(
                                showSeat.getSeat().getTheater().getTheaterId(),
                                showSeat.getSeat().getTheater().getName(),
                                showSeat.getSeat().getTheater().getAddress()
                        )
                )
        );
    }
}
