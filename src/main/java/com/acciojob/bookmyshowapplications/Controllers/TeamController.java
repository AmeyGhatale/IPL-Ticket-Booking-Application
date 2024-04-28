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
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping("/addTeam")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addTeam(@RequestBody Team team){

        String response = teamService.addTeam(team);
        return response;
    }


    @PutMapping("/updateTeamAttributes")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateTeamAttributes(@RequestBody UpdateTeamRequest teamRequest){
        String result = teamService.updateTeamAttributes(teamRequest);
        return result;

    }




}
