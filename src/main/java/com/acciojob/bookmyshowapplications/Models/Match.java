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

@Entity
@Table(name = "matches")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matchId;

    private LocalDate matchDate; //"YYYY-MM-DD"

    private LocalTime matchTime; //"HH:MM:SS"

    @ManyToOne
    @JoinColumn
    private Team team1;

    @ManyToOne
    @JoinColumn
    private Team team2;

    @ManyToOne
    @JoinColumn
    private Stadium stadium;

    @Override
    public String toString() {
        return "Match{" +
                "matchId=" + matchId +
                ", matchDate=" + matchDate +
                ", matchTime=" + matchTime +
                ", team=" + team1.getTeamName() +" vs "+ team2.getTeamName()+
                ", stadium=" + stadium.getName() +
                '}';
    }
}
