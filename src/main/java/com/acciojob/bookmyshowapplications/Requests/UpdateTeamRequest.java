package com.acciojob.bookmyshowapplications.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTeamRequest {

    private Integer teamId;
    private Integer noOfTrophies;
    private String captainName;
}
