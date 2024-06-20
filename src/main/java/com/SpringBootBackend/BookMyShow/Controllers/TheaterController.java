package com.SpringBootBackend.BookMyShow.Controllers;

import com.SpringBootBackend.BookMyShow.DTO.TheaterDTO;
import com.SpringBootBackend.BookMyShow.Mappers.TheaterMapper;
import com.SpringBootBackend.BookMyShow.Models.Theater;
import com.SpringBootBackend.BookMyShow.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/theaters")
public class TheaterController {
    @Autowired
    private TheaterService theaterService;

    @PostMapping("")
    public ResponseEntity<TheaterDTO> addTheater(@RequestBody Theater theater) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                TheaterMapper.toTheaterDTO(theaterService.addTheater(theater))
        );
    }

    @GetMapping("/get")
    public ResponseEntity<TheaterDTO> getTheater(@RequestParam("theaterId") Long theaterId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(
                TheaterMapper.toTheaterDTO(theaterService.getTheater(theaterId))
        );
    }

    @GetMapping("")
    public ResponseEntity<List<TheaterDTO>> getAllTheaters() {
        return ResponseEntity.status(HttpStatus.FOUND).body(
                theaterService.getAllTheaters()
                        .stream()
                        .map(TheaterMapper::toTheaterDTO)
                        .collect(Collectors.toList())
        );
    }

//    @PatchMapping("/profile")
//    public ResponseEntity<TheaterDTO> updateTheaterProfile(
//            @RequestParam("theaterId") Long theaterId,
//            @RequestBody MultipartFile profile
//    ) throws IOException {
//        return ResponseEntity.ok().body(
//                UserMapper.toUserDTO(theaterService.updateTheaterProfile(theaterId, profile))
//        );
//    }

    @PatchMapping("")
    public ResponseEntity<TheaterDTO> updateTheater(
            @RequestBody Theater theater,
            @RequestParam("theaterId") Long theaterId
    ) {
        return ResponseEntity.ok().body(
                TheaterMapper.toTheaterDTO(theaterService.updateTheater(theater, theaterId))
        );
    }

    @DeleteMapping("")
    public ResponseEntity<TheaterDTO> deleteTheater(@RequestParam("theaterId") Long theaterId) {
        return ResponseEntity.ok().body(
                TheaterMapper.toTheaterDTO(theaterService.deleteTheater(theaterId))
        );
    }
}
