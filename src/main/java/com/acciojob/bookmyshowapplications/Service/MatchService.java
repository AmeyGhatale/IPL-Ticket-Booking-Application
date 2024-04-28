package com.acciojob.bookmyshowapplications.Service;

import com.acciojob.bookmyshowapplications.Enums.SeatType;
import com.acciojob.bookmyshowapplications.Models.*;
import com.acciojob.bookmyshowapplications.Models.Match;
import com.acciojob.bookmyshowapplications.Repository.TeamRepository;
import com.acciojob.bookmyshowapplications.Repository.MatchRepository;
import com.acciojob.bookmyshowapplications.Repository.MatchSeatRepository;
import com.acciojob.bookmyshowapplications.Repository.StadiumRepository;
import com.acciojob.bookmyshowapplications.Requests.AddMatchRequest;
import com.acciojob.bookmyshowapplications.Requests.AddMatchSeatsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private StadiumRepository stadiumRepository;

    @Autowired
    private MatchSeatRepository matchSeatRepository;



    public String addMatch(AddMatchRequest matchRequest) {

        Team team1 = teamRepository.findTeam(matchRequest.getTeam1Name());
        Team team2 = teamRepository.findTeam(matchRequest.getTeam2Name());

        Stadium stadium = stadiumRepository.findById(matchRequest.getStadiumId()).get();

        Match match = Match.builder()
                .matchDate(matchRequest.getMatchDate())
                .matchTime(matchRequest.getMatchTime())
                .team1(team1)
                .team2(team2)
                .stadium(stadium)
                .build();

        match = matchRepository.save(match);

        return "Match of "+team1.getTeamName()+" vs "+team2.getTeamName()+" has been fixed on "+match.getMatchDate()
        +" with matchId : "+ match.getMatchId();
    }


    public String addMatchSeats(AddMatchSeatsRequest matchSeatsRequest) {

        Integer matchId = matchSeatsRequest.getMatchId();
        Match match = matchRepository.findById(matchId).get();

        Stadium stadium = match.getStadium();
        List<StadiumSeat> stadiumSeatList = stadium.getStadiumSeatList();

        //Goal is generation of show Seat List
        List<MatchSeat> matchSeatList = new ArrayList<>();

        for(StadiumSeat stadiumSeat : stadiumSeatList){

            MatchSeat matchSeat = MatchSeat.builder()
                    .seatNo(stadiumSeat.getSeatNo())
                    .seatType(stadiumSeat.getSeatType())
                    .isAvailable(Boolean.TRUE)
                    .match(match)
                    .build();

            if(stadiumSeat.getSeatType().equals(SeatType.CLASSIC)){
                matchSeat.setPrice(matchSeatsRequest.getPriceOfClassicSeats());
            }else
                matchSeat.setPrice(matchSeatsRequest.getPriceOfPremiumSeats());

            matchSeatList.add(matchSeat);
        }

        matchSeatRepository.saveAll(matchSeatList);

        return "Match seats have been generated for the given matchId : "+match.getMatchId();
    }

}
