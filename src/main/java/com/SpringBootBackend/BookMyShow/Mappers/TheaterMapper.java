package com.SpringBootBackend.BookMyShow.Mappers;

import com.SpringBootBackend.BookMyShow.DTO.TheaterDTO;
import com.SpringBootBackend.BookMyShow.Models.Theater;
import org.springframework.stereotype.Component;

@Component
public class TheaterMapper {
    public static TheaterDTO toTheaterDTO(Theater theater) {
        return new TheaterDTO(
                theater.getTheaterId(),
                theater.getName(),
                theater.getAddress()
        );
    }
}
