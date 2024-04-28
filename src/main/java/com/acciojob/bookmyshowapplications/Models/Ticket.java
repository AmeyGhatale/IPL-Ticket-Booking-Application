package com.acciojob.bookmyshowapplications.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tickets")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ticketId;

    private String movieName;

    private List<String> seatNoList;

    private LocalDate showDate;

    private LocalTime showTime;

    private String theaterNameAndAddress;

    private Integer totalAmtPaid;

    @ManyToOne
    @JoinColumn
    private UserInfo userInfo;

    @Override
    public String toString() {
        return "Ticket_Id = '" + ticketId + '\'' +
                ", \nMovie Name = '" + movieName + '\'' +
                ",  \nSeats = " + seatNoList +
                ",  \nShow Date = " + showDate +
                ",  \nShow Time = " + showTime +
                ",  \nTheater Name and Address = '" + theaterNameAndAddress + '\'' +
                ",  \nTotal Amount Paid = " + totalAmtPaid ;
//                ",  Name = " + userInfo.getName() +
//                ",  Mobile No = "+userInfo.getMobNo();
    }
}
