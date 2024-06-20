package com.SpringBootBackend.BookMyShow.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "show_seats")
@Data
@AllArgsConstructor
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "show_seat_id")
    private Long showSeatId;

    @Column(name = "is_booked")
    private Boolean isBooked;

    @Column(name = "is_disabled")
    private Boolean isDisabled;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "seat")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public ShowSeat() {
        this.isBooked = false;
        this.isDisabled = false;
    }
}
