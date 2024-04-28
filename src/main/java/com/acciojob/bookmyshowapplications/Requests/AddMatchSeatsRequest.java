package com.acciojob.bookmyshowapplications.Requests;

import lombok.Data;

@Data
public class AddMatchSeatsRequest {

    private Integer matchId;
    private Integer priceOfClassicSeats;
    private Integer priceOfPremiumSeats;

}
