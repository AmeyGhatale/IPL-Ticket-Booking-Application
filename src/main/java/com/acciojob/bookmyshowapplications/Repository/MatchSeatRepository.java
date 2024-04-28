package com.acciojob.bookmyshowapplications.Repository;

import com.acciojob.bookmyshowapplications.Models.Match;
import com.acciojob.bookmyshowapplications.Models.MatchSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchSeatRepository extends JpaRepository<MatchSeat,Integer> {

    public List<MatchSeat> findAllByMatch(Match match); //Inbuilt method invoking

    //custom JPL Query
    @Query(nativeQuery = true,value = "select * from match_seats where match_match_id = :matchId")
    public List<MatchSeat> findMatchSeats(Integer matchId);



}
