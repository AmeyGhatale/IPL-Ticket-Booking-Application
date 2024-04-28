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
@RequestMapping("/stadium")
public class StadiumController {

    @Autowired
    private StadiumService stadiumService;


    @PostMapping("addStadium")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addStadium(@RequestBody AddStadiumRequest addStadiumRequest) {

        String result = stadiumService.addStadium(addStadiumRequest);
        return result;
    }

    @PostMapping("addStadiumSeats")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addStadiumSeats(@RequestBody AddStadiumSeatsRequest addStadiumSeatsRequest){

        String result = stadiumService.addStadiumSeats(addStadiumSeatsRequest);
        return result;

    }

}
