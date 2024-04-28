package com.acciojob.bookmyshowapplications.Models;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stadiums")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Stadium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stadiumId;

    private String name;

    private String address;

    @OneToMany(mappedBy = "stadium",cascade = CascadeType.ALL)
    private List<StadiumSeat> stadiumSeatList = new ArrayList<>();
}
