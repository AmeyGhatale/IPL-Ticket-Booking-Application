package com.acciojob.bookmyshowapplications.Service;

import com.acciojob.bookmyshowapplications.Models.Team;
import com.acciojob.bookmyshowapplications.Repository.MovieRepository;
import com.acciojob.bookmyshowapplications.Requests.UpdateMovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public String addMovie(Team team){

        team = movieRepository.save(team);
        return "The movie has been saved to the DB with movieId : "+ team.getMovieId();
    }

    public String updateMovieAttributes(UpdateMovieRequest movieRequest){

        Team team = movieRepository.findById(movieRequest.getMovieId()).get();

        double rating = movieRequest.getRating();
        double duration = movieRequest.getDuration();

        team.setDuration(duration);
        team.setRating(rating);

        movieRepository.save(team);
        return "Attributes are modified";


    }

}
