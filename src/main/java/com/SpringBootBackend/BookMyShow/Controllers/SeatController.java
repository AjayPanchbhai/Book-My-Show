package com.SpringBootBackend.BookMyShow.Controllers;

import com.SpringBootBackend.BookMyShow.DTO.SeatDTO;
import com.SpringBootBackend.BookMyShow.Mappers.SeatMapper;
import com.SpringBootBackend.BookMyShow.Models.Seat;
import com.SpringBootBackend.BookMyShow.Services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/seats")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @GetMapping("/get")
    public ResponseEntity<SeatDTO> getSeat(@RequestParam("seatId") Long seatId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(
                SeatMapper.toSeatDTO(seatService.getSeat(seatId))
        );
    }

    @GetMapping("")
    public ResponseEntity<List<SeatDTO>> getAllTheaterSeats(@RequestParam("theaterId") Long theaterId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(
                seatService.getAllTheaterSeats(theaterId)
                        .stream()
                        .map(SeatMapper::toSeatDTO)
                        .collect(Collectors.toList())
        );
    }

    @PatchMapping("")
    public ResponseEntity<SeatDTO> updateSeat(
            @RequestBody Seat seat,
            @RequestParam("seatId") Long seatId
    ) {
        return ResponseEntity.ok().body(
                SeatMapper.toSeatDTO(seatService.updateSeat(seat, seatId))
        );
    }

    @PatchMapping("/upgrade")
    public ResponseEntity<List<SeatDTO>> upgradeTheaterSeats(
            @RequestParam("theaterId") Long theaterId,
            @RequestBody List<Long> seatIds
    ) {
        return ResponseEntity.ok().body(
                seatService.upgradeSeat(theaterId, seatIds)
                        .stream()
                        .map(SeatMapper::toSeatDTO)
                        .collect(Collectors.toList())
        );
    }

    @PatchMapping("/degrade")
    public ResponseEntity<List<SeatDTO>> degradeTheaterSeats(
            @RequestParam("theaterId") Long theaterId,
            @RequestBody List<Long> seatIds
    ) {
        return ResponseEntity.ok().body(
                seatService.degradeSeat(theaterId, seatIds)
                        .stream()
                        .map(SeatMapper::toSeatDTO)
                        .collect(Collectors.toList())
        );
    }

    @DeleteMapping("")
    public ResponseEntity<SeatDTO> deleteSeat(@RequestParam("seatId") Long seatId) {
        return ResponseEntity.ok().body(
                SeatMapper.toSeatDTO(seatService.deleteSeat(seatId))
        );
    }
}
