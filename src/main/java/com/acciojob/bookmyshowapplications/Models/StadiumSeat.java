package com.acciojob.bookmyshowapplications.Models;

import com.acciojob.bookmyshowapplications.Enums.SeatType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stadium_seats")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StadiumSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stadiumSeatId;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    @JoinColumn
    @ManyToOne
    private Stadium stadium;
}
