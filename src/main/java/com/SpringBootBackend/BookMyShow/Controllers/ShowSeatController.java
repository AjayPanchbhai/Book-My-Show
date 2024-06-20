package com.SpringBootBackend.BookMyShow.Controllers;

import com.SpringBootBackend.BookMyShow.DTO.ShowSeatDTO;
import com.SpringBootBackend.BookMyShow.Mappers.ShowSeatMapper;
import com.SpringBootBackend.BookMyShow.Services.ShowSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/showSeats")
public class ShowSeatController {
    @Autowired
    private ShowSeatService showSeatService;

    @GetMapping("/get")
    public ResponseEntity<ShowSeatDTO> getShowSeat(@RequestParam("showSeatId") Long showSeatId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(
                ShowSeatMapper.toShowSeatDTO(showSeatService.getShowSeat(showSeatId))
        );
    }

    @PatchMapping("/disable")
    public ResponseEntity<ShowSeatDTO> disableShowSeat(@RequestParam("showSeatId") Long showSeatId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(
                ShowSeatMapper.toShowSeatDTO(showSeatService.disableShowSeat(showSeatId))
        );
    }

    @PatchMapping("/enable")
    public ResponseEntity<ShowSeatDTO> enableShowSeat(@RequestParam("showSeatId") Long showSeatId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(
                ShowSeatMapper.toShowSeatDTO(showSeatService.enableShowSeat(showSeatId))
        );
    }
}
