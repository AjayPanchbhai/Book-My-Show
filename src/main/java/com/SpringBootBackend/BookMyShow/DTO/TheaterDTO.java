package com.SpringBootBackend.BookMyShow.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class TheaterDTO {
    private Long theaterId;
    private String name;
    private String address;
}
