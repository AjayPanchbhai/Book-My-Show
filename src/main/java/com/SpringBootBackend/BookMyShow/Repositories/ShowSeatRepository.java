package com.SpringBootBackend.BookMyShow.Repositories;

import com.SpringBootBackend.BookMyShow.Models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
}
