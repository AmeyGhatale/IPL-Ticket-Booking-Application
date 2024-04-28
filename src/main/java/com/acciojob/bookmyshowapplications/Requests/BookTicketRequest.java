package com.acciojob.bookmyshowapplications.Requests;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class BookTicketRequest {

    private String team1Name;
    private String team2Name;
    private LocalDate matchDate;
    private LocalTime matchTime;
    private List<String> requestedSeats;
    private Integer stadiumId;
    private String mobNo;

}
