package com.acciojob.bookmyshowapplications.Controllers;

import com.acciojob.bookmyshowapplications.Requests.AddStadiumRequest;
import com.acciojob.bookmyshowapplications.Requests.AddStadiumSeatsRequest;
import com.acciojob.bookmyshowapplications.Service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    private StadiumService stadiumService;


    @PostMapping("addTheater")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addTheater(@RequestBody AddStadiumRequest addStadiumRequest) {

        String result = stadiumService.addTheater(addStadiumRequest);
        return result;
    }

    @PostMapping("addTheaterSeats")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addTheaterSeats(@RequestBody AddStadiumSeatsRequest addStadiumSeatsRequest){

        String result = stadiumService.addTheaterSeats(addStadiumSeatsRequest);
        return result;

    }

    //Add Theater Seats
}
