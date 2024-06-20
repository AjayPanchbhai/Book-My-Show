package com.SpringBootBackend.BookMyShow.Models;

import com.SpringBootBackend.BookMyShow.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "seats")
@Data
@AllArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id", nullable = false)
    private Long seatId;

    @Column(name = "seat_no")
    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "seat_type")
    private SeatType seatType;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<ShowSeat> showSeats;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Seat() {
        this.seatType = SeatType.CLASSIC;
    }
}
