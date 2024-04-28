package com.acciojob.bookmyshowapplications.Repository;

import com.acciojob.bookmyshowapplications.Models.Match;
import com.acciojob.bookmyshowapplications.Models.MatchSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<MatchSeat,Integer> {

    public List<MatchSeat> findAllByShow(Match match); //Inbuilt method invoking

    //custom JPL Query
    @Query(nativeQuery = true,value = "select * from show_seats where show_show_id = :showId")
    public List<MatchSeat> findShowSeats(Integer showId);



}
