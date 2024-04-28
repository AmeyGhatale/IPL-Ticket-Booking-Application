package com.acciojob.bookmyshowapplications.Controllers;

import com.acciojob.bookmyshowapplications.Models.Team;
import com.acciojob.bookmyshowapplications.Service.TeamService;
import com.acciojob.bookmyshowapplications.Requests.UpdateTeamRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private TeamService teamService;

    @PostMapping("/addMovie")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addMovie(@RequestBody Team team){

        String response = teamService.addTeam(team);
        return response;
    }


    @PutMapping("/updateMovieAttributes")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateMovieAttributes(@RequestBody UpdateTeamRequest movieRequest){
                //You have made sure that only relevant attributes
                //are expose to the client

        String result = teamService.updateTeamAttributes(movieRequest);
        return result;

    }




}
