package com.acciojob.bookmyshowapplications.Service;

import com.acciojob.bookmyshowapplications.Exceptions.SeatUnavailableException;
import com.acciojob.bookmyshowapplications.Models.*;
import com.acciojob.bookmyshowapplications.Models.Match;
import com.acciojob.bookmyshowapplications.Repository.TeamRepository;
import com.acciojob.bookmyshowapplications.Repository.MatchRepository;
import com.acciojob.bookmyshowapplications.Repository.MatchSeatRepository;
import com.acciojob.bookmyshowapplications.Repository.StadiumRepository;
import com.acciojob.bookmyshowapplications.Repository.TicketRepository;
import com.acciojob.bookmyshowapplications.Repository.UserInfoRepository;
import com.acciojob.bookmyshowapplications.Requests.BookTicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private StadiumRepository stadiumRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private MatchSeatRepository matchSeatRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket bookTicket(BookTicketRequest bookTicketRequest) throws Exception{

        //1. Calculate the total cost of the tickets

        Team team1 = teamRepository.findTeamByTeamName(bookTicketRequest.getTeam1Name());
        Team team2 = teamRepository.findTeamByTeamName(bookTicketRequest.getTeam2Name());
        Stadium stadium = stadiumRepository.findById(bookTicketRequest.getStadiumId()).get();

        //1.1 Find the ShowEntity with this date and Time
        Match match = matchRepository.findMatchByMatchDateAndMatchTimeAndTeam1AndTeam2AndStadium(bookTicketRequest.getMatchDate(),
                bookTicketRequest.getMatchTime(), team1, team2, stadium);


        Integer matchId = match.getMatchId();
        List<MatchSeat> matchSeatList = matchSeatRepository.findMatchSeats(matchId);

        //Calculate the total Amt and if all seats mentioned are available or not
        int totalAmount = 0;
        Boolean areAllSeatsAvailable = Boolean.TRUE;

        for(String seatNo:bookTicketRequest.getRequestedSeats()) {
            for(MatchSeat matchSeat : matchSeatList) {
                if(matchSeat.getSeatNo().equals(seatNo))
                {
                    if(matchSeat.getIsAvailable()==Boolean.FALSE){
                        areAllSeatsAvailable = Boolean.FALSE;
                        break;
                    }
                    totalAmount = totalAmount+ matchSeat.getPrice();
                }
            }
        }

        if(areAllSeatsAvailable==Boolean.FALSE){
            throw new SeatUnavailableException("The requested Seats are unavailable");
        }


        //2. Make the seats booked:(Only if seats are available : otherwise throw exception)
//        Ticket ticket = new Ticket();
//        ticket.getSeatNoList(bookTicketRequest.getRequestedSeats());
        for(String seatNo : bookTicketRequest.getRequestedSeats()) {
            for(MatchSeat matchSeat : matchSeatList) {
                if(matchSeat.getSeatNo().equals(seatNo))
                {
                    matchSeat.setIsAvailable(Boolean.FALSE);
//                    ticket.getSeatNoList().add(seatNo);
                }
            }
        }

        UserInfo userInfo = userInfoRepository.findUserByMobNo(bookTicketRequest.getMobNo());

        //3. Save the ticketEntity

         Ticket ticket = Ticket.builder().userInfo(userInfo)
                .team1Name(bookTicketRequest.getTeam1Name())
                 .team2Name(bookTicketRequest.getTeam2Name())
                .matchDate(bookTicketRequest.getMatchDate())
                .stadiumNameAndAddress(stadium.getName()+" "+ stadium.getAddress())
                .matchTime(bookTicketRequest.getMatchTime())
                .totalAmtPaid(totalAmount)
                 .seatNoList(bookTicketRequest.getRequestedSeats())
                .build();

        ticket = ticketRepository.save(ticket);

        //4. Generate and return back the actual ticket response

        return ticket;


    }


    public List<String> getTicket(Integer userId){
        List<Ticket> ticketList = ticketRepository.findTicketByUserId(userId);
        List<String> ticketStr = new ArrayList<>();
        for(Ticket ticket : ticketList)
            ticketStr.add("\n\n"+ticket.toString());
        return ticketStr;
    }

}
