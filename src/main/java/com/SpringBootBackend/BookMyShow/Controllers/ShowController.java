package com.SpringBootBackend.BookMyShow.Controllers;

import com.SpringBootBackend.BookMyShow.DTO.ShowDTO;
import com.SpringBootBackend.BookMyShow.Mappers.ShowMapper;
import com.SpringBootBackend.BookMyShow.Models.Show;
import com.SpringBootBackend.BookMyShow.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shows")
public class ShowController {
    @Autowired
    private ShowService showService;

    @PostMapping("")
    public ResponseEntity<ShowDTO> addShow(
            @RequestBody Show show,
            @RequestParam("theaterId") Long theaterId,
            @RequestParam("movieId") Long movieId,
            @RequestParam("classicPrice") Double classicPrice,
            @RequestParam("premiumPrice") Double premiumPrice
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ShowMapper.toShowDTO(showService.addShow(
                        show,
                        theaterId,
                        movieId,
                        classicPrice,
                        premiumPrice)
                )
        );
    }

    @GetMapping("/get")
    public ResponseEntity<ShowDTO> getShow(@RequestParam("showId") Long showId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(
                ShowMapper.toShowDTO(showService.getShow(showId))
        );
    }
}
