package com.acciojob.bookmyshowapplications.Repository;

import com.acciojob.bookmyshowapplications.Models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Team,Integer> {

    Team findMovieByMovieName(String movieName);

    @Query(value = "select * from movies where movie_name = :movieName", nativeQuery = true)
    Team findMovie(String movieName);

}
