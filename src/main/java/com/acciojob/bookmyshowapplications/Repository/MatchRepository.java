package com.acciojob.bookmyshowapplications.Repository;

import com.acciojob.bookmyshowapplications.Models.Match;
import com.acciojob.bookmyshowapplications.Models.Team;
import com.acciojob.bookmyshowapplications.Models.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface MatchRepository extends JpaRepository<Match,Integer> {

    public Match findMatchByMatchDateAndMatchTimeAndTeam1AndTeam2AndStadium(LocalDate matchDate,
                                                                 LocalTime matchTime,
                                                                 Team team1,
                                                                 Team team2,
                                                                 Stadium stadium);

}
