package com.acciojob.bookmyshowapplications.Controllers;

import com.acciojob.bookmyshowapplications.Models.Ticket;
import com.acciojob.bookmyshowapplications.Models.UserInfo;
import com.acciojob.bookmyshowapplications.Repository.UserInfoRepository;
import com.acciojob.bookmyshowapplications.Requests.BookTicketRequest;
import com.acciojob.bookmyshowapplications.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserInfoRepository userInfoRepository;

    @PostMapping("/bookTicket")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity bookTicket(@RequestBody BookTicketRequest bookTicketRequest){

        try {

            Ticket ticket = ticketService.bookTicket(bookTicketRequest);
            return new ResponseEntity(ticket.toString(),HttpStatus.OK);

        }catch (Exception e) {
            String errMsg = "Error while booking you tickets : "+e.getMessage();
            return new ResponseEntity(errMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTicket/{userId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity getTicket(@PathVariable ("userId") Integer userId){
        UserInfo user = userInfoRepository.findById(userId).get();
        List<String> ticketList =  ticketService.getTicket(userId);
        return new ResponseEntity("Customer Name : "+user.getName()+"     Mobile No : "+user.getMobNo()+"\n\n"+
                ticketList,  HttpStatus.ACCEPTED);
    }
}
