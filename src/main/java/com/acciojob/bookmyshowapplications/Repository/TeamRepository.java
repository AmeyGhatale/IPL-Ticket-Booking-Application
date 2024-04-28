package com.acciojob.bookmyshowapplications.Repository;

import com.acciojob.bookmyshowapplications.Models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamRepository extends JpaRepository<Team,Integer> {

    Team findTeamByTeamName(String teamName);

    @Query(value = "select * from teams where team_name = :teamName", nativeQuery = true)
    Team findTeam(String teamName);

}
