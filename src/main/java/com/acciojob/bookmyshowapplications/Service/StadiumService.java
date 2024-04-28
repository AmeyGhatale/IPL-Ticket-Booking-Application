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
public class StadiumService {
    @Autowired
    private StadiumRepository stadiumRepository;

    @Autowired
    private StadiumSeatRepository stadiumSeatRepository;

    public String addStadium(AddStadiumRequest stadiumRequest){

        Stadium stadium = Stadium.builder()
                .address(stadiumRequest.getAddress())
                .name(stadiumRequest.getName())
                .build();


        stadium = stadiumRepository.save(stadium);
        return "The stadium is saved with a stadiumId : "+ stadium.getStadiumId();
    }


    public String addStadiumSeats(AddStadiumSeatsRequest stadiumSeatsRequest){

        int noOfClassicSeats = stadiumSeatsRequest.getNoOfClassicSeats();
        int noOfPremiumSeats = stadiumSeatsRequest.getNoOfPremiumSeats();

        Integer stadiumId = stadiumSeatsRequest.getStadiumId();
        Stadium stadium = stadiumRepository.findById(stadiumId).get();

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
            if(classicSeatCounter%10==0) {
                rowNo = rowNo+1;
                ch = 'A';
            }
            classicSeatCounter++;
        }
        int premiumSeatCounter = 1;
        ch='A';

        if(classicSeatCounter%10!=1)
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
            if(premiumSeatCounter%15==0) {
                rowNo = rowNo+1;
                ch = 'A';
            }
            premiumSeatCounter++;
        }

        stadium.setStadiumSeatList(stadiumSeatList);
        stadiumRepository.save(stadium);

        return "Stadium seats have been generated";
    }

}
