package com.SpringBootBackend.BookMyShow.Services;

import com.SpringBootBackend.BookMyShow.Exceptions.ResourceNotFoundException;
import com.SpringBootBackend.BookMyShow.Models.ShowSeat;
import com.SpringBootBackend.BookMyShow.Repositories.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowSeatService {
    @Autowired
    private ShowSeatRepository showSeatRepository;

    public ShowSeat getShowSeat(Long showSeatId) {
        return showSeatRepository.findById(showSeatId)
                .orElseThrow(() -> new ResourceNotFoundException("ShowSeat is Not Found with Id : " + showSeatId));
    }

    public ShowSeat disableShowSeat(Long showSeatId) {
        ShowSeat showSeat = this.getShowSeat(showSeatId);

        showSeat.setIsDisabled(true);

        return showSeatRepository.save(showSeat);
    }

    public ShowSeat enableShowSeat(Long showSeatId) {
        ShowSeat showSeat = this.getShowSeat(showSeatId);

        showSeat.setIsDisabled(false);

        return showSeatRepository.save(showSeat);
    }


}
