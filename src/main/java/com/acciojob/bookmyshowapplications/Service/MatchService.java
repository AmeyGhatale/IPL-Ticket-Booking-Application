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
public class ShowService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private StadiumRepository stadiumRepository;

    @Autowired
    private MatchSeatRepository matchSeatRepository;



    public String addShows(AddMatchRequest showRequest) {

        //Build an object of the Show Entity and save it to the DB

        //I need to get the Movie Entity and Theater Entity : create the Show Entity

        Team team = teamRepository.findMovie(showRequest.getMovieName());

        Stadium stadium = stadiumRepository.findById(showRequest.getTheaterId()).get();

        Match match = Match.builder()
                .showDate(showRequest.getShowDate())
                .showTime(showRequest.getShowTime())
                .team(team)
                .stadium(stadium)
                .build();

        match = matchRepository.save(match);

        return "The show has been saved to the DB with showId : "+ match.getShowId();
    }


    public String addShowSeats(AddMatchSeatsRequest showSeatsRequest) {

        Integer showId = showSeatsRequest.getShowId();
        Match match = matchRepository.findById(showId).get();

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
                matchSeat.setPrice(showSeatsRequest.getPriceOfClassicSeats());
            }else
                matchSeat.setPrice(showSeatsRequest.getPriceOfPremiumSeats());

            matchSeatList.add(matchSeat);
        }

        matchSeatRepository.saveAll(matchSeatList);

        return "Show seats have been generated for the given showId";
    }

}
