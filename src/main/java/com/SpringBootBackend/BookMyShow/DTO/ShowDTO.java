package com.SpringBootBackend.BookMyShow.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class ShowDTO {
    private Long showId;
    private LocalDate date;
    private LocalTime time;
    private MovieDTO movie;
    private TheaterDTO theater;
}
