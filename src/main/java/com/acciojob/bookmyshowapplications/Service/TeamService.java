package com.acciojob.bookmyshowapplications.Service;

import com.acciojob.bookmyshowapplications.Models.Team;
import com.acciojob.bookmyshowapplications.Repository.TeamRepository;
import com.acciojob.bookmyshowapplications.Requests.UpdateTeamRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public String addTeam(Team team){

        team = teamRepository.save(team);
        return "The team has been saved to the DB with teamId : "+ team.getTeamId();
    }

    public String updateTeamAttributes(UpdateTeamRequest teamRequest){

        Team team = teamRepository.findById(teamRequest.getTeamId()).get();

        String captainName = teamRequest.getCaptainName();
        Integer noOfTrophies = teamRequest.getNoOfTrophies();

        team.setCaptainName(captainName);
        team.setNoOfTrophies(noOfTrophies);

        teamRepository.save(team);
        return "Attributes are modified";


    }

}
