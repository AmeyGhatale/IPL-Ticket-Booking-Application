package com.acciojob.bookmyshowapplications.Service;

import com.acciojob.bookmyshowapplications.Enums.SeatType;
import com.acciojob.bookmyshowapplications.Models.Stadium;
import com.acciojob.bookmyshowapplications.Models.StadiumSeat;
import com.acciojob.bookmyshowapplications.Repository.StadiumRepository;
import com.acciojob.bookmyshowapplications.Repository.StadiumSeatRepository;
import com.acciojob.bookmyshowapplications.Requests.AddStadiumRequest;
import com.acciojob.bookmyshowapplications.Requests.AddStadiumSeatsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    private StadiumRepository stadiumRepository;

    @Autowired
    private StadiumSeatRepository stadiumSeatRepository;

    public String addTheater(AddStadiumRequest addStadiumRequest){

        Stadium stadium = Stadium.builder()
                .address(addStadiumRequest.getAddress())
                .noOfScreens(addStadiumRequest.getNoOfScreens())
                .name(addStadiumRequest.getName())
                .build();

        //Save the entity to the DB

        stadium = stadiumRepository.save(stadium);
        return "The theater is with a theaterId : "+ stadium.getTheaterId();
    }


    public String addTheaterSeats(AddStadiumSeatsRequest addStadiumSeatsRequest){

        int noOfClassicSeats = addStadiumSeatsRequest.getNoOfClassicSeats();
        int noOfPremiumSeats = addStadiumSeatsRequest.getNoOfPremiumSeats();

        Integer theaterId = addStadiumSeatsRequest.getTheaterId();
        Stadium stadium = stadiumRepository.findById(theaterId).get();

        int classicSeatCounter = 1;
        char ch='A';
        int rowNo = 1;
        List<StadiumSeat> stadiumSeatList = new ArrayList<>();
        while(classicSeatCounter<=noOfClassicSeats) {

            String seatNo = rowNo+""+ch;
            StadiumSeat stadiumSeat = StadiumSeat.builder()
                                    .seatNo(seatNo)
                                    .seatType(SeatType.CLASSIC)
                                    .stadium(stadium)
                                    .build();

            stadiumSeatList.add(stadiumSeat);
            ch++;
            if(classicSeatCounter%5==0) {
                rowNo = rowNo+1;
                ch = 'A';
            }
            classicSeatCounter++;
        }
        int premiumSeatCounter = 1;
        ch='A';

        if(classicSeatCounter%5!=1)
            rowNo = rowNo+1;


        while(premiumSeatCounter<=noOfPremiumSeats){

            String seatNo = rowNo+""+ch;
            StadiumSeat stadiumSeat = StadiumSeat.builder()
                    .seatNo(seatNo)
                    .stadium(stadium) //Setting the unidirectional
                    .seatType(SeatType.PREMIUM)
                    .build();

            stadiumSeatList.add(stadiumSeat);
            ch++;
            if(premiumSeatCounter%5==0) {
                rowNo = rowNo+1;
                ch = 'A';
            }
            premiumSeatCounter++;
        }

        stadium.setStadiumSeatList(stadiumSeatList);
        stadiumRepository.save(stadium);

        //Theater seats will get automatically saved
        //bcz of cascading property written in the parent table
        return "Theater seats have been generated";
    }

}
