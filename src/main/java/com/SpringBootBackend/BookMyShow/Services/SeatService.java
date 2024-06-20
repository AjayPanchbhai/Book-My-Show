package com.SpringBootBackend.BookMyShow.Services;

import com.SpringBootBackend.BookMyShow.Enums.SeatType;
import com.SpringBootBackend.BookMyShow.Exceptions.ResourceNotFoundException;
import com.SpringBootBackend.BookMyShow.Models.Seat;
import com.SpringBootBackend.BookMyShow.Models.Theater;
import com.SpringBootBackend.BookMyShow.Repositories.SeatRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private TheaterService theaterService;

    public Seat getSeat(Long seatId) {
        return seatRepository.findById(seatId)
                .orElseThrow(() -> new ResourceNotFoundException("Seat Not Found with ID : " + seatId));
    }

    public List<Seat> getAllTheaterSeats(Long theaterId) {
        return theaterService.getTheater(theaterId).getSeats();
    }

    public Seat updateSeat(@NotNull Seat seat, Long seatId) {
        Seat seat1 = this.getSeat(seatId);

        if(seat.getSeatNo() != null) seat1.setSeatNo(seat.getSeatNo());
        if(seat.getSeatType() != null) seat1.setSeatType(seat.getSeatType());

        return seatRepository.save(seat1);
    }


    public List<Seat> upgradeSeat(Long theaterId, @NotNull List<Long> seatIds) {
        Theater theater = theaterService.getTheater(theaterId);
        List<Seat> seats = theater.getSeats();

        List<Seat> updatedSeats = new ArrayList<>();

        for(Long seatId : seatIds) {
            for(Seat seat : seats) {
                if(Objects.equals(seatId, seat.getSeatId())) {
                    seat.setSeatType(SeatType.PREMIUM);
                    updatedSeats.add(seat);
                    break;
                }
            }
        }

        return  seatRepository.saveAll(updatedSeats);
    }

    public List<Seat> degradeSeat(Long theaterId, @NotNull List<Long> seatIds) {
        Theater theater = theaterService.getTheater(theaterId);
        List<Seat> seats = theater.getSeats();

        List<Seat> updatedSeats = new ArrayList<>();

        for(Long seatId : seatIds) {
            for(Seat seat : seats) {
                if(Objects.equals(seatId, seat.getSeatId())) {
                    seat.setSeatType(SeatType.CLASSIC);
                    updatedSeats.add(seat);
                    break;
                }
            }
        }

        return  seatRepository.saveAll(updatedSeats);
    }

    public Seat deleteSeat(Long seatId) {
        Seat seat = this.getSeat(seatId);

        seatRepository.deleteById(seatId);

        return seat;
    }
}
