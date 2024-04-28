package com.acciojob.bookmyshowapplications.Controllers;

import com.acciojob.bookmyshowapplications.Requests.AddMatchRequest;
import com.acciojob.bookmyshowapplications.Requests.AddMatchSeatsRequest;
import com.acciojob.bookmyshowapplications.Service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    private MatchService matchService;

    @PostMapping("addShow")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addShow(@RequestBody AddMatchRequest showRequest) {

        String result = matchService.addShows(showRequest);
        return result;
    }

    @PostMapping("addShowSeats")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addShowSeats(@RequestBody AddMatchSeatsRequest showSeatsRequest){

        String response = matchService.addShowSeats(showSeatsRequest);
        return response;
    }

}
