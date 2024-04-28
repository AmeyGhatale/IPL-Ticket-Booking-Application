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
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping("/addMatch")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addMatch(@RequestBody AddMatchRequest matchRequest) {

        String result = matchService.addMatch(matchRequest);
        return result;
    }

    @PostMapping("addMatchSeats")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addMatchSeats(@RequestBody AddMatchSeatsRequest matchSeatsRequest){

        String response = matchService.addMatchSeats(matchSeatsRequest);
        return response;
    }

}
