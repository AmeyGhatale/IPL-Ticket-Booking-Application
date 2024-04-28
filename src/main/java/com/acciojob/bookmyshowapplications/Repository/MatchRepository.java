package com.acciojob.bookmyshowapplications.Repository;

import com.acciojob.bookmyshowapplications.Models.Match;
import com.acciojob.bookmyshowapplications.Models.Team;
import com.acciojob.bookmyshowapplications.Models.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ShowRepository extends JpaRepository<Match,Integer> {

    public Match findShowByShowDateAndShowTimeAndMovieAndTheater(LocalDate showDate,
                                                                 LocalTime showTime,
                                                                 Team team,
                                                                 Stadium stadium);

}
