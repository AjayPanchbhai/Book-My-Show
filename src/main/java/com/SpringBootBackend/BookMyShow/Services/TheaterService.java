package com.SpringBootBackend.BookMyShow.Services;

import com.SpringBootBackend.BookMyShow.Exceptions.ResourceNotFoundException;
import com.SpringBootBackend.BookMyShow.Helpers.PasswordValidator.ValidPassword;
import com.SpringBootBackend.BookMyShow.Models.Movie;
import com.SpringBootBackend.BookMyShow.Models.Theater;
import com.SpringBootBackend.BookMyShow.Models.User;
import com.SpringBootBackend.BookMyShow.Repositories.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;

    public Theater addTheater(Theater theater) {
        return theaterRepository.save(theater);
    }

    public Theater getTheater(Long theaterId) {
        return theaterRepository.findById(theaterId)
                .orElseThrow(() -> new ResourceNotFoundException("Theater Not Found with ID : " + theaterId));

    }

    public List<Theater> getAllTheaters() {
        List<Theater> theaters = theaterRepository.findAll();

        if(theaters.isEmpty())
            throw new ResourceNotFoundException("No theater is Found!");

        return theaters;
    }

    public Theater updateTheater(Theater theater, Long theaterId) {
        Theater theater1 = this.getTheater(theaterId);

        if(theater.getName() != null) theater1.setName(theater.getName());
        if(theater.getAddress() != null) theater1.setAddress(theater.getAddress());

        return theaterRepository.save(theater1);
    }

    public Theater deleteTheater(Long theaterId) {
        Theater theater = this.getTheater(theaterId);

        theaterRepository.deleteById(theaterId);

        return theater;
    }
}
