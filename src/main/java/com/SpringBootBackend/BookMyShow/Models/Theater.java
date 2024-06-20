package com.SpringBootBackend.BookMyShow.Models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theaters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theater_id", nullable = false)
    private Long theaterId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "num_rows", nullable = false) // Changed column name
    private Integer numRows;

    @Column(name = "columns", nullable = false)
    private Integer columns;

    @OneToMany
    @JoinColumn(name = "photos")
    private List<File> photos;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Seat> seats;

    @OneToMany(mappedBy = "theater")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Show> shows;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    private void onCreate() {
        if (this.seats == null || this.seats.isEmpty()) {
            this.seats = new ArrayList<>();
            char ch = 'A';
            for(int row = 1; row <= this.numRows; ++row) {
                for(int col = 1; col <= this.columns; ++col) {
                    String seatNo = String.valueOf(ch + "" +col);
                    Seat seat = new Seat();
                    seat.setSeatNo(seatNo);
                    seat.setTheater(this);

                    this.getSeats().add(seat);
                }

                ch = (char) (ch + 1);
            }
        }
    }
}
