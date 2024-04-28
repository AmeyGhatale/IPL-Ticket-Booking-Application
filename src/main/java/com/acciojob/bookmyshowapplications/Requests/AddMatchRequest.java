package com.acciojob.bookmyshowapplications.Requests;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AddMatchRequest {

    private LocalDate matchDate;
    private LocalTime matchTime;
    private String team1Name;
    private String team2Name;
    private Integer stadiumId;

}
